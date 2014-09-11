package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.userview.*;

import models.*;

public class UserCtl extends Controller {

    public static Result GO_HOME = redirect(
           routes.UserCtl.index()); 

    static Form<User> formulaire = form(User.class);

    public static Result index() {
        return ok(
            listeUserView.render(
                User.find.where()
                    .orderBy("username ASC").findList()));
    }

    public static Result show() {
        return ok(creationUserView.render(formulaire));
    }

    public static Result create() {
        Form<User> filledForm = formulaire.bindFromRequest();
        Logger.debug("1");
        if (filledForm.hasErrors()) {
            if (filledForm.field("username").valueOr("").isEmpty()) {
            Logger.debug("2");
                filledForm.reject("username", "le nom est nécéssaire");
            }
            
            if (filledForm.field("password").valueOr("").isEmpty()) {
            Logger.debug("3");
                filledForm.reject("password", "le mot de passe est necessaire");
            }
            
            if (filledForm.field("votekey").valueOr("").isEmpty()) {
            Logger.debug("4");
                filledForm.reject("votekey", "la cle de vote est necessaire");
            }
            Logger.debug("5");
            return badRequest(
                creationUserView.render(filledForm)
            );
        }

       Votekey votekey = new Votekey();

        Logger.error("salut grenouille");
        Logger.debug("votekey. = " + filledForm.field("votekey").value());

       votekey.find.where("votekey=" + 
                filledForm.field("votekey").value()).findUnique();

        if (votekey.user != null) {
            filledForm.reject("votekey", "la cle a deja ete assignée");

            return badRequest(
                creationUserView.render(filledForm)
            );
        }

        //votekey.save();

        filledForm.get().save();

        return GO_HOME;
    }
}
