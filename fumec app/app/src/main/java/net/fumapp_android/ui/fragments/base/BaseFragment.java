package net.fumapp_android.ui.fragments.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import net.fumapp_android.R;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class BaseFragment extends Fragment {

    private ProgressDialog dialog;
    private FragmentActivity activity;

    public void onAttach(Context context) {

        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    protected void setTitle(int title) {
        this.activity.setTitle(getString(title));
    }

    protected void callProgressDialog(final Context context) {
        dialog = ProgressDialog.show(context, "",
                getString(R.string.general_loading), true);
    }

    protected void dismissProgressDialog() {
        if(dialog != null) {
            dialog.dismiss();
        }
    }

    protected void showToats(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    protected void callFragment(Fragment fragment) {

        FragmentManager fragmentManager = this.activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
