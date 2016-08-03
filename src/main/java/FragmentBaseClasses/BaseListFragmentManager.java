package FragmentBaseClasses;

import android.support.v7.widget.RecyclerView;

import BaseModels.BaseModel;
import Interfaces.MultiSelectEnableListener;

import java.util.ArrayList;

import RecycleViewBaseClasses.BaseMultiselectRecycleView;

/**
 * Created by Akshay.Borgave on 02-08-2016.
 */
public class BaseListFragmentManager< T extends BaseModel, Z extends BaseMultiselectRecycleView> extends BaseFragmentManager implements MultiSelectEnableListener<T> {

    protected ArrayList<T> dataList;
    protected ArrayList<T> selectedList;
    boolean isMultiSelectEnable;
    protected Z baseListRecyclerViewAdapter;
    protected RecyclerView recyclerView;

    @Override
    public void multiSelectPerfromed(ArrayList<T> dataList) {
        this.selectedList = dataList;
        getAppActivity().multiSelectPerfromed(dataList);


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

    public void setMultiSelectEnable(boolean multiSelectEnable) {
        isMultiSelectEnable = multiSelectEnable;
    }
}
