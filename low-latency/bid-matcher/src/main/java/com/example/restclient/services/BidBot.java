package com.example.restclient.services;

import com.example.restclient.models.Bid;
import com.example.restclient.models.Exchange;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class BidBot implements Callable<Integer> {
    final Exchange exchange;

    public BidBot(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;
        String filename = "c:/temp/bid0.dat";
        System.out.println("Generating bids from filename =  " + filename + " thread = " + Thread.currentThread().getId());
        final List<Bid> bids = loadBidsFromFile(filename);
        processBids(bids);
        return fact;
    }

    private void processBids(List<Bid> bids) {
        bids.forEach(bid -> {
            exchange.submitBids(bid);
        });
    }

    private List<Bid> loadBidsFromFile(String filename) {

        ArrayList<Bid> bids = new ArrayList<Bid>();
        BufferedReader reader = null;
        try {
            FileReader fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
            final long[] i = {0};
            reader.lines().forEach(line -> {
                String[] parts = line.split(" ");
                long buySellInd = parts[0].compareToIgnoreCase("B") == 0 ? 1 : 0;
                long quantity = Long.parseLong(parts[1]);
                double price = Double.parseDouble(parts[2]);
                long time = new Date().getTime();

                ++i[0];
                Bid bid = new Bid(i[0],time, buySellInd, quantity, price);
                bids.add(bid);
            });

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bids;
    }
}