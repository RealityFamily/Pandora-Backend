package ru.realityfamily.pandorabackend.shared.models.gridfs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Model3d extends BaseGridFsFile {


    public Model3d(String name) {
        super();
        this.setFileTitle(name);
        this.setFileType(FileType.Model);
    }

    public Model3d() {
        super();
    }



}
