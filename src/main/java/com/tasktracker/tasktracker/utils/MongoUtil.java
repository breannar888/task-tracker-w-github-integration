package com.tasktracker.tasktracker.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//dont need this, document before removing
@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@EnableMongoAuditing
public class MongoUtil {

    @Value("${mongodb.uri}")
    private String mongoUri;

    public MongoUtil() {
    }

    @Bean
    public MongoClientFactoryBean mongoClient() {
        //ServerApi defines server version
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        //provides container to translate Mongo Exceptions to exceptions in Springs DataAccessException hierarchy
        //exception translation
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .serverApi(serverApi)
                .build();

        mongo.setMongoClientSettings(settings);
        return mongo;
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(
            final LocalValidatorFactoryBean factoryBean) {
        return new ValidatingMongoEventListener(factoryBean);
    }
}
