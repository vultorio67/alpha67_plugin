package com.vultorio.Alpha67.events;

import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.data.Data;
import com.vultorio.Alpha67.data.dataProvider;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OnPlayerConnect implements Listener {

    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent ev) throws IOException {
        System.out.println("player connect");

        String playerName = ev.getPlayer().getName();
        Player player = ev.getPlayer();

        save(player);

    }


    public void save(Player player)
    {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("uuid", player.getUniqueId().toString());
        config.set("player-name", player.getPlayer().getName());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

