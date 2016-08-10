package com.example.genericactivity.BaseActivities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.genericactivity.R;

import FragmentBaseClasses.BaseFragmentManager;
import Interfaces.FragmentCallBacks;
import UtilityBaseClasses.MyDrawerLayout;

/**
 * Created by Akshay.Borgave on 31-03-2016.
 */
public abstract class GenericFragmentMenuedActivity < T extends BaseFragmentManager> extends GenericMenuedActivity {

    protected FragmentCallBacks callBacks;
    public T fragment;
    protected Class<T> tClass;


    public void onCreate(Bundle savedInstanceState, Class<T> classTemplate, int resourceId, boolean enableBackButton, boolean enableNavDrawer, int titleResorceId) {

        try {
            super.onCreate(savedInstanceState,resourceId,enableBackButton,enableNavDrawer,titleResorceId);
            tClass =  classTemplate;
            if(savedInstanceState == null)
                initializeFragment();


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     *
     * It will initialize fragmenttemplatee provided in onCreate method.
     */
    private void initializeFragment( ) throws IllegalAccessException, InstantiationException {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = tClass.newInstance();
        callBacks = (FragmentCallBacks) fragment;

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.frameLayout, fragment);
        //transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
    public void enableNavigationDrawer(){

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MyDrawerLayout drawer = (MyDrawerLayout) fragment.contentView.findViewById(getNavigationDrawerResourceId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) fragment.contentView.findViewById(getNavigationViewResourceId());
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        callBacks.handleMenuClick(item);
        return true;
    }

    /**
     *
     * @return FragmentCallBacks
     * It will return the instance of fargment class that is implementing @FragmentCallBacks interface.
     */
    public FragmentCallBacks getCallBacks() {
        return callBacks;
    }

    /**
     *
     * @return FragmentCallBacks
     * It will set the instance of fargment class that is implementing @FragmentCallBacks interface.
     */
    public void setCallBacks(FragmentCallBacks callBacks) {
        this.callBacks = callBacks;
    }


}
