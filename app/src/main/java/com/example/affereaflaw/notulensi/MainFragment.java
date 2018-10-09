package com.example.affereaflaw.notulensi;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        final Button btnSimpan = (Button) view.findViewById(R.id.btnSimpan);
        final EditText etJudul = (EditText) view.findViewById(R.id.etJudul);
        final EditText etTanggal = (EditText) view.findViewById(R.id.etTanggal);
        final EditText etNotulen = (EditText) view.findViewById(R.id.etNotulen);
        final EditText etNotulensi = (EditText) view.findViewById(R.id.etNotulensi);
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Notulensi");
        databaseReference.keepSynced(true);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String judul = etJudul.getText().toString();
                final String tanggal = etTanggal.getText().toString();
                final String notulen = etNotulen.getText().toString();
                final String notulensi = etNotulensi.getText().toString();
                final String currentId = auth.getCurrentUser().getUid();
                SharedPreferences mPrefs = getActivity().getSharedPreferences("label", 0);
                String kode = mPrefs.getString("tag", "0");
                if (!TextUtils.isEmpty(judul)&&!TextUtils.isEmpty(tanggal)&&!TextUtils.isEmpty(notulen)&&!TextUtils.isEmpty(notulensi)){
                    DatabaseReference newNotulensi = databaseReference.push();
                    newNotulensi.child("judul").setValue(judul);
                    newNotulensi.child("tanggal").setValue(tanggal);
                    newNotulensi.child("notulen").setValue(notulen);
                    newNotulensi.child("notulensi").setValue(notulensi);
                    newNotulensi.child("kode").setValue(kode);
                    newNotulensi.child("id").setValue(currentId);
                    Toast.makeText(getActivity(), "Notulensi berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getActivity(), "Notulensi gagal disimpan, pastikan semua terisi dan terkoneksi internet", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
