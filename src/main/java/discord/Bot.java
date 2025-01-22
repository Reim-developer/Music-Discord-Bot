/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord
 * File: Bot.java
 */

package discord;

import discord.command.music.Join;
import discord.ulti.Token;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.EnumSet;

public class Bot {

    private final Token token = new Token();
    private final Join join = new Join(this);
    public JDA jda;

    public static void main(String[] args) {
        Bot bot = new Bot();

        String botToken = bot.token.getToken();

        EnumSet<GatewayIntent> botIntents = EnumSet.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS
        );
        try {
            bot.jda = JDABuilder.create(botToken, botIntents)
                    .setActivity(Activity.watching("Reim developer"))
                    .addEventListeners(new Join(bot))
                    .build()
                    .awaitReady();

            bot.join.setJoinCommand();
        } catch (InterruptedException interruptedException) {
            System.out.println("Interrupted");
        }
    }
}
