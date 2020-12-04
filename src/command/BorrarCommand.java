package command;

import java.util.List;

import exception.GeneralException;
import exception.IllegalFormatException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BorrarCommand implements TextCommand{

	@Override
	public void run(MessageReceivedEvent e) throws IllegalFormatException, GeneralException {
		//Si el usuario no tiene permisos de administracion de mensajes no podra usar el comando
		if(!e.getMember().hasPermission(e.getTextChannel(), Permission.MESSAGE_MANAGE))
			throw new GeneralException("El usuario no dispone del permiso necesario para borrar mensajes");
		
		//Obtenemos mensajes y numero de mensajes a borrar
		Message msg = e.getMessage();
		int limit = 0;
		try {
			//Get number of messages to delete (No more than 50 messages can be deleted in a single command call)
			limit = Integer.parseInt(msg.getContentRaw().split("\\s+")[1]);
			if(limit > 50)
				throw new GeneralException("No puedes borrar mas de 50 mensajes al mismo tiempo.");
			
			//Retrieve messages
			List<Message> messagesToDelete = e.getTextChannel()
					.getHistoryBefore(msg.getId(), limit).complete()
					.getRetrievedHistory();
			
			//Delete those messages
			msg.delete().queue();
			e.getTextChannel().purgeMessages(messagesToDelete);
		}catch(NumberFormatException exc) {
			throw new IllegalFormatException("No se esperaba esa expresión");
		}
	}

}