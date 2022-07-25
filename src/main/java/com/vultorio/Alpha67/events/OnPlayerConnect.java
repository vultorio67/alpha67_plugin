package com.vultorio.Alpha67.events;

import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.data.Data;
import com.vultorio.Alpha67.sync.syncronisation;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OnPlayerConnect implements Listener {

    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent ev) throws IOException {
        System.out.println("player connect");

        String playerName = ev.getPlayer().getName();
        Player player = ev.getPlayer();

        Data.saveOnLogin(player);
        syncronisation.saveServerData();

        try {
            syncronisation.money(player);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

