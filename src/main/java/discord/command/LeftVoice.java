/*
 * Project : Music Discord bot
 * Package: discord.command
 * File: LeftVoice.java
 * Contribution:  Reim developer
 */
package discord.command;

import discord.ulti.CheckVoice;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

public class LeftVoice extends ListenerAdapter {

    private final CheckVoice checkVoice = new CheckVoice();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("left")) {
            Guild guild = event.getGuild();
            String authorID = event.getUser().getId();
            String botID = event.getJDA().getSelfUser().getId();

            if(guild == null) {
                event.reply("You can't use this command in DM chat. Please use this in your server")
                        .queue();
                return;
            }

            if(!checkVoice.setCheckVoice(guild, botID)) {
                event.reply("The bot is not in a voice channel. No need to leave voice channel now")
                        .queue();
                return;
            }

            if(!checkVoice.setCheckVoice(guild, authorID)) {
                event.reply("Please join a voice channel to use this command")
                        .queue();
                return;
            }

            Member botMember = guild.getSelfMember();
            VoiceChannel botVoice = (VoiceChannel)
                    Objects.requireNonNull(
                            botMember.getVoiceState()).getChannel();

            VoiceChannel authorVoice = (VoiceChannel)
                           Objects.requireNonNull(Objects.requireNonNull(
                                   event.getMember())
                                   .getVoiceState()).getChannel();

            assert  botVoice != null;
            if(!botVoice.equals(authorVoice)) {
                event.reply("You need to join to the same channel as the bot to use this command")
                        .queue();
                return;
            }

            AudioManager audioManager = guild.getAudioManager();
            audioManager.closeAudioConnection();
            event.reply("Successfully leave voice channel")
                    .queue();
        }
    }
}
