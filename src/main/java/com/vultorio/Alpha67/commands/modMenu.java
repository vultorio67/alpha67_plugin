package com.vultorio.Alpha67.commands;

import com.google.common.eventbus.Subscribe;
import com.vultorio.Alpha67.menu.IconMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class modMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory ModGui = Bukkit.createInventory(null, 9 * 5, ChatColor.BLACK + "Here you can buy the mod");

            IconMenu menu = new IconMenu("IconMenu", 6, new IconMenu.onClick() {
                @Override
                public boolean click(Player p, IconMenu menu, IconMenu.Row row, int slot, ItemStack item) {
                    item.getItemMeta().getDisplayName();
                    if(row.getRowItem(slot).getType().name() == "ok") {

                            Bukkit.broadcastMessage(row.getRowItem(slot).getType().name());

                    }
                    return true;
                }
            });
            menu.addButton(menu.getRow(1), 0, new ItemStack(Material.STONE), "Stone Button ;)");
            menu.addButton(menu.getRow(2), 0, new ItemStack(Material.DIAMOND), "Diamond Button ;)");
            menu.addButton(menu.getRow(3), 0, new ItemStack(Material.GOLD_BLOCK), "Gold Button ;)");
            menu.addButton(menu.getRow(4), 0, new ItemStack(Material.IRON_BLOCK), "Iron Button ;)");
            menu.addButton(menu.getRow(5), 0, new ItemStack(Material.OBSIDIAN), "Obby Button ;)");
            menu.addButton(menu.getRow(6), 0, new ItemStack(Material.ANVIL), "Anvil Button ;)");
            menu.addButton(menu.getRow(7), 0, new ItemStack(Material.STONE_BUTTON), "Button Button ;)");

            menu.open(player.getPlayer());
//The first parameter, is the slot that is assigned to. Starts counting at 0

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }



}
