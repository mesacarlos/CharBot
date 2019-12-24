package command;

import java.awt.Color;

import main.CharBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class StatusCommand implements TextCommand{

	@Override
	public void run(MessageReceivedEvent e) {
		MessageEmbed eb = new EmbedBuilder()
				.setTitle("Estadísticas")
				.setColor(Color.blue)
				.addField("En línea", toStringDateFormat(System.currentTimeMillis()-CharBot.BOOT_TIME), true)
				.addField("Retardo (ms)", "" + e.getJDA().getPing(), true)
				.build();
		e.getTextChannel().sendMessage(eb).queue();
	}
	
	private String toStringDateFormat(long milliseconds) {
		long seconds = milliseconds / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		return days + "d, " + b(hours % 24) + ":" + b(minutes % 60) + ":" + b(seconds % 60);
	}
	
	/**
	 * Number beautifier
	 * @param n Number to beautify
	 * @return String containing number beautified
	 */
	private String b(long n) {
		if(n < 10)
			return "0" + n;
		return ""+n;
	}

}