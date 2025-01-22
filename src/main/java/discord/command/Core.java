/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.command
 * File: Core.java
 */
package discord.command;

import discord.Bot;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Core {
    private final Bot bot;

    public Core(Bot bot) { this.bot = bot; }

    public void syncCommands() {
        CommandListUpdateAction commands = bot.jda.updateCommands();
        commands.addCommands(
                Commands.slash("join", "Join a voice channel"),
                Commands.slash("leave", "Leave a voice channel")
        ).queue();
    }
}
