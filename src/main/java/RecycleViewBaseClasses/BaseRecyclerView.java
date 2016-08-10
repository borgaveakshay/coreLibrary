package RecycleViewBaseClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import BaseModels.BaseModel;
import FragmentBaseClasses.BaseFragmentManager;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public abstract class BaseRecyclerView < T extends BaseModel, Z extends RecyclerView.ViewHolder  > extends RecyclerView.Adapter < Z >{

   protected ArrayList < T > dataList;
   protected LayoutInflater inflater;
   protected Context con;
   protected int parentViewResourceId;
   protected boolean isLoading;
   protected RecyclerView recyclerView;
   protected BaseFragmentManager baseFragmentManager;

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Z onCreateViewHolder(ViewGroup parent, int viewType) {

        return onCreateView(parent,viewType);
    }

    @Override
    public void onBindViewHolder(final Z holder, int position) {

        onBind(holder,position);

    }
    public void setLoaded() {
        isLoading = false;
    }

    /**
     *
     * @param recyclerView
     * It sets the recycle view instance which binds to the adaptor.
     */
    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return Z
     * This method called to initialize view for one every list component.
     */
    public abstract Z onCreateView(ViewGroup parent, int viewType);

    /**
     *
     * @param holder
     * @param pos
     * Method gets called to bind data with view for a particular position in the list.
     */
    public abstract void onBind(Z holder, int pos);


}
