package net.fumapp_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.Toast;

import net.fumapp_android.R;
import net.fumapp_android.app.Preferences;
import net.fumapp_android.model.Student;
import net.fumapp_android.presenter.BasePresenter;
import net.fumapp_android.presenter.Presenter;
import net.fumapp_android.ui.activities.base.BaseActivity;
import net.fumapp_android.util.NetworkConnection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class LoginActivity extends BaseActivity implements BasePresenter.view {

    @BindView(R.id.edit_text_username)
    EditText username;

    @BindView(R.id.edit_text_password)
    EditText password;

    @BindView(R.id.text_input_layout_registry)
    TextInputLayout textInputLayoutRegistry;

    @BindView(R.id.text_input_layout_password)
    TextInputLayout textInputLayoutPassword;

    private static BasePresenter.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Preferences.getInstance().getCurrentStudent() != null)
            successAttempt();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (presenter == null) {
            presenter = new Presenter();
        }
        presenter.setView(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @OnClick(R.id.button_send)
    public void onClick() {

        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

            textInputLayoutRegistry.setErrorEnabled(false);
            textInputLayoutPassword.setErrorEnabled(false);

            if (NetworkConnection.isNetworkAvailable(this)) {

                presenter.attempt(new Student(username.getText().toString(), password.getText().toString()));
            } else {
                Toast.makeText(this, getResources().getString(R.string.general_network_message), Toast.LENGTH_SHORT).show();
            }
        } else {

            if (username.getText().toString().isEmpty()) {
                textInputLayoutRegistry.setErrorEnabled(true);
                textInputLayoutRegistry.setError(getResources().getString(R.string.activity_login_registry_error));
            } else {
                textInputLayoutRegistry.setErrorEnabled(false);
            }

            if (password.getText().toString().isEmpty()) {
                textInputLayoutPassword.setErrorEnabled(true);
                textInputLayoutPassword.setError(getResources().getString(R.string.activity_login_password_error));
            } else {
                textInputLayoutPassword.setErrorEnabled(false);
            }
        }
    }

    @Override
    public void showProgressBar(int visibility) {
        findViewById(R.id.progress_bar).setVisibility(visibility);
    }

    @Override
    public void showToast(String message) {
        showToats(this, message);
    }

    @Override
    public void successAttempt() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter = null;
    }
}
