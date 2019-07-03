package database.service;

import database.model.FraseMitica;
import database.mongodb.MongoDB;

public class FrasesMiticasInsert {
	public static void saveFraseMitica(FraseMitica fraseMitica) {
		MongoDB.getInstance().getCollection("FrasesMiticas").insertOne(fraseMitica.toDocument());
	}

}