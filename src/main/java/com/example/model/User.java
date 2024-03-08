package com.example.model;

import com.example.dto.UpdateUser;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;

@JsonPropertyOrder({"id", "name", "age"})
public class User extends ReactivePanacheMongoEntity {

    @Id
    public ObjectId id;
    public String name;
    public Integer age;

    public static Multi<User> findAllUser() {
        return streamAll();
    }

    public static Uni<Void> deleteUserById(String id) {
        Uni<User> userUni = findById(new ObjectId(id));

        return userUni.call(ReactivePanacheMongoEntityBase::delete)
                .replaceWithVoid();
    }

    public static Uni<Void> updateUser(String id, UpdateUser updateUser) {
        Uni<User> userUni = findById(id);

        return userUni.onItem().transform(user -> {
            user.age = updateUser.age();
            user.name = updateUser.name();
            return user;
        }).call(user -> user.persistOrUpdate())
                .replaceWithVoid();
    }

}
