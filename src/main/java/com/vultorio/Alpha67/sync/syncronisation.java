package com.vultorio.Alpha67.sync;

import com.vultorio.Alpha67.Alpha67;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public final class syncronisation {

public static void sync()
    {

        File Bridge = new File("communication-alpha");
        boolean res = Bridge.mkdir();

        File playerData = new File("communication-alpha/playerData/");
        boolean res2 = playerData.mkdir();




        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("stone", "1");
        jsonObject.put("First_Name", "Shikhar");
        jsonObject.put("Last_Name", "Dhawan");
        jsonObject.put("Date_Of_Birth", "1981-12-05");
        jsonObject.put("Place_Of_Birth", "Delhi");
        jsonObject.put("Country", "India");
        try {
            FileWriter file = new FileWriter("output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
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




    }

    /*
    Read json file:
            Object ob = new JSONParser().parse(new FileReader("JSONFile.json"));
            JSONObject js = (JSONObject) ob;

            String firstName = (String) js.get("firstName");
            String lastName = (String) js.get("lastName");
     */

}
