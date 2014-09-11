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

    @Required
    public String directoryPath;

    @Required
    public boolean voteOpen;

    @Required
    public boolean uploadOpen;

    public Compo() {
    }

    public Compo(final String aName,
                 final Date aStartDate,
                 final Date aEndDate,
                 final String aDirectoryPath,
                 final boolean aVoteOpen,
                 final boolean aUploadOpen) {
        name = aName;
        startDate = aStartDate;
        endDate = aEndDate;
        directoryPath = aDirectoryPath;
        voteOpen = aVoteOpen;
        uploadOpen = aUploadOpen;
    }




    public String toString() {
        return "compo name: " + name +
               " startDate: " + startDate +
               "endDate: " + endDate +
               " directoryPath: " + directoryPath +
               " voteOpen: " + voteOpen +
               " uploadOpen: " + uploadOpen;
    }

    public static Finder<Long, Compo> find =
                    new Finder<Long, Compo>(Long.class, Compo.class);
}
