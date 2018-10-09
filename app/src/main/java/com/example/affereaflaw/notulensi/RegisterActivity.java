package com.example.affereaflaw.notulensi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private String userKey;
    private Button btnRegister;
    private EditText etNama, etEmail, etSandi;
    private FirebaseAuth auth;
    private DatabaseReference dbUser;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSandi = (EditText) findViewById(R.id.etSandi);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nama = etNama.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String password = etSandi.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukkan email Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukkan password Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password terlalu pendek, minimal 6 karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.setMessage("Register...");
                progress.show();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "Pembuatan akun berhasil", Toast.LENGTH_SHORT).show();
                                userKey = auth.getCurrentUser().getUid();
                                dbUser = FirebaseDatabase.getInstance().getReference().child("Users").child(userKey);
                                HashMap<String, String> userMap = new HashMap<>();
                                userMap.put("nama", nama);
                                userMap.put("email", email);
                                dbUser.setValue(userMap);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Pembuatan akun gagal" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                } else {
                                    Intent i = new Intent(RegisterActivity.this, KodeActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
