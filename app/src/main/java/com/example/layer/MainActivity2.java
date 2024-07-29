package com.example.layer;


import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.opt_Home) {
                loadFragment(new AFragment());
            } else if (id == R.id.opt_menu) {
                loadFragment(new Menu_Fragment());
            } else if (id == R.id.opt_login) {
                loadFragment(new loginFragment());
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Create an OnBackPressedCallback to handle back presses
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    FragmentManager fm = getSupportFragmentManager();
                    if (fm.getBackStackEntryCount() > 0) {
                        fm.popBackStack();
                    } else {
                        finish();
                    }
                }
            }
        };

        // Add the callback to the dispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);

        // Initially load the Home fragment
        loadFragment(new AFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null); // Add the transaction to the back stack
        ft.commit();
    }
}
