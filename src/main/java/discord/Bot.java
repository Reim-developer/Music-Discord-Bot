/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord
 * File: Bot.java
 */

package discord;

import discord.command.Core;
import discord.command.music.Join;
import discord.command.music.Leave;
import discord.ulti.Token;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.EnumSet;

public class Bot {

    private final Token token = new Token();
    private final Core botCore = new Core(this);

    public JDA jda;

    public static void main(String[] args) {
        Bot bot = new Bot();

        String botToken = bot.token.getToken();

        EnumSet<GatewayIntent> botIntents = EnumSet.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES
        );
        try {
            bot.jda = JDABuilder.create(botToken, botIntents)
                    .setActivity(Activity.watching("Reim developer"))
                    .addEventListeners(new Join())
                    .addEventListeners(new Leave())
                    .build()
                    .awaitReady();

            bot.botCore.syncCommands();
        } catch (InterruptedException interruptedException) {
            System.out.println("Interrupted");
        }
    }
}
