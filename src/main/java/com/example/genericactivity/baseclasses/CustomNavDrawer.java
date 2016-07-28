package com.example.genericactivity.baseclasses;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextMenu;

import com.example.genericactivity.R;

/**
 * Created by Akshay.Borgave on 08-06-2016.
 */
public class CustomNavDrawer extends android.support.design.widget.NavigationView {

    int drawerMenuResourceId;
    Context context;

    public CustomNavDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNavDrawer);
        final int count = typedArray.getIndexCount();

        for (int i =0 ;i < count; i ++){

            int attr = typedArray.getIndex(i);


            if( typedArray.getIndex(i) == R.styleable.CustomNavDrawer_custom_menu){

                drawerMenuResourceId = typedArray.getIndex(attr);
            }

        }
        typedArray.recycle();
    }

    @Override
    public void inflateMenu(int resId) {
        super.inflateMenu(drawerMenuResourceId);
    }
}
