package com.example.wholeman.taewonleemarket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment1;
    private Fragment mFragment2;
    private Fragment mFragment3;

    FragmentTransaction ft;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragment1 = new HomeFragment();
                    switchFragment(mFragment1);
                    return true;
                case R.id.navigation_dashboard:
                    mFragment2 = new ChatRoomListFragment();
                    switchFragment(mFragment2);
                    return true;
                case R.id.navigation_notifications:
                    mFragment3 = new NotificationsFragment();
                    switchFragment(mFragment3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment()).commit();

    }

    private void switchFragment(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.commit();
    }

}
