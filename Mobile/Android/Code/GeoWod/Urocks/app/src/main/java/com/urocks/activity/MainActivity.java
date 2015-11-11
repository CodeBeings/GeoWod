package com.urocks.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new Camera_Fragment());
        fragmentTransaction.commit();*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
       // toolbar.setNavigationIcon(R.drawable.ic_facebook);
        toolbar.setTitleTextColor(Color.WHITE);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                menuItem.setChecked(true);

                Fragment fragment;
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                switch (menuItem.getItemId()) {

                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "home Selected", Toast.LENGTH_SHORT).show();
                       /* getSupportActionBar().setTitle("Home");
                        fragment = new Camera_Fragment();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();*/
                        return true;

                    case R.id.challenges:
                        Toast.makeText(getApplicationContext(), "challenges Selected", Toast.LENGTH_SHORT).show();
                        getSupportActionBar().setTitle("Challenges");
                        fragment = new Challenges_fragment();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.leaderboard:
                        Toast.makeText(getApplicationContext(), "leaderboard Selected", Toast.LENGTH_SHORT).show();
                        getSupportActionBar().setTitle("Leader Board");
                        return true;
                    case R.id.earnpoints:

                        getSupportActionBar().setTitle("Earn Points");
                        Toast.makeText(getApplicationContext(), "earnpoints Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.profile:
                        getSupportActionBar().setTitle("Profile");
                        fragment = new ContentFragment();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.logout:

                        Toast.makeText(getApplicationContext(), "APP Close", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
         drawerLayout.closeDrawers();
    }
}
