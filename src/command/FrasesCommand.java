package command;

import java.util.ArrayList;
import java.util.List;

import database.model.FraseMitica;
import database.service.FrasesMiticasLoad;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class FrasesCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) {
		List<FraseMitica> fms;
		fms = FrasesMiticasLoad.loadFrasesMiticas(e.getGuild().getIdLong());
		
		List<StringBuilder> builders = new ArrayList<StringBuilder>();
		builders.add(new StringBuilder());
		
		//hacer que si la proxima frase supera los 2k añadir un nuevo stringbuilder
		for(FraseMitica frase : fms) {
			StringBuilder lastStringBuilder = builders.get(builders.size()-1);
			if(lastStringBuilder.length() + frase.getFrase().length() >= 1950) {
				builders.add(new StringBuilder());
				lastStringBuilder = builders.get(builders.size()-1);
			}
			
			lastStringBuilder.append(frase.getFrase() + "\n");
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