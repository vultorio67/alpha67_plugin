package com.vultorio.Alpha67.market;

import com.vultorio.Alpha67.data.serverData;

public class stonePriceCalculator {

    public static int calculatePrice()
    {

        float defalutSellPrice = 50;
        float defaultBuyPrice = (float) (defalutSellPrice*1.2);

        int a = 100;
        float b = 172000000;
        float startTime = (float) (b/1.3);
        float c = 0;

        float min = 0.0F;
        float max = 1.2F;
        double random = min + Math.random() * (max - min);

        long currentTime = (long) ((System.currentTimeMillis() - serverData.getTime()));
        currentTime = (long) (currentTime+startTime);

        long currentTimeC = (long) ((System.currentTimeMillis() - serverData.getTimeC()));
        currentTimeC = (long) (currentTimeC+startTime);



        System.out.println("current time c = "+currentTimeC);


        defalutSellPrice = (float) ((-a*2)*(1.1*(1-(1/Math.exp(currentTime/b))-(1-(1/Math.exp(currentTime/2/b)))))+c);
        defaultBuyPrice = (float) (defalutSellPrice*1.2);
        System.out.println("sell: "+ defalutSellPrice);

        System.out.println(currentTime);


        return 0;
    }

}
