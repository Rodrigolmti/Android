package br.fumec.turtle4;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // pull out the turtle ID to show from the activity's intent
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("turtle_id", R.id.leo);
        setTurtleId(id);
    }

    /*
     * Sets the actively selected ninja turtle text based on the given resource ID.
     */
    public void setTurtleId(int id) {
        int index;
        if (id == R.id.leo) {
            index = 0;
        } else if (id == R.id.mike) {
            index = 1;
        } else if (id == R.id.don) {
            index = 2;
        } else { // if (id == R.id.raph)
            index = 3;
        }

        Resources res = getResources();
        String[] TURTLE_DETAILS = res.getStringArray(R.array.turtle_details);

        String text = TURTLE_DETAILS[index];
        TextView tv = (TextView) getActivity().findViewById(R.id.turtle_info);
        tv.setText(text);
    }

}
