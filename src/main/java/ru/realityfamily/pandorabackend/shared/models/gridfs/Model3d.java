package ru.realityfamily.pandorabackend.shared.models.gridfs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Model3d extends BaseGridFsFile {


    public Model3d(String name) {
        super();
        this.setFileTitle(name);
        this.setFileType("3dmodel");
        this.setFileType("7z");
    }

    public Model3d() {
        super();
    }



}
