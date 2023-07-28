package com.isil.efinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Platos(View view) {
        startActivity(new Intent(HomeActivity.this,PlatosActivity.class));
    }

    public void Ordenes(View view) {
        startActivity(new Intent(HomeActivity.this,OrdenesActivity.class));
    }
}