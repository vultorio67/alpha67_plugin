package com.vultorio.Alpha67.data;

import com.archyx.aureliumskills.AureliumSkills;
import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.events.OnPlayerConnect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class Data extends dataProvider{

    @Override
    public void save(Player player) {
        super.save(player);
    }


    public Data(Alpha67 plugin) {
        super(plugin);
    }

    @Override
    public void save(Player player, boolean removeFromMemory) {
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

