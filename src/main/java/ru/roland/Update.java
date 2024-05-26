package ru.roland;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import static java.util.Objects.isNull;

public class Update {

    public static void main(String[] args) {
        String connectionString = System.getProperty("mongodb.uri");

        if (isNull(connectionString))
            connectionString="mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.2.6";

        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("student");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("students");

            // update one document
            Bson filter = eq("secondName", "Pastushkov");
            Bson updateOperation = set("group", "M-851");
            UpdateResult updateResult = gradesCollection.updateOne(filter, updateOperation);
            System.out.println("=> Updating the doc with {\"student_id\":10000}. Adding comment.");
            System.out.println(gradesCollection.find(filter).first().toJson(prettyPrint));
            System.out.println(updateResult);
        }
    }
}