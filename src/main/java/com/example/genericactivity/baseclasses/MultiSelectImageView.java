package com.example.genericactivity.baseclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public class MultiSelectImageView extends ImageView {

    Bitmap previousImageBitmap;

    public MultiSelectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        setPreviousImageBitmap(bm);
    }

    public void setPreviousBitMap(){
        setImageBitmap(getPreviousImageBitmap());
    }

    public Bitmap getPreviousImageBitmap() {
        return previousImageBitmap;
    }

    public void setPreviousImageBitmap(Bitmap previousImageBitmap) {
        this.previousImageBitmap = previousImageBitmap;
    }

}
