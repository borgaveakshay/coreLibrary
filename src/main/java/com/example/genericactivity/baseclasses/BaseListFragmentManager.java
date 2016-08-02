package com.example.genericactivity.baseclasses;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public class BaseListFragmentManager<T> extends BaseFragmentManager implements MultiSelectEnableListener<T>{

    ArrayList<T> dataList;

    @Override
    public void multiSelectPerfromed(ArrayList<T> dataList) {
        getAppActivity().multiSelectPerfromed(dataList);
    }
}
