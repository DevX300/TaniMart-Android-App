package com.washeed.tanimart.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.washeed.tanimart.fragments.CartFragment;
import com.washeed.tanimart.fragments.CategoryFragment;
import com.washeed.tanimart.fragments.HomeFragment;
import com.washeed.tanimart.fragments.MyOrderFragment;
import com.washeed.tanimart.fragments.ProfileFragment;
import com.washeed.tanimart.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_bar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, -1000, systemBars.right, -1080);
            return insets;
        });

        //-----------------------------------main page navbar set--------------------------------------------------------
        navBar = findViewById(R.id.nav_bar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment()).commit();
        }
        navBar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment()).commit();
                return true;
            } else if (id == R.id.nav_category) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CategoryFragment()).commit();
                return true;
            }else if (id == R.id.nav_cart) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CartFragment()).commit();
                return true;
            }else if (id == R.id.nav_my_order) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new MyOrderFragment()).commit();
                return true;
            }else if (id == R.id.nav_profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new ProfileFragment()).commit();
                return true;
            }
            return false;
        });
        handleCartNavigationIntent(getIntent()); //call the intent from any activity to main activity and receives it
    }
    private void handleCartNavigationIntent(Intent intent) {  //this navigation only handles the cart fragment
        if (intent != null && "cart".equals(intent.getStringExtra("open_fragment"))) {
            navBar.setSelectedItemId(R.id.nav_cart);
            intent.removeExtra("open_fragment");
        }
    }
    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleCartNavigationIntent(intent);
    }
}
//for main home activity