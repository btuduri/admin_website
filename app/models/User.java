package models;

import javax.persistence.*;

import javax.validation.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import com.avaje.ebean.*;

@Entity
public class User extends Model {
    @Id
    public Long id;
    @Required
    public String username;
    @Required
    public String password;
    public String nickname;
    public String groupname;

    @OneToOne(mappedBy="user")
    @Transient
    @Required
    public String  votekey;

    public User() {
    }

    public User(final String userName,
                final String passWord,
                final String nickName,
                final String groupName) {
        username = userName;
        password = passWord;
        nickname = nickName;
        groupname = groupName;
    }

    public User(final String userName,
                final String passWord,
                final String nickName,
                final String groupName,
                final String voteKey) {
        this(userName, passWord,
             nickName, groupName);
        votekey = voteKey;
    }

    public String toString() {
        return "obj User: " + username + " cle vote saisie=" + votekey;
    }

    public static Finder<Long, User> find =
                    new Finder<Long, User>(Long.class, User.class);
}
