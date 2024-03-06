package com.tasktracker.tasktracker.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Project implements Serializable{

    @NonNull
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    @Size(min=24, max = 24, message = "Invalid ID")
    private String projectID;

    @NonNull
    @Size(min = 3, max = 35, message = "Title too long, must be less than 50 characters")
    private String name;

    @NonNull
    @DateTimeFormat
    @CreatedDate
    private LocalDate createdOn;

    @NonNull
    @DateTimeFormat
    @LastModifiedDate
    private LocalDate updatedOn;

    @NonNull
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String userId;

    //TODO: update authUsers to store String value of ObjectID
    @NotNull
    private ArrayList<ObjectId> authUsers;

    @NonNull
    private ArrayList<Task> tasks;

    @NotEmpty
    private ArrayList<String> permissions;

    private String githubRepo;
}
