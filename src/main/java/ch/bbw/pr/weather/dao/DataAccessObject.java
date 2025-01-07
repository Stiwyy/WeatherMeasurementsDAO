package ch.bbw.pr.weather.dao;

import ch.bbw.pr.weather.model.Measure;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDateTime;
import org.bson.BsonTimestamp;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class DataAccessObject {
    String connectionString = "mongodb://root:1234@localhost:27017";
    MongoClient mongoClient;
    public void setupDB() {
        mongoClient = MongoClients.create(connectionString);
    }
    public void shutdownDB() {
        mongoClient.close();
    }

    public void printDatabases() {
        System.out.println("List of all Databases");
        mongoClient.listDatabaseNames().forEach((Consumer<? super String>) System.out::println);
    }


    public void saveMeasure(String city, String plz, List<Measure> measures){
        MongoDatabase statisticDB = mongoClient.getDatabase("weathermeasuredb");
        MongoCollection<Document> statisticCollection= statisticDB.getCollection("measurements");

        Document doc = new Document();
        doc.append("type", "Wettermessung");
        doc.append("timestamp", new BsonDateTime(new Date().getTime()));

        Document embeddedDoc = new Document();
        embeddedDoc.append("city", city);
        embeddedDoc.append("plz", plz);
        doc.append("station", embeddedDoc);

        List<Document> measuredocs = new ArrayList<>();
        for(Measure measure : measures){
            measuredocs.add(MeasureToDocument.measureToDocument(measure));
        }
        doc.append("measure", measuredocs);
        statisticCollection.insertOne(doc);
    }

}
