/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.ulti
 * File: UserUlti.kt
 */

package discord.ulti

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class UserUlti {
    fun isVoice(member: Member?, event: SlashCommandInteractionEvent) {
        if(member?.voiceState?.inAudioChannel() == false) {
            event.reply("You need join a voice channel to use this command").queue()
            return
        }
    }

    fun isBotVoice(guild: Guild?, event: SlashCommandInteractionEvent) {
        if(guild?.selfMember?.voiceState?.inAudioChannel() == true) {
            event.reply("Bot already joined voice channel ${guild.selfMember.voiceState?.channel?.asMention}").queue()
            return
        }
    }
}