package com.example.restclient.services;

import com.example.restclient.models.BuyBidsQueue;
import com.example.restclient.models.Exchange;
import com.example.restclient.models.SellBidsQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExchangeClient {

    final ExecutorService executor;
    final List<Future<Integer>> futures;
    final Exchange exchange;
    Future cleanupThreadFuture;

    public ExchangeClient() {
        this.exchange = new Exchange(new BuyBidsQueue(), new SellBidsQueue());
        this.executor = Executors.newFixedThreadPool(10);
        this.futures = new ArrayList<Future<Integer>>();
    }

    public void startBidding() {
        //ExchangeCleanupThread cleanupThread = new ExchangeCleanupThread(exchange);
        //for(int i=0; i<100; i++)
        submitJob(new BidBot(exchange));
    }

    private void submitJob(BidBot bidBot) {
        Future<Integer> future = executor.submit(bidBot);
        futures.add(future);
    }

    public void awaitCompletion() {

        for (Future<Integer> future : futures) {
            if (!future.isCancelled()) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        /*
        try {
            if (null != cleanupThreadFuture) {
                cleanupThreadFuture.cancel(true);
                cleanupThreadFuture.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        */

        executor.shutdown();
    }

    public void printStatus() {
        exchange.printStatus();
    }

}
