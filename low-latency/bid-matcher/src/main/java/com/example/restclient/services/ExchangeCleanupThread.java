package com.example.restclient.services;

import com.example.restclient.models.Exchange;

import java.util.concurrent.Callable;

public class ExchangeCleanupThread implements Callable<Integer> {
    final Exchange exchange;

    public ExchangeCleanupThread(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;

        do {
            Thread.sleep(1000l);
            try {
                exchange.lock();
                exchange.CleanupOldObjects();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                exchange.unlock();
            }
        } while (fact != 0);

        return fact;
    }
}