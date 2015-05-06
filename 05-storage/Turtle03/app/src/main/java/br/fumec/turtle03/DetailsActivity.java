package br.fumec.turtle03;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class DetailsActivity extends ActionBarActivity {
    private Set<String> dictionary;   // dictionary of words to look up

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

        if (dictionary == null) {
            loadDictionary();
        }
    }

    /*
     * Called when the user presses the Submit button.
     * Checks whether the user has typed a legal dictionary word in the text box,
     * and if so, closes this activity and returns to the MainActivity.
     */
    public void onclickSubmit(View view) {
        // if user has typed a valid dictionary word, accept it and send it back to main activity
        EditText edit = (EditText) findViewById(R.id.the_word);
        String text = edit.getText().toString().trim().toLowerCase();
        if (dictionary.contains(text)) {
            Intent intent = new Intent();
            intent.putExtra("the_word", text);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Not in dictionary, brah", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Loads the dictionary words from the provided text file, dict.txt.
     */
    private void loadDictionary() {
        dictionary = new HashSet<String>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dict));
        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            dictionary.add(word);
        }
    }

}
