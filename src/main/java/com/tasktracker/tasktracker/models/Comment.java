package com.tasktracker.tasktracker.models;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Data
public class Comment {

    @NonNull
    public String username;

    @NonNull
    @Length(max = 500)
    public String comment;

    public Timestamp timestamp;
}
