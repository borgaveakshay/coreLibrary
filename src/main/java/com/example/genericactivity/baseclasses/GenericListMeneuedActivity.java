package com.example.genericactivity.baseclasses;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 08-03-2016.
 */
public abstract class GenericListMeneuedActivity<T> extends GenericMenuedActivity{

    protected ArrayList<T> dataList = null;

    public abstract void intialize();

    @Override
    protected void onResume() {
        super.onResume();
          intialize();
    }
}
