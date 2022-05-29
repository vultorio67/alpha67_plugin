package com.vultorio.Alpha67.events;

import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.File;
import java.util.List;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e)
    {
        Block block = e.getBlock();
        Player player = e.getPlayer();

        System.out.println(block);
        //String result = String.valueOf(e.getRecipe().getResult());
        int modLevel = loadData(player);
        List<String> modList = Util.ModListId();
        int modNumber = modList.size();

        for(int i = 0; i <= modNumber-1; i++) {
            String modName = modList.get(i);
            if (block.toString().contains(modName) && modLevel < i)
            {
                e.setCancelled(true);
                //e.getWhoClicked().closeInventory();
                Bukkit.broadcastMessage(ChatColor.RED + "Sorry but you can't craft this item, please type /mod to buy autorisation to use this mod");
            }
        }
    }

    public int loadData(Player player) {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getInt("mod-level");
    }
}
