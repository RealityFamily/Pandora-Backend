package ru.realityfamily.pandorabackend.shared.models.gridfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Photo extends BaseGridFsFile {

    private PhotoSize photoSize;

    public Photo(String fileTitle, PhotoSize photoSize){
        super();
        this.setFileTitle(fileTitle);
        this.setFileType(FileType.Photo);
        this.setPhotoSize(photoSize);
    }

    @Override
    public String toString() {
        return "Photo{}";
    }
}
