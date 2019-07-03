package database.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import database.model.FraseMitica;
import database.mongodb.MongoDB;

public class FrasesMiticasLoad {
	@SuppressWarnings("deprecation")
	public static List<FraseMitica> loadFrasesMiticas(long groupId) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection("FrasesMiticas");

		List<FraseMitica> lista = new ArrayList<FraseMitica>();
		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document doc) {
				FraseMitica fm = new FraseMitica(doc.getString("frase"), doc.getLong("groupId"), doc.getLong("creadorId"), doc.getDate("fechaCreacion"));
				lista.add(fm);
			}
		};

		collection.find(Filters.eq("groupId", groupId)).sort(Sorts.ascending("fechaCreacion")).forEach(printBlock);
		return lista;
	}
}