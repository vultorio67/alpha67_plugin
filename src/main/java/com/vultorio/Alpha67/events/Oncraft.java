package com.vultorio.Alpha67.events;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skills;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class Oncraft implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {

        //Block block = e.getBlock();
        Player player = (Player) e.getWhoClicked();

        int FarmingSkill = AureliumAPI.getSkillLevel(player, Skills.FARMING);

        System.out.println(e.getRecipe().getResult());
        String result = String.valueOf(e.getRecipe().getResult());

        String MYSTICALAGRICULTURE_INFERIUM_ESSENCE = "MYSTICALAGRICULTURE_INFERIUM_ESSENCE";
        boolean MYSTICALAGRICULTURE_INFERIUM_ESSENCE_B = result.contains(MYSTICALAGRICULTURE_INFERIUM_ESSENCE);

        String MYSTICALAGRICULTURE_PRUDENTIUM_ESSENCE = "MYSTICALAGRICULTURE_PRUDENTIUM_ESSENCE";
        boolean MYSTICALAGRICULTURE_PRUDENTIUM_ESSENCE_B = result.contains(MYSTICALAGRICULTURE_PRUDENTIUM_ESSENCE);

        String MYSTICALAGRICULTURE_TERTIUM_ESSENCE = "MYSTICALAGRICULTURE_TERTIUM_ESSENCE";
        boolean MYSTICALAGRICULTURE_TERTIUM_ESSENCE_B = result.contains(MYSTICALAGRICULTURE_TERTIUM_ESSENCE);

        String MYSTICALAGRICULTURE_IMPERIUM_ESSENCE = "MYSTICALAGRICULTURE_IMPERIUM_ESSENCE";
        boolean MYSTICALAGRICULTURE_IMPERIUM_ESSENCE_B = result.contains(MYSTICALAGRICULTURE_IMPERIUM_ESSENCE);

        String MYSTICALAGRICULTURE_SUPREMIUM_ESSENCE = "MYSTICALAGRICULTURE_SUPREMIUM_ESSENCE";
        boolean MYSTICALAGRICULTURE_SUPREMIUM_ESSENCE_B = result.contains(MYSTICALAGRICULTURE_SUPREMIUM_ESSENCE);

        if (MYSTICALAGRICULTURE_INFERIUM_ESSENCE_B == true && FarmingSkill < 5)
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("to craft this item you must be at least level 5 in the job FARMER");
        }

        if (MYSTICALAGRICULTURE_PRUDENTIUM_ESSENCE_B == true && FarmingSkill < 10)
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("to craft this item you must be at least level 10 in the job FARMER");
        }

        if (MYSTICALAGRICULTURE_TERTIUM_ESSENCE_B == true && FarmingSkill < 15)
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("to craft this item you must be at least level 15 in the job FARMER");
        }

        if (MYSTICALAGRICULTURE_IMPERIUM_ESSENCE_B == true && FarmingSkill < 25)
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("to craft this item you must be at least level 25 in the job FARMER");
        }

        if (MYSTICALAGRICULTURE_SUPREMIUM_ESSENCE_B == true && FarmingSkill < 40)
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("to craft this item you must be at least level 40 in the job FARMER");
        }

    }

}
