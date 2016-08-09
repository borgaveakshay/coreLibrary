package RecycleViewBaseClasses;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.genericactivity.R;

import java.util.ArrayList;

import BaseModels.BaseModel;
import Interfaces.OnLoadMoreListener;

/**
 * Created by Akshay.Borgave on 08-08-2016.
 */
public abstract class BaseInfiniteDataLoadRecycleView < T extends BaseModel,Z extends RecyclerView.ViewHolder > extends BaseRecyclerView<T,Z> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private RecyclerView recyclerView;
    int totalItemCount;
    int lastVisibleItem;
    int visibleThreshold = 1;

    public BaseInfiniteDataLoadRecycleView(RecyclerView recyclerView) {
        setRecyclerView(recyclerView);
        setOnScrollListener();

    }

    @Override
    public Z onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(con).inflate(R.layout.litview_progress_bar, null);
            return ((Z) new LoadingViewHolder(view));
        }
        else if(viewType == VIEW_TYPE_ITEM)
        {
            return onCreateView(parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(Z holder, int position) {

        if(holder instanceof LoadingViewHolder){

            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
        else {
            onBind(holder,position);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarlistView);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setOnScrollListener(){

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {

                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

}
