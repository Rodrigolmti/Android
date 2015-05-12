package com.example.rodrigo.todolist.adapter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.rodrigo.todolist.R;
import com.example.rodrigo.todolist.adapter.ActivityMain;
import com.example.rodrigo.todolist.dao.UsuarioDAO;
import com.example.rodrigo.todolist.model.Usuario;
import com.example.rodrigo.todolist.util.Mensagem;


public class CadUsuarioActivity extends ActionBarActivity {

    private EditText edtNome, edtLogin, edtSenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        edtNome = (EditText)findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText)findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText)findViewById(R.id.usuario_edtSenha);

        usuarioDAO = new UsuarioDAO(this);
    }

    @Override
    protected void onDestroy(){
        usuarioDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar(){
        boolean validacao = true;

        String nome = edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if(nome == null || nome.equals("")) {
            validacao = false;
            edtNome.setError(getString(R.string.campo_obrigatorio));
        }

        if(login == null || login.equals("")) {
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));
        }

        if(senha == null || senha.equals("")) {
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));
        }

        if(validacao) {
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            if(idusuario > 0) {
                usuario.set_id(idusuario);
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);
            if(resultado != -1) {
                if(idusuario > 0) {
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizada));
                } else {
                    Mensagem.Msg(this, getString(R.string.mensagem_sucesso));
                }
                finish();
                startActivity(new Intent(this, ActivityMain.class));
            } else {
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastros, menu);

        if(idusuario > 0) {
            menu.findItem(R.id.action_menu_excluir).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_excluir:

                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, TelaInicio.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
