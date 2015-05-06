package com.example.daniel.lab2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class VelhaMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velha_main);
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

    public void onEscolhaPlayer(View view) {

        Button bt1Start= (Button) findViewById(R.id.btStart);
        Button btOk= (Button) findViewById(R.id.ok);
        Button btSair= (Button) findViewById(R.id.btSair);
        Button btRegras= (Button) findViewById(R.id.btRegras);
        btSair.setEnabled(true);
        btRegras.setEnabled(true);
        bt1Start.setEnabled(true);
        btOk.setEnabled(false);
        btOk.setVisibility(View.INVISIBLE);

    }

    public void onClickButton(View view) {
        Intent intent = new Intent(this, TabuleiroActivity.class);
        Button group = (Button) findViewById(R.id.btStart);
        int id = group.getId();
        intent.putExtra("tabulero_id", id);
        startActivity(intent);
    }
}
