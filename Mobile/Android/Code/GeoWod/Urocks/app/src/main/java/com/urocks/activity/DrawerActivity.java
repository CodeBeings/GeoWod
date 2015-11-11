package com.urocks.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sony on 17-10-2015.
 */
public class DrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView listView;
    private ImageView mToolbar;
    private TextView mTile;
    Fragment fragment;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_acitvity);
        String [] values = new String[]{"Home","Challenges","LeaderBoard","EarnPoints","Profile","Logout"};
        ListViewAdapter adapter = new ListViewAdapter(this,R.layout.home_drawer,values);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (ImageView) findViewById(R.id.toolbar_top_left_image);
        listView = (ListView) findViewById(R.id.listview);
        mTile = (TextView) findViewById(R.id.textview_title);
        listView.setAdapter(adapter);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,null, R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        listView.setOnItemClickListener(this);
        mToolbar.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        closeDrawers();
        switch (position){
            case 0:
                Toast.makeText(getApplicationContext(), "home Selected", Toast.LENGTH_SHORT).show();
                setTitle("Home");
                fragment = new FragmentLocation();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;

            case 1:
                Toast.makeText(getApplicationContext(), "challenges Selected", Toast.LENGTH_SHORT).show();
                setTitle("Challenges");
               fragment = new Challenges_fragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;

            case 2:
                Toast.makeText(getApplicationContext(), "leaderboard Selected", Toast.LENGTH_SHORT).show();
                setTitle("LeaderBoard");
                break;

            case 3:
                setTitle("EarnPoints");
                Toast.makeText(getApplicationContext(), "earnpoints Selected", Toast.LENGTH_SHORT).show();
                break;

            case 4:
                setTitle("Profile");
                fragment = new ContentFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;
            case 5:
                setTitle("Logout");
                Toast.makeText(getApplicationContext(), "APP Close", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    private void closeDrawers()
    {
        if ( mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
           mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.toolbar_top_left_image:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if ( mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    private void setTitle(String title)
    {
        mTile.setText(title);
    }
}
