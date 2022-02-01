package com.example.restclient.models;

public interface BiddingQueue {
    void insertBid(Bid inBid);

    Bid executeBid(Bid buyBid);

    void CleanupOldObjects();
}
