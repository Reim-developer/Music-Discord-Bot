package discord.ulti

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class JoinVoice {
    fun setJoinVoice(guild: Guild?, event: SlashCommandInteractionEvent) {
        val authorVoice = event.member?.voiceState?.channel
        val audioManager = guild?.audioManager

        audioManager?.openAudioConnection(authorVoice)

        event.deferReply()
        Thread.sleep(1000)

        event.reply(
            "Joined voice channel ${authorVoice?.asMention}"
        ).queue()
    }
}