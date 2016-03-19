package com.example.roshk1n.test_hubert_dreyfus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    DB db = new DB(this);;
    Button btnLog;
    EditText etPassword, etUserName;
    TextView tvregisterLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUserName = (EditText) findViewById(R.id.etUsername);
        btnLog = (Button) findViewById(R.id.btnLogin);
        tvregisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        btnLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                db.open();
                String pass= etPassword.getText().toString();
                String username = etUserName.getText().toString();

                if(db.verification(username,pass)) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("name",username);
                    intent.putExtra("pas",pass);
                    startActivity(intent);
                }
                db.close();
                break;
        }
    }
    public void goRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));

    }
}
