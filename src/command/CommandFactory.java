package command;

import java.util.HashMap;

public class CommandFactory {
	public static final String PREFIX = ".";

	public static HashMap<String, TextCommand> getCommandsHashMap() {
		HashMap<String, TextCommand> commands = new HashMap<String, TextCommand>();
		// Miscel�neas
		commands.put(PREFIX + "ayuda", new AyudaCommand());
		commands.put(PREFIX + "status", new StatusCommand());
		commands.put(PREFIX + "borrar", new BorrarCommand());

		// Frases c�lebres
		commands.put(PREFIX + "frases", new FrasesCommand());
		commands.put(PREFIX + "añadirfrase", new AñadirFraseCommand());
		return commands;
	}
}