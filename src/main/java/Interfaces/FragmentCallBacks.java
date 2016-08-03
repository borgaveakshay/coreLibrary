package Interfaces;

import android.view.MenuItem;

/**
 * Created by Akshay.Borgave on 31-03-2016.
 */
public interface FragmentCallBacks {

      public void showProgressIndicator();

      public void hideProgressIndicator();

      public void handleMenuClick(MenuItem menuItem);

      public void onBackPressed();
}
