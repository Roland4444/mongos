package ru.roland;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static java.util.Objects.isNull;

public class Read {

    public static void main(String[] args) {
        String connectionString = System.getProperty("mongodb.uri");

        if (isNull(connectionString))
            connectionString="mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.2.6";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("student");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("students");

            // find one document with new Document
            Document student1 = gradesCollection.find(new Document("secondName", "Pastushkov")).first();////(new Document("student_id", 10000)).first();
            System.out.println("Student 1: " + student1.toJson());
        }
    }
}