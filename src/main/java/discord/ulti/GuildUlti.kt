/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord
 * File: Guild.kt
 */

package discord.ulti

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class GuildUlti {
    fun checkIfExists(guild: Guild?, event: SlashCommandInteractionEvent) {
        if(guild == null) {
            event.reply("You can't use this command in DM chat").queue()
            return
        }
    }
}