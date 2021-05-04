package ru.realityfamily.pandorabackend.shared.models.gridfs;

import lombok.Getter;
import lombok.Setter;
import java.io.InputStream;

@Getter
@Setter
public class BaseGridFsFile {
    private String _id;
    private String fileTitle;
    private FileType fileType;// can be photo or model3d
    private InputStream stream;

    @Override
    public String toString() {
        return "BaseGridFsFile{" +
                "_id='" + _id + '\'' +
                ", name='" + fileTitle + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
