package command;

import exception.GeneralException;
import exception.IllegalFormatException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface TextCommand {
	void run(MessageReceivedEvent e) throws IllegalFormatException, GeneralException;
}