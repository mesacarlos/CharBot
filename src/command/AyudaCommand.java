package command;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AyudaCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) {
		MessageEmbed eb = new EmbedBuilder()
				.setTitle("Ayuda")
				.setColor(Color.GREEN)
				.addField("Misceláneos",
						"**.ayuda** Muestra este diálogo\n"
						+ "**.status** Muestra el estado del bot\n"
						+ "**.borrar (num)** Borra el número de mensajes especificado", true)
				.addField("Frases célebres",
						"**.frases** Ver frases célebres\n"
						+ "**.añadirfrase** Añadir frase célebre", true)
				.build();
		e.getTextChannel().sendMessage(eb).queue();
	}

}