/*
 * Project : Music Discord bot
 * Package: discord.ulti
 * File: LoadCommands.java
 * Contribution:  Reim developer
 */
package discord.ulti;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class LoadCommands {
    public void setLoadCommands(JDA jda) {
        CommandListUpdateAction commandListUpdateAction = jda.updateCommands();
        commandListUpdateAction.addCommands(
                Commands.slash("join", "join a voice channel"),
                Commands.slash("left", "Left a voice channel;")
        ).queue();
    }
}
