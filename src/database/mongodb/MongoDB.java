package database.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import util.PropertiesReader;

public class MongoDB {
	private static MongoDB instance;
	private MongoClient mongoClient;
	
	private MongoDB() {
		mongoClient = MongoClients.create(new PropertiesReader().getProperty("mongoURI"));
	}
	
	public static MongoDB getInstance() {
		if(instance == null)
			instance = new MongoDB();
		return instance;
	}
	
	public MongoCollection<Document> getCollection(String collection){
		MongoDatabase database = mongoClient.getDatabase("CharBotDB");
		return database.getCollection(collection);
	}
	
}