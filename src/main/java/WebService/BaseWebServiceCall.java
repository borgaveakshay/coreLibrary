package WebService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import Interfaces.FragmentCallBacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */
public abstract class BaseWebServiceCall<T extends Call<Z>, Z> extends Thread {

    T callBack;
    AppCompatActivity context;
    AlertDialog alertDialog;
    FragmentCallBacks fragmentCallBacks;
    boolean isProgressBarEnabled;

    public BaseWebServiceCall(AppCompatActivity context, T callBackInstance, FragmentCallBacks fragmentCallBacks) {
        this.context = context;
        callBack = callBackInstance;
        this.fragmentCallBacks = fragmentCallBacks;
    }

    public BaseWebServiceCall(AppCompatActivity context, T callBackInstance) {
        this.context = context;
        callBack = callBackInstance;
    }

    public void makeCall() {

        if (isProgressBarEnabled()) {
            if (fragmentCallBacks != null)
                fragmentCallBacks.showProgressIndicator();
        }
        callBack.clone().enqueue(new Callback<Z>() {
            @Override
            public void onResponse(Call<Z> call,final Response<Z> response) {

                if (isProgressBarEnabled()) {
                    if (fragmentCallBacks != null)
                        fragmentCallBacks.hideProgressIndicator();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onResponseReceived(response.body());
                    }
                });

            }

            @Override
            public void onFailure(final Call<Z> call, final Throwable t) {

                if (isProgressBarEnabled()) {
                    if (fragmentCallBacks != null)
                        fragmentCallBacks.hideProgressIndicator();
                }
                showErrorDialog(t);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onCallFailure(call, t);
                    }
                });


            }
        });
    }

    public void showErrorDialog(Throwable t) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setTitle("Error");
        alertBuilder.setMessage("Message : " + t.getMessage());
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    @Override
    public void run() {
        makeCall();
    }

    public boolean isProgressBarEnabled() {
        return isProgressBarEnabled;
    }

    public void setProgressBarEnabled(boolean progressBarenebled) {
        isProgressBarEnabled = progressBarenebled;
    }

    public abstract void onResponseReceived(Z data);

    public abstract void onCallFailure(Call<Z> call, Throwable t);

}
