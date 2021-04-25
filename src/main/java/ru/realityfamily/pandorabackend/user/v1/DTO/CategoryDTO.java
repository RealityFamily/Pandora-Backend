package ru.realityfamily.pandorabackend.user.v1.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    private String id;
    private String category;
}
