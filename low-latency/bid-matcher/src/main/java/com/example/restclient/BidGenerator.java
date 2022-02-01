package com.example.restclient;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BidGenerator {

    static long MAX_BIDS = 1000000;
    static int MAX_QTY = 1000;
    static double PRICE = 100.0;
    static Random rand = new Random();
    static String[] direction = {"B", "S"};

    public static void main(String[] args) throws IOException {

        int id = 0;
        StringBuilder HOME_PATH = new StringBuilder("C:/temp");
        StringBuilder fileName = new StringBuilder("bid").append(id).append(".dat");
        String file_path = HOME_PATH + "/" + fileName;

        createBidFile(file_path);

    }

    private static void createBidFile(String filename) throws IOException {

        FileWriter fileWriter = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (int i = 1; i < MAX_BIDS; i++) {
            String bid = getBid();
            writer.append(bid).append("\n");
        }

        writer.close();
    }

    private static String getBid() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(getBuySellInd()).append(" ").append(getQty()).append(" ").append(getPrice()).toString();
    }

    private static String getPrice() {
        // Random price increase or decrease
        PRICE = (rand.nextDouble() * 2) > 1 ? rand.nextDouble() + PRICE : PRICE - rand.nextDouble();
        String price = String.format("%,.2f", PRICE);

        // 30% bids  wildCard price
        //if (rand.nextInt(30) / 2 > 10)
        //    return "*";
        //else
        return price;
    }

    private static String getBuySellInd() {
        // Random Buy or Sell Bid
        int indx = rand.nextInt(20);
        return direction[indx % 2];
    }

    private static int getQty() {
        // bid quantity in multiply of 10
        return rand.nextInt(MAX_QTY) * 10;
    }
}
