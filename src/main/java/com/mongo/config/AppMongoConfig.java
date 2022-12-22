package com.mongo.config;

import com.mongodb.*;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
public class AppMongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;
    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("${app.mongodb.read-timeout:5000}")
    private int connectionTimeout;
    @Value("${app.mongodb.connection-timeout:5000}")
    private int readTimeout;
    @Value("${app.mongodb.server-selection-timeout:3000}")
    private int serverSelectionTimeout;

    private final ApplicationContext applicationContext;

    /**
     * To remove _class field from payload being saved in DB.
     */
    @PostConstruct
    public void init() {
        MappingMongoConverter mappingMongoConverter = applicationContext.getBean(MappingMongoConverter.class);
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, dbName);
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MongoClientSettings.builder()
                .applyToSocketSettings(builder ->
                        builder.connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                                .readTimeout(readTimeout, TimeUnit.MILLISECONDS))
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(serverSelectionTimeout, TimeUnit.MILLISECONDS))
                .applyConnectionString(new ConnectionString(connectionString))
                .build());
    }
}
