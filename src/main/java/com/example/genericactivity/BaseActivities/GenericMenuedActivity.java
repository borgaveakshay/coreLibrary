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
   protected boolean isToolBarHideOnScroll;

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

    /**
     * Default On Back Press behaviour provided for activity.
     */
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

    /**
     *
     * @return menuResourceId
     * It returns the menu resource identifier for the Activity.
     *
     */
    public int getMenuResourceId() {
        return menuResourceId;
    }

    /**
     *
     * @return menuResourceId
     * It sets menu resource identifier for Activity.
     *
     */
    public void setMenuResourceId(int menuResourceId) {
        this.menuResourceId = menuResourceId;
    }

    protected void sync(){

    }

    /**
     * Calling this method will display back button on toolbar.
     */
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

    /**
     * Calling to this method will enable navigation drawer for the activity.\n
     *
     * Note: before calling this method you have to set Navigation Drawer resource by calling @setNavigationDrawerResourceId and set Navigation view by calling @setNavigationViewResourceId.
     */
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

    /**
     *
     * @param menuResourceId
     * Calling this method wil replace the menu with the given menu resource identifier.
     */
    public void replaceToolBarMenu(int menuResourceId){
        toolbar.getMenu().clear();
        toolbar.inflateMenu(menuResourceId);
    }

    /**
     * Calling this button will disable the back button from toolbar.
     */
    public void disableBackButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    /**
     *
     * @param titleResourceId
     * Calling this method will cause changing the title for the Toolbar. Need to provide string resource identifier.
     */
    public void setToolbarTitleResource(int titleResourceId){
        getSupportActionBar().setTitle(getResources().getString(titleResourceId));
    }

    /**
     * Calling this method will hide the toolbar.
     */
    public void hideToolBar(){
        getSupportActionBar().hide();
        setToolBarHidden(true);
    }

    /**
     * Calling this method will show toolbar.
     */

    public void showToolBar(){

        getSupportActionBar().show();
        setToolBarHidden(false);
    }

    /**
     *
     * @return isToolBarHidden
     * It will notify whether the toolbar is hidden or not.
     */
    public boolean isToolBarHidden() {
        return isToolBarHidden;
    }

    /**
     *
     * @param toolBarHidden
     * Call to notify whether toolbar is explicitly hidden by calling @hideToolBar method.
     */

    public void setToolBarHidden(boolean toolBarHidden) {
        isToolBarHidden = toolBarHidden;
    }

    /**
     * Can call this method to clear menu for Activity.
     */
    public void clearToolBarMenu(){
        toolbar.getMenu().clear();
    }

    /**
     *
     * @return isNavDrawerEnabled
     *  It will notify whether Navigation Drawer is enabled or not.
     */
    public boolean isNavDrawerEnabled() {
        return isNavDrawerEnabled;
    }

    /**
     *
     * @return isNavDrawerEnabled
     *  It will enable Navigation Drawer for Activity.
     */
    public void setNavDrawerEnabled(boolean navDrawerEnabled) {
        isNavDrawerEnabled = navDrawerEnabled;
    }

    /**
     *
     * @return navigationDrawerResourceId
     *
     *  It will return Navigation Drawer resource identifier from layout.
     */
    public int getNavigationDrawerResourceId() {
        return navigationDrawerResourceId;
    }

    /**
     *
     * @return navigationDrawerResourceId
     *
     *  It will set Navigation Drawer resource identifier from layout.
     */
    public void setNavigationDrawerResourceId(int navigationDrawerResourceId) {
        this.navigationDrawerResourceId = navigationDrawerResourceId;
    }

    /**
     *
     * @return navigationDrawerResourceId
     *
     *  It will return Navigation View resource identifier from layout.
     */
    public int getNavigationViewResourceId() {
        return navigationViewResourceId;
    }

    /**
     *
     * @return navigationDrawerResourceId
     *
     *  It will set Navigation View resource identifier from layout.
     */
    public void setNavigationViewResourceId(int navigationViewResourceId) {
        this.navigationViewResourceId = navigationViewResourceId;
    }

    /**
     *
     * @return isScrollBarHideOnScroll
     * It will notify whether tool bar will be hidden while scrolling.
     */
    public boolean isToolBarHideOnScroll() {
        return isToolBarHideOnScroll;
    }
    /**
     *
     * @return isScrollBarHideOnScroll
     * It will set whether to hide tool bar while scrolling.
     */
    public void setToolBarHideOnScroll(boolean toolBarHideOnScroll) {
        isToolBarHideOnScroll = toolBarHideOnScroll;
    }


}
