package com.tasktracker.tasktracker.repositories;

import com.tasktracker.tasktracker.models.Project;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectRepo extends MongoRepository<Project, String> {

    Project findByName(String name);

    @Query("{ 'userId': ?0}")
    List<Project> findByUserId(ObjectId userId);

    List<Project> findByGitRepo(String gitRepo);

    //warning due to overriding - expecting type S (String) instead of Project...
    //would need to update MongoRepo types to get rid of warning or suppress warning
    @NonNull Project insert(@NonNull Project project);
}
