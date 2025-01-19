/*
 * Project : Music Discord bot
 * Package: discord.ulti
 * File: Check.java
 * Contribution:  Reim developer
 */
package discord.ulti;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import org.jetbrains.annotations.NotNull;

public class CheckVoice {
    public boolean setCheckVoice(@NotNull Guild guild, String userID) {
        for(VoiceChannel voiceChannel : guild.getVoiceChannels()) {
            if(voiceChannel.getMembers().stream().anyMatch(member ->  member.getId().equals(userID))) {
                return true;
            }
        }
        return false;
    }
}
