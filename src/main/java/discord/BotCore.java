/*
 * Project : Music Discord bot
 * Package: discord
 * File: BotCore.java
 * Contribution:  Reim developer
 */
package discord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import discord.command.JoinVoice;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.File;
import java.util.EnumSet;

public class BotCore {

    private final JoinVoice joinVoice;
    public JDA jda;
    public  BotCore() {
        joinVoice = new JoinVoice();
    }

    public void setBotCore() {
        File file = new File("src/main/java/discord/Config.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try { jsonNode = objectMapper.readTree(file); }
        catch (Exception exception) {
            System.out.println("Found error:\n" + exception);
            return;
        }

        final String BOT_TOKEN = jsonNode.get("BOT_TOKEN").asText();
        try { startBot(BOT_TOKEN); }
        catch (Exception exception) {
            System.out.printf("Found error:\n%s", exception);
        }
        joinVoice.test(jda);
    }

    private void startBot(final String BOT_TOKEN) throws InterruptedException {
        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_MEMBERS
        );

        jda = JDABuilder.create(BOT_TOKEN, intents)
                .enableCache(CacheFlag.VOICE_STATE, CacheFlag.MEMBER_OVERRIDES)
                .setActivity(Activity.watching("By Reim developer | Make with Java"))
                .setStatus(OnlineStatus.IDLE)
                .addEventListeners(new JoinVoice())
                .build()
                .awaitReady();
    }
}