package com.tasktracker.tasktracker.data;

import com.tasktracker.tasktracker.contants.TaskPriority;
import com.tasktracker.tasktracker.contants.TaskStatus;
import com.tasktracker.tasktracker.contants.TaskType;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Task {

    @MongoId
    @NonNull
    public ObjectId taskID;

    @Length(max = 50)
    @NonNull
    public String title;

    @NonNull
    public String assigneeName;

    @Length(max = 500)
    @NonNull
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
