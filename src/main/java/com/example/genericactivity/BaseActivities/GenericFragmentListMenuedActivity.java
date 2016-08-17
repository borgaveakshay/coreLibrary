package com.example.genericactivity.BaseActivities;

import android.os.Bundle;

import com.example.genericactivity.R;

import java.util.ArrayList;

import FragmentBaseClasses.BaseListFragmentManager;
import Interfaces.MultiSelectEnableListener;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public  abstract class GenericFragmentListMenuedActivity < T extends BaseListFragmentManager, Z > extends GenericFragmentMenuedActivity<T> implements MultiSelectEnableListener<Z> {

    ArrayList<Z> dataList;
    int defaultTitleResourceId;

    public void onCreate(Bundle savedInstanceState, Class<T> classTemplate, int resourceId,  int titleResorceId) {
        super.onCreate(savedInstanceState, classTemplate, resourceId, titleResorceId);

        defaultTitleResourceId = titleResorceId;
    }

    /**
     *
     * @param dataList
     * Method gets called for @BaseListFragmentManager
     */
    @Override
    public void multiSelectPerfromed(ArrayList<Z> dataList) {

        if(dataList.size() == 1){
            replaceToolBarMenu(R.menu.multi_select_menu);
            enableBackButton();
            toolbar.setTitle(dataList.size() + " Selected");
        }
        else if(dataList.size() == 0 || dataList == null){
            setDefaultToolBarConfig();
        }
        else {
            toolbar.setTitle(dataList.size() + " Selected");
        }
    }

    @Override
    public void onBackButtonPressed() {
        if(isListMultiselectEnableInd()){
            setDefaultToolBarConfig();
            callBacks.onBackPressed();
        }
        else
        {
            super.onBackButtonPressed();
        }
    }

    public void setDefaultToolBarConfig()
    {
        clearToolBarMenu();
        replaceToolBarMenu(getMenuResourceId());
        disableBackButton();
        setToolbarTitleResource(defaultTitleResourceId);
        if(isNavDrawerEnabled()){
            enableNavigationDrawer();
        }
    }

    /**
     *
     * @return boolean
     * Implement this method to notify the list should provide mechanism for multiselect.
     * Note: For this to work we have to implement @BaseMultiSelectRecycleView as adapter.
     */
    public abstract boolean isListMultiselectEnableInd();


}
