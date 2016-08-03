package FragmentBaseClasses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import Interfaces.FragmentCallBacks;
import com.example.genericactivity.BaseActivities.GenericFragmentListMenuedActivity;

/**
 * Created by Akshay.Borgave on 07-03-2016.
 */
public class BaseFragmentManager extends Fragment implements FragmentCallBacks {

    protected Context context;
    protected GenericFragmentListMenuedActivity appActivity;
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

    public GenericFragmentListMenuedActivity getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(GenericFragmentListMenuedActivity appActivity) {
        this.appActivity = appActivity;
    }

    @Override
    public void showProgressIndicator() {

      try {
                progressView = (ProgressBar) getView().findViewById(getProgressBarResourceId());
                progressView.setVisibility(View.VISIBLE);
          }
       catch (Exception e) {

        }
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

    @Override
    public void onBackPressed() {

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

        setRetainInstance(true);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        appActivity.setCallBacks(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        appActivity.setCallBacks(this);
    }


}
