package br.fumec.turtle02;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    /*
     * Called when the activity first gets created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Resources res = getResources();
        String[] TURTLE_DETAILS = res.getStringArray(R.array.turtle_details);

        // pull the turtle's ID out of the intent that the MainActivity used to load me
        Intent intent = getIntent();
        int id = intent.getIntExtra("turtle_id", R.id.leo);
        String text = "";
        if (id == R.id.leo) {
            text = TURTLE_DETAILS[0];
        } else if (id == R.id.mike) {
            text = TURTLE_DETAILS[1];
        } else if (id == R.id.don) {
            text = TURTLE_DETAILS[2];
        } else { // if (id == R.id.raph)
            text = TURTLE_DETAILS[3];
        }
        TextView tv = (TextView) findViewById(R.id.turtle_info);
        tv.setText(text);
    }
}
