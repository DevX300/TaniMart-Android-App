package com.washeed.tanimart.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.washeed.tanimart.Fragments.CartFragment;
import com.washeed.tanimart.Fragments.CategoryFragment;
import com.washeed.tanimart.Fragments.HomeFragment;
import com.washeed.tanimart.Fragments.MyOrderFragment;
import com.washeed.tanimart.Fragments.ProfileFragment;
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


        //main page navbar set
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





    }




}
//for main home activity