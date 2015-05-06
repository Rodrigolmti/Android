package br.com.lopes.lab3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {


    ImageView imgMain;
    EditText edtName;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("testing", "onCreate got called; Bundle=" + savedInstanceState);
    }

    public void onPause() {
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

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        edtName = (EditText) findViewById(R.id.editText);
        outState.putString("Text", String.valueOf(edtName));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("testing", "onRestoreInstanceState got called");
        savedInstanceState.getInt("edtName");
    }

    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), " waiting the change of layout ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class ) ;
        Intent rodrigo = Intent.putExtra("Rodrigo", nome);
        startActivity(intent);
    }
}
