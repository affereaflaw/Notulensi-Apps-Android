package com.example.affereaflaw.notulensi;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Affe Reaflaw on 11/5/2017.
 */
public class Offline extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
