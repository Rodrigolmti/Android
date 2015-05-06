package br.fumec.insultlist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;


public class InsultListActivity extends ActionBarActivity {

    private ArrayList<String> lines;       // lines of file of insults

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insult_list);

        // read input file
        lines = readEntireFile(R.raw.phrases);

        // set up the ListView to use the lines from the file
        ListView myList = (ListView) findViewById(R.id.list_of_insults);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, lines);
        myList.setAdapter(adapter);

        // set up event listening for clicks on the list
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                handleClick(index);
            }
        });
    }

    /*
     * Handles a click on the list item at the given 0-based index.
     * Speaks the given insult aloud using text-to-speech.
     */
    private void handleClick(int index) {
        String text = lines.get(index);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    /*
     * Reads the lines of the file with the given resource ID,
     * returning them as an array list of strings.
     * Assumes that the file with the given ID exists in res/raw folder.
     */

    private ArrayList<String> readEntireFile(int id) {
        ArrayList<String> list = new ArrayList<String>();
        Scanner scan = new Scanner(getResources().openRawResource(id));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            list.add(line);
        }

        return list;
    }
}
