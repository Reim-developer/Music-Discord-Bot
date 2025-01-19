/*
 * Project : Music Discord bot
 * Package: discord
 * File: BotIndex.java
 * Contribution:  Reim developer
 */
package discord;

public final class BotIndex {

    private final BotCore botCore;
    public  BotIndex() {
        botCore = new BotCore();
    }

    public static void main(String[] args) {
        BotIndex botIndex = new BotIndex();
        botIndex.botCore.setBotCore();
    }
}
