package com.isil.efinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.isil.efinal.adapter.PlatosAdapter;
import com.isil.efinal.entity.Plato;

import java.util.ArrayList;
import java.util.List;

public class PlatosActivity extends AppCompatActivity {

    private RecyclerView rvPlatos;
    private PlatosAdapter platosAdapter;
    private List<Plato> listaPlatos;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        rvPlatos = findViewById(R.id.rvPlatos);
        rvPlatos.setLayoutManager(new LinearLayoutManager(this));
        listaPlatos = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        listaPlatos.add(new Plato("1", "Tallarines Rojos", 20.00, R.drawable.tallarin));
        listaPlatos.add(new Plato("2", "Anticuchos Criollos", 30.00, R.drawable.anticucho));
        listaPlatos.add(new Plato("3", "Chau de pollo", 14.00,  R.drawable.chaufa));
        listaPlatos.add(new Plato("4", "Caldo de Gallina", 8.00,  R.drawable.caldo));
        listaPlatos.add(new Plato("5", "Cheesecake", 5.00,  R.drawable.cheesecake));
        listaPlatos.add(new Plato("6", "Papa Rellena", 7.00, R.drawable.papa));
        listaPlatos.add(new Plato("7", "Ramen", 2.00, R.drawable.ramen));
        listaPlatos.add(new Plato("8", "Torta de Chocolate", 10.00, R.drawable.torta));

        platosAdapter = new PlatosAdapter(this, listaPlatos);
        rvPlatos.setAdapter(platosAdapter);
    }

}
