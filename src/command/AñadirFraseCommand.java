package command;

import java.net.UnknownHostException;
import java.util.Date;

import database.model.FraseMitica;
import database.service.FrasesMiticasInsert;
import exception.GeneralException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AñadirFraseCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) throws GeneralException {
		String frase = e.getMessage().getContentDisplay().split(" ", 2)[1];
		long groupId = e.getGuild().getIdLong();
		long creadorId = e.getAuthor().getIdLong();
		Date fechaCreacion = new Date();
		FraseMitica fm = new FraseMitica(frase, groupId, creadorId, fechaCreacion);
		
		try {
			FrasesMiticasInsert.saveFraseMitica(fm);
		} catch (UnknownHostException e1) {
			throw new GeneralException("Error al conectar con la base de datos.");
		}
		e.getTextChannel().sendMessage("Frase enviada con éxito.").queue();
	}

}