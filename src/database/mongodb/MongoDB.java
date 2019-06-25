package database.mongodb;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import util.PropertiesReader;

public class MongoDB {
	private static MongoDB instance;
	private MongoClient mongoClient;
	
	private MongoDB() throws UnknownHostException {
		mongoClient = MongoClients.create(new PropertiesReader().getProperty("mongoURI"));
	}
	
	public static MongoDB getInstance() throws UnknownHostException {
		if(instance == null)
			instance = new MongoDB();
		return instance;
	}
	
	public void insertarDocumento(Document obj, String coleccion) throws UnknownHostException {
		MongoDatabase database = mongoClient.getDatabase("CharBotDB");
		MongoCollection<Document> collection = database.getCollection("FrasesMiticas");
		collection.insertOne(obj);
	}
	
	public MongoCollection<Document> getCollection(String collection){
		MongoDatabase database = mongoClient.getDatabase("CharBotDB");
		return database.getCollection(collection);
	}
	
}