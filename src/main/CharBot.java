package main;

import javax.security.auth.login.LoginException;

import command.CommandFactory;
import listener.MessageReceivedListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import util.PropertiesReader;

public class CharBot {
	public static final long BOOT_TIME = System.currentTimeMillis();

	public static void main(String[] args) {
		System.out.println("DISCORD BOT (c) 2017-2019 SrCharlystar. MIT License applies.");
		System.out.println("Conectando con Discord...");
		try {
			JDA jda = new JDABuilder(AccountType.BOT)
					.setToken(new PropertiesReader().getProperty("token"))
					.addEventListener(new MessageReceivedListener()).build();
			jda.awaitReady();
			System.out.println("Conectado con discord.");
			jda.getPresence().setGame(Game.watching("Armin van Buuren " + CommandFactory.PREFIX + "ayuda"));
		} catch (LoginException e) {
			System.out.println(
					"Error al conectar con discord. A continuación se mostrará más información de depuración.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Se ha podido conectar a Discord pero ocurrió un problema en la inicialización. "
					+ "A continuación se mostrará más información de depuración.");
			e.printStackTrace();
		}
	}

}