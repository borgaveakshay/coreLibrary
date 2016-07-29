package com.example.genericactivity.baseclasses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.genericactivity.R;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

/**
 * Created by Akshay.Borgave on 07-03-2016.
 */
public class BaseFragmentManager extends Fragment implements FragmentCallBacks {

    protected Context context;
    protected GenericFragmentMenuedActivity appActivity;
    ProgressBar progressView;
    public View contentView;
    public int progressBarResourceId;

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AppCompatActivity getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(GenericFragmentMenuedActivity appActivity) {
        this.appActivity = appActivity;
    }

    @Override
    public void showProgressIndicator() {
        progressView = (ProgressBar)  contentView.findViewById(getProgressBarResourceId());
        progressView.setVisibility(View.VISIBLE);

    }
    @Override
    public void hideProgressIndicator() {

        if (progressView != null) {

            progressView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void handleMenuClick(MenuItem menuItem) {

    }

    public void setMenuResource(int menuResourceId) {
        appActivity.setMenuResourceId(menuResourceId);
    }

    public int getProgressBarResourceId() {
        return progressBarResourceId;
    }

    public void setProgressBarResourceId(int progressBarResourceId) {
        this.progressBarResourceId = progressBarResourceId;
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public void initFragment() {

        setAppActivity((GenericFragmentMenuedActivity) getActivity());
    }

}
