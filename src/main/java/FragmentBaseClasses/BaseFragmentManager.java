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
import WebService.BaseWebServiceCall;

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
    static public View contentView;
    public int progressBarResourceId;
    protected static int menuResourceId;
    protected BaseWebServiceCall webServiceCall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return onViewCreated(inflater, container, savedInstanceState);
    }

    /**
     * It will show the progress bar if provided in xml layout file after starting the network call
     */
    @Override
    public void showProgressIndicator() {

        try {
            if (appListMenuedActivity != null) {

                appListMenuedActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                        if(progressView != null) {
                            progressView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            } else if (appMenuedActivity != null) ;
            appMenuedActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                    if(progressView != null) {
                        progressView.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    /**
     * It will hide the progress bar if provided in xml layout file after finishing the network call
     */
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

    /**
     *
     * @param menuItem
     * Method gets called when any menu item is clicked.
     */
    @Override
    public void handleMenuClick(MenuItem menuItem) {

    }

    /**
     * Method gets called when user clicks back button.
     */
    @Override
    public void onBackPressed() {

    }

    public void setMenuResource(int menuResourceId) {
        if (appListMenuedActivity != null)
            appListMenuedActivity.setMenuResourceId(menuResourceId);
        else if (appMenuedActivity != null)
            appMenuedActivity.setMenuResourceId(menuResourceId);
    }

    /**
     *
     * @return progressBarResourceId
     * It returns Progress Bar resource id from layout if set o/w 0.
     */
    public int getProgressBarResourceId() {
        return progressBarResourceId;
    }

    /**
     *
     * @return contentView
     * This will return view set for the Fragment.
     */
    public View getContentView() {
        return contentView;
    }

    /**
     *
     * @return appListMenuedActivity
     * Returns ListActivity instance
     */
    public GenericFragmentListMenuedActivity getAppListMenuedActivity() {
        return appListMenuedActivity;
    }

    /**
     *
     * @return appMenuedActivity
     * Returns Menued Activity Instance.
     */
    public GenericFragmentMenuedActivity getAppMenuedActivity() {
        return appMenuedActivity;
    }
    /**
     * This Method should be called explicitly after view is initialized in onViewCreated method so as to call all abstract initialization methods.
     * Which will set all the resources required.
     */
    public void initFragment() {

        setRetainInstance(true);

        contentView = setFragmentView() != null ? setFragmentView() : contentView;
        progressBarResourceId = setProgressBarResourceId();
        appListMenuedActivity = setMenuedListActivity();
        appMenuedActivity = setMenuedActivity();
        menuResourceId = menuResourceId != 0 ? menuResourceId : setMenuResourceId();

        if (appListMenuedActivity != null) {
            appListMenuedActivity.setMenuResourceId(menuResourceId);
            appListMenuedActivity.setCallBacks(this);

        }
        else if (appMenuedActivity != null) {
            appMenuedActivity.setCallBacks(this);
            appListMenuedActivity.setMenuResourceId(menuResourceId);
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

    /**
     * @return Initialise ProgressBar from Fragment View
     */
    public abstract int setProgressBarResourceId();

}
