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

            if(userUlti.isBotVoice(event.getGuild(), event)) return;
            if(!userUlti.isVoice(event.getMember(), event)) return;

            AudioManager audioManager = Objects.requireNonNull(event.getGuild()).getAudioManager();
            ReplyCallbackAction replyCallbackAction = event.deferReply();

            System.out.println("Reply callback action: " + replyCallbackAction);
            audioManager.closeAudioConnection();

            event.reply("Successfully leave voice channel").queue();
        }
    }
}
