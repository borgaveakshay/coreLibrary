package com.example.genericactivity.BaseActivities;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;

import com.example.genericactivity.R;

import UtilityBaseClasses.CustomNavDrawer;
import UtilityBaseClasses.MyDrawerLayout;

public abstract class GenericMenuedActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
   protected Toolbar toolbar;
   int menuResourceId;
   protected boolean isNavDrawerEnabled;
   protected int navigationDrawerResourceId;
   protected int navigationViewResourceId;
   protected MyDrawerLayout drawer;
   protected NavigationView navigationView;
   protected boolean isToolBarHidden;
   protected boolean isScrollBarHideOnScroll;

    public void onCreate(Bundle savedInstanceState, int resourceId, boolean enableBackButton, boolean enableNavDrawer, int titleResorceId ){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resourceId);
        setTitle(titleResorceId);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.titleBarColor));
        setSupportActionBar(toolbar);
        isNavDrawerEnabled = enableNavDrawer;
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
         drawer = (MyDrawerLayout) findViewById(getNavigationViewResourceId());
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

    public void enableBackButton()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonPressed();
            }
        });

    }

    public void enableNavigationDrawer(){

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MyDrawerLayout drawer = (MyDrawerLayout) findViewById(getNavigationDrawerResourceId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(getNavigationViewResourceId());
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void replaceToolBarMenu(int menuResourceId){
        toolbar.getMenu().clear();
        toolbar.inflateMenu(menuResourceId);
    }

    public void disableBackButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    public void setToolbarTitleResource(int titleResourceId){
        getSupportActionBar().setTitle(getResources().getString(titleResourceId));
    }

    public void hideToolBar(){
        getSupportActionBar().hide();
        setToolBarHidden(true);
    }

    public void showToolBar(){

        getSupportActionBar().show();
        setToolBarHidden(false);
    }

    public boolean isToolBarHidden() {
        return isToolBarHidden;
    }

    public void setToolBarHidden(boolean toolBarHidden) {
        isToolBarHidden = toolBarHidden;
    }
    public void clearToolBarMenu(){
        toolbar.getMenu().clear();
    }

    public boolean isNavDrawerEnabled() {
        return isNavDrawerEnabled;
    }

    public void setNavDrawerEnabled(boolean navDrawerEnabled) {
        isNavDrawerEnabled = navDrawerEnabled;
    }
    public int getNavigationDrawerResourceId() {
        return navigationDrawerResourceId;
    }

    public void setNavigationDrawerResourceId(int navigationDrawerResourceId) {
        this.navigationDrawerResourceId = navigationDrawerResourceId;
    }

    public int getNavigationViewResourceId() {
        return navigationViewResourceId;
    }

    public void setNavigationViewResourceId(int navigationViewResourceId) {
        this.navigationViewResourceId = navigationViewResourceId;
    }
    public boolean isScrollBarHideOnScroll() {
        return isScrollBarHideOnScroll;
    }

    public void setScrollBarHideOnScroll(boolean scrollBarHideOnScroll) {
        isScrollBarHideOnScroll = scrollBarHideOnScroll;
    }


}
