package net.fumapp_android.ui.activities.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.fumapp_android.R;

import butterknife.ButterKnife;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    public void setContentView(int layoutResID) {

        super.setContentView(layoutResID);
        ButterKnife.bind(this);
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

    protected void callFragment(final Fragment fragment) {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
