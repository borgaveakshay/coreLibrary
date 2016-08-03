package RecycleViewBaseClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import BaseModels.BaseModel;
import Interfaces.MultiSelectEnableListener;
import UtilityBaseClasses.MultiSelectImageView;
import com.example.genericactivity.R;
import java.util.ArrayList;
import FragmentBaseClasses.BaseListFragmentManager;

/**
 * Created by Akshay.Borgave on 03-08-2016.
 */
public abstract class BaseMultiselectRecycleView < T extends BaseModel, Z extends RecyclerView.ViewHolder> extends BaseRecyclerView< T, Z > {

    protected  ArrayList < T > selectList;
    protected int parentViewResourceId;
    protected boolean isMultiSelectEnable;
    protected BaseListFragmentManager baseFragmentManager;
    protected int setImageResource;
    protected boolean isMultipleItemSelected;
    protected MultiSelectEnableListener< T > multiSelectEnableListener;

    public BaseMultiselectRecycleView(boolean doMultiSelectEnable, int imageResourceId, int parentViewResourceId) {

        isMultiSelectEnable = doMultiSelectEnable;
        setImageResource = imageResourceId;
        this.parentViewResourceId = parentViewResourceId;

    }

    @Override
    public void onBindViewHolder(final Z holder, int position) {

        onBind(holder,position);

        if(isMultiSelectEnable) {

            View view = holder.itemView.findViewById(parentViewResourceId);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int pos = (int)v.getTag();
                    dataList.get(pos).setSelectedImageResource(R.mipmap.ic_tick);
                    MultiSelectImageView imageView = null;
                    if(!dataList.get(pos).isSelected()) {

                        if(selectList == null) {
                            selectList = new ArrayList<T>();
                        }
                        dataList.get(pos).setSelected(true);
                        imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                        imageView.setImageResource(dataList.get(pos).getSelectedImageResource());
                        selectList.add((T) dataList.get(pos));

                        if(selectList.size() > 0)
                            isMultipleItemSelected = true;
                        baseFragmentManager.multiSelectPerfromed(selectList);
                    }
                    else
                    {
                        if(imageView == null) {
                            imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                        }
                        dataList.get(pos).setSelected(false);
                        imageView.setPreviousBitMap();
                        selectList.remove(dataList.get(pos));
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
                        int pos = (int)v.getTag();

                        dataList.get(pos).setSelectedImageResource(R.mipmap.ic_tick);
                        if (!dataList.get(pos).isSelected()) {

                            dataList.get(pos).setSelected(true);
                            imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                            imageView.setImageResource(dataList.get(pos).getSelectedImageResource());
                            selectList.add((T) dataList.get(pos));
                            baseFragmentManager.multiSelectPerfromed(selectList);

                        }
                        else
                        {
                            imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
                            imageView.setPreviousBitMap();
                            dataList.get(pos).setSelected(false);
                            selectList.remove(dataList.get(pos));
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
        if(dataList.get(position).isSelected()){

            imageView.setImageResource(dataList.get(position).getSelectedImageResource());
        }
        else
        {
            imageView.getPreviousImageBitmap();
        }

        holder.itemView.setTag(position);

    }

    public MultiSelectEnableListener<T> getMultiSelectEnableListener() {
        return multiSelectEnableListener;
    }

    public void setMultiSelectEnableListener(MultiSelectEnableListener<T> multiSelectEnableListener) {
        this.multiSelectEnableListener = multiSelectEnableListener;
    }
    public ArrayList<T> getSelectList() {
        return selectList;
    }

    public void setSelectList(ArrayList<T> selectList) {
        this.selectList = selectList;
    }
    public void setMultipleItemSelected(boolean multipleItemSelected) {
        isMultipleItemSelected = multipleItemSelected;
    }
}
