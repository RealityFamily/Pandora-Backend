package ru.realityfamily.pandorabackend.models;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Locale;

@Setter
@Getter
@Document(collection = "users")
public class User {

    @Id
    String id;

    String mail;

    String nickname;

    Long passwordHash;

    Role role;

    @DBRef
    List<Item> assignedItems;

    public User(String mail, String nickname, Long passwordHash, Role role) {
        this.mail = mail;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(String mail, String nickname, Long passwordHash) {
        this.mail = mail;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
    }

    public User(String mail, Long passwordHash) {
        this.mail = mail;
        this.passwordHash = passwordHash;
        this.role = Role.User;
        this.nickname = mail.toLowerCase(Locale.ROOT).split("@")[0]; // removes everything right from @ char. List it like it is our username
    }

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

