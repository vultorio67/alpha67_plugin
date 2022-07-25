package com.vultorio.Alpha67;

import com.vultorio.Alpha67.commands.modMenu;
import com.vultorio.Alpha67.events.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;
import com.vultorio.Alpha67.sync.syncronisation;
import org.json.simple.parser.ParseException;


public final class Alpha67 extends JavaPlugin implements Listener {

    private static Alpha67 instance;

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    private final String CHANNEL_NAME = "amcbase:main";

    @Override
    public void onEnable() {

        //garder en haut
        instance = this;

        //serverData.saveServerData();
        syncronisation.sync();

        //new Data(Alpha67.getPlugin(Alpha67.class), "ohai.yml").set("one", "ThreadLocalRandom().current().nextInt()").set("two", "ThreadLocalRandom().current().nextInt()").set("three", "ThreadLocalRandom().current().nextInt()").set("four", "I am you're father").set("five", "You*re").save();

        System.out.println("Le plugin Alpha67 c'est bien lancé.");

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        //setupPermissions();
       // setupChat();

        createFiles();

        getServer().getPluginManager().registerEvents(new OnBlockBreak(), instance);
        getServer().getPluginManager().registerEvents(new Oncraft(), instance);
        getServer().getPluginManager().registerEvents(new OnPlayerConnect(), instance);
        getServer().getPluginManager().registerEvents(new OnItemPickup(), instance);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), instance);
        getCommand("mod").setExecutor(new modMenu());
        System.out.println("Le plugin Alpha67 c'est bien lancé.");


        getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable(){
            public void run() {

                syncronisation.sync();
                System.out.println("stone sync");

                for(Player player : Bukkit.getServer().getOnlinePlayers()){
                    try {
                        syncronisation.money(player);
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, 20L, 20L);



        //Bukkit.getMessenger().registerOutgoingPluginChannel(this, "appleskin:amc");
        //Bukkit.getMessenger().registerIncomingPluginChannel(instance, CHANNEL_NAME, new MessageListener());

        //getServer().getPluginManager().registerEvents(new BreakBlock(), this);
    }


    @Override
    public void onDisable() {
        System.out.println("Good Bye, alpha67 ce desactive.");
        System.out.println(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }


    public double getBalance(UUID uuid) {
        return econ.bankBalance(uuid.toString()).balance;

    }



    public static Alpha67 getInstance() {
        return instance;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

   /* private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }*/



    private File configf, reasonsf, helpf;
    private FileConfiguration config, reasons, help;


    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");


        if(!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();


        try {
            config.load(configf);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }


    }


}


/*class MyPluginMessageListener implements PluginMessageListener {
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        // Do something with this message from the player
    }
}*/
