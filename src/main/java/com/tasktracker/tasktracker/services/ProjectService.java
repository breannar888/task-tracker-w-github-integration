package com.tasktracker.tasktracker.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import com.mongodb.client.result.InsertOneResult;
import com.tasktracker.tasktracker.models.Project;
import com.tasktracker.tasktracker.utils.MongoUtil;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class ProjectService {

    private final MongoUtil mongoUtil;

    private final String mongoDbName;

    public ProjectService(
            MongoUtil mongoUtil,
            @Value("${mongodb.db.name}") String mongoDbName) {
        this.mongoUtil = mongoUtil;
        this.mongoDbName = mongoDbName;
    }

    //get
    public Project getProjectByProjectId(String id) {
        ObjectId value = new ObjectId(id);
        final String key = "_id";

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).conventions(List.of(Conventions.ANNOTATION_CONVENTION)).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

        MongoClient mongoClient = mongoUtil.mongoClient();
            Project project = mongoClient.getDatabase(mongoDbName)
                    .getCollection("Project", Project.class)
                    .withCodecRegistry(pojoCodecRegistry)
                    .find(Filters.eq(key, value))
                    .first();

            //once exception is thrown shouldn't need this if/else block
            if(project == null) {
                System.out.println("Document not found for id: " + key);
                //TODO: throw custom ProductNotFoundException
            }

        return project;
    }

    public Project getProjectByName(String name) {
        final String key = "name";

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

        MongoClient mongoClient = mongoUtil.mongoClient();
        Project project = mongoClient.getDatabase(mongoDbName)
                .getCollection("Project", Project.class)
                .withCodecRegistry(pojoCodecRegistry)
                .find(Filters.eq(key, name))
                .first();

        System.out.println("project: " + project);
        //once exception is thrown shouldn't need this if/else block
        if(project == null) {
            System.out.println("Document not found for name: " + name);
            //TODO: return custom ProductNotFoundException exception in responseBody
            return null;
        }

        return project;
    }

    public List<Project> getProjectsByUserId(String userId) {
        final String key = "userId";

        MongoClient mongoClient = mongoUtil.mongoClient();

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

        ObjectId userID = new ObjectId(userId);

        List<Project> projects = mongoClient.getDatabase(mongoDbName)
                .getCollection("Project", Project.class)
                .withCodecRegistry(pojoCodecRegistry)
                .find(Filters.eq(key, userID))
                .into(new ArrayList<>());

        if (projects.isEmpty()) {
            System.out.println("No documents found for userId: " + userID);
            //throw NotFound exception - will prevent code under this block from executing
        }

        return projects;
    }

    //create
    public Project createProject(Project project) {
        System.out.println("project: " + project);
        MongoClient mongoClient = mongoUtil.mongoClient();

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

        Project newProject = new Project();

        newProject.setName(project.getName());
        newProject.setUserId(project.getUserId());
        newProject.setAuthUsers(project.getAuthUsers());
        newProject.setGithubRepo(project.getGithubRepo());

        InsertOneResult result = mongoClient.getDatabase(mongoDbName)
                .getCollection("Project", Project.class)
                .withCodecRegistry(pojoCodecRegistry)
                .insertOne(newProject);

        System.out.println("result: " + result.getInsertedId());

        if(!result.wasAcknowledged()) {
            System.out.println("Failed to insert project: " + newProject);
            return null;
        }

        return newProject;
    }
}
