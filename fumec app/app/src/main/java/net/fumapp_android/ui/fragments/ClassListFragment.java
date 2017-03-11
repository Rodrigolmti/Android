package net.fumapp_android.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.fumapp_android.R;
import net.fumapp_android.app.Preferences;
import net.fumapp_android.model.Note;
import net.fumapp_android.model.TO.TONote;
import net.fumapp_android.presenter.BasePresenter;
import net.fumapp_android.presenter.Presenter;
import net.fumapp_android.ui.adapters.ClassAdapter;
import net.fumapp_android.ui.fragments.base.BaseFragment;
import net.fumapp_android.ui.interfaces.ClassAdapterItemOnClick;
import net.fumapp_android.util.NetworkConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class ClassListFragment extends BaseFragment implements BasePresenter.view {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view_class)
    RecyclerView recyclerView;

    public static final String FRAGMENT_NOTE = "fragment_note";
    private static BasePresenter.presenter presenter;
    private boolean refresh = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.class_list_fragment, container, false);
        ButterKnife.bind(this, fragmentView);
        setTitle(R.string.fragment_class_title);

        if (presenter == null) {
            presenter = new Presenter();
        }

        presenter.setView(this);
        if (Preferences.getInstance().getClassResponse() == null) {
            getClassResponse();
        } else {
            loadAdapterWithResponse(Preferences.getInstance().getClassResponse());
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        refresh = true;
                        presenter.points();
                    }
                }
        );

        return fragmentView;
    }

    private void getClassResponse() {

        if (NetworkConnection.isNetworkAvailable(getActivity())) {

            presenter.points();

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.general_network_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadAdapterWithResponse(TONote toNote) {
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
        ClassAdapter adapter = new ClassAdapter(toNote.getNotes(), getActivity(), new ClassAdapterItemOnClick() {
            @Override
            public void onClick(Note note) {
                Fragment fragment = new NoteListFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(FRAGMENT_NOTE, note);
                fragment.setArguments(bundle);
                callFragment(fragment);
            }
        });
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

        loadAdapterWithResponse(Preferences.getInstance().getClassResponse());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter = null;
    }
}
