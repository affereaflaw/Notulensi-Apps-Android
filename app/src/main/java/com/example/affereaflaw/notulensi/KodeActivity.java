package com.example.affereaflaw.notulensi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KodeActivity extends AppCompatActivity {

    private Button btnKode;
    private EditText etKode, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode);

        btnKode = (Button) findViewById(R.id.btnKode);
        etKode = (EditText) findViewById(R.id.etKode);
        etPass = (EditText) findViewById(R.id.etPass);

        btnKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kode = etKode.getText().toString().trim();
                final String password = etPass.getText().toString().trim();
                final String enroll = kode + password;
                SharedPreferences mPrefs = getSharedPreferences("label", 0);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putString("tag", enroll).apply();
                Intent intent = new Intent(KodeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
