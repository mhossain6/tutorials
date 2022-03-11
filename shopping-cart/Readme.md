Code Challenge
Problem Statement
You have a shopping Cart that has some items. Write a method that returns the total price of items in the cart. The merchant may offer various types of promotions at any point in time. The customers may buy any number of items in any combination regardless of whether the items are part of a promotion or not.

We want your price calculation to take into account the promotions below that are active at the same time:

Buy A and get B for half price. Half price is applied to B items based on the number of A items.
Buy any 3 items from a set of products {X, Y, Z, P, Q} and get the cheapest one for free.
Requirement
Provide a way to calculate the final price. The products and prices should be configurable.

Notes
Please create such promotions in your code with any item prices of your choice and calculate the total cart price based on those. You can safely assume that the merchant will not set up the same item to be part of different promotions at the same time. You do not need to provide any input interface e.g cli or file-based.

Example 1
Products:

A = $10.00
B = $9.00
Cart has A (1 item) and B (2 item)

Output: Total = 23.50

Explanation:

Half price is only applied to 1 B because there is only 1 A, the other B is full price.

Price = 10.00 + (9.00 / 2) + 9.00 = 23.50

Example 2
Products:

X = $10.00
Y = $5.00
Z = $4.00
P = $3.00
Q = $8.00
R = $2.00
Cart has X (1 item), Y (1 item), Z (1 item) and R (1 item)

Output: Total = 17.00

Explanation:

We have 4 items in the cart. R is not part of promotion. Cheapest based on promotion products is Z so it will be free.

Price = 10.00 + 5.00 + 0.00 + 2.00 = 17.00

Food for thought
Will the solution work if the cart has 10X, 10Y, 10Z, 10P, 10Q and 10R items? If you are a customer with this combination of items in your cart, which ones would you expect the merchant to give for free?

