package com.example.coronaupdatetoday.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.coronaupdatetoday.Fragment.AffectedCountriesFragment;
import com.example.coronaupdatetoday.Fragment.GlobalDataFragment;
import com.example.coronaupdatetoday.Fragment.OurCountryFragment;
import com.example.coronaupdatetoday.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    private GlobalDataFragment globalDataFragment;
    private OurCountryFragment ourCountryFragment;
    private AffectedCountriesFragment affectedCountriesFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        frameLayout = findViewById( R.id.fragmentContiner);
        bottomNavigationView = findViewById( R.id.bottomNavigationView);

        globalDataFragment = new GlobalDataFragment();
        ourCountryFragment = new OurCountryFragment();
        affectedCountriesFragment = new AffectedCountriesFragment();

        ((AppCompatActivity) MainActivity.this).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new OurCountryFragment()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.our_country:
                        InitialFragment(ourCountryFragment);
                        return true;

                    case R.id.global:
                        InitialFragment(globalDataFragment);
                        return true;


                    case R.id.affected_countries:
                        InitialFragment(affectedCountriesFragment);
                        return true;


                }
                return false;

            }
        });

    }

    public void  InitialFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContiner,fragment);
        fragmentTransaction.commit();
    }
}
