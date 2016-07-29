package WebService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.example.genericactivity.baseclasses.FragmentCallBacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */
public abstract class BaseWebServiceCall<T extends Call<Z>, Z> {

    T callBack;
    Context context;
    AlertDialog alertDialog;
    FragmentCallBacks fragmentCallBacks;

    public BaseWebServiceCall(Context context, T callBackInstance, FragmentCallBacks fragmentCallBacks) {
        this.context = context;
        callBack = callBackInstance;
        this.fragmentCallBacks = fragmentCallBacks;
        makeCall();
    }

    public void makeCall() {

            fragmentCallBacks.showProgressIndicator();
            callBack.enqueue(new Callback<Z>() {
                @Override
                public void onResponse(Call<Z> call, Response<Z> response) {

                    fragmentCallBacks.hideProgressIndicator();
                    onResponseReceived(response.body());
                }
                @Override
                public void onFailure(final Call<Z> call, final Throwable t) {

                    fragmentCallBacks.hideProgressIndicator();
                    showErrorDialog(t);
                    onCallFailure(call, t);
                }
            });
    }

    public void showErrorDialog(Throwable t) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setTitle("Error");
        alertBuilder.setMessage("Log : " + t.getMessage());
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    public abstract void onResponseReceived(Z data);

    public abstract void onCallFailure(Call<Z> call, Throwable t);

}
