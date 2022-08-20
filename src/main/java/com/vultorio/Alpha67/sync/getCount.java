package com.vultorio.Alpha67.sync;

import com.vultorio.Alpha67.Alpha67;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class getCount {

    public static void manageCount()
    {
        Object ob = null;
        try {

            ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            File file = new File(Alpha67.getInstance().getDataFolder() + "/server-data.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            try {
                double stone = (double) js.get("stone");
                config.set("stoneCount", stone);
            } catch (Exception e){}
            try {
                double wood = (double) js.get("wood");
                config.set("woodCount", wood);
            } catch (Exception e){e.printStackTrace();}
            try {
                double gold = (double) js.get("gold");
                config.set("goldCount", gold);
            } catch (Exception e){}
            try {
                double diamond = (double) js.get("diamond");
                config.set("diamondCount", diamond);
            } catch (Exception e){}

            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();

                try {
                    JSONObject jsonObject2 = new JSONObject();
                    FileWriter file2 = new FileWriter("communication-alpha/count.json");
                    file2.write(jsonObject2.toJSONString());
                    file2.close();

                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

        } catch (IOException | ParseException e) {
            try {
                JSONObject jsonObject2 = new JSONObject();
                FileWriter file2 = new FileWriter("communication-alpha/count.json");
                file2.write(jsonObject2.toJSONString());
                file2.close();

            } catch (IOException e2) {
                e2.printStackTrace();
            }
            throw new RuntimeException(e);
        }

    }

    public static double getCount(String type)
    {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/server-data.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if(type == "stone")
        {
            return config.getDouble("stoneCount");
        }
        else if(type == "wood")
        {
            return config.getDouble("woodCount");
        }
        else if(type == "gold")
        {
            return config.getDouble("goldCount");
        }
        else if(type == "diamond")
        {
            return config.getDouble("diamondCount");
        }
        else {
            return -1;
        }
    }
}
