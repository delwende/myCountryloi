package com.example.eliane.burkinaloi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button bstart;
    EditText etUsername;

    private SharedPreferences saveUsername;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);
       bstart=(Button)findViewById(R.id.bstart);
       etUsername=(EditText)findViewById(R.id.etUsername);
        saveUsername = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = saveUsername.edit();
        editor.putString("username", etUsername.getText().toString());
        editor.commit();
        bstart.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bstart:

                startActivity(new Intent(this, MainActivity.class));


                break;
            /*case R.id.tvRegisterLink:
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
                break;*/
        }
    }


}
