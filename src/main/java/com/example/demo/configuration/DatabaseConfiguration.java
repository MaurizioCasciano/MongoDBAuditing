package com.example.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;



@Configuration
@RequiredArgsConstructor
public class DatabaseConfiguration extends AbstractMongoClientConfiguration {

    private final MongoProperties mongoProperties;
    @Override
    protected String getDatabaseName() {
        return this.mongoProperties.getDatabase();
    }

    @Override
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
        return new CustomMongoTemplate(databaseFactory, converter);
    }

    @Override
    public MongoClient mongoClient() {
        String host = this.mongoProperties.getHost();
        Integer port = this.mongoProperties.getPort();

        return MongoClients.create("mongodb://" + host + ":" + port);
    }
}
