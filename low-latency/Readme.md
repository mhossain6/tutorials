Assignment: Stock Exchange

 

A Stock Exchange's Matching Engine is fundamental to all trading activities. Not only does it maintains and manages all of the investor’s orders, it also generates trades from them. The Matching Engine has to process a large amount of data at any given interval. On top of this, it has to accomplish multiple functions on the back of each order processed (e.g. sending Market Data Update, Storing the Order, Generating any resulting Trade).

 

Your challenge is to design a Stock Exchange's Matching Engine’s crossing functionality, storing orders and generate any resulting trades from new orders. Below are some of the requirements:

Implement in Java
Be able to handle multiple client connections into the Engine
Solution needs to be thread safe