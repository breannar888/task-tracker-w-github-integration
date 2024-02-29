package com.tasktracker.tasktracker.models;

import com.tasktracker.tasktracker.constants.TaskPriority;
import com.tasktracker.tasktracker.constants.TaskStatus;
import com.tasktracker.tasktracker.constants.TaskType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Task {

    @MongoId
    @NonNull
    @Size(min=24, max=24, message = "Invalid ID")
    public ObjectId taskID;

    @Length(min = 3, max = 50, message = "Title too long, must be less than 50 characters")
    @NonNull
    public String title;

    @NonNull
    public String assigneeName;

    @Length(max = 500,message = "Description too long, must be less than 500 characters")
    public String description;

    @NonNull
    public TaskStatus status;

    @NonNull
    public TaskType type;

    @NonNull
    public TaskPriority priority;

    public ArrayList<String> tags;

    @NonNull
    public String submitterName;

    @NonNull
    public final Date createdOn;

    @NonNull
    public Date updateOn;

    public ArrayList<Activity> activity;

    public ArrayList<Task> subtask;

    public ArrayList<String> attachments;

    public ArrayList<Comment> comments;

    public String githubBranch;
}
