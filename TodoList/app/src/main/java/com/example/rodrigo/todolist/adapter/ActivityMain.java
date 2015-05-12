package com.example.rodrigo.todolist.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.rodrigo.todolist.R;
import com.example.rodrigo.todolist.dao.DatabaseHeper;
import com.example.rodrigo.todolist.model.Tarefa;
import com.example.rodrigo.todolist.util.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends ActionBarActivity {

    private EditText editTextInput;
    private Button buttonAdd;
    private ListView listView;
    private ArrayList<String> itemArrey;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHeper db = new DatabaseHeper(this);


        Log.d("Reading: ", "Reading all tasks ..");
        List<Tarefa> tarefas = db.getAllTasks();

        for(Tarefa tf : tarefas) {
            String log = "Id: "+tf.get_id()+"  ,Task:  " + tf.getTarefa();
            Log.d("Task:  ", log);
        }

        setContentView(R.layout.activity_activity_main);
        editTextInput = (EditText) this.findViewById(R.id.editText);
        buttonAdd = (Button) this.findViewById(R.id.buttonMain);
        listView = (ListView) this.findViewById(R.id.listView);

        itemArrey = new ArrayList<String>();
        //Adiciona um novo item na tela
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemArrey);
        listView.setAdapter(itemAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                itemArrey.add(editTextInput.getText().toString());
                editTextInput.setText("");
                itemAdapter.notifyDataSetChanged();
            }
        });

        Log.d("INSERT: ", "INSERTING .. ");
        db.addTask(new Tarefa(itemArrey);

        //Faz a remoção de um item na tela com long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListAdapter listAdapter = listView.getAdapter();
                Object obj = listAdapter.getItem(position);
                itemArrey.remove(obj);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:

                break;
            case R.id.action_menu_sair:
                finish();
                break;
            case R.id.action_menu_logout:
                SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();;
        }
        return super.onOptionsItemSelected(item);
    }
}