package ch.bbw.pr.weather;

import ch.bbw.pr.weather.dao.DataAccessObject;
import ch.bbw.pr.weather.model.Measure;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDateTime;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * @author Andrin Renggli
 * @version 7.1.2024
 */
public class App {
    public static void main(String[] args) {
        //Funktioniert eigentlich genau gleich wie die Basic Variante, nur dass das speichern etc getrennt wird.

        DataAccessObject dao = new DataAccessObject();
        dao.setupDB();
        dao.printDatabases();
        List<Measure> measures = List.of(new Measure("wind", 33.3), new Measure("temperature", 29.8));
        dao.saveMeasure("Zürich", "8000", measures);
        dao.shutdownDB();
    }
}
