package com.rudy.utilityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.rudy.utilityapp.databinding.ActivityMainBinding;
import com.rudy.utilityapp.fragments.Dashboard;
import com.rudy.utilityapp.fragments.Info;
import com.rudy.utilityapp.fragments.Settings;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding biding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        biding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
        replaceFragment(new Dashboard());

        biding.navigationBottom.setOnItemSelectedListener(item -> {

            //declare bottomNavigation to make it disappear when bottom has been clicked
            //BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBottom);
            //FrameLayout frame = findViewById(R.id.fragment_container);
            switch (item.getItemId()){
                case R.id.item_menu_dashboard:
                    //makes bottomNavBar disappear when click
                    //bottomNavigationView.findViewById(R.id.navigationBottom).setVisibility(View.GONE);
                    //implements the Fragment
                    replaceFragment(new Dashboard());
                    break;

                case R.id.item_menu_info:
                    replaceFragment(new Info());
                    break;

                case R.id.item_menu_settings:
                    replaceFragment(new Settings());
                    break;
            }

            return true;
        });

    }

    public  void replaceFragment(Fragment fragment){
        //make a fragment run

//        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();



    }

}