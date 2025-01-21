/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * Package: discord
 * File: Main.kt
 */

package discord

import dev.kord.core.Kord
import discord.ulti.Token

private val token: Token by lazy { Token() }

suspend fun main() {
    val botToken = token.getToken()
    val kord = botToken?.let{ Kord(it) }

    kord?.login()
}