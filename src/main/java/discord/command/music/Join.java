/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.music
 * File: Join.java
 */

package discord.command.music;

import discord.Bot;
import discord.ulti.GuildUlti;
import discord.ulti.JoinVoice;
import discord.ulti.PermUlti;
import discord.ulti.UserUlti;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Join extends ListenerAdapter {

    private final Bot bot;
    private final GuildUlti guildUlti = new discord.ulti.GuildUlti();
    private final UserUlti userUlti = new UserUlti();
    private final PermUlti permUlti = new PermUlti();
    private final JoinVoice joinVoice = new JoinVoice();

    public Join(Bot bot) {
        this.bot = bot;
    }

    public void setJoinCommand() {
        CommandListUpdateAction command = bot.jda.updateCommands();
        command.addCommands(
                Commands.slash("join", "Join a voice channel")
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("join")) {
            Guild guild = event.getGuild();

            guildUlti.checkIfExists(guild, event);
            permUlti.checkVoicePerm(guild, event);
            if(userUlti.isBotVoice(guild, event)) return;
            if(!userUlti.isVoice(event.getMember(), event)) return;
            joinVoice.setJoinVoice(guild, event);
        }
    }
}
