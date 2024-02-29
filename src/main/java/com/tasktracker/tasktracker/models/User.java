package com.tasktracker.tasktracker.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class User {
    @BsonId
    @NonNull
    @Size(min=24, max = 24, message = "Invalid ID")
    public ObjectId userId;

    @Length(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    @NonNull
    public String username;

    @Length(min = 5, max = 55, message = "Name cannot be longer than 55 characters")
    @NonNull
    public String fullname;

    @Length(max = 25)
    @Email
    @NonNull
    public String email;

    @Length(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @NonNull
    private String password;

    public String authToken;

    @CreatedDate
    @NonNull
    public final Date creationDate;

    @NonNull
    public Date lastLogin;

    public ArrayList<ProjectDTO> projects;
}
