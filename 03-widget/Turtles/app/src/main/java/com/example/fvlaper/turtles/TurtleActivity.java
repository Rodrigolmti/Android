package com.example.fvlaper.turtles;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class TurtleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turtle);
    }

    public void chooseTurtle (View view) {
        ImageView img = (ImageView)findViewById(R.id.turtle);

        switch (view.getId()) {
            case R.id.leo:
                img.setImageResource(R.drawable.tmntleo); break;
            case R.id.mike:
                img.setImageResource(R.drawable.tmntmike); break;
            case R.id.don:
                img.setImageResource(R.drawable.tmntdon); break;
            case R.id.raph:
                img.setImageResource(R.drawable.tmntraph); break;
        }
    }

}
