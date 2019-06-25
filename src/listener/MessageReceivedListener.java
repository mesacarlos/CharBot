package listener;

import java.util.HashMap;

import command.TextCommand;
import exception.GeneralException;
import exception.IllegalFormatException;
import command.CommandFactory;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.DateTimeUtils;

public class MessageReceivedListener extends ListenerAdapter {
	private HashMap<String, TextCommand> commands = CommandFactory.getCommandsHashMap();

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		try {
			if (event.isFromType(ChannelType.PRIVATE)) 
				onPrivateChannelMessageReceived(event);
			else
				onTextChannelMessageReceived(event);
		}catch(IllegalFormatException exception) {
			event.getTextChannel().sendMessage("Ocurri� el siguiente error: "
				+ "`" + exception.getMessage() + "`\n"
				+ "Revisa la sintaxis del comando e int�ntalo de nuevo.")
				.queue();
		} catch (GeneralException exception) {
			event.getTextChannel().sendMessage("Ocurri� el siguiente error:\n"
				+ "`" + exception.getMessage() + "`")
				.queue();
		}
	}

	private void onPrivateChannelMessageReceived(MessageReceivedEvent event) {
		System.out.printf("[%s][PM] %s: %s\n", DateTimeUtils.getDateAsString(), event.getAuthor().getName(),
				event.getMessage().getContentDisplay());
		event.getPrivateChannel().sendMessage("Lo siento, no se puede utilizar el bot a trav�s de mensajes privados");
	}

	private void onTextChannelMessageReceived(MessageReceivedEvent event) throws IllegalFormatException, GeneralException {
		System.out.printf("[%s][%s][%s] %s: %s\n", DateTimeUtils.getDateAsString(), event.getGuild().getName(),
				event.getTextChannel().getName(), event.getMember().getEffectiveName(),
				event.getMessage().getContentDisplay());

		String receivedCommand = event.getMessage().getContentRaw().split(" ")[0];
		TextCommand cmd = commands.get(receivedCommand);
		if (cmd != null)
			cmd.run(event);
	}
}