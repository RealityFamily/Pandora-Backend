package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Subtag;

import java.util.List;

public interface ISubtagClientService {

    public List<Subtag> geSubtagWithItems(String subgroupId);
}
