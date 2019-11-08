package com.inkeox.area11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //savedInstanceState.putInt("TEST", 54);
        setContentView(R.layout.activity_main);
    }

    public void creerEntrainement(View view) {
        Intent intent = new Intent(this, CreerEntrainementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void listeEntrainements(View view) {
        Intent intent = new Intent(this, LancerEntrainementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
