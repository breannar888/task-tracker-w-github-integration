package com.tasktracker.tasktracker.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Project implements Serializable {

    @NonNull
    @BsonId
    @Size(min=24, max = 24, message = "Invalid ID")
    public ObjectId projectID;

    @NonNull
    @Size(min = 3, max = 35, message = "Title too long, must be less than 50 characters")
    public String name;

    @NonNull
    @CreatedDate
    @DateTimeFormat
    public Date createdOn;

    @NonNull
    @LastModifiedDate
    @DateTimeFormat
    public Date updatedOn;

    @NonNull
    public ObjectId userId;

    @NotNull
    public ArrayList<ObjectId> authUsers;

    @NonNull
    public ArrayList<Task> tasks;

    @NotEmpty
    public ArrayList<String> permissions;

    public String githubRepo;
}
