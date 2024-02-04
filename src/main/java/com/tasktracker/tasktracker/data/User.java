package com.tasktracker.tasktracker.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class User {
    @MongoId
    @NonNull
    public ObjectId userId;

    @Length(min = 5, max = 15)
    @NonNull
    public String username;

    @Length(min = 5, max = 30)
    @NonNull
    public String fullname;

    @Length(max = 25)
    @Email
    @NonNull
    public String email;

    @Length(min = 12, max = 15)
    @NonNull
    private String password;

    public String authToken;

    @NonNull
    public final Date creationDate;

    @NonNull
    public Date lastLogin;

    public ArrayList<ProjectDTO> projects;
}
