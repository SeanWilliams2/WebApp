package edu.temple.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PageControlFragment palletFragment = new PageControlFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1,palletFragment)
                .commit();
    }
}