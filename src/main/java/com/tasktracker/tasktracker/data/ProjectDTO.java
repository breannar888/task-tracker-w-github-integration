package com.tasktracker.tasktracker.data;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
public class ProjectDTO {
    @MongoId
    @NonNull
    public ObjectId projectID;

    @NonNull
    public String name;

    @NonNull
    public final Date createdOn;

    public String githubRepo;
}
