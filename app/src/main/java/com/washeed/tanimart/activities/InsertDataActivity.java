package com.washeed.tanimart.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.entities.Article;
import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.entities.Category;
import com.washeed.tanimart.data.entities.Order;
import com.washeed.tanimart.data.entities.OrderItem;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.entities.PromoReward;
import com.washeed.tanimart.data.entities.Track;
import com.washeed.tanimart.data.entities.User;
import com.washeed.tanimart.data.entities.UserPromo;
import com.washeed.tanimart.data.repository.ArticleRepository;
import com.washeed.tanimart.data.repository.CartRepository;
import com.washeed.tanimart.data.repository.OrderRepository;
import com.washeed.tanimart.data.repository.ProductRepository;
import com.washeed.tanimart.data.repository.PromoRepository;
import com.washeed.tanimart.data.repository.UserRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertDataActivity extends AppCompatActivity {
//    TextView text1, text2, text3, text4, text5, text6;
    EditText text1, text2, text3, text4, text5, text6;
    Button btn1, btn2, btn3, btn4;
    UserRepository userRepository;
    ProductRepository productsRepository;
    ArticleRepository articleRepository;
    CartRepository cartRepository;
    OrderRepository orderRepository;
    PromoRepository promoRepository;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    int count = 1;
    int dataCount=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn3.setText("Clear Fields");
        btn1.setText("Insert Data");
        btn2.setText("Get Data");
        btn3.setOnClickListener(v -> clearFields());
        showDataTable();
        btn4.setOnClickListener(v -> changeDataTable());
    }

    // ================================================================
    // USER TEST
    // ================================================================
    private void insertUser() {
        String name = getInput(text1);
        String email = getInput(text2);
        String password = getInput(text3);
        String pointsText = getInput(text4);
        String location = getInput(text5);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                pointsText.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(pointsText)) {
            Toast.makeText(this, "Points must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(
                name,
                email,
                password,
                Integer.parseInt(pointsText),
                location
        );
        executorService.execute(() -> {
            long userID = userRepository.userInsert(user);
            runOnUiThread(() -> {
                text1.setText(user.getUserName());
                text2.setText(user.getUserEmail());
                text3.setText(user.getUserPass());
                text4.setText(String.valueOf(user.getPoints()));
                text5.setText(user.getLocation());
                text6.setText("User ID: " + userID);
            });
        });
    }
    private void getUser() {
        executorService.execute(() -> {
            User user = userRepository.getUserById(count);
            count++;
            runOnUiThread(() -> {
                if (user != null) {
                    text1.setText(user.getUserName());
                    text2.setText(user.getUserEmail());
                    text3.setText(user.getUserPass());
                    text4.setText(String.valueOf(user.getPoints()));
                    text5.setText(user.getLocation());
                    text6.setText("User ID: " + user.getUserID());
                } else {
                    clearFields();
                    text1.setText("User not found");
                }
            });
        });
    }

    // ================================================================
    // CATEGORY TEST
    // ================================================================
    private void insertCategory() {
        String name = getInput(text1);
        String iconText = getInput(text2);

        if (name.isEmpty() || iconText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(iconText)) {
            Toast.makeText(this, "Icon ID must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        Category category = new Category(
                name,
                Integer.parseInt(iconText)
        );

        executorService.execute(() -> {
            long categoryID = productsRepository.categoryInsert(category);

            runOnUiThread(() -> {
                text1.setText(category.getCategoryName());
                text2.setText(String.valueOf(category.getCategoryIconID()));
                text3.setText("Category ID: " + categoryID);
            });
        });
    }
    private void getCategory() {
        executorService.execute(() -> {
            Category category = productsRepository.getCategoryById(count);
            count++;

            runOnUiThread(() -> {
                if (category != null) {
                    text1.setText(category.getCategoryName());
                    text2.setText(String.valueOf(category.getCategoryIconID()));
                    text3.setText("Category ID: " + category.getCategoryID());
                } else {
                    clearFields();
                    text1.setText("Category not found");
                }
            });
        });
    }

    // ================================================================
    // PRODUCT TEST
    // ================================================================
    private void insertProduct() {
        String categoryText = getInput(text1);
        String name = getInput(text2);
        String description = getInput(text3);
        String specification = getInput(text4);
        String imageText = getInput(text5);

        if (categoryText.isEmpty() || name.isEmpty() || description.isEmpty() ||
                specification.isEmpty() || imageText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(categoryText) || !isNumber(imageText)) {
            Toast.makeText(this, "Category ID and Image ID must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(
                Integer.parseInt(categoryText),
                name,
                description,
                specification,
                Integer.parseInt(imageText)
        );

        executorService.execute(() -> {
            long productID = productsRepository.productInsert(product);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(product.getCategoryID()));
                text2.setText(product.getProductName());
                text3.setText(product.getDescription());
                text4.setText(product.getSpecification());
                text5.setText(String.valueOf(product.getProductImageID()));
                text6.setText("Product ID: " + productID);
            });
        });
    }
    private void getProduct() {
        executorService.execute(() -> {
            Product product = productsRepository.getProductById(count);
            count++;

            runOnUiThread(() -> {
                if (product != null) {
                    text1.setText(String.valueOf(product.getCategoryID()));
                    text2.setText(product.getProductName());
                    text3.setText(product.getDescription());
                    text4.setText(product.getSpecification());
                    text5.setText(String.valueOf(product.getProductImageID()));
                    text6.setText("Product ID: " + product.getProductID());
                } else {
                    clearFields();
                    text1.setText("Product not found");
                }
            });
        });
    }

    // ================================================================
    // PRICE TEST
    // ================================================================
    private void insertPrice() {
        String productText = getInput(text1);
        String weightText = getInput(text2);
        String stockText = getInput(text3);
        String priceText = getInput(text4);

        if (productText.isEmpty() || weightText.isEmpty() ||
                stockText.isEmpty() || priceText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(productText) || !isNumber(weightText) ||
                !isNumber(stockText) || !isNumber(priceText)) {
            Toast.makeText(this, "All numeric fields must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        Price price = new Price(
                Integer.parseInt(productText),
                Integer.parseInt(weightText),
                Integer.parseInt(stockText),
                Integer.parseInt(priceText)
        );

        executorService.execute(() -> {
            long priceID = productsRepository.priceInsert(price);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(price.getProductID()));
                text2.setText(String.valueOf(price.getWeightKG()));
                text3.setText(String.valueOf(price.getStock()));
                text4.setText(String.valueOf(price.getTotalPrice()));
                text5.setText("Price ID: " + priceID);
            });
        });
    }
    private void getPrice() {
        executorService.execute(() -> {
            Price price = productsRepository.getPriceById(count);
            count++;

            runOnUiThread(() -> {
                if (price != null) {
                    text1.setText(String.valueOf(price.getProductID()));
                    text2.setText(String.valueOf(price.getWeightKG()));
                    text3.setText(String.valueOf(price.getStock()));
                    text4.setText(String.valueOf(price.getTotalPrice()));
                    text5.setText("Price ID: " + price.getPriceID());
                } else {
                    clearFields();
                    text1.setText("Price not found");
                }
            });
        });
    }

    // ================================================================
    // ARTICLE TEST
    // ================================================================
    private void insertArticle() {
        String name = getInput(text1);
        String description = getInput(text2);
        String category = getInput(text3);
        String pictureText = getInput(text4);

        if (name.isEmpty() || description.isEmpty() ||
                category.isEmpty() || pictureText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(pictureText)) {
            Toast.makeText(this, "Picture ID must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        Article article = new Article(
                name,
                description,
                category,
                Integer.parseInt(pictureText)
        );

        executorService.execute(() -> {
            long articleID = articleRepository.articleInsert(article);

            runOnUiThread(() -> {
                text1.setText(article.getArticleName());
                text2.setText(article.getDescription());
                text3.setText(article.getArticleCategory());
                text4.setText(String.valueOf(article.getArticlePictureID()));
                text5.setText("Article ID: " + articleID);
            });
        });
    }
    private void getArticle() {
        executorService.execute(() -> {
            Article article = articleRepository.getArticleById(count);
            count++;

            runOnUiThread(() -> {
                if (article != null) {
                    text1.setText(article.getArticleName());
                    text2.setText(article.getDescription());
                    text3.setText(article.getArticleCategory());
                    text4.setText(String.valueOf(article.getArticlePictureID()));
                    text5.setText("Article ID: " + article.getArticleID());
                } else {
                    clearFields();
                    text1.setText("Article not found");
                }
            });
        });
    }

    // ================================================================
    // CART TEST
    // ================================================================
    private void insertCart() {
        String userText = getInput(text1);
        String productText = getInput(text2);
        String priceText = getInput(text3);
        String quantityText = getInput(text4);
        String totalText = getInput(text5);

        if (userText.isEmpty() || productText.isEmpty() ||
                priceText.isEmpty() || quantityText.isEmpty() || totalText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(userText) || !isNumber(productText) ||
                !isNumber(priceText) || !isNumber(quantityText) || !isNumber(totalText)) {
            Toast.makeText(this, "All fields must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        Cart cart = new Cart(
                Integer.parseInt(userText),
                Integer.parseInt(productText),
                Integer.parseInt(priceText),
                Integer.parseInt(quantityText),
                Integer.parseInt(totalText)
        );

        executorService.execute(() -> {
            long cartID = cartRepository.cartInsert(cart);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(cart.getUserID()));
                text2.setText(String.valueOf(cart.getProductID()));
                text3.setText(String.valueOf(cart.getPriceID()));
                text4.setText(String.valueOf(cart.getQuantity()));
                text5.setText(String.valueOf(cart.getTotalPrice()));
                text6.setText("Cart ID: " + cartID);
            });
        });
    }
    private void getCart() {
        executorService.execute(() -> {
            Cart cart = cartRepository.getCartById(count);
            count++;

            runOnUiThread(() -> {
                if (cart != null) {
                    text1.setText(String.valueOf(cart.getUserID()));
                    text2.setText(String.valueOf(cart.getProductID()));
                    text3.setText(String.valueOf(cart.getPriceID()));
                    text4.setText(String.valueOf(cart.getQuantity()));
                    text5.setText(String.valueOf(cart.getTotalPrice()));
                    text6.setText("Cart ID: " + cart.getCartID());
                } else {
                    clearFields();
                    text1.setText("Cart not found");
                }
            });
        });
    }

    // ================================================================
    // ORDER TEST
    // ================================================================
    private void insertOrder() {
        String userText = getInput(text1);
        String transaction = getInput(text2);
        String status = getInput(text3);
        String totalText = getInput(text4);
        String dateText = getInput(text5);

        if (userText.isEmpty() || transaction.isEmpty() || status.isEmpty() ||
                totalText.isEmpty() || dateText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(userText) || !isNumber(totalText) || !isLongNumber(dateText)) {
            Toast.makeText(this, "Invalid numeric value", Toast.LENGTH_SHORT).show();
            return;
        }

        Order order = new Order(
                Integer.parseInt(userText),
                transaction,
                status,
                Integer.parseInt(totalText),
                Long.parseLong(dateText)
        );

        executorService.execute(() -> {
            long orderID = orderRepository.orderInsert(order);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(order.getUserID()));
                text2.setText(order.getTransactionID());
                text3.setText(order.getOrderStatus());
                text4.setText(String.valueOf(order.getTotalPrice()));
                text5.setText(String.valueOf(order.getOrderDate()));
                text6.setText("Order ID: " + orderID);
            });
        });
    }
    private void getOrder() {
        executorService.execute(() -> {
            Order order = orderRepository.getOrderById(count);
            count++;

            runOnUiThread(() -> {
                if (order != null) {
                    text1.setText(String.valueOf(order.getUserID()));
                    text2.setText(order.getTransactionID());
                    text3.setText(order.getOrderStatus());
                    text4.setText(String.valueOf(order.getTotalPrice()));
                    text5.setText(String.valueOf(order.getOrderDate()));
                    text6.setText("Order ID: " + order.getOrderID());
                } else {
                    clearFields();
                    text1.setText("Order not found");
                }
            });
        });
    }

    // ================================================================
    // ORDER ITEM TEST
    // ================================================================
    private void insertOrderItem() {
        String orderText = getInput(text1);
        String productText = getInput(text2);
        String priceText = getInput(text3);
        String quantityText = getInput(text4);
        String totalText = getInput(text5);

        if (orderText.isEmpty() || productText.isEmpty() ||
                priceText.isEmpty() || quantityText.isEmpty() || totalText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(orderText) || !isNumber(productText) ||
                !isNumber(priceText) || !isNumber(quantityText) || !isNumber(totalText)) {
            Toast.makeText(this, "All fields must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        OrderItem orderItem = new OrderItem(
                Integer.parseInt(orderText),
                Integer.parseInt(productText),
                Integer.parseInt(priceText),
                Integer.parseInt(quantityText),
                Integer.parseInt(totalText)
        );

        executorService.execute(() -> {
            long orderItemID = orderRepository.orderItemInsert(orderItem);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(orderItem.getOrderID()));
                text2.setText(String.valueOf(orderItem.getProductID()));
                text3.setText(String.valueOf(orderItem.getPriceID()));
                text4.setText(String.valueOf(orderItem.getQuantity()));
                text5.setText(String.valueOf(orderItem.getTotalPrice()));
                text6.setText("Order Item ID: " + orderItemID);
            });
        });
    }
    private void getOrderItem() {
        executorService.execute(() -> {
            OrderItem orderItem = orderRepository.getOrderItemById(count);
            count++;

            runOnUiThread(() -> {
                if (orderItem != null) {
                    text1.setText(String.valueOf(orderItem.getOrderID()));
                    text2.setText(String.valueOf(orderItem.getProductID()));
                    text3.setText(String.valueOf(orderItem.getPriceID()));
                    text4.setText(String.valueOf(orderItem.getQuantity()));
                    text5.setText(String.valueOf(orderItem.getTotalPrice()));
                    text6.setText("Order Item ID: " + orderItem.getOrderItemID());
                } else {
                    clearFields();
                    text1.setText("Order Item not found");
                }
            });
        });
    }

    // ================================================================
    // TRACK TEST
    // ================================================================
    private void insertTrack() {
        String orderText = getInput(text1);
        String location = getInput(text2);
        String description = getInput(text3);
        String dateText = getInput(text4);
        String status = getInput(text5);

        if (orderText.isEmpty() || location.isEmpty() ||
                description.isEmpty() || dateText.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(orderText) || !isLongNumber(dateText)) {
            Toast.makeText(this, "Order ID and Delivery Date must be numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        Track track = new Track(
                Integer.parseInt(orderText),
                location,
                description,
                Long.parseLong(dateText),
                status
        );

        executorService.execute(() -> {
            long trackID = orderRepository.trackInsert(track);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(track.getOrderID()));
                text2.setText(track.getDeliveryLocation());
                text3.setText(track.getDescription());
                text4.setText(String.valueOf(track.getDeliveryDate()));
                text5.setText(track.getTrackingStatus());
                text6.setText("Track ID: " + trackID);
            });
        });
    }
    private void getTrack() {
        executorService.execute(() -> {
            Track track = orderRepository.getTrackById(count);
            count++;
            runOnUiThread(() -> {
                if (track != null) {
                    text1.setText(String.valueOf(track.getOrderID()));
                    text2.setText(track.getDeliveryLocation());
                    text3.setText(track.getDescription());
                    text4.setText(String.valueOf(track.getDeliveryDate()));
                    text5.setText(track.getTrackingStatus());
                    text6.setText("Track ID: " + track.getTrackId());
                } else {
                    clearFields();
                    text1.setText("Track not found");
                }
            });
        });
    }

    // ================================================================
    // PROMO REWARD TEST
    // ================================================================
    private void insertPromo() {
        String name = getInput(text1);
        String code = getInput(text2);
        String discountText = getInput(text3);
        String condition = getInput(text4);
        String expiryText = getInput(text5);

        if (name.isEmpty() || code.isEmpty() || discountText.isEmpty() ||
                condition.isEmpty() || expiryText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(discountText) || !isLongNumber(expiryText)) {
            Toast.makeText(this, "Invalid numeric value", Toast.LENGTH_SHORT).show();
            return;
        }

        PromoReward promo = new PromoReward(
                name,
                code,
                Integer.parseInt(discountText),
                condition,
                Long.parseLong(expiryText)
        );

        executorService.execute(() -> {
            long promoID = promoRepository.promoInsert(promo);

            runOnUiThread(() -> {
                text1.setText(promo.getPromoName());
                text2.setText(promo.getPromoCode());
                text3.setText(String.valueOf(promo.getDiscount()));
                text4.setText(promo.getConditionDescription());
                text5.setText(String.valueOf(promo.getExpiryDate()));
                text6.setText("Promo ID: " + promoID);
            });
        });
    }
    private void getPromo() {
        executorService.execute(() -> {
            PromoReward promo = promoRepository.getPromoById(count);
            count++;

            runOnUiThread(() -> {
                if (promo != null) {
                    text1.setText(promo.getPromoName());
                    text2.setText(promo.getPromoCode());
                    text3.setText(String.valueOf(promo.getDiscount()));
                    text4.setText(promo.getConditionDescription());
                    text5.setText(String.valueOf(promo.getExpiryDate()));
                    text6.setText("Promo ID: " + promo.getPromoID());
                } else {
                    clearFields();
                    text1.setText("Promo not found");
                }
            });
        });
    }

    // ================================================================
    // USER PROMO TEST
    // ================================================================
    private void insertUserPromo() {
        String userText = getInput(text1);
        String promoText = getInput(text2);
        String status = getInput(text3);
        String claimText = getInput(text4);
        String useText = getInput(text5);

        if (userText.isEmpty() || promoText.isEmpty() || status.isEmpty() ||
                claimText.isEmpty() || useText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNumber(userText) || !isNumber(promoText) ||
                !isLongNumber(claimText) || !isLongNumber(useText)) {
            Toast.makeText(this, "Invalid numeric value", Toast.LENGTH_SHORT).show();
            return;
        }

        UserPromo userPromo = new UserPromo(
                Integer.parseInt(userText),
                Integer.parseInt(promoText),
                status,
                Long.parseLong(claimText),
                Long.parseLong(useText)
        );

        executorService.execute(() -> {
            promoRepository.userPromoInsert(userPromo);

            runOnUiThread(() -> {
                text1.setText(String.valueOf(userPromo.getUserID()));
                text2.setText(String.valueOf(userPromo.getPromoID()));
                text3.setText(userPromo.getStatus());
                text4.setText(String.valueOf(userPromo.getClaimDate()));
                text5.setText(String.valueOf(userPromo.getUseDate()));
                text6.setText("User Promo inserted");
            });
        });
    }
    private void getUserPromo() {
//        executorService.execute(() -> {
////            UserPromo userPromo = promoRepository.getUserPromoById(count);
//            count++;
//
//            runOnUiThread(() -> {
//                if (userPromo != null) {
//                    text1.setText(String.valueOf(userPromo.getUserID()));
//                    text2.setText(String.valueOf(userPromo.getPromoID()));
//                    text3.setText(userPromo.getStatus());
//                    text4.setText(String.valueOf(userPromo.getClaimDate()));
//                    text5.setText(String.valueOf(userPromo.getUseDate()));
//                    text6.setText("User Promo");
//                } else {
//                    clearFields();
//                    text1.setText("User Promo not found");
//                }
//            });
//        });
    }

    // ================================================================
    // CLEAR FIELDS
    // ================================================================
    private void clearFields() {

        count = 1;

        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }


    // ============================================================
    // DATA VALIDATION
    // ============================================================
    private String getInput(EditText field) {
        return field.getText().toString().trim();
    }
    private boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isLongNumber(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ============================================================
    // DATA TABLE SWITCH
    // ============================================================
    private void showDataTable() {
        count = 1;

        switch (dataCount) {
            // ====================================================
            // USER
            // ====================================================
            case 1:
                userRepository = new UserRepository(AppDatabase.getDatabase(getApplicationContext()).userDao());
                btn4.setText("User");

                text1.setHint("User Name");
                text2.setHint("User Email");
                text3.setHint("User Password");
                text4.setHint("Points");
                text5.setHint("Location");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertUser());
                btn2.setOnClickListener(v -> getUser());
                break;

            // ====================================================
            // CATEGORY
            // ====================================================
            case 2:
                productsRepository = new ProductRepository(
                        AppDatabase.getDatabase(getApplicationContext()).productDao(),
                        AppDatabase.getDatabase(getApplicationContext()).categoryDao(),
                        AppDatabase.getDatabase(getApplicationContext()).priceDao()
                );
                btn4.setText("Category");

                text1.setHint("Category Name");
                text2.setHint("Category Icon ID");
                text3.setHint("");
                text4.setHint("");
                text5.setHint("");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertCategory());
                btn2.setOnClickListener(v -> getCategory());
                break;

            // ====================================================
            // PRODUCT
            // ====================================================
            case 3:
                productsRepository = new ProductRepository(
                        AppDatabase.getDatabase(getApplicationContext()).productDao(),
                        AppDatabase.getDatabase(getApplicationContext()).categoryDao(),
                        AppDatabase.getDatabase(getApplicationContext()).priceDao()
                );
                btn4.setText("Product");

                text1.setHint("Category ID");
                text2.setHint("Product Name");
                text3.setHint("Description");
                text4.setHint("Specification");
                text5.setHint("Product Image ID");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertProduct());
                btn2.setOnClickListener(v -> getProduct());
                break;

            // ====================================================
            // PRICE
            // ====================================================
            case 4:
                productsRepository = new ProductRepository(
                        AppDatabase.getDatabase(getApplicationContext()).productDao(),
                        AppDatabase.getDatabase(getApplicationContext()).categoryDao(),
                        AppDatabase.getDatabase(getApplicationContext()).priceDao()
                );
                btn4.setText("Price");

                text1.setHint("Product ID");
                text2.setHint("Weight KG");
                text3.setHint("Stock");
                text4.setHint("Total Price");
                text5.setHint("");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertPrice());
                btn2.setOnClickListener(v -> getPrice());
                break;

            // ====================================================
            // ARTICLE
            // ====================================================
            case 5:
                articleRepository = new ArticleRepository(AppDatabase.getDatabase(getApplicationContext()).articleDao());
                btn4.setText("Article");

                text1.setHint("Article Name");
                text2.setHint("Description");
                text3.setHint("Article Category");
                text4.setHint("Article Picture ID");
                text5.setHint("");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertArticle());
                btn2.setOnClickListener(v -> getArticle());
                break;

            // ====================================================
            // CART
            // ====================================================
            case 6:
                cartRepository = new CartRepository(AppDatabase.getDatabase(getApplicationContext()).cartDao());
                btn4.setText("Cart");

                text1.setHint("User ID");
                text2.setHint("Product ID");
                text3.setHint("Price ID");
                text4.setHint("Quantity");
                text5.setHint("Total Price");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertCart());
                btn2.setOnClickListener(v -> getCart());
                break;

            // ====================================================
            // ORDER
            // ====================================================
            case 7:
                orderRepository = new OrderRepository(
                        AppDatabase.getDatabase(getApplicationContext()).orderDao(),
                        AppDatabase.getDatabase(getApplicationContext()).orderItemDao(),
                        AppDatabase.getDatabase(getApplicationContext()).trackDao()
                );
                btn4.setText("Order");

                text1.setHint("User ID");
                text2.setHint("Transaction ID");
                text3.setHint("Order Status");
                text4.setHint("Total Price");
                text5.setHint("Order Date");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertOrder());
                btn2.setOnClickListener(v -> getOrder());
                break;

            // ====================================================
            // ORDER ITEM
            // ====================================================
            case 8:
                orderRepository = new OrderRepository(
                        AppDatabase.getDatabase(getApplicationContext()).orderDao(),
                        AppDatabase.getDatabase(getApplicationContext()).orderItemDao(),
                        AppDatabase.getDatabase(getApplicationContext()).trackDao()
                );
                btn4.setText("Order Item");

                text1.setHint("Order ID");
                text2.setHint("Product ID");
                text3.setHint("Price ID");
                text4.setHint("Quantity");
                text5.setHint("Total Price");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertOrderItem());
                btn2.setOnClickListener(v -> getOrderItem());
                break;

            // ====================================================
            // TRACK
            // ====================================================
            case 9:
                orderRepository = new OrderRepository(
                        AppDatabase.getDatabase(getApplicationContext()).orderDao(),
                        AppDatabase.getDatabase(getApplicationContext()).orderItemDao(),
                        AppDatabase.getDatabase(getApplicationContext()).trackDao()
                );
                btn4.setText("Track");

                text1.setHint("Order ID");
                text2.setHint("Delivery Location");
                text3.setHint("Description");
                text4.setHint("Delivery Date");
                text5.setHint("Tracking Status");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertTrack());
                btn2.setOnClickListener(v -> getTrack());
                break;

            // ====================================================
            // PROMO REWARD
            // ====================================================
            case 10:
                promoRepository = new PromoRepository(
                        AppDatabase.getDatabase(getApplicationContext()).promoRewardDao(),
                        AppDatabase.getDatabase(getApplicationContext()).userPromoDao()
                );
                btn4.setText("Promo Reward");

                text1.setHint("Promo Name");
                text2.setHint("Promo Code");
                text3.setHint("Discount");
                text4.setHint("Condition Description");
                text5.setHint("Expiry Date");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertPromo());
                btn2.setOnClickListener(v -> getPromo());
                break;

            // ====================================================
            // USER PROMO
            // ====================================================
            case 11:
                promoRepository = new PromoRepository(
                        AppDatabase.getDatabase(getApplicationContext()).promoRewardDao(),
                        AppDatabase.getDatabase(getApplicationContext()).userPromoDao()
                );
                btn4.setText("User Promo");

                text1.setHint("User ID");
                text2.setHint("Promo ID");
                text3.setHint("Status");
                text4.setHint("Claim Date");
                text5.setHint("Use Date");
                text6.setHint("");

                btn1.setOnClickListener(v -> insertUserPromo());
                btn2.setOnClickListener(v -> getUserPromo());
                break;

            // ====================================================
            // LOOP BACK TO USER
            // ====================================================
            case 12:
                dataCount = 1;
                showDataTable();
                break;
        }
    }
    private void changeDataTable() {
        dataCount++;
        if (dataCount > 12) {dataCount = 1;}
        count = 1;
        clearFields();
        showDataTable();
    }
}