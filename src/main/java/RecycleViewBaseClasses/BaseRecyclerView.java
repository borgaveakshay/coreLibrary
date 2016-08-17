package RecycleViewBaseClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
    public void onBindViewHolder(final Z holder, final int position) {


        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener(holder,(int)v.getTag());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongItemClickListener(holder,(int) v.getTag());
                return true;
            }
        });
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

    /**
     *
     * @param position
     * Method gets called when user clicks on a particular item.
     */
    public abstract void onItemClickListener(Z viewHolder, int position);

    /**
     *
     * @param position
     * Method gets called when user Long Press on a particular item.
     */
    public abstract void onLongItemClickListener(Z viewHolder,int position);


}
