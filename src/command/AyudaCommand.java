package command;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AyudaCommand implements TextCommand {

	@Override
	public void run(MessageReceivedEvent e) {
		MessageEmbed eb = new EmbedBuilder()
				.setTitle("Ayuda")
				.setColor(Color.GREEN)
				.addField("Miscel�neos",
						"**.ayuda** Muestra este di�logo\n"
						+ "**.status** Muestra el estado del bot\n"
						+ "**.borrar (num)** Borra el n�mero de mensajes especificado", true)
				.addField("Frases c�lebres",
						"**.frases** Ver frases c�lebres\n"
						+ "**.a�adirfrase** A�adir frase c�lebre", true)
				.build();
		e.getTextChannel().sendMessage(eb).queue();
	}

}