package com.tasktracker.tasktracker.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class MongoUtil {

    @Value("${mongodb.uri}")
    private String mongoUri;

    public MongoUtil() {
    }

    @Bean
    public MongoClient mongoClient() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(getMongoUri()))
                .serverApi(serverApi)
                .build();

        return MongoClients.create(settings);
    }

    private String getMongoUri() {
        System.out.println(mongoUri);
        return mongoUri;
    }
}
