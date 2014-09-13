package controllers;

import play.*;
import play.mvc.*;
import static play.mvc.Http.*;
import play.data.*;
import static play.data.Form.*;

import views.html.productionuploadview.*;
import models.*;

import play.mvc.Http.MultipartFormData.FilePart;
import java.nio.file.*;
import java.io.*;

public class ProductionCtl extends Controller {

    public static Result GO_HOME = redirect(
           routes.ProductionCtl.index()); 

    static Form<Production> formulaire = form(Production.class);

    public static Result index() {
        return ok(
            listeProductionUploadView.render(
                Production.find.where()
                    .orderBy("name ASC").findList()));
    }

    public static Result show() {
        return ok(creationProductionUploadView.render(formulaire));
    }

    public static Result upload() {
        Form<Production> filledForm = formulaire.bindFromRequest();
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart prod = body.getFile("picture");

        if (prod == null) {
                Logger.debug("probleme upload fichier");
                filledForm.reject("filename", "fichier invalide");
                return badRequest(
                    creationProductionUploadView.render(filledForm)
                );
        }

        Logger.info("1");;
        try {
            Logger.info("2");
            Files.move(prod.getFile().toPath(), Paths.get("/tmp", prod.getFilename()), StandardCopyOption.REPLACE_EXISTING);
            Logger.info("3");
       } catch (IOException ioe) {
            Logger.info("4");
            Logger.debug(ioe.getMessage());
            Logger.info("5");
        }

        Logger.info("6");
        Logger.info(" filledForm created ");
        Logger.info("name = " + filledForm.field("name").value());
        Logger.info("comment = " + filledForm.field("comment").value());

        if (filledForm.hasErrors()) {
           Logger.info("I ve some errors");
           return badRequest(
                creationProductionUploadView.render(filledForm)
            );
       }

        filledForm.get().save();
        return GO_HOME;
    }
}
