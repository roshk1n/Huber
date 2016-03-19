package com.example.roshk1n.test_hubert_dreyfus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class help_activity extends AppCompatActivity {
    TextView tvAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText(R.string.tvAbout);
    }
}
