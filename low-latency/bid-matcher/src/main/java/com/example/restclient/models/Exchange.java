package com.example.restclient.models;

import com.example.restclient.services.BidPrinter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exchange {

    final BuyBidsQueue buyQueue;
    final SellBidsQueue sellQueue;
    double ticker_price; // current ticker Price
    Lock lock; // Lock to whole object

    public Exchange(BuyBidsQueue buyQueue, SellBidsQueue sellQueue) {
        this.buyQueue = buyQueue;
        this.sellQueue = sellQueue;
        this.ticker_price = 0;
        this.lock = new ReentrantLock();
    }

    public void printStatus() {
        //Side Time Qty Price Qty Time Side
        System.out.println("Id Side Time Qty Price Qty Time Side");
        BidPrinter printer = new BidPrinter();
        printer.print(sellQueue.getList());
        printer.print(buyQueue.getList());
    }

    public void CleanupOldObjects() {
        buyQueue.CleanupOldObjects();
        sellQueue.CleanupOldObjects();
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public void submitBids(Bid bid) {

        System.out.println("Execution bid ... ");
        new BidPrinter().print(bid);

        double prev_price = ticker_price;
        if (bid.getBuySellInd() == 0) {
            // Sell
            Bid ret = buyQueue.executeBid(bid);
            if (ret.getQuantity() > 0)
                sellQueue.insertBid(ret);
            if (prev_price != ret.getPrice())
                updateTickerPrice(prev_price, ret.getPrice());
        } else {
            // Buy
            Bid ret = sellQueue.executeBid(bid);
            if (ret.getQuantity() > 0)
                buyQueue.insertBid(ret);

            if (prev_price != ret.getPrice())
                updateTickerPrice(prev_price, ret.getPrice());
        }
    }

    private void updateTickerPrice(double prev_price, double new_price) {
        try {
            lock();
            if (prev_price == ticker_price)
                this.ticker_price = new_price;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }
}
