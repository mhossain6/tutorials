package com.example.restclient;

import com.example.restclient.services.ExchangeClient;

import java.io.IOException;

public class TestRunner {

    public static void main(String[] args) throws IOException {


        long start = System.currentTimeMillis();
        System.out.format("Current starting time %,d \n", start);

        ExchangeClient exchangeClient = new ExchangeClient();

        exchangeClient.startBidding();

        exchangeClient.awaitCompletion();

        exchangeClient.printStatus();

        long duration = System.currentTimeMillis() - start;

        System.out.format("Total duration taken %,d \n", duration);

    }
}
