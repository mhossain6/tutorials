package com.example.restclient.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BuyBidsQueue implements BiddingQueue {

    final ArrayList<Bid> buyQueue;
    double head_price; // current head price to allow fast match
    Lock lock; // Lock to whole bid - queue

    public BuyBidsQueue() {
        head_price = 0;
        buyQueue = new ArrayList<Bid>();
        lock = new ReentrantLock();
    }

    public ArrayList<Bid> getList() {
        return buyQueue;
    }

    public void CleanupOldObjects() {
        try {
            lock();
            List<Bid> new_sellQueue = buyQueue.stream().filter(bid -> bid.getQuantity() > 0).collect(Collectors.toList());
            buyQueue.clear();
            buyQueue.addAll(new_sellQueue);
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


    public Bid executeBid(Bid sellBid) {
        if (sellBid.getPrice() <= head_price && !buyQueue.isEmpty()) {
            return matchAndExecBid(sellBid);
        }
        return sellBid;
    }

    private Bid matchAndExecBid(Bid sellBid) {
        try {
            lock();
            // check again that the queue has not changed
            if (!buyQueue.isEmpty()) {
                buyQueue.forEach((bid) -> {
                    double sellPrice = sellBid.getPrice();
                    double buyPrice = bid.getPrice();
                    long sellQty = sellBid.getQuantity();
                    if (sellPrice <= buyPrice && sellQty > 0) {
                        long buyQty = bid.getQuantity();
                        if (buyQty <= sellQty) {
                            long remainQty = sellQty - buyQty;
                            bid.setQuantity(0);
                            sellBid.setQuantity(remainQty); // buy all qty
                        } else {
                            bid.setQuantity(buyQty - sellQty);
                            sellBid.setQuantity(0); // all quantity sold
                        }
                        head_price = bid.getPrice();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
        CleanupOldObjects();

        return sellBid;
    }


    public void insertBid(Bid inBid) {
        try {
            lock();
            buyQueue.add(inBid);
            Collections.sort(buyQueue);
            head_price = buyQueue.get(0).getPrice();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }
}
