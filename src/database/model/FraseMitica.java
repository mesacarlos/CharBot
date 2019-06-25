package database.model;

import java.util.Date;

import org.bson.Document;

public class FraseMitica {
	private String frase;
	private long groupId;
	private long creadorId;
	private Date fechaCreacion;
	
	public FraseMitica(String frase, long groupId, long creadorId, Date fechaCreacion) {
		super();
		this.frase = frase;
		this.groupId = groupId;
		this.creadorId = creadorId;
		this.fechaCreacion = fechaCreacion;
	}

	public String getFrase() {
		return frase;
	}

	public long getCreadorId() {
		return creadorId;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public long getGroupId() {
		return groupId;
	}

	public Document toDocument() {
		return new Document()
	        .append("frase", frase)
	        .append("groupId", groupId)
	        .append("creadorId", creadorId)
	        .append("fechaCreacion", fechaCreacion);
	}
	
}