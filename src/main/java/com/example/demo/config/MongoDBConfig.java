package com.example.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {
    private static final String CONNECTION_STRING = System.getenv("MONGODB_URI");

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(new ConnectionString(CONNECTION_STRING));
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient client) {
        return client.getDatabase("blogging_platform_v2");
    }
}