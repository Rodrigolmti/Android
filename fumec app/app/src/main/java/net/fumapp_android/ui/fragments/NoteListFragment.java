package net.fumapp_android.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fumapp_android.R;
import net.fumapp_android.model.Note;
import net.fumapp_android.ui.adapters.NoteAdapter;
import net.fumapp_android.ui.fragments.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class NoteListFragment extends BaseFragment {

    @BindView(R.id.recycler_view_class)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.note_list_fragment, container, false);
        ButterKnife.bind(this, fragmentView);

        Fragment f = getActivity().getSupportFragmentManager().findFragmentById(R.id.placeholder);
        if (f instanceof NoteListFragment)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.fragment_note_title);

        final Note note;
        if (getArguments() != null) {
            note = (Note) getArguments().getSerializable(ClassListFragment.FRAGMENT_NOTE);
            RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layout);
            NoteAdapter adapter = new NoteAdapter(getActivity(), note);
            recyclerView.setAdapter(adapter);
        }

        return fragmentView;
    }
}
