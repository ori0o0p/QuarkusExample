package com.example.article.model;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.util.Objects;

public class Article extends ReactivePanacheMongoEntity {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    public static Uni<Article> findArticleById(String id) {
        return findById(new ObjectId(id));
    }

    public static Uni<Void> save(Article article) {
        return persist(article);
    }

    public static Multi<Article> findAllArticle() {
        return streamAll();
    }

}
