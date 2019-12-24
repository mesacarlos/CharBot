package command;

import java.util.ArrayList;
import java.util.List;

import database.model.FraseMitica;
import database.service.FrasesMiticasLoad;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class FrasesCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) {
		List<FraseMitica> fms;
		
		fms = FrasesMiticasLoad.loadFrasesMiticas(e.getGuild().getIdLong());
		
		//Union de mensajes
		List<StringBuilder> builders = new ArrayList<StringBuilder>();
		builders.add(new StringBuilder());
		for(FraseMitica frase : fms) {
			//Añadir frase
			builders.get(builders.size()-1).append(frase.getFrase() + "\n");
			
			//Si ya hay mas de 1800 caracteres, creamos el siguiente StringBuilder
			if(builders.get(builders.size()-1).length() > 1800)
				builders.add(new StringBuilder());
		}
		
		//Envio de mensaje
		if(fms.isEmpty()) {
			e.getTextChannel().sendMessage("No hay frases célebres en este servidor.").queue();
		}else {
			for(StringBuilder sb : builders)
				e.getTextChannel().sendMessage(sb.toString()).queue();
		}
	}

}