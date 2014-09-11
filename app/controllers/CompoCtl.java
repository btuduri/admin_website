package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.compoview.*;

import models.*;

public class CompoCtl extends Controller {

    public static Result GO_HOME = redirect(
           routes.CompoCtl.index()); 

    static Form<Compo> formulaire = form(Compo.class);

    public static Result index() {
        return ok(
            listeCompoView.render(
                Compo.find.where()
                    .orderBy("startDate ASC").findList()));
    }

    public static Result show() {
        return ok(creationCompoView.render(formulaire));
    }

    public static Result create() {
        Form<Compo> filledForm = formulaire.bindFromRequest();
        Logger.info(" filledForm created ");
        Logger.info("date start = " + filledForm.field("startDate").value());
        Logger.info("date end = " + filledForm.field("endDate").value());
        Logger.info("nom event = " + filledForm.field("name").value());
        if (filledForm.hasErrors()) {
            Logger.info("I ve some errors");
            return badRequest(
                creationCompoView.render(filledForm)
            );
        }

        filledForm.get().save();

        return GO_HOME;
    }
}
