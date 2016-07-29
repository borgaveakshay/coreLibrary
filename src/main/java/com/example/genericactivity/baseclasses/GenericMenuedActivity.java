package com.example.genericactivity.baseclasses;

import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.genericactivity.R;

public abstract class GenericMenuedActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    int menuResourceId;
    public void onCreate(Bundle savedInstanceState, int resourceId, boolean enableBackButton, boolean enableNavDrawer, int titleResorceId ){
        setContentView(resourceId);
        setTitle(titleResorceId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(enableBackButton) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onBackButtonPressed();
                }
            });        }

        if(enableNavDrawer){

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(getMenuResourceId(), menu);
        return true;
    }


    public void onBackButtonPressed(){
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int getMenuResourceId() {
        return menuResourceId;
    }

    public void setMenuResourceId(int menuResourceId) {
        this.menuResourceId = menuResourceId;
    }

    protected void sync(){

    }
}
