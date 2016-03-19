package com.example.roshk1n.test_hubert_dreyfus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    EditText etRUsername,etRPassword,etRPassword2;
    DB db = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRUsername = (EditText) findViewById(R.id.etRUsername);
        etRPassword = (EditText) findViewById(R.id.etRPassword);
        etRPassword2 = (EditText) findViewById(R.id.etRPassword2);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRegister:
                db.open();
                String username = etRUsername.getText().toString();
                String password = etRPassword.getText().toString();
                if(password.equals(etRPassword2.getText().toString()))
                {
                    User user = new User(username,password);
                    db.inserUser(user);
                    startActivity(new Intent(this,LoginActivity.class));
                    db.close();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Password don`t match!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
