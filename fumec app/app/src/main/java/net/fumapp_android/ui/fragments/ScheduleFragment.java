package net.fumapp_android.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.fumapp_android.R;
import net.fumapp_android.app.Preferences;
import net.fumapp_android.model.Schedule;
import net.fumapp_android.presenter.BasePresenter;
import net.fumapp_android.presenter.Presenter;
import net.fumapp_android.ui.adapters.ScheduleAdapter;
import net.fumapp_android.ui.fragments.base.BaseFragment;
import net.fumapp_android.util.NetworkConnection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class ScheduleFragment extends BaseFragment implements BasePresenter.view{

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view_schedule)
    RecyclerView recyclerView;

    private static BasePresenter.presenter presenter;
    private boolean refresh = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.schedule_list_fragment, container, false);
        ButterKnife.bind(this, fragmentView);

        setTitle(R.string.fragment_schedule_title);

        if (presenter == null) {
            presenter = new Presenter();
        }

        presenter.setView(this);

        if (Preferences.getInstance().getScheduleResponse() == null) {
            getScheduleResponse();
        } else {
            loadAdapterWithResponse(Preferences.getInstance().getScheduleResponse());
        }

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        refresh = true;
                        presenter.schedule();
                    }
                }
        );

        return fragmentView;
    }

    private void getScheduleResponse() {

        if (NetworkConnection.isNetworkAvailable(getActivity())) {

            presenter.schedule();
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.general_network_message), Toast.LENGTH_SHORT).show();
        }

    }

    private void loadAdapterWithResponse(List<Schedule> schedules) {

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
        ScheduleAdapter adapter = new ScheduleAdapter(schedules);
        recyclerView.setAdapter(adapter);

        if (refresh) {
            refreshLayout.setRefreshing(false);
            refresh = false;
        }
    }

    @Override
    public void showProgressBar(int visibility) {

        if (!refresh) {
            switch (visibility) {
                case View.VISIBLE:
                    callProgressDialog(getActivity());
                    break;
                case View.GONE:
                    dismissProgressDialog();
                    break;
            }
        }
    }

    @Override
    public void showToast(String message) {
        showToats(getActivity(), message);
    }

    @Override
    public void successAttempt() {

        loadAdapterWithResponse(Preferences.getInstance().getScheduleResponse());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter = null;
    }
}
