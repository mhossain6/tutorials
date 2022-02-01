package com.example.restclient.services;

import com.example.restclient.models.Bid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BidPrinter {

    public void print(Bid bid) {

        //Side Time Qty Price Qty Time Side
        // -     -   -    x    x    x    x
        // x    x    x    x    -    -    -
        if (bid.getBuySellInd() == 0) {
            System.out.print(bid.getId() + "  -      -       -    ");
            System.out.println(" " + bid.getPriceStr() + " " + bid.getQuantity() + " " + getTimeStr(bid.getTime()) + " Sell");
        } else {
            System.out.print(bid.getId()+ " Buy " + getTimeStr(bid.getTime()) + " " + bid.getQuantity() + " " + bid.getPriceStr());
            System.out.println("  -      -       -    ");
        }
    }

    public void print(List<Bid> bids) {
        bids.forEach(bid -> print(bid));
    }

    public String getTimeStr(long time) {
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        return dateFormat.format(date);
    }
}
