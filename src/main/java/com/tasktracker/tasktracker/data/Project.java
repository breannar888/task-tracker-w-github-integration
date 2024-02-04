package com.tasktracker.tasktracker.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Project {
    @NonNull
    @MongoId
    public ObjectId projectID;

    @NonNull
    @Length(max = 35)
    public String name;

    @NonNull
    public final Date createdOn;

    @NonNull
    @MongoId
    public ObjectId userID;

    public ArrayList<User> authUsers;

    public ArrayList<Task> tasks;

    @NotEmpty
    public ArrayList<String> permissions;

    public String githubRepo;
}
