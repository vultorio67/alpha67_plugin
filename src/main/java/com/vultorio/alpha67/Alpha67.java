package com.vultorio.alpha67;

import com.vultorio.alpha67.events.OnBlockBreak;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Alpha67 extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        System.out.println("Le plugin Alpha67 c'est bien lancé.");

        instance = this;
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), instance);

        //getServer().getPluginManager().registerEvents(new BreakBlock(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Good Bye, alpha67 ce desactive.");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
