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
    boolean isMultiSelectEnable;
    protected Z baseListRecyclerViewAdapter;
    protected RecyclerView recyclerView;

    @Override
    public void multiSelectPerfromed(ArrayList<T> dataList) {
        this.dataList = dataList;
        getAppActivity().multiSelectPerfromed(dataList);


    }

    @Override
    public void onBackPressed() {

     if(isMultiSelectEnable()) {

         for (int i = 0; i < dataList.size(); i++) {

             if (dataList.get(i).isSelected()) {

                 dataList.get(i).setSelected(false);
             }
         }
         if (recyclerView != null) {
             if (baseListRecyclerViewAdapter != null)
                 recyclerView.setAdapter(baseListRecyclerViewAdapter);
         }
     }
        super.onBackPressed();
 }

    public boolean isMultiSelectEnable() {
        return isMultiSelectEnable;
    }

    public void setMultiSelectEnable(boolean multiSelectEnable) {
        isMultiSelectEnable = multiSelectEnable;
    }
}
