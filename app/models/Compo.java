package models;

import org.joda.time.*;

import java.util.*;
import javax.persistence.*;

import javax.validation.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;

import com.avaje.ebean.*;

@Entity
public class Compo extends Model {
    @Id
    public Long id;

    @Required
    public String name;

    @Formats.DateTime(pattern="dd-MM-yyyy HH:mm:ss")
    public Date startDate;

    @Formats.DateTime(pattern="dd-MM-yyyy HH:mm:ss")
    public Date endDate;


    public Compo() {
    }

    public Compo(final String aName,
                 final Date aStartDate,
                 final Date aEndDate) {
        name = aName;
        startDate = aStartDate;
        endDate = aEndDate;
    }

    public static Finder<Long, Compo> find =
                    new Finder<Long, Compo>(Long.class, Compo.class);
}
