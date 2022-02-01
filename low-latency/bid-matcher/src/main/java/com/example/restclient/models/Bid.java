package com.example.restclient.models;

import com.example.restclient.services.Utils;

import java.util.concurrent.locks.Lock;

public class Bid implements Comparable<Bid> {
    long id;
    long time;
    long buySellInd;  // to do fast match 0 = Sell, 1 = Buy
    long quantity;
    double price;
    Lock lock; // bid level locking

    public Bid(long id,
               long time,
               long buySellInd,
               long quantity,
               double price) {
        this.id = id;
        this.time = time;
        this.buySellInd = buySellInd;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public int compareTo(Bid obj) {

        double price_diff = this.price - obj.price;
        if (this.buySellInd > 0) {
            // buy side
            if (price_diff > 0.0) {
                return -1;
            } else if (price_diff < 0.0) {
                return 1;
            }
            return (int) (this.time - obj.time);

        } else {
            if (price_diff < 0.0) {
                return -1;
            } else if (price_diff > 0.0) {
                return 1;
            }
            return (int) (this.time - obj.time);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public long getTime() {
        return time;
    }

    public long getBuySellInd() {
        return buySellInd;
    }

    public void setBuySellInd(long buySellInd) {
        this.buySellInd = buySellInd;
    }

    public String getBuySellIndStr() {
        return buySellInd > 0 ? "Buy" : "Sell";
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceStr() {
        return String.format("%,.2f", price);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getBuySellIndStr()).append(" quantity:").append(getQuantity()).append(" price:")
                .append(getPriceStr()).append(" time:").append(Utils.getTimeStr(getTime()));
        return builder.toString();
    }
}
