package FragmentBaseClasses;

import android.support.v7.widget.RecyclerView;

import com.example.genericactivity.BaseActivities.GenericFragmentMenuedActivity;

import java.util.ArrayList;

import BaseModels.BaseModel;
import Interfaces.MultiSelectEnableListener;
import RecycleViewBaseClasses.BaseMultiselectRecycleView;

/**
 * Created by Akshay.Borgave on 08-08-2016.
 */
public abstract class BaseListMultiSelectFragmentManager < T extends BaseModel, Z extends BaseMultiselectRecycleView> extends BaseFragmentManager implements MultiSelectEnableListener<T> {

    protected ArrayList<T> dataList;
    protected ArrayList<T> selectedList;
    boolean isMultiSelectEnable;
    protected Z baseListRecyclerViewAdapter;
    protected RecyclerView recyclerView;

    @Override
    public void multiSelectPerfromed(ArrayList<T> dataList) {
        this.selectedList = dataList;
        getAppListMenuedActivity().multiSelectPerfromed(dataList);


    }

    @Override
    public void onBackPressed() {

        if(isMultiSelectEnable()) {

            baseListRecyclerViewAdapter.setSelectList(new ArrayList<T>());
            for (int i = 0 ; i < dataList.size(); i++){

                dataList.get(i).setSelected(false);
            }
            baseListRecyclerViewAdapter.notifyDataSetChanged();
            baseListRecyclerViewAdapter.setMultipleItemSelected(false);
        }
        else {

            super.onBackPressed();
        }
    }

    public boolean isMultiSelectEnable() {
        return isMultiSelectEnable;
    }

    @Override
    public GenericFragmentMenuedActivity setMenuedActivity() {
        return (GenericFragmentMenuedActivity) getActivity();
    }

    @Override
    public void initFragment() {
        isMultiSelectEnable = setMultiSelectFlag();
        super.initFragment();
    }


    public abstract boolean setMultiSelectFlag();
}
