package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.compoview.*;

import models.*;

import java.nio.file.*;

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
        Logger.info("directory path = " + filledForm.field("directory"));
        Logger.info("voteOpen = " + filledForm.field("voteOpen"));
        Logger.info("uploadOpen = " + filledForm.field("uploadOpen"));


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
    }
}
