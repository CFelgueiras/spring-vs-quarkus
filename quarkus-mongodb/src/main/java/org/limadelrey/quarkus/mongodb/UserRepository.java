package org.limadelrey.quarkus.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class UserRepository {

    public static final String DATABASE_NAME = "book_store";
    public static final String COLLECTION_NAME = "users";

    @Inject
    MongoClient client;

    public List<User> readAll() {
        final List<User> users = new ArrayList<>();

        try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                final Document document = cursor.next();
                users.add(User.of(document));
            }
        }

        return users;
    }

    public User readOne(String id) {
        final Document document = (Document) getCollection().find(eq("_id", id)).first();

        if (document == null) {
            throw new RuntimeException("User with id " + id + " not found.");
        }

        return User.of(document);
    }

    public User insert(User user) {
        final String id = UUID.randomUUID().toString();
        getCollection().insertOne(user.toDocument(id));

        return user;
    }

    public void update(User userAfterUpdate, String id) {
        getCollection().updateOne(eq("_id", id), userAfterUpdate.toDocument(id));
    }

    public void delete(String id) {
        getCollection().deleteOne(eq("_id", id));
    }

    // Private methods
    private MongoCollection getCollection() {
        return client.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
    }

}
