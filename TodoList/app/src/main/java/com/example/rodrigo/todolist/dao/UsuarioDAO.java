package com.example.rodrigo.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;

import com.example.rodrigo.todolist.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 03/05/15.
 */
public class UsuarioDAO {

    private DatabaseHeper databaseHeper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context){
        databaseHeper = new DatabaseHeper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null) {
            database = databaseHeper.getWritableDatabase();
        }

        return database;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DatabaseHeper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHeper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHeper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHeper.Usuarios.SENHA))
        );

        return model;
    }

    public List<Usuario> listarUsuarios() {
        Cursor cursor = getDatabase().query(DatabaseHeper.Usuarios.TABELA,DatabaseHeper.Usuarios.COLUNAS, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHeper.Usuarios.NOME, usuario.getNome());
        valores.put(DatabaseHeper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DatabaseHeper.Usuarios.SENHA, usuario.getSenha());

        if(usuario.get_id() !=null) {
            return getDatabase().update(DatabaseHeper.Usuarios.TABELA, valores, "_id = ?", new String[]{usuario.get_id().toString()});
        }
        return getDatabase().insert(DatabaseHeper.Usuarios.TABELA, null, valores);
    }

    public boolean removerUsuario(int id) {
        return getDatabase().delete(DatabaseHeper.Usuarios.TABELA, "_id = ?", new String[] {Integer.toString(id)}) > 0;
    }

    public Usuario buscarUsuarioPorId(int id) {
        Cursor cursor = getDatabase().query(DatabaseHeper.Usuarios.TABELA, DatabaseHeper.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);

        if(cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public boolean logar(String usuario, String senha) {
        Cursor cursor = getDatabase().query(DatabaseHeper.Usuarios.TABELA,null, "LOGIN = ? AND SENHA = ?",
                new String[]{usuario, senha}, null, null, null);

        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public void fechar(){
        databaseHeper.close();
        database = null;
    }
}
