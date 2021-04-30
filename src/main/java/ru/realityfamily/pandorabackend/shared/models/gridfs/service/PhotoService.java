package ru.realityfamily.pandorabackend.shared.models.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.gridfs.FileType;
import ru.realityfamily.pandorabackend.shared.models.gridfs.Photo;
import ru.realityfamily.pandorabackend.shared.models.gridfs.PhotoSize;

import java.io.IOException;

@AllArgsConstructor
@Component
public class PhotoService {

    private GridFsTemplate gridFsTemplate;

    private GridFsOperations operations;

    public Photo getPhoto(String id) throws  IllegalStateException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        Photo photo = new Photo();
        photo.setFileTitle(file.getMetadata().get("title").toString());
        photo.setStream(operations.getResource(file).getInputStream());
        photo.setFileType(FileType.Photo);

        String filesize = file.getMetadata().get("photosize").toString();
        if( filesize == PhotoSize.Large.name()){
            photo.setPhotoSize(PhotoSize.Large);
        } else if (filesize == PhotoSize.Small.name()) {
            photo.setPhotoSize(PhotoSize.Small);
        }

        return photo;
    }

    public String addPhoto(String title, MultipartFile photo,
                           PhotoSize photoSize) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("type", FileType.Photo.name());
        metadata.put("title", title);
        metadata.put("photosize", photoSize.name());
        ObjectId id = gridFsTemplate.store(
                photo.getInputStream(), photo.getName(),
                photo.getContentType(), metadata
        );
        return id.toString();
    }

}
