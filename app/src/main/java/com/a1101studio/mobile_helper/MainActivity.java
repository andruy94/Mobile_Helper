package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView password;
    TextView login;
    Button aut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (TextView) findViewById(R.id.login);
        password = (TextView) findViewById(R.id.password);
        aut = (Button) findViewById(R.id.aut);
        View.OnClickListener oclaut = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("1") && password.getText().toString().equals("1"))
                {
                    Intent intent = new Intent(MainActivity.this, List.class);
                    startActivity(intent);
                }

            }
        };
        
        aut.setOnClickListener(oclaut);

    }
}
