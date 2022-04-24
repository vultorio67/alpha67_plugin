package com.vultorio.Alpha67;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {

            byte[] effectiveMessage = Arrays.copyOfRange(message, 1, message.length);
            System.out.println(new String(effectiveMessage));
            player.sendMessage("RECEIVED MESSAGE");
            /*byte[] output = new byte[8];
            output[0] = (byte)0;
            output[1] = (byte)0;
            output[2] = (byte)0;
            output[3] = (byte)0;
            output[4] = (byte)0;
            output[5] = (byte)0;
            output[6] = (byte)0;
            output[7] = (byte)0;
            player.sendPluginMessage(Alpha67.getInstance(), "amcbase:main", output);
            System.out.println("Sent back to the client");*/

    }
}
