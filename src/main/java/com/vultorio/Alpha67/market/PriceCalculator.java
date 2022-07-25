package com.vultorio.Alpha67.market;

import com.vultorio.Alpha67.sync.syncronisation;

public class PriceCalculator {

    public static float calculateStonePrice()
    {

        float defalutSellPrice = 50;
        float defaultBuyPrice = (float) (defalutSellPrice*1.2);

        long time = System.currentTimeMillis();
        long timeC = time;


        long currentTimeCa = (long) ((System.currentTimeMillis() - timeC));
        currentTimeCa = (long) (currentTimeCa+1);
        int a = 100000; //coef

        float b = 1200; //coef
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        int c = 100;

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getStone(0)));

        System.out.println("currentTime: "+currentStoneTime);

        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);
        System.out.println("sell: "+ defalutSellPrice);

        System.out.println(currentStoneTime);

        return defalutSellPrice;
    }


    public static float calculateWoodPrice()
    {

        float defalutSellPrice = 50;
        float defaultBuyPrice = (float) (defalutSellPrice*1.2);

        long time = System.currentTimeMillis();
        long timeC = time;


        long currentTimeCa = (long) ((System.currentTimeMillis() - timeC));
        currentTimeCa = (long) (currentTimeCa+1);
        int a = 100000; //coef

        float b = 1200; //coef
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        int c = 200;

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getStone(1)));

        System.out.println("currentTime: "+currentStoneTime);

        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);
        System.out.println("sell: "+ defalutSellPrice);

        System.out.println(currentStoneTime);

        return defalutSellPrice;
    }


    public static float calculateGoldPrice()
    {

        float defalutSellPrice = 50;
        float defaultBuyPrice = (float) (defalutSellPrice*1.2);

        long time = System.currentTimeMillis();
        long timeC = time;


        long currentTimeCa = (long) ((System.currentTimeMillis() - timeC));
        currentTimeCa = (long) (currentTimeCa+1);
        int a = 100000; //coef

        float b = 1200; //coef
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        int c = 2000;

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getStone(2)));

        System.out.println("currentTime: "+currentStoneTime);

        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);
        System.out.println("sell: "+ defalutSellPrice);

        System.out.println(currentStoneTime);

        return defalutSellPrice;
    }


    public static float calculateDiamondPrice()
    {

        float defalutSellPrice = 50;
        float defaultBuyPrice = (float) (defalutSellPrice*1.2);

        long time = System.currentTimeMillis();
        long timeC = time;


        long currentTimeCa = (long) ((System.currentTimeMillis() - timeC));
        currentTimeCa = (long) (currentTimeCa+1);
        int a = 100000; //coef

        float b = 1200; //coef
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        int c = 20000;

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getStone(3)));

        System.out.println("currentTime: "+currentStoneTime);

        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);
        System.out.println("sell: "+ defalutSellPrice);

        System.out.println(currentStoneTime);

        return defalutSellPrice;
    }


}
