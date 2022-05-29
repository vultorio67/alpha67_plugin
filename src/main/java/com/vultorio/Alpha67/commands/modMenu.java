package com.vultorio.Alpha67.commands;

import com.google.common.eventbus.Subscribe;
import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.data.Data;
import com.vultorio.Alpha67.menu.IconMenu;
import com.vultorio.Alpha67.utils.Util;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class modMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;


            Inventory ModGui = Bukkit.createInventory(null, 9 * 5, ChatColor.BLACK + "Here you can buy the mod");

            int modLevel = loadData(player);

            IconMenu menu = new IconMenu("IconMenu", 6, new IconMenu.onClick() {
                @Override
                public boolean click(Player p, IconMenu menu, IconMenu.Row row, int slot, ItemStack item) {
                    item.getItemMeta().getDisplayName();

                    List<String> modList = Util.ModList();
                    int modNumber = modList.size();
                    long price = 500000;

                    for(int i = 0; i <= modNumber; i++)
                    {
                        String modName = modList.get(i);

                        if (row.getRowItem(slot).getItemMeta().getDisplayName().contains(modName)) {

                            if (Alpha67.econ.getBalance(p.getPlayer()) >= price && loadData(player) == i) {
                                print(modName, true);
                                Alpha67.econ.depositPlayer(player.getPlayer(), -price);
                                Data.saveModLevel(player, i+1);
                                menu.close(player);

                            } else {
                                print(modName, false);
                            }
                        }

                        price = (long) (price*1.25);
                    }return true;
                }
            });

            List<String> modList = Util.ModList();
            int modNumber = modList.size();
            long price = 500000;
            modLevel = loadData(player);

            for(int i = 0; i <= modNumber-1; i++) {
                String modName = modList.get(i);
                if (i > modLevel)
                {
                    menu.addButton(menu.getRow(row(i)), postion(i), new ItemStack(Material.RED_STAINED_GLASS), ChatColor.GOLD+modName, ChatColor.RED + "cost: "+price);
                }
                else if (i == modLevel)
                {
                    menu.addButton(menu.getRow(row(i)), postion(i), new ItemStack(Material.ORANGE_STAINED_GLASS), ChatColor.GOLD+modName, ChatColor.RED + "cost: "+price);
                }
                else {
                    menu.addButton(menu.getRow(row(i)), postion(i), new ItemStack(Material.GREEN_STAINED_GLASS), ChatColor.GOLD+modName, ChatColor.RED + "cost: "+price);

                }
                price = (long) (price*1.25);
            }
            menu.open(player.getPlayer());
//The first parameter, is the slot that is assigned to. Starts counting at 0

        }
        // If the player (or console) uses our command correct, we can return true
        return true;
    }


    public int loadData(Player player) {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getInt("mod-level");
    }

    public void print(String modName, boolean canBuy)
    {
        if (canBuy == true)
        {
            Bukkit.broadcastMessage("Vous avez assez d'argent pour acheter: "+modName+" transaction vers la banque centrale reussie.");
        }
        else
        {
            Bukkit.broadcastMessage("Vous n'avez pas assez d'argent pour acheter: "+modName);
        }
    }

    public int row(int listNumber)
    {
        if(listNumber <= 5)
        {
            return listNumber;
        }
        else if (listNumber <=7 && listNumber >= 5 || listNumber >=21 && listNumber >= 19)
        {
            return 5;
        }
        else if (listNumber <=11 && listNumber >= 8)
        {
            return -listNumber+12;
        }
        else if (listNumber <=14 && listNumber >= 12)
        {
            return 0;
        }
        else if (listNumber <=18 && listNumber >= 15)
        {
            return listNumber-14;
        }
        else if (listNumber <=21 && listNumber >= 19)
        {
            return 5;
        }
        else
        {
            return 0;
        }

    }

    public int postion(int ListNumnber)
    {
        if (ListNumnber <= 5)
        {
            return 0;
        }
        else if (ListNumnber == 6)
        {
            return 1;
        }
        else if (ListNumnber <= 12 && ListNumnber >=7)
        {
            return 2;
        }
        else if (ListNumnber == 13)
        {
            return 3;
        }
        else if (ListNumnber <= 19 && ListNumnber >=14)
        {
            return 4;
        }
        else if (ListNumnber == 20)
        {
            return 5;
        }
        else if (ListNumnber <= 23 && ListNumnber >=21)
        {
            return 4;
        }
        else {return 0;}
    }


}
