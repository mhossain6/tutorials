package com.example.restclient.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SellBidsQueue implements BiddingQueue {
    //final SortedSet<Bid> sellQueue;
    final List<Bid> sellQueue;
    double head_price; // current head price to allow fast match
    Lock lock; // Lock to whole bid - queue

    public SellBidsQueue() {
        head_price = 0;
        sellQueue = new ArrayList<>();
        lock = new ReentrantLock();
    }

    public List<Bid> getList() {
        return sellQueue;
    }


    public void CleanupOldObjects() {
        try {
            lock();

            List<Bid> new_sellQueue = sellQueue.stream().filter(bid -> bid.getQuantity() > 0).collect(Collectors.toList());

            sellQueue.clear();
            sellQueue.addAll(new_sellQueue);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public Bid executeBid(Bid buyBid) {
        if (buyBid.getPrice() >= head_price && !sellQueue.isEmpty()) {
            return matchAndExecBid(buyBid);
        } else {
            return buyBid;
        }
    }

    private Bid matchAndExecBid(Bid buyBid) {
        try {
            lock();
            // check again that the queue has not changed
            if (!sellQueue.isEmpty()) {
                sellQueue.forEach((bid) -> {
                    double sellPrice = bid.getPrice();
                    double buyPrice = buyBid.getPrice();
                    long buyQty = buyBid.getQuantity();
                    if (sellPrice <= buyPrice && buyQty > 0) {
                        long sellQty = bid.getQuantity();
                        if (buyQty <= sellQty) {
                            long remainQty = sellQty - buyQty;
                            bid.setQuantity(remainQty); // buy all qty
                            head_price = bid.getPrice();
                            buyBid.setQuantity(0);
                        } else {
                            buyBid.setQuantity(buyQty - sellQty);
                            bid.setQuantity(0); // all quantity sold
                        }
                    }
                });
            } else {
                throw new RuntimeException("Queue has changed ....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
        CleanupOldObjects();
        return buyBid;
    }


    public void insertBid(Bid inBid) {
        try {
            lock();
            sellQueue.add(inBid);
            Collections.sort(sellQueue);
            head_price = sellQueue.get(0).getPrice();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }

    }

}
