package ru.realityfamily.pandorabackend.shared.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Document(collection = "verification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {
    private static final int EXPIRATION= 60*24;

    @Id
    private String id;

    private String token;

    @JsonIgnore
    @DBRef
    private User user;

    private Date expiryDate;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate= calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int  expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
