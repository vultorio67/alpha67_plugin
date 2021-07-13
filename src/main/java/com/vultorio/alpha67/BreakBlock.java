package com.vultorio.alpha67;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event)
    {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.WHEAT)
        {

            event.setCancelled(true);
            blockBroken.setType(Material.WHEAT);
        }

    }
}
