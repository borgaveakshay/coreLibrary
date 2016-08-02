package com.example.genericactivity.baseclasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
   protected BaseListFragmentManager<T> baseFragmentManager;
   private int setImageResource;
   private boolean isMultipleItemSelected;
   private MultiSelectEnableListener<T> multiSelectEnableListener;


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

        onBind(holder,position);
        final BaseModel model = dataList.get(position);

        if(isMultiSelectEnable) {

            View view = holder.itemView.findViewById(parentViewResourceId);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                   BaseModel selectedModel = (BaseModel) v.getTag();
                   selectedModel.setSelectedImageResource(R.mipmap.ic_tick);
                   MultiSelectImageView imageView = null;
                    if(!selectedModel.isSelected()) {

                        if(selectList == null) {
                            selectList = new ArrayList<T>();
                        }
                        selectedModel.setSelected(true);
                        imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                        imageView.setImageResource(selectedModel.getSelectedImageResource());
                        selectList.add((T) selectedModel);

                        if(selectList.size() > 0)
                            isMultipleItemSelected = true;
                        baseFragmentManager.multiSelectPerfromed(selectList);
                    }
                    else
                    {
                        if(imageView == null) {
                            imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                        }
                        selectedModel.setSelected(false);
                        imageView.setPreviousBitMap();
                        selectList.remove(selectedModel);
                        baseFragmentManager.multiSelectPerfromed(selectList);
                    }
                    return true;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(isMultipleItemSelected) {
                            MultiSelectImageView imageView;
                            BaseModel selectedModel = (BaseModel) v.getTag();
                            selectedModel.setSelectedImageResource(R.mipmap.ic_tick);
                            if (!selectedModel.isSelected()) {

                                selectedModel.setSelected(true);
                                 imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                                 imageView.setImageResource(selectedModel.getSelectedImageResource());
                                selectList.add((T) selectedModel);
                                baseFragmentManager.multiSelectPerfromed(selectList);

                            }
                            else
                            {
                                imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                                imageView.setPreviousBitMap();
                                selectedModel.setSelected(false);
                                selectList.remove(selectedModel);
                                if(selectList.size() == 0){
                                    isMultipleItemSelected = false;
                                }
                                baseFragmentManager.multiSelectPerfromed(selectList);
                            }
                        }
                    }
                });

        }
        MultiSelectImageView imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
        if(model.isSelected()){

            imageView.setImageResource(model.getSelectedImageResource());
        }
        else
        {
            imageView.getPreviousImageBitmap();
        }
        holder.itemView.setTag(model);

    }

    public MultiSelectEnableListener<T> getMultiSelectEnableListener() {
        return multiSelectEnableListener;
    }

    public void setMultiSelectEnableListener(MultiSelectEnableListener<T> multiSelectEnableListener) {
        this.multiSelectEnableListener = multiSelectEnableListener;
    }

    public abstract Z onCreateView(ViewGroup parent, int viewType);
    public abstract void onBind(Z holder, int pos);


}
