package command;

import exception.GeneralException;
import exception.IllegalFormatException;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BorrarCommand implements TextCommand{

	@Override
	public void run(MessageReceivedEvent e) throws IllegalFormatException, GeneralException {
		//Si el usuario no tiene permisos de administración de mensajes no podrá usar el comando
		if(!e.getMember().hasPermission(e.getTextChannel(), Permission.MESSAGE_MANAGE))
			throw new GeneralException("El usuario no dispone del permiso necesario para borrar mensajes");
		
		//Obtenemos mensajes y numero de mensajes a borrar
		Message msg = e.getMessage();
		int limit = 0;
		try {
			limit = Integer.parseInt(msg.getContentRaw().split("\\s+")[1]);
			MessageHistory history = e.getTextChannel().getHistoryBefore(msg.getId(), limit).complete();
			
			//Borramos el mensaje del usuario y el disparador del comando
			msg.delete().queue();
			for(Message message : history.getRetrievedHistory()) {
				System.out.println("Deleting: " + message);
				message.delete().queue();
			}
		}catch(NumberFormatException exc) {
			throw new IllegalFormatException("No se esperaba esa expresión");
		}
	}

}