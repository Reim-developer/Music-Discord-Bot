/*
 * Project : Music Discord bot
 * Package: discord.command
 * File: JoinVoice.java
 * Contribution:  Reim developer
 */
package discord.command;

import discord.ulti.CheckVoice;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Objects;

public class JoinVoice extends ListenerAdapter {

    private final CheckVoice checkVoice;
    public JoinVoice() {
        checkVoice = new CheckVoice();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("join")) {
            String userId = event.getUser().getId();
            Guild guild = event.getGuild();
            Member member = event.getMember();

            if(guild == null) {
                event.reply("Can't use this command in DM").setEphemeral(true)
                        .queue();
                return;
            }

            if(!checkVoice.setCheckVoice(guild, userId)) {
                event.reply("Please join a voice channel to use this command").queue();
                return;
            }

            assert member != null;
            VoiceChannel voiceChannel = (VoiceChannel)
                    Objects.requireNonNull(member
                            .getVoiceState())
                    .getChannel();
            AudioManager audioManager  = guild.getAudioManager();
            if(audioManager.isConnected()) {
                event.reply("I am already in your voice channel, " + member.getUser().getName())
                        .queue();
                return;
            }

            audioManager.openAudioConnection(voiceChannel);
            event.reply(
                    "Joined voice channel: " +
                            Objects.requireNonNull(voiceChannel).getAsMention()
            ).queue();
        }
    }
}
