package FragmentBaseClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import Interfaces.FragmentCallBacks;

import com.example.genericactivity.BaseActivities.GenericFragmentListMenuedActivity;
import com.example.genericactivity.BaseActivities.GenericFragmentMenuedActivity;

/**
 * Created by Akshay.Borgave on 07-03-2016.
 */
public abstract class BaseFragmentManager extends Fragment implements FragmentCallBacks {

    protected Context context;
    protected GenericFragmentListMenuedActivity appListMenuedActivity;
    protected GenericFragmentMenuedActivity appMenuedActivity;
    ProgressBar progressView;
    public View contentView;
    public int progressBarResourceId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return onViewCreated(inflater, container, savedInstanceState);
    }

    public GenericFragmentListMenuedActivity getAppListMenuedActivity() {
        return appListMenuedActivity;
    }

    public GenericFragmentMenuedActivity getAppMenuedActivity() {
        return appMenuedActivity;
    }

    @Override
    public void showProgressIndicator() {

        try {
            if (appListMenuedActivity != null) {

                appListMenuedActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                        progressView.setVisibility(View.VISIBLE);
                    }
                });
            } else if (appMenuedActivity != null) ;
            appMenuedActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                    progressView.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void hideProgressIndicator() {

        if (progressView != null) {
            if (appListMenuedActivity != null) {
                appListMenuedActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                        progressView.setVisibility(View.INVISIBLE);
                    }
                });
            } else if (appMenuedActivity != null) ;
            appMenuedActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                    progressView.setVisibility(View.INVISIBLE);
                }
            });

        }
    }

    @Override
    public void handleMenuClick(MenuItem menuItem) {

    }

    @Override
    public void onBackPressed() {

    }

    public void setMenuResource(int menuResourceId) {
        if (appListMenuedActivity != null)
            appListMenuedActivity.setMenuResourceId(menuResourceId);
        else if (appMenuedActivity != null)
            appMenuedActivity.setMenuResourceId(menuResourceId);
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

    /**
     * This Method should be call explicitly after view is initialized in onViewCreated method so as to call all abstract initialization methods.
     * Which will set all the resources required.
     */
    public void initFragment() {

        setRetainInstance(true);
        contentView = setFragmentView();
        appListMenuedActivity = setMenuedListActivity();
        appMenuedActivity = setMenuedActivity();
        if (appListMenuedActivity != null) {
            appListMenuedActivity.setMenuResourceId(setMenuResourceId());
            appListMenuedActivity.setCallBacks(this);
        } else if (appMenuedActivity != null) {
            appMenuedActivity.setCallBacks(this);
            appListMenuedActivity.setMenuResourceId(setMenuResourceId());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (appListMenuedActivity != null)
            appListMenuedActivity.setCallBacks(this);
        else if (appMenuedActivity != null)
            appMenuedActivity.setCallBacks(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (appListMenuedActivity != null)
            appListMenuedActivity.setCallBacks(this);
        else if (appMenuedActivity != null)
            appMenuedActivity.setCallBacks(this);
    }

    /**
     * @return Sets Toolbar Menu according to returned resource Id;
     */
    public abstract int setMenuResourceId();

    /**
     * @return It will set Fragment view in base class so as to fetch view components if required.
     */
    public abstract View setFragmentView();

    /**
     * @return Sets the @GenericFragmentListMenuedActivity instance so that it can call methods inside Activity if required by fragment.
     */
    public abstract GenericFragmentMenuedActivity setMenuedActivity();

    /**
     * @return Sets the @GenericFragmentMenuedActivity instance so that it can call methods inside Activity if required by fragment.
     */
    public abstract GenericFragmentListMenuedActivity setMenuedListActivity();

    /**
     * @return Initialise fragment view components here.
     */
    public abstract View onViewCreated(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


}
