package com.vultorio.Alpha67.sync;

import com.vultorio.Alpha67.Alpha67;
import com.vultorio.Alpha67.market.PriceCalculator;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public final class syncronisation {

    static File customConfigFile;
    static FileConfiguration customConfig;

    public static void saveServerData()
    {
        customConfigFile = new File(Alpha67.getInstance().getDataFolder(), "server-data.yml");

        if (!customConfigFile.exists()) {
            File file = new File(Alpha67.getInstance().getDataFolder() + "/server-data.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("timeStone", System.currentTimeMillis());
            config.set("timeWood", System.currentTimeMillis());
            config.set("timeGold", System.currentTimeMillis());
            config.set("timeDiamond", System.currentTimeMillis());

            System.out.println("save data");
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public static long getStone(int id)
    {
        File file = new File(Alpha67.getInstance().getDataFolder() + "/server-data.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return 10;
        }

        if (id == 0)
        {
            return config.getLong("timeStone");
        }
        else if (id == 1)
        {
            return config.getLong("timeWood");
        }
        else if (id == 2)
        {
            return config.getLong("timeGold");
        }
        else if (id == 3)
        {
            return config.getLong("timeDiamond");
        }

        else {
            return -1;
        }
    }



    public static void sync()
    {



    }




    public static void money(Player player) throws IOException, ParseException {
        try {

            Object ob = new JSONParser().parse(new FileReader("communication-alpha/playerData/"+player.getUniqueId()+".json"));
            JSONObject js = (JSONObject) ob;

            Boolean modif = (Boolean) js.get("modification");

            double money = (double) js.get("money");

            double vaultMoney = Alpha67.econ.getBalance(player);



            if (modif) {

                if (money < vaultMoney) {

                    double add = vaultMoney - money;
                    Alpha67.econ.withdrawPlayer(player, add);
                    System.out.println(add+"1"+Alpha67.econ.getBalance(player));

                    System.out.println("the money amount of"+player.getUniqueId()+"is now of"+Alpha67.econ.getBalance(player));

                } else if (money > vaultMoney) {

                    double add = money - vaultMoney;
                    Alpha67.econ.depositPlayer(player, add);
                    System.out.println(add+"2"+Alpha67.econ.getBalance(player));
                    System.out.println("the money amount of"+player.getUniqueId()+"is now of"+Alpha67.econ.getBalance(player));
                }

                JSONObject jsonObject = new JSONObject();
                //Inserting key-value pairs into the json object
                jsonObject.put("money", money);
                jsonObject.put("modification", false);
                try {
                    FileWriter file = new FileWriter("communication-alpha/playerData/" + player.getUniqueId() + ".json");
                    file.write(jsonObject.toJSONString());
                    file.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if(money != vaultMoney){
                JSONObject jsonObject = new JSONObject();
                //Inserting key-value pairs into the json object
                jsonObject.put("money", Alpha67.econ.getBalance(player));
                jsonObject.put("modification", modif);
                try {
                    FileWriter file = new FileWriter("communication-alpha/playerData/" + player.getUniqueId() + ".json");
                    file.write(jsonObject.toJSONString());
                    file.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch(Exception e) {
            JSONObject jsonObject = new JSONObject();
            //Inserting key-value pairs into the json object
            jsonObject.put("money", Alpha67.econ.getBalance(player));
            jsonObject.put("modification", false);
            try {
                FileWriter file = new FileWriter("communication-alpha/playerData/" + player.getUniqueId() + ".json");
                file.write(jsonObject.toJSONString());
                file.close();

            } catch (IOException er) {
                er.printStackTrace();
            }
        }


        File Bridge = new File("communication-alpha");
        boolean res = Bridge.mkdir();

        File playerData = new File("communication-alpha/playerData/");
        boolean res2 = playerData.mkdir();

        float stonePrice = PriceCalculator.calculateStonePrice();
        float woodPrice = PriceCalculator.calculateWoodPrice();
        float goldPrice = PriceCalculator.calculateGoldPrice();
        float diamondPrice = PriceCalculator.calculateDiamondPrice();
        float stoneMax = (float) 150.15;

        JSONObject bridgeServers = new JSONObject();
        //Inserting key-value pairs into the json object
        bridgeServers.put("StonePrice", stonePrice);
        bridgeServers.put("WoodPrice", woodPrice);
        bridgeServers.put("GoldPrice", goldPrice);
        bridgeServers.put("DiamondPrice", diamondPrice);

        bridgeServers.put("StoneMax", stoneMax);
        bridgeServers.put("WoodMax", stoneMax);
        bridgeServers.put("GoldMax", stoneMax);
        bridgeServers.put("DiamondMax", stoneMax);
        try {
            FileWriter file = new FileWriter("communication-alpha/bridge-Server.json");
            System.out.println("save");
            file.write(bridgeServers.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }



    public static void stoneMarketPriceSync()
    {
       /* float stonePrice = stonePriceCalculator.calculatePrice();

        System.out.println("sell sync : "+stonePrice);

        JSONObject bridgeServer = new JSONObject();

        bridgeServer.put("stonePrice", stonePrice);

        try {
            FileWriter file = new FileWriter("communication-alpha/bridge-Server.json");
            file.write(bridgeServer.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/



    }

    /*
    Read json file:
            Object ob = new JSONParser().parse(new FileReader("JSONFile.json"));
            JSONObject js = (JSONObject) ob;

            String firstName = (String) js.get("firstName");
            String lastName = (String) js.get("lastName");
     */

}
