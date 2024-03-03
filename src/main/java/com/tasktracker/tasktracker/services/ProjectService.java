package com.tasktracker.tasktracker.services;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import com.tasktracker.tasktracker.models.Project;
import com.tasktracker.tasktracker.utils.MongoUtil;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Project getProjectByProjectId(String id) {
        ObjectId value = new ObjectId(id);
        final String key = "_id";

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
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
        List<Project> projects = new ArrayList<>();

        MongoClient mongoClient = mongoUtil.mongoClient();

        PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

        ObjectId userID = new ObjectId(userId);
        FindIterable<Project> cursor = mongoClient.getDatabase(mongoDbName)
                .getCollection("Project", Project.class)
                .withCodecRegistry(pojoCodecRegistry)
                .find(Filters.eq(key, userID));

        cursor.into(projects);

        if (projects.isEmpty()) {
            System.out.println("No documents found for userId: " + userID);
            //throw NotFound exception - will prevent code under this block from executing
        }

        return projects;
    }
}
