package com.example.rodrigo.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rodrigo.todolist.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 03/05/15.
 */
public class DatabaseHeper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tasks";
    private static final String TABLE_TAKS = "tarefas";
    private static final int VERSAO = 1;

    //TASKS TABLE COLUMNS NAMES
    private static final String KEY_ID = "id";
    private static final String KEY_TAKS = "taks";

    public DatabaseHeper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLES
       String CREATE_TAKS_TABLE = "CREATE TABLE" + TABLE_TAKS + "("
               + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAKS +
               " TEXT " + ")";
        db.execSQL(CREATE_TAKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTED
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAKS);
        //CREATE TABLES AGAIN
        onCreate(db);
    }

    //ADDING NEW CONTACT
    public void addTask(Tarefa tarefa) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAKS, tarefa.getTarefa());//Tarefa

        //INSERTING ROW
        db.insert(TABLE_TAKS, null, values);
        db.close();
    }

    //GETTING SINGLE TASK
    public Tarefa getTarefa(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TAKS, new String[] {KEY_ID, KEY_TAKS}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Tarefa tarefa = new Tarefa(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return tarefa;
    }

    //GET ALL TAKS
    public List<Tarefa> getAllTasks() {
        List<Tarefa> taskList = new ArrayList<Tarefa>();
        String selectQuery = "SELECT * FROM " + TABLE_TAKS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Tarefa tarefa = new Tarefa();
                tarefa.set_id(Integer.parseInt(cursor.getString(0)));
                tarefa.setTarefa(cursor.getString(1));
                taskList.add(tarefa);
            } while (cursor.moveToNext());
        }

        return taskList;
    }

    //GETTING TASKS COUNT
    public int getTaskCount() {
        String countQuery = "SELECT * FROM " + TABLE_TAKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    //UPDATING SINGLE TASK
    public int updateTask(Tarefa tarefa) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAKS, tarefa.getTarefa());

        return db.update(TABLE_TAKS, values, KEY_ID + "=?",
                new String[] {String.valueOf(tarefa.get_id())});
    }

    //DELETING SINGLE TASK
    public void deleteTaks(Tarefa tarefa) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_TAKS, KEY_ID + "=?",
                new String[] {String.valueOf(tarefa.get_id())});
        db.close();
    }
}
