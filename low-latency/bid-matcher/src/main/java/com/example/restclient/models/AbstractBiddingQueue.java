package com.example.restclient.models;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

public abstract class AbstractBiddingQueue implements BiddingQueue {

    final List<Bid> queue;
    double head_price; // current head price to allow fast match
    Lock lock; // Lock to whole bid - queue

    protected AbstractBiddingQueue(List<Bid> queue){
        this.queue = queue;
    }

    public List<Bid> getQueue() {
        return queue;
    }

    public double getHead_price() {
        return head_price;
    }

    public void setHead_price(double head_price) {
        this.head_price = head_price;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
    @Override
    public void insertBid(Bid inBid) {
        try {
            lock();
            queue.add(inBid);
            Collections.sort(queue);
            head_price = queue.get(0).getPrice();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }

    @Override
    public void CleanupOldObjects() {
        try {
            lock();
            List<Bid> new_sellQueue = queue.stream().filter(bid -> bid.getQuantity() > 0).collect(Collectors.toList());
            queue.clear();
            queue.addAll(new_sellQueue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }

    @Override
    public Bid executeBid(Bid buyBid) {
        return null;
    }
}
