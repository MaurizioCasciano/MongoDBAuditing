package com.example.demo.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CustomMongoTemplate extends MongoTemplate {
    public CustomMongoTemplate(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public CustomMongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public CustomMongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }

    @Override
    protected UpdateResult doUpdate(String collectionName, Query query, UpdateDefinition update, Class<?> entityClass, boolean upsert, boolean multi) {
        System.out.println("Rating MongoTemplate Do update");
        Document updateDocument = update.getUpdateObject();
        List<?> list = this.find(query, entityClass);
        if (!list.isEmpty()) {
            Object existingObject = list.get(0);
            Document existingDocument = new Document();
            this.getConverter().write(existingObject, existingDocument);
            // Keep the values of the existing document
            if (existingDocument.keySet().containsAll(Arrays.asList("version", "creator", "created"))) {
//                Long version = existingDocument.getLong("version");
                String creator = existingDocument.getString("creator");
                Date created = existingDocument.getDate("created");
                System.out.println("Creator: " + creator);
                System.out.println("Created: " + created);
//                updateDocument.put("version", version++);
                updateDocument.put("creator", creator);
                updateDocument.put("created", created);
                System.out.println("Update Document");
                System.out.println(updateDocument.toJson());
            }

            return super.doUpdate(collectionName, query, Update.fromDocument(updateDocument), entityClass, upsert, multi);
        } else {
            return super.doUpdate(collectionName, query, update, entityClass, upsert, multi);
        }
    }
}
