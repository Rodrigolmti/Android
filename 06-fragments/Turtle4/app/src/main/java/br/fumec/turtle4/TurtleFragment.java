package br.fumec.turtle4;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;


public class TurtleFragment extends Fragment {

    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;

    public TurtleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_turtle, container, false);
    }


    /*
     * This method is called after the containing activity is done being created.
     * This is a good place to attach event listeners and do other initialization.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // attach event listener to radio button group
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateTurtleImage();
            }
        });

        // attach click event listener to turtle image button
        ImageButton img = (ImageButton) getActivity().findViewById(R.id.turtle);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsAboutTurtle();
            }
        });
    }

    /*
     * Updates which turtle image is showing
     * based on which radio button is currently checked.
     */
    private void updateTurtleImage() {
        ImageButton img = (ImageButton) getActivity().findViewById(R.id.turtle);
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        int checkedID = group.getCheckedRadioButtonId();
        if (checkedID == R.id.leo) {
            img.setImageResource(R.drawable.tmntleo);
        } else if (checkedID == R.id.mike) {
            img.setImageResource(R.drawable.tmntmike);
        } else if (checkedID == R.id.don) {
            img.setImageResource(R.drawable.tmntdon);
        } else if (checkedID == R.id.raph) {
            img.setImageResource(R.drawable.tmntraph);
        }

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            showDetailsAboutTurtle();
        }
    }

    /*
     * Shows more detailed information about the currently selected ninja turtle.
     * In portrait mode, this pops up a second activity.
     * In landscape mode, this updates the DetailsFragment within the same activity.
     */
    public void showDetailsAboutTurtle() {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        int id = group.getCheckedRadioButtonId();

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            // show in same activity
            DetailsFragment frag = (DetailsFragment) getFragmentManager().findFragmentById(R.id.fragment2);
            frag.setTurtleId(id);
        } else {
            // launch details as its own activity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("turtle_id", id);
            startActivityForResult(intent, REQUEST_CODE_DETAILS_ACTIVITY);
        }
    }

}
