package com.example.genericactivity.baseclasses;

import android.os.Bundle;

import com.example.genericactivity.R;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public  abstract class GenericFragmentListMenuedActivity<T extends BaseListFragmentManager,Z> extends GenericFragmentMenuedActivity<T> implements MultiSelectEnableListener<Z> {

    ArrayList<Z> dataList;
    boolean listMultiselectEnableInd;
    int defaultTitleResourceId;
    public void onCreate(Bundle savedInstanceState, Class<T> classTemplate, int resourceId, boolean enableBackButton, boolean enableNavDrawer, int titleResorceId, boolean listMultiselectInd) {
        super.onCreate(savedInstanceState, classTemplate, resourceId,enableBackButton,enableNavDrawer, titleResorceId);
        listMultiselectEnableInd = listMultiselectInd;
        defaultTitleResourceId = titleResorceId;
    }


    @Override
    public void multiSelectPerfromed(ArrayList<Z> dataList) {

        if(dataList.size() == 1){
            replaceToolBarMenu(R.menu.multi_select_menu);
            enableBackButton();
        }
        else
        if(dataList.size() == 0 || dataList == null){
            setDefaultToolBarConfig();
        }
        else {
            toolbar.setTitle(dataList.size() + " Selected");
        }
    }

    @Override
    public void onBackButtonPressed() {
        if(listMultiselectEnableInd){
            setDefaultToolBarConfig();
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
        setToolbarTitle(defaultTitleResourceId);

    }
}
