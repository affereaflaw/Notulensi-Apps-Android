package com.example.affereaflaw.notulensi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotulensiEdit extends AppCompatActivity {

    private String kode;
    private EditText etJudul, etTanggal, etNotulen, etNotulensi;
    private DatabaseReference databaseReference;
    private Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notulensi_edit);

        kode = getIntent().getExtras().getString("kode");
        etJudul = (EditText) findViewById(R.id.etJudul);
        etTanggal = (EditText) findViewById(R.id.etTanggal);
        etNotulen = (EditText) findViewById(R.id.etNotulen);
        etNotulensi = (EditText) findViewById(R.id.etNotulensi);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notulensi");
        databaseReference.keepSynced(true);

        databaseReference.child(kode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String judulView = (String) dataSnapshot.child("judul").getValue();
                String tanggalView = (String) dataSnapshot.child("tanggal").getValue();
                String notulenView = (String) dataSnapshot.child("notulen").getValue();
                String notulensiView = (String) dataSnapshot.child("notulensi").getValue();

                etJudul.setText(judulView);
                etTanggal.setText(tanggalView);
                etNotulen.setText(notulenView);
                etNotulensi.setText(notulensiView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                DatabaseReference dbNotulensi = FirebaseDatabase.getInstance().getReference().child("Notulensi");
                                dbNotulensi.child(kode).removeValue();
                                startActivity(new Intent(NotulensiEdit.this, MainActivity.class));
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(NotulensiEdit.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Apakah Anda ingin menghapus notulensi ini?").setPositiveButton("Ya", onClickListener).setNegativeButton("Tidak", onClickListener).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReferenceUpdate = databaseReference.child(kode);
                final String judul = etJudul.getText().toString();
                final String tanggal = etTanggal.getText().toString();
                final String notulen = etNotulen.getText().toString();
                final String notulensi = etNotulensi.getText().toString();

                if (!TextUtils.isEmpty(judul)&&!TextUtils.isEmpty(tanggal)&&!TextUtils.isEmpty(notulen)&&!TextUtils.isEmpty(notulensi)){
                    databaseReferenceUpdate.child("judul").setValue(judul);
                    databaseReferenceUpdate.child("tanggal").setValue(tanggal);
                    databaseReferenceUpdate.child("notulen").setValue(notulen);
                    databaseReferenceUpdate.child("notulensi").setValue(notulensi);
                    Toast.makeText(getApplicationContext(), "Update berhasil", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Update gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
