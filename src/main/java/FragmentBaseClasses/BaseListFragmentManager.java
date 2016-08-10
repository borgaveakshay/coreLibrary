package FragmentBaseClasses;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.genericactivity.BaseActivities.GenericFragmentMenuedActivity;

import BaseModels.BaseModel;
import Interfaces.MultiSelectEnableListener;

import java.util.ArrayList;

import RecycleViewBaseClasses.BaseMultiselectRecycleView;
import RecycleViewBaseClasses.BaseRecyclerView;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public abstract class BaseListFragmentManager< T extends BaseModel, Z extends BaseRecyclerView> extends BaseFragmentManager  {

    protected ArrayList<T> dataList;
    protected Z baseListRecyclerViewAdapter;
    protected RecyclerView recyclerView;


    @Override
    public GenericFragmentMenuedActivity setMenuedActivity() {
        return (GenericFragmentMenuedActivity) getActivity();
    }

    /**
     * You can call this method to hide toolbar while scrolling the list. It will only work if @getAppListMenuedActivity.setOnScrollHideToolBarListener is set to true.
     * Note: This method should be called after initializing recycle view.
     */
    public void setOnScrollHideToolBarListener(){

        if(getAppListMenuedActivity().isToolBarHideOnScroll())
        {
            if(recyclerView != null) {
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        if (getAppListMenuedActivity().isToolBarHideOnScroll()) {
                            if (dy > 20) {
                                getAppListMenuedActivity().setToolBarHidden(false);

                            } else if (dy < -5) {
                                getAppListMenuedActivity().setToolBarHidden(true);
                            }
                        }

                    }

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        if (getAppListMenuedActivity().isToolBarHideOnScroll()) {
                            if (getAppListMenuedActivity().isToolBarHidden()) {
                                getAppListMenuedActivity().showToolBar();

                            } else {
                                getAppListMenuedActivity().hideToolBar();
                            }
                        }
                    }
                });
            }
            else
            {
                Log.d("OnScrollToolBarHide","Call setOnScrollHideToolBarListener() method after intializing recycleview instance ");
            }
        }
    }

}
