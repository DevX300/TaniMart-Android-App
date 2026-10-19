# TaniMart: Product Data Retrieval

## Problem

TaniMart stores product information across related database tables:

- **Product:** name, description, image, rating, reviews, sold count, and category ID.
- **Price:** product ID, weight, stock, price, and discount.
- **Category:** category name and icon.
- **ProductSpecification:** product ID, specification name, and specification value.

Different screens need different combinations of this data. The goal is to retrieve related information without merging the tables or duplicating data unnecessarily.

## Proposed Solution

Keep the entities separate and use DAO queries to retrieve the data each screen needs. The Repository provides these queries to the ViewModel, and the ViewModel supplies the data to the screen.

- **Product cards:** Retrieve the product and one default price option, such as the smallest weight. Specifications are not needed on a card.
- **Product details:** Retrieve one product, all of its price options, and all of its specifications. Selecting a weight displays that option's price, discount, and stock.
- **Cart:** Retrieve each cart item with its product information and the exact price option selected by the customer.
- **Checkout:** Use the selected items, quantities, prices, stock, and order total. Recheck price and stock before confirming the order.
- **My Order:** Retrieve an order and its purchased items, including their images, weights, quantities, and purchase prices.
- **Track Order:** Retrieve the selected order's items and tracking information.


## How the Data Is Retrieved

Use SQL JOIN queries when related information is needed together, such as Product + Price for product cards or Cart + Product + selected Price for the cart.

For product details, retrieve Product, its list of Prices, and its list of ProductSpecifications. Keeping prices and specifications as separate lists avoids duplicated results that can happen when joining two one-to-many relationships in one query.

Use normal Java result/model classes for combined data when needed. These are not Room entities and do not create extra database tables.

## Important Notes

- Decide what the default product-card price means, such as the smallest weight option.
- Store the selected `priceID` in Cart and OrderItem so the chosen weight/price option is preserved.
- Save purchase-time product name, weight, and price with the order item so later catalogue changes do not alter old order history.
- Keep database operations off the main UI thread.

## Summary

Keep the database entities separate and create screen-focused retrieval methods:

- **Cards:** Product + default Price
- **Details:** Product + all Prices + all ProductSpecifications
- **Cart and Checkout:** Cart items + Product + selected Price
- **My Order and Tracking:** Order + its items + tracking data, including purchase-time details

This approach keeps the design simple, avoids unnecessary data duplication, and provides each screen with the information it needs.
