package com.a1101studio.mobile_helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;


public class LoginActivity1 extends AppCompatActivity {
    TextView password;
    TextView login;
    Button aut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        login = (TextView) findViewById(R.id.login);
        password = (TextView) findViewById(R.id.password);
        aut = (Button) findViewById(R.id.aut);
        loginAndPassword();
        View.OnClickListener oclaut = v -> {
            if(login.getText().toString().equals("login") && password.getText().toString().equals("password")) {
                Intent intent = new Intent(LoginActivity1.this, List.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder alert=new AlertDialog.Builder(LoginActivity1.this);
                alert.setMessage(R.string.auth_error).setPositiveButton(R.string.Confirm, (dialog, which) -> dialog.cancel()).show();
            }
        };

        aut.setOnClickListener(oclaut);

    }

    void loginAndPassword(){
        Observable.combineLatest(
                RxTextView.textChanges(login),
                RxTextView.textChanges(password),
                (login,password)->login.length()>0 && password.length()>0
        ).subscribe(aut::setEnabled);
    }
}
