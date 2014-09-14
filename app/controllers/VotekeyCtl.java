package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.votekeyview.*;

import models.*;

public class VotekeyCtl extends Controller {

    public static Result GO_HOME = redirect(
           routes.VotekeyCtl.index()); 

    static Form<Votekey> formulaire = form(Votekey.class);

    public static Result index() {
        return ok(
            listeVotekeyView.render(
                Votekey.find.where()
                    .orderBy("votekey ASC").findList()));
    }

    public static Result show() {
        //return ok(creationCompoView.render(formulaire));
        return TODO;
    }

    public static Result create() {
        /*
        Form<Compo> filledForm = formulaire.bindFromRequest();
        Logger.info(" filledForm created ");
        Logger.info("date start = " + filledForm.field("startDate").value());
        Logger.info("date end = " + filledForm.field("endDate").value());
        Logger.info("nom event = " + filledForm.field("name").value());
        Logger.info("directory path = " + filledForm.field("directory"));
        Logger.info("voteOpen = " + filledForm.field("voteOpen"));
        Logger.info("uploadOpen = " + filledForm.field("uploadOpen"));

        Compo c = Compo.find.where().eq("name", filledForm.field("name").value()).findUnique();

        if (c != null) {
            //d_nok
            filledForm.reject("name", "la competition a deja ete renseignee");
            return badRequest(
                creationCompoView.render(filledForm)
            );
        }

        if (filledForm.hasErrors()) {
            Logger.info("I ve some errors");
            return badRequest(
                creationCompoView.render(filledForm)
            );
        }

        Path p;
        try {
            Logger.info("---" + filledForm.field("directoryPath").value());
            p = Paths.get(filledForm.field("directoryPath").value());
        } catch (InvalidPathException ipe) {
            filledForm.reject("directoryPath", ipe.getMessage());
            return badRequest(
                creationCompoView.render(filledForm)
            );
        }
        Logger.info(p.toString());

        //attempt to check the read/write rights
        if (!Files.isReadable(p) || !Files.isWritable(p)) {
            filledForm.reject("directoryPath","the directory rights are not correct. Must be readable/writable");
            return badRequest(
                creationCompoView.render(filledForm)
            );
        }

        filledForm.get().save();

        return GO_HOME;
        */
        return TODO;
    }
}