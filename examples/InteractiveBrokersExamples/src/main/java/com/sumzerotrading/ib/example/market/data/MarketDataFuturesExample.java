/**
 * MIT License

Copyright (c) 2015  Rob Terpilowski

Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
and associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.sumzerotrading.ib.example.market.data;

import com.sumzerotrading.data.Commodity;
import com.sumzerotrading.data.Exchange;
import com.sumzerotrading.data.FuturesTicker;
import com.sumzerotrading.interactive.brokers.client.InteractiveBrokersClient;
import com.sumzerotrading.interactive.brokers.client.InteractiveBrokersClientInterface;
import com.sumzerotrading.marketdata.ILevel1Quote;



public class MarketDataFuturesExample {
    
    
    public void start() {
         InteractiveBrokersClientInterface ibClient = InteractiveBrokersClient.getInstance("localhost", 7497, 1);
        ibClient.connect();
        
        
        FuturesTicker esTicker=  new FuturesTicker();
        esTicker.setSymbol("ES");
        esTicker.setExpiryMonth(12);
        esTicker.setExpiryYear(2019);
        esTicker.setExchange(Exchange.GLOBEX);

        
        Commodity si = Commodity.SILVER_NYMEX;
        Commodity es = Commodity.SP500_INDEX_MINI_GLOBEX;
        
        
        FuturesTicker hsi = new FuturesTicker();
        hsi.setSymbol("HSI");
        hsi.setExpiryMonth(12);
        hsi.setExpiryYear(2019);
        hsi.setExchange(Exchange.HKFE);
        hsi.setCurrency("HKD");
        
        
        FuturesTicker vixTicker = FuturesTicker.getInstance(si, 12, 2019);
        // FuturesTicker zw = FuturesTicker.getInstance(Commodity.WHEAT_ECBOT, 12, 2019);
        
        ibClient.subscribeLevel1(vixTicker, (ILevel1Quote quote) -> {
            System.out.println("Received Quote: " + quote );
        });
        
    }
    
    public static void main(String[] args) {
        new MarketDataFuturesExample().start();
    }
}
