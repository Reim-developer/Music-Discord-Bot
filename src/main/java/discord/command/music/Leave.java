/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.music
 * File: Left.java
 */

package discord.command.music;

import discord.ulti.GuildUlti;
import discord.ulti.PermUlti;
import discord.ulti.UserUlti;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Leave extends ListenerAdapter {
    private final GuildUlti guildUlti = new GuildUlti();
    private final PermUlti permUlti = new PermUlti();
    private final UserUlti userUlti = new UserUlti();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equals("leave")) {
            guildUlti.checkIfExists(event.getGuild(), event);
            permUlti.checkVoicePerm(event.getGuild(), event);

            if(!userUlti.isVoice(event.getMember(), event)) return;

            if (event.getMember() == null) return;
            if (event.getMember().getVoiceState() == null) return;
            AudioChannel authorVoice = event
                    .getMember().getVoiceState().getChannel();

            if(event.getGuild() == null) return;
            if(event.getGuild().getSelfMember().getVoiceState() == null) return;
            AudioChannel botVoice = event
                    .getGuild().getSelfMember().getVoiceState().getChannel();

            if(authorVoice != botVoice) {
                event.reply("You need join the same channel with bot to use this command").queue();
                return;
            }

            AudioManager audioManager = Objects.requireNonNull(event.getGuild()).getAudioManager();
            ReplyCallbackAction replyCallbackAction = event.deferReply();

            System.out.println("Reply callback action: " + replyCallbackAction);
            audioManager.closeAudioConnection();

            event.reply("Successfully leave voice channel").queue();
        }
    }
}
