package br.com.lopes.lab3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Intent  intent=getIntent();
        Stringextra = intent.getExtra( "nome" ) ;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    }public void onPause() {
        super.onPause();

        Log.d("testing", "onPause got called");
    }

    public void onResume() {
        super.onResume();

        Log.d("testing", "onResume got called");
    }

    public void onStart() {
        super.onStart();
        Log.d("testing", "onStart got called");
    }

    public void onStop() {
        super.onStop();
        Log.d("testing", "onStop got called");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("testing", "onDestroy got called");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saved(View v) throws FileNotFoundException {
        //write short data
        File outDir = getExternalFilesDir(null);
        //root dir
        File outFile = new File(outDir, "saves.txt");
        PrintStream output = new PrintStream(outFile);
        output.print("Nome salvado com sucesso");
        output.close();
        //read list
        File textDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        for(File file : textDir.listFiles()) {}
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
}
