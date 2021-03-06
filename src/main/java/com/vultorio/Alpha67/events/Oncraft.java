package com.vultorio.Alpha67.events;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skills;
import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public class Oncraft implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {

        //Block block = e.getBlock();
        Player player = (Player) e.getWhoClicked();

        //int FarmingSkill = AureliumAPI.getSkillLevel(player, Skills.FARMING);

        System.out.println(e.getRecipe().getResult());
        String result = String.valueOf(e.getRecipe().getResult());
        int modLevel = loadData(player);
        List<String> modList = Util.ModListId();
        int modNumber = modList.size();

        for(int i = 0; i <= modNumber-1; i++) {
            String modName = modList.get(i);
            if (result.contains(modName) && modLevel < i)
            {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
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
