package RecycleViewBaseClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import BaseModels.BaseModel;
import FragmentBaseClasses.BaseListMultiSelectFragmentManager;
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
    protected boolean isMultiSelectEnable;
    protected BaseListMultiSelectFragmentManager baseFragmentManager;
    protected int setImageResource;
    protected boolean isMultipleItemSelected;
    protected MultiSelectEnableListener< T > multiSelectEnableListener;

    public BaseMultiselectRecycleView(boolean doMultiSelectEnable, int imageResourceId) {

        isMultiSelectEnable = doMultiSelectEnable;
        setImageResource = imageResourceId;

    }
    @Override
    public void onBindViewHolder(final Z holder, int position) {

        super.onBindViewHolder(holder,position);

        MultiSelectImageView imageView = (MultiSelectImageView) holder.itemView.findViewById(setImageResource);
        if(dataList.get(position).isSelected()){

            imageView.setImageResource(dataList.get(position).getSelectedImageResource());
        }
        onBind(holder,position);
    }

    @Override
    public void onLongItemClickListener(Z viewHolder, int position) {

        if(isMultiSelectEnable) {
            dataList.get(position).setSelectedImageResource(R.mipmap.ic_tick);
            MultiSelectImageView imageView = null;
            if (!dataList.get(position).isSelected()) {

                if (selectList == null) {
                    selectList = new ArrayList<T>();
                }
                dataList.get(position).setSelected(true);
                imageView = (MultiSelectImageView) viewHolder.itemView.findViewById(setImageResource);
                imageView.setImageResource(dataList.get(position).getSelectedImageResource());
                selectList.add((T) dataList.get(position));

                if (selectList.size() > 0)
                    isMultipleItemSelected = true;
                baseFragmentManager.multiSelectPerfromed(selectList);
            } else {
                if (imageView == null) {
                    imageView = (MultiSelectImageView) viewHolder.itemView.findViewById(setImageResource);
                }
                dataList.get(position).setSelected(false);
                imageView.setPreviousBitMap();
                selectList.remove(dataList.get(position));
                baseFragmentManager.multiSelectPerfromed(selectList);
            }
        }
    }

    @Override
    public void onItemClickListener(Z viewHolder, int position) {

        MultiSelectImageView imageView;
        if(isMultipleItemSelected) {

            dataList.get(position).setSelectedImageResource(R.mipmap.ic_tick);
            if (!dataList.get(position).isSelected()) {

                dataList.get(position).setSelected(true);
                imageView = (MultiSelectImageView) viewHolder.itemView.findViewById(setImageResource);
                imageView.setImageResource(dataList.get(position).getSelectedImageResource());
                selectList.add((T) dataList.get(position));
                baseFragmentManager.multiSelectPerfromed(selectList);

            } else {
                imageView = (MultiSelectImageView) viewHolder.itemView.findViewById(setImageResource);
                imageView.setPreviousBitMap();
                dataList.get(position).setSelected(false);
                selectList.remove(dataList.get(position));
                if (selectList.size() == 0) {
                    isMultipleItemSelected = false;
                }
                baseFragmentManager.multiSelectPerfromed(selectList);
            }
        }
 }

    /**
     *
     * @return multiSelectEnableListener
     * I will return instance of recycle view which implements @MultiSelectEnableListener interface.
     */
    public MultiSelectEnableListener<T> getMultiSelectEnableListener() {
        return multiSelectEnableListener;
    }
    /**
     *
     * @return multiSelectEnableListener
     * I will set instance of recycle view which implements @MultiSelectEnableListener interface.
     */
    public void setMultiSelectEnableListener(MultiSelectEnableListener<T> multiSelectEnableListener) {
        this.multiSelectEnableListener = multiSelectEnableListener;
    }

    /**
     *
     * @return selectList
     * It will return the selected list from the list.
     */
    public ArrayList<T> getSelectList() {
        return selectList;
    }

    /**
     *
     * @return selectList
     * It will set the selected list from the list.
     */
    public void setSelectList(ArrayList<T> selectList) {
        this.selectList = selectList;
    }

    /**
     *
     * @param multipleItemSelected
     * If there is atleast one item is selected in list. it should be call to notify
     */
    public void setMultipleItemSelected(boolean multipleItemSelected) {
        isMultipleItemSelected = multipleItemSelected;
    }
}
