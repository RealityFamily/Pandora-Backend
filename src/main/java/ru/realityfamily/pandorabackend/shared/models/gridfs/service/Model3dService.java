package ru.realityfamily.pandorabackend.shared.models.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.gridfs.FileType;
import ru.realityfamily.pandorabackend.shared.models.gridfs.PhotoSize;

import java.io.IOException;

@Component
@AllArgsConstructor
public class Model3dService {


    private GridFsTemplate gridFsTemplate;

    private GridFsOperations operations;

    public String addModel3d(String title, MultipartFile model3d) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("type", FileType.Model.name());
        metadata.put("title", title);
        ObjectId id = gridFsTemplate.store(
                model3d.getInputStream(), model3d.getName(),
                model3d.getContentType(), metadata
        );
        return id.toString();
    }

}
