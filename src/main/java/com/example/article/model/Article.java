package com.example.article.model;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;

public class Article extends ReactivePanacheMongoEntity {

    @Id
    public ObjectId id;

    public String title;

    public String content;

    public static Uni<Article> findArticleById(String id) {
        return findById(new ObjectId(id));
    }
    
}
