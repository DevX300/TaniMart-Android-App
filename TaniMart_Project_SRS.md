# Software Requirements Specification for TaniMart (SRS)

## 1. Introduction

### 1.1 Purpose

The purpose of TaniMart is to make the buying process easier and more convenient for farmers, businesses and people who want natural quality products.

TaniMart allows users to easily find and purchase different types of agricultural and farming-related products according to their needs. The application aims to provide quality products, affordable prices, and convenient delivery.

### 1.2 Scope

TaniMart is a shopping application focused on agricultural and farming-related products.All types of users can browse and search for products, add products to their cart, place orders and track their purchases.<br>
A registered user can access additional features such as purchase history, loyalty points, and useful product-related articles tailored to their needs.

### 1.3 Intended Users

* Farmers
* Businesses

## 2. Overall Description

### 2.1 Product Perspective

TaniMart is an android mobile shopping application that allows farmers and businesses to purchase agricultural and farming-related products from one platform.

### 2.2 Product Features

* Login/ SignUp user
* Product search by category
* Browse and purchase products
* Shopping cart and checkout
* Promo codes and loyalty points
* Purchase history
* Order tracking
* Product-related articles

### 2.3 User Types

* Registered User
* Guest User

### 2.4 Operating Environment

* Android mobile devices
* Android operating system
* Internet connection for online features
* Java and XML-based Android application

### 2.5 Constraints

* The application will be developed for Android devices.
* The application will use Java and XML.
* Room Database will be used for local data storage.
* The application will follow the MVVM architecture.

## 3. Functional Requirements

- Signup or login for registered users
- Home/Product Browsing
- Product category search from categories
- Products search from product listings
- Show product Details 
- Cart Management for multiple products
- Promo Code System and loyalty points
- Checkout to confirm order
- Address Management and payment management during checkout
- Order History management 
- Profile Management with settings 
- Product related articles

## 4. Non-Functional Requirements

### 4.1 Performance

- Pages should load within a reasonable time.
- Product searches should return results quickly and appopriate filtered result.
- Cart and checkout operations should respond without noticeable delay.
- Database operations should not freeze the application.


### 4.2 Reliability

- The application should work for a guest user providing only limited access to functional features.
- The application should not crash during normal usage.
- Cart data should remain available when navigating between screens or any orientations and state changes.
- Order information should be stored correctly.
- Invalid operations should be handled without crashing the application.
- The application should handle connection or database errors appropriately.


### 4.3 Maintainability

- The application should follow the <b>MVVM</b> architecture.
- Code should be organized into separate components with clear responsibilities.
- Database operations should be separated from UI code.
- Reusable components should be used where appropriate.
- The project should follow consistent naming and coding practices.

### 4.4 Compatibility

- The application should run on supported Android devices.
- The UI should adapt to different screen sizes.
- The application should work correctly across the selected range of Android versions.
- The application should support the required screen orientations.

## 5. Data Requirements

- Product categories 
- Product listings for each categories 
- Product details from each listings
- Cart and cart items added information
- Checkout and Checkout items information
- Purchase history and my orders status information
- Order tracking abstract implementation for both registered and guest profile
- Product-related articles
- Promo code and loyalty point information for each purchases
- Address and payment Information(Dmeo implement)
- User Profile details and history

## 6. External Interface Requirements

### 6.1 User Interface

- The application will use XML-based Android layouts.
- The UI will follow the provided Figma design.
- The application will support different screen sizes.

### 6.3 Software Interface (Stack Used)

- Android operating system
- Java
- XML
- Room Database
- SQLite
- Android SDK
- Gradle Kotlin DSL

