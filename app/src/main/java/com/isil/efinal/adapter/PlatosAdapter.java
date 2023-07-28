package com.isil.efinal.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.isil.efinal.R;
import com.isil.efinal.entity.Orden;
import com.isil.efinal.entity.Plato;

import java.util.List;

public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.PlatosViewHolder> {

    private Context context;
    private List<Plato> platosList;
    // Variable para almacenar el listener de clic\
    private FirebaseAuth mAuth;

    public PlatosAdapter(Context context, List<Plato> platosList) {
        this.context = context;
        this.platosList = platosList;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public PlatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plato, parent, false);
        return new PlatosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatosViewHolder holder, int position) {
        Plato plato = platosList.get(position);
        holder.tvNombrePlato.setText(plato.getNomPlato());
        holder.tvPrecioPlato.setText("S/ "+(plato.getPrecio()));
        holder.ivPlato.setImageResource(plato.getNomImagen());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoCantidad(plato);
            }
        });

    }


    @Override
    public int getItemCount() {
        return platosList.size();
    }

    public static class PlatosViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombrePlato;
        private TextView tvPrecioPlato;
        private ImageView ivPlato;

        public PlatosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombrePlato = itemView.findViewById(R.id.tvNombrePlato);
            tvPrecioPlato = itemView.findViewById(R.id.tvPrecioPlato);
            ivPlato = itemView.findViewById(R.id.imgPlato);
        }
    }
    // Método para mostrar el AlertDialog con campo de entrada para la cantidad
    private void mostrarDialogoCantidad(Plato plato) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Agregar Cantidad");

        final EditText inputCantidad = new EditText(context);
        inputCantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputCantidad.setHint("Ingrese la cantidad");
        builder.setView(inputCantidad);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cantidadStr = inputCantidad.getText().toString();
                if (!TextUtils.isEmpty(cantidadStr)) {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0) {
                        registrarOrden(plato, cantidad);
                    } else {
                        Toast.makeText(context, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Ingrese la cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    // Método para registrar la orden en Firestore con la cantidad ingresada por el usuario
    private void registrarOrden(Plato plato, int cantidad) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userID = currentUser.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference ordenesRef = db.collection("ordenes");
            DocumentReference nuevaOrdenRef = ordenesRef.document();

            Orden orden = new Orden(nuevaOrdenRef.getId(), userID, plato.getId(), plato.getNomPlato(), plato.getPrecio(), cantidad,(plato.getPrecio()* cantidad) );
            nuevaOrdenRef.set(orden)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Orden registrada correctamente", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error al registrar la orden", Toast.LENGTH_SHORT).show();
                    });
        }
    }

}
