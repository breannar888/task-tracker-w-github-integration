package com.tasktracker.tasktracker.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application-dev.properties")
public class MongoInitalizer {

    @Value("${mongodb_uri}")
    private String uri;

    @Value("${mongodb_db_name}")
    private String dbName;

    //will be executed after dependency injection
    @PostConstruct
    public void initalize() {

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
        }
    }
}
