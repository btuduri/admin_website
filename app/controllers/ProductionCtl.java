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
import java.util.*;

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
        FilePart prod = body.getFile("filename");

        if (prod == null) {
                Logger.debug("probleme upload fichier");
                filledForm.reject("filename", "fichier invalide");
                return badRequest(
                    creationProductionUploadView.render(filledForm)
                );
        }

        try {
            Files.move(prod.getFile().toPath(), Paths.get("/tmp", prod.getFilename()), StandardCopyOption.REPLACE_EXISTING);
       } catch (IOException ioe) {
            Logger.debug(ioe.getMessage());
        }

        Logger.info(" filledForm created ");
        Logger.info("compo = " + filledForm.field("compo").value());
        Logger.info("name = " + filledForm.field("name").value());
        Logger.info("comment = " + filledForm.field("comment").value());
        Logger.info("filename uploaded = " + prod.getFilename());
        Logger.info("user = " + filledForm.field("user").value());

        if (filledForm.hasErrors()) {
           Logger.info("I ve some errors");
            Logger.error(filledForm.error("compo").message());
           return badRequest(
                creationProductionUploadView.render(filledForm)
            );
       }

        Production p = filledForm.get();
        p.filename = prod.getFilename();
        p.save();
        return GO_HOME;
    }
}
