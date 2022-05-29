package com.vultorio.Alpha67.data;

import com.vultorio.Alpha67.Alpha67;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class serverData {

    static File customConfigFile;
    static FileConfiguration customConfig;

    public static void saveServerData()
    {

        boolean isFirstTime;

        customConfigFile = new File(Alpha67.getInstance().getDataFolder(), "server-data.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            Alpha67.getInstance().saveResource("server-data.yml", false);
            isFirstTime = true;
        }
        else {
            isFirstTime = false;
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
            if(isFirstTime)
            {
                customConfig.set("FirstTime", System.currentTimeMillis());
                customConfig.set("FirstTimeCalcule", System.currentTimeMillis());
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static long getTime()
    {
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        int a = customConfig.getInt("FirstTime");
        return a;
    }

    public static long getTimeC()
    {
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        int a = customConfig.getInt("FirstTimeCalcule");
        return a;
    }

}
