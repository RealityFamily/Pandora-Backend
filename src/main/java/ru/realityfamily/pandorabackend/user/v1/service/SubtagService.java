package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.user.v1.DTO.SubtagDTO;

import java.util.List;

public interface SubtagService {
    public List<SubtagDTO> geSubtagWithItemsDTO();
}
