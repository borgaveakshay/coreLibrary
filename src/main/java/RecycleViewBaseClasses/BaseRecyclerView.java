package RecycleViewBaseClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import BaseModels.BaseModel;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public abstract class BaseRecyclerView < T extends BaseModel, Z extends RecyclerView.ViewHolder  > extends RecyclerView.Adapter < Z >{

   protected ArrayList < T > dataList;
   protected LayoutInflater inflater;
   protected Context con;

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Z onCreateViewHolder(ViewGroup parent, int viewType) {

        return onCreateView(parent,viewType);
    }

    @Override
    public void onBindViewHolder(final Z holder, int position) {

        onBind(holder,position);

    }

    public abstract Z onCreateView(ViewGroup parent, int viewType);
    public abstract void onBind(Z holder, int pos);
}
