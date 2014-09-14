package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.votekeyview.*;

import models.*;

import java.util.*;

public class VotekeyCtl extends Controller {

    public static Result GO_HOME = redirect(
           routes.VotekeyCtl.index()); 

    static Form<VotekeyGeneration> formulaire = form(VotekeyGeneration.class);

    public static Result index() {
        return ok(
            listeVotekeyView.render(
                Votekey.find.where()
                    .orderBy("votekey ASC").findList()));
    }

    public static Result show() {
        return ok(creationVotekeyView.render(formulaire));
        //return TODO;
    }

    public static Result create() {
        Long number;
        Form<VotekeyGeneration> filledForm = formulaire.bindFromRequest();

        try {
            number = Long.parseLong(filledForm.field("number").value());
        } catch (NumberFormatException nfe) {
            filledForm.reject("number", "veuillez entrer un nombre valide");
            return badRequest(
                creationVotekeyView.render(filledForm)
            );
        }

        VotekeyGeneration vkg = new VotekeyGeneration(number);
        List<Votekey> l = vkg.generate();
        for (Votekey v : l) {
            v.save();
        }

        return GO_HOME;
    }
}
