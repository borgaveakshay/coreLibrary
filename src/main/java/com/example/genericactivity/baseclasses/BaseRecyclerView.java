package com.example.genericactivity.baseclasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.genericactivity.R;
import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public abstract  class BaseRecyclerView < T extends BaseModel , Z extends RecyclerView.ViewHolder  > extends RecyclerView.Adapter < Z >{

   protected ArrayList < T > dataList;
   protected  ArrayList < T > selectList;
   protected LayoutInflater inflater;
   protected Context con;
   protected int parentViewResourceId;
   private boolean isMultiSelectEnable;
   protected BaseFragmentManager baseFragmentManager;
   private int setImageResource;

    public BaseRecyclerView(boolean doMultiSelectEnable, int imageResourceId , int parentViewResourceId){
        isMultiSelectEnable = doMultiSelectEnable;
        setImageResource = imageResourceId;
        this.parentViewResourceId = parentViewResourceId;
    }
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

        final BaseModel model = dataList.get(position);

        if(isMultiSelectEnable) {
            View view = holder.itemView.findViewById(parentViewResourceId);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   BaseModel selectedModel = (BaseModel) v.getTag();
                    selectedModel.setSelectedImageResource(R.mipmap.ic_tick);
                    if(!selectedModel.isSelected()) {
                        selectedModel.setSelected(true);
                        ImageView imageView = (ImageView) holder.itemView.findViewById(setImageResource);
                        imageView.setImageResource(selectedModel.getSelectedImageResource());
                    }
                    return true;
                }
            });
        }
        onBind(holder,position);

        if(model.isSelected()){
            ImageView imageView = (ImageView) holder.itemView.findViewById(setImageResource);
            imageView.setImageResource(model.getSelectedImageResource());
        }
        holder.itemView.setTag(model);

    }

    public abstract Z onCreateView(ViewGroup parent, int viewType);
    public abstract void onBind(Z holder, int pos);

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    };

}
