package ru.realityfamily.pandorabackend.shared.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class BaseMongoTemplate {
    @Id
    protected String id;
}
