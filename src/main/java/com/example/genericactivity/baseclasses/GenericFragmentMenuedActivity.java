package com.example.genericactivity.baseclasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.genericactivity.R;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Akshay.Borgave on 31-03-2016.
 */
public class GenericFragmentMenuedActivity < T extends BaseFragmentManager > extends GenericMenuedActivity {

    FragmentCallBacks callBacks;
    int menuResourceId;
    public T fragment;
    Class<T> tClass;

    public void onCreate(Bundle savedInstanceState, Class<T> classTemplate, int resourceId, boolean enableBackButton, boolean enableNavDrawer, int titleResorceId) {
        super.onCreate(savedInstanceState, resourceId, enableBackButton, enableNavDrawer, titleResorceId);
        try {

            tClass =  classTemplate;
            initializeFragment();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(getMenuResourceId(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        callBacks.handleMenuClick(item);
        return true;
    }

    public FragmentCallBacks getCallBacks() {
        return callBacks;
    }

    public void setCallBacks(FragmentCallBacks callBacks) {
        this.callBacks = callBacks;
    }

    public int getMenuResourceId() {
        return menuResourceId;
    }

    public void setMenuResourceId(int menuResourceId) {
        this.menuResourceId = menuResourceId;
    }
}
