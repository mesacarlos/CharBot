package database.service;

import java.net.UnknownHostException;

import database.model.FraseMitica;
import database.mongodb.MongoDB;

public class FrasesMiticasInsert {
	public static void saveFraseMitica(FraseMitica fraseMitica) throws UnknownHostException {
		MongoDB.getInstance().insertarDocumento(fraseMitica.toDocument(), "FrasesMiticas");
	}
	
}