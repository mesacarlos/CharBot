package command;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import database.model.FraseMitica;
import database.service.FrasesMiticasLoad;
import exception.GeneralException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class FrasesCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) throws GeneralException {
		List<FraseMitica> fms;
		try {
			fms = FrasesMiticasLoad.loadFrasesMiticas(e.getGuild().getIdLong());
		} catch (UnknownHostException e1) {
			throw new GeneralException("Error al conectar con la base de datos.");
		}
		
		//Union de mensajes
		List<StringBuilder> builders = new ArrayList<StringBuilder>();
		builders.add(new StringBuilder());
		for(FraseMitica frase : fms) {
			//A�adir frase
			builders.get(builders.size()-1).append(frase.getFrase() + "\n");
			
			//Si ya hay mas de 1800 caracteres, creamos el siguiente StringBuilder
			if(builders.get(builders.size()-1).length() > 1800)
				builders.add(new StringBuilder());
		}
		
		//Envio de mensaje
		if(fms.isEmpty()) {
			e.getTextChannel().sendMessage("No hay frases c�lebres en este servidor.").queue();
		}else {
			for(StringBuilder sb : builders)
				e.getTextChannel().sendMessage(sb.toString()).queue();
		}
	}

}