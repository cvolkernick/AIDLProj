package com.example.cvolk.aidlproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static LocalDataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new LocalDataSource(getApplicationContext(), ParcelableObject.getContract());

        try {
            db.saveObject(new ParcelableObject(0, "Object1"));
            db.saveObject(new ParcelableObject(1, "Object2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
