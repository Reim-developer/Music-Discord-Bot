/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.utli
 * File: PermUlti.kt
 */

package discord.ulti

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class PermUlti {
    fun checkVoicePerm(guild: Guild?, event: SlashCommandInteractionEvent) {
        if(!guild?.selfMember?.hasPermission(Permission.VOICE_CONNECT, Permission.VOICE_SPEAK)!!) {
            event.reply(
                "Can't connect to this voice channel.\n" +
                        "Require `VOICE_CONNECT` & `VOICE_SPEAK` permission").queue()
            return
        }
    }
}