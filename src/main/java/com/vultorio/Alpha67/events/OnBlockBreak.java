package com.vultorio.Alpha67.events;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skills;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.vultorio.Alpha67.Alpha67;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.InvocationTargetException;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void breakEvent(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        Player player = e.getPlayer();

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
}
