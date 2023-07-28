package com.isil.efinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.isil.efinal.adapter.OrdenesAdapter;
import com.isil.efinal.entity.Orden;

import java.util.ArrayList;
import java.util.List;

public class OrdenesActivity extends AppCompatActivity {

    private ListView listViewOrdenes;
    private List<Orden> ordenesList;
    private OrdenesAdapter ordenAdapter;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenes);

        listViewOrdenes = findViewById(R.id.listViewOrdenes);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        ordenesList = new ArrayList<>();
        ordenAdapter = new OrdenesAdapter(this, ordenesList);
        listViewOrdenes.setAdapter(ordenAdapter);

        obtenerOrdenesDelUsuarioActual();
    }

    private void obtenerOrdenesDelUsuarioActual() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userID = currentUser.getUid();

            CollectionReference ordenesRef = db.collection("ordenes");
            ordenesRef.whereEqualTo("idUsuario", userID)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            ordenesList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Orden orden = document.toObject(Orden.class);
                                ordenesList.add(orden);
                            }
                            ordenAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }
}
