package com.vultorio.Alpha67.data;

import com.vultorio.Alpha67.Alpha67;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Data{


    public static void saveOnLogin(Player player) {
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

    public static void saveModLevel(Player player,int modLevel) {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("mod-level", modLevel);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

