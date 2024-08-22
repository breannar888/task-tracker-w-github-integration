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
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

//TODO: need to validate values on creation AND read
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Document(collection = "Project")
public class Project implements Serializable{

    @NonNull
    @Id
    @BsonRepresentation(BsonType.OBJECT_ID)
    @Size(min=24, max = 24, message = "Invalid ID")
    private String id;

    @NonNull
    @Size(min = 3, max = 35, message = "Title too long, must be less than 50 characters")
    private String name;

    @NonNull
    @DateTimeFormat
    @CreatedDate
    private Date createdAt;

    @NonNull
    @DateTimeFormat
    @LastModifiedDate
    private Date updatedAt;

    @NonNull
    @Field("userId")
    private String userId;

    //TODO: store in mongodb as ArrayList<ObjectId>
    @NotNull
    private ArrayList<String> authUsers;

    @NonNull
    private ArrayList<Task> tasks;

    @NotEmpty
    private ArrayList<String> permissions;

    private String gitRepo;
}
