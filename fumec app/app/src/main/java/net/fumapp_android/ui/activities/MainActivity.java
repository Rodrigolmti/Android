package net.fumapp_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.fumapp_android.R;
import net.fumapp_android.presenter.BasePresenter;
import net.fumapp_android.presenter.Presenter;
import net.fumapp_android.ui.activities.base.BaseActivity;
import net.fumapp_android.ui.fragments.ClassListFragment;
import net.fumapp_android.ui.fragments.InfoFragment;
import net.fumapp_android.ui.fragments.ScheduleFragment;
import net.fumapp_android.util.NetworkConnection;

import butterknife.BindView;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class MainActivity extends BaseActivity implements BasePresenter.view {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private BasePresenter.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (presenter == null) {
            presenter = new Presenter();
        }
        presenter.setView(this);

        callFragment(new ClassListFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_note:
                                fragment = new ClassListFragment();
                                break;
                            case R.id.action_schedule:
                                fragment = new ScheduleFragment();
                                break;
                            case R.id.action_info:
                                fragment = new InfoFragment();
                                break;
                            case R.id.action_logout:
                                if (NetworkConnection.isNetworkAvailable(MainActivity.this)) {
                                    presenter.logout();
                                } else {
                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.general_network_message), Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }

                        if (fragment != null)
                            callFragment(fragment);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                callProgressDialog(this);
                break;
            case View.GONE:
                dismissProgressDialog();
                break;
        }
    }

    @Override
    public void showToast(String message) {
        showToats(this, message);
    }

    @Override
    public void successAttempt() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
