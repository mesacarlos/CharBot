package command;

import java.util.Date;

import database.model.FraseMitica;
import database.service.FrasesMiticasInsert;
import exception.IllegalFormatException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AñadirFraseCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) throws IllegalFormatException {
		long groupId = e.getGuild().getIdLong();
		long creadorId = e.getAuthor().getIdLong();
		Date fechaCreacion = new Date();
		String frase = e.getMessage().getContentDisplay().split(" ", 2)[1];
		
		if(frase.isEmpty())
			throw new IllegalFormatException("No has especificado frase a añadir. Usa este formato: " + CommandFactory.PREFIX + "añadirfrase frase [Creador, fecha]");
		
		FraseMitica fm = new FraseMitica(frase, groupId, creadorId, fechaCreacion);
		
		FrasesMiticasInsert.saveFraseMitica(fm);
		
		e.getTextChannel().sendMessage("Frase enviada con éxito.").queue();
	}

}