package listener;

import java.util.HashMap;

import command.TextCommand;
import exception.GeneralException;
import exception.IllegalFormatException;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import command.CommandFactory;
import util.DateTimeUtils;

public class MessageReceivedListener extends ListenerAdapter {
	private HashMap<String, TextCommand> commands = CommandFactory.getCommandsHashMap();

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE))
			onPrivateChannelMessageReceived(event);
		else
			onTextChannelMessageReceived(event);
	}

	private void onPrivateChannelMessageReceived(MessageReceivedEvent event) {
		System.out.printf("[%s][PM] %s: %s\n", DateTimeUtils.getDateAsString(), event.getAuthor().getName(),
				event.getMessage().getContentDisplay());
		event.getPrivateChannel().sendMessage("Lo siento, no se puede utilizar el bot a través de mensajes privados");
	}

	private void onTextChannelMessageReceived(MessageReceivedEvent event) {
		//Print message in console
		System.out.printf("[%s][%s][%s] %s: %s\n", DateTimeUtils.getDateAsString(), event.getGuild().getName(),
				event.getTextChannel().getName(), event.getMember().getEffectiveName(),
				event.getMessage().getContentDisplay());
		
		//Received command as string
		String receivedCommand = event.getMessage().getContentRaw().split(" ")[0];
		
		//Get and execute command
		TextCommand cmd = commands.get(receivedCommand);
		if (cmd != null) {
			try {
				cmd.run(event);
			} catch (IllegalFormatException exception) {
				event.getTextChannel().sendMessage("Ocurrió el siguiente error: " + "`" + exception.getMessage() + "`\n"
						+ "Revisa la sintaxis del comando e inténtalo de nuevo.").queue();
			} catch (GeneralException exception) {
				event.getTextChannel().sendMessage("Ocurrió el siguiente error:\n" + "`" + exception.getMessage() + "`")
						.queue();
			}
		}//endif
		
	}
}