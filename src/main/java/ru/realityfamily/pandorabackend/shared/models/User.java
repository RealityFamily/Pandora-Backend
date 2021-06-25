package ru.realityfamily.pandorabackend.shared.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Setter
@Getter
@Document(collection = "users")
public class User extends BaseMongoTemplate{
    String mail;
    String nickname;
    String passwordHash;
    List<Role> role;

    @DBRef
    Set<Item> assignedItems;

    public User() {
    }

    public User(String mail, String nickname, String passwordHash, List<Role> role) {
        this.mail = mail;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(String mail, String nickname, String passwordHash) {
        this.mail = mail;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
    }

/*    public User(String mail, Long passwordHash) {
        this.mail = mail;
        this.passwordHash = passwordHash;
        this.role = Role.User;
        this.nickname = mail.toLowerCase(Locale.ROOT).split("@")[0]; // removes everything right from @ char. List it like it is our username
    }*/

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mail='" + mail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", passwordHash=" + passwordHash +
                ", role=" + role +
                ", assignedItems=" + assignedItems +
                '}';
    }
}

