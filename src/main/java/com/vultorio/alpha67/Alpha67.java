package com.vultorio.Alpha67;

import com.vultorio.Alpha67.commands.modMenu;
import com.vultorio.Alpha67.data.Data;
import com.vultorio.Alpha67.data.dataProvider;
import com.vultorio.Alpha67.events.OnBlockBreak;
import com.vultorio.Alpha67.events.OnPlayerConnect;
import com.vultorio.Alpha67.events.Oncraft;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Logger;


public final class Alpha67 extends JavaPlugin implements Listener {

    private static Alpha67 instance;

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    private final String CHANNEL_NAME = "amcbase:main";

    @Override
    public void onEnable() {

        createCustomConfig();

        //new Data(Alpha67.getPlugin(Alpha67.class), "ohai.yml").set("one", "ThreadLocalRandom().current().nextInt()").set("two", "ThreadLocalRandom().current().nextInt()").set("three", "ThreadLocalRandom().current().nextInt()").set("four", "I am you're father").set("five", "You*re").save();

        System.out.println("Le plugin Alpha67 c'est bien lancé.");

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

        createFiles();




        instance = this;
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), instance);
        getServer().getPluginManager().registerEvents(new Oncraft(), instance);
        getServer().getPluginManager().registerEvents(new OnPlayerConnect(), instance);
        getCommand("mod").setExecutor(new modMenu());
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

    private File customConfigFile;
    private FileConfiguration customConfig;

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }


    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "custom.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
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

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }



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
