package controllers;

import play.*;
import play.mvc.*;
import static play.mvc.Http.*;
import play.data.*;
import static play.data.Form.*;

import views.html.productionuploadview.*;

import models.*;

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
       MultipartFormData body = request().body().asMultipartFormData();
        Logger.info("filename " + body.getFiles().get(0).getFilename());
        return GO_HOME;
    }
}
