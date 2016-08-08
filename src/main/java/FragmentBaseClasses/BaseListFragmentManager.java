package FragmentBaseClasses;

import android.support.v7.widget.RecyclerView;

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

}
