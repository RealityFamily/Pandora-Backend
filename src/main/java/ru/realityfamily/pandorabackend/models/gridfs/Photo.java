package ru.realityfamily.pandorabackend.models.gridfs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Photo extends BaseGridFsFile {

    public Photo(String fileTitle){
        super();
        this.setFileTitle(fileTitle);
        this.setFileType("image");
        this.setFileType("");
    }

    @Override
    public String toString() {
        return "Photo{}";
    }
}
