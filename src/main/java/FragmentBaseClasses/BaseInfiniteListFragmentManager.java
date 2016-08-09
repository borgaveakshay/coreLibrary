package FragmentBaseClasses;

import java.util.ArrayList;

import BaseModels.BaseModel;
import Interfaces.OnLoadMoreListener;
import RecycleViewBaseClasses.BaseInfiniteDataLoadRecycleView;
import RecycleViewBaseClasses.BaseRecyclerView;
import WebService.BaseWebServiceCall;
import retrofit2.Call;

/**
 * Created by Akshay.Borgave on 08-08-2016.
 */
public abstract class BaseInfiniteListFragmentManager<T extends BaseModel,Y extends Call<T>, Z extends BaseInfiniteDataLoadRecycleView> extends BaseListFragmentManager<T,Z> implements OnLoadMoreListener {
   protected Call<ArrayList<T>> call;

    @Override
    public void onLoadMore() {

        dataList.add(null);
        baseListRecyclerViewAdapter.notifyItemInserted(dataList.size());
        webServiceCall = new BaseWebServiceCall<Call<ArrayList<T>>, ArrayList<T>>(getAppListMenuedActivity(), call, this) {


            @Override
            public void onResponseReceived(ArrayList<T> data) {
                dataList.remove(dataList.size() - 1);
                baseListRecyclerViewAdapter.notifyItemRemoved(dataList.size());
                dataList.addAll(data);
                baseListRecyclerViewAdapter.notifyDataSetChanged();
                baseListRecyclerViewAdapter.setLoaded();
            }

            @Override
            public void onCallFailure(Call<ArrayList<T>> call, Throwable t) {

            }
        };
        webServiceCall.setProgressBarEnabled(false);
        webServiceCall.start();
    }
}
