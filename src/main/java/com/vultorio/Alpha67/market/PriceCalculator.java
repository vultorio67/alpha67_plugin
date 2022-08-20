package com.vultorio.Alpha67.market;

import com.vultorio.Alpha67.sync.getCount;
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
        int a = (int) (50 + getCount.getCount("stone")); //coef

        float b = 1200; //time in second
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        double c = getMaxStone();

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getTime(0)));

        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);

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
        int a = (int) (50 + getCount.getCount("wood")); //coef

        float b = 1200; //time in second
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        double c = getMaxWood();


        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getTime(1)));


        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);

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
        int a = (int) (50 + getCount.getCount("gold")); //coef

        float b = 1200; //time in second
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        double c = getMaxGold();

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getTime(2)));


        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);

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
        int a = (int) (50 + getCount.getCount("diamond")); //coef

        float b = 1200; //time in second
        float startTime = (float) (b/1.2);

        b = b*65;

        //max value
        double c = getMaxDiamond();

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentStoneTime = (long) ((System.currentTimeMillis() - syncronisation.getTime(3)));


        currentStoneTime = (long) (currentStoneTime+startTime);

        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentStoneTime/b))-(1-(1/Math.exp(currentStoneTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);

        return defalutSellPrice;
    }

    public static double getMaxStone()
    {
        return 50 + getCount.getCount("stone");
    }
    public static double getMaxWood()
    {
        return (50 + getCount.getCount("wood"))*4;
    }
    public static double getMaxGold()
    {
        return (50 + getCount.getCount("gold"))*10;
    }
    public static double getMaxDiamond()
    {
        return (50 + getCount.getCount("diamond"))*30;
    }




}
