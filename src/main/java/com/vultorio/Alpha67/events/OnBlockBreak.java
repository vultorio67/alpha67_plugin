package com.vultorio.Alpha67.events;

import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.market.PriceCalculator;
import com.vultorio.Alpha67.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.File;
import java.util.List;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void breakEvent(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        Player player = e.getPlayer();

      //  System.out.println(stonePriceCalculator.calculatePrice());

        System.out.println( Alpha67.econ.getBalance(player));
        System.out.println(PriceCalculator.calculateStonePrice());

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

        //AureliumAPI.addXp(player, Skills.EXCAVATION, 6000);


        //System.out.println(Alpha67.econ.getBalance(player));
        //Alpha67.econ.depositPlayer(player, 100);

        Material cropBlockType = null;

        if(block.getType() == Material.WHEAT)
        {
            cropBlockType = Material.WHEAT;
        }

        if(cropBlockType != null && isGrown(block))
        {
           Material seedType = getSeedMaterial(cropBlockType);

           if (isSeedInInventory(player, cropBlockType))
           {
                removeSeed(player, seedType);
                replantCrop(block.getLocation(), cropBlockType);
           }
        }

    }

    public void replantCrop(Location loaction, Material cropBlockType)
    {
        Bukkit.getScheduler().runTaskLater(Alpha67.getInstance(), () -> {
            loaction.getBlock().setType(cropBlockType);
        }, 50L);
    }

    public void removeSeed(Player player, Material seedType)
    {
        int seedIndexLocation = -1;
        ItemStack currentItems;
        PlayerInventory inventory = player.getInventory();

        //check ou est la seed
        for (int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
        {
            currentItems = inventory.getItem(slotIndex);

            if (currentItems != null)
            {
                if (currentItems.getType() == seedType)
                {
                    seedIndexLocation = slotIndex;

                    slotIndex = inventory.getSize();
                }
            }
        }

        if (seedIndexLocation != -1)
        {
            ItemStack seedItemStack = inventory.getItem(seedIndexLocation);
            if (seedItemStack != null)
            {
                int seedAmount = seedItemStack.getAmount();
                seedItemStack.setAmount(seedAmount - 1);
            }
        }
    }

    public boolean isSeedInInventory(Player player, Material cropBlockType)
    {
        PlayerInventory playerInventory = player.getInventory();
        if (cropBlockType == Material.WHEAT)
        {
            return playerInventory.contains(Material.WHEAT_SEEDS);
        }

        return false;
    }

    public Material getSeedMaterial(Material cropBlockType)
    {
        if(cropBlockType == Material.WHEAT)
        {
            return Material.WHEAT_SEEDS;
        }
        return null;

    }


    public boolean isGrown(Block block)
    {
        Ageable ageable = (Ageable) block.getBlockData();
        int maxAge = ageable.getMaximumAge();

        return ageable.getAge() == maxAge;
    }


    public int loadData(Player player) {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getInt("mod-level");
    }


}
