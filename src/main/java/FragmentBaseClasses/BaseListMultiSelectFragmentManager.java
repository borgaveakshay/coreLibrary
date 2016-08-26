package FragmentBaseClasses;

import android.support.v7.widget.RecyclerView;

import com.example.genericactivity.BaseActivities.GenericFragmentListMenuedActivity;
import com.example.genericactivity.BaseActivities.GenericFragmentMenuedActivity;

import java.util.ArrayList;

import BaseModels.BaseModel;
import Interfaces.MultiSelectEnableListener;
import RecycleViewBaseClasses.BaseMultiselectRecycleView;

/**
 * Created by Akshay.Borgave on 08-08-2016.
 */
public abstract class BaseListMultiSelectFragmentManager < T extends BaseModel, Z extends BaseMultiselectRecycleView> extends BaseListFragmentManager implements MultiSelectEnableListener<T> {

    protected ArrayList<T> dataList;
    protected ArrayList<T> selectedList;
    boolean isMultiSelectEnable;
    protected Z baseListRecyclerViewAdapter;

    /**
     *
     * @param dataList
     * Method gets called when user start selecting items from the list by long pressing the items.
     */
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

    /**
     *
     * @return @isMultiSelectEnable
     * It Notifies whether multi select feature for list is enabled or not.
     */
    public boolean isMultiSelectEnable() {
        return isMultiSelectEnable;
    }


    @Override
    public void initFragment() {
        isMultiSelectEnable = setMultiSelectFlag();
        super.initFragment();
    }

    @Override
    public GenericFragmentListMenuedActivity setMenuedListActivity() {
        return (GenericFragmentListMenuedActivity) getActivity();
    }

    /**
     *
     * @return @isMultiSelectEnable
     * This method should be overridden by the extended class to notify whether it is supporting multi select functionality or not.
     * Default is false.
     */
    public abstract boolean setMultiSelectFlag();
}
