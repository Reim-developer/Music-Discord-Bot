/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord.ulti
 * File: Token.kt
 */

package discord.ulti

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

class Token {
    fun getToken(): String?{
        val configFile = File("./src/main/kotlin/discord/config/Config.json")
        val jsonData = configFile.readText()

        val jsonElement = Json.parseToJsonElement(jsonData)
        val botToken = jsonElement
            .jsonObject["BOT_TOKEN"]?.jsonPrimitive?.content

        return botToken
    }
}