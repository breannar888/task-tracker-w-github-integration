package com.tasktracker.tasktracker.data;

import com.tasktracker.tasktracker.contants.TaskType;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;

@Data
public class Activity {
    @MongoId
    @NonNull
    public ObjectId userID;

    @NonNull
    public TaskType type;

    @NonNull
    public Timestamp timestamp;

    @NonNull
    public String details;
}
