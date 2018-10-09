package com.example.affereaflaw.notulensi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotulensiViewActivity extends AppCompatActivity {

    private String kode;
    private EditText etJudul, etTanggal, etNotulen, etNotulensi;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notulensi_view);

        kode = getIntent().getExtras().getString("kode");
        etJudul = (EditText) findViewById(R.id.etJudul);
        etTanggal = (EditText) findViewById(R.id.etTanggal);
        etNotulen = (EditText) findViewById(R.id.etNotulen);
        etNotulensi = (EditText) findViewById(R.id.etNotulensi);

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
    }
}
