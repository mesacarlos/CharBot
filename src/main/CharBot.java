package main;

import javax.security.auth.login.LoginException;

import command.CommandFactory;
import listener.MessageReceivedListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import util.PropertiesReader;

public class CharBot {
	public static final long BOOT_TIME = System.currentTimeMillis();

	public static void main(String[] args) {
		System.out.println("CharBot 2017-2020 mesacarlos. MIT License applies.");
		System.out.println("Conectando con Discord...");
		try {
			JDA jda = JDABuilder.createDefault(new PropertiesReader().getProperty("token"))
					.addEventListeners(new MessageReceivedListener()).build();
			jda.awaitReady();
			System.out.println("Conectado con discord.");
			jda.getPresence().setActivity(Activity.listening("Ayuda: " + CommandFactory.PREFIX + "ayuda")); //.setGame(Game.watching("Ayuda: " + CommandFactory.PREFIX + "ayuda"));
		} catch (LoginException e) {
			System.out.println(
					"Error al conectar con discord. A continuación se mostrará más información de depuración.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Se ha podido conectar a Discord pero ocurrió un problema en la inicialización. "
					+ "A continuación se mostrará mas información de depuración.");
			e.printStackTrace();
		}
	}

}