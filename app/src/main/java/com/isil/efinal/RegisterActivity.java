package com.isil.efinal;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnRegistrar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmailNuevo);
        txtPassword = findViewById(R.id.txtPasswordNuevo);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuario);
        btnRegistrar.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        String correo = txtEmail.getText().toString().trim();
        String contraseña = txtPassword.getText().toString().trim();

        if (correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el correo y la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(RegisterActivity.this, "Usuario registrado con el correo: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                        switch (errorCode) {
                            case "ERROR_INVALID_EMAIL":
                                Toast.makeText(RegisterActivity.this, "El correo ingresado no es válido", Toast.LENGTH_SHORT).show();
                                break;
                            case "ERROR_EMAIL_ALREADY_IN_USE":
                                Toast.makeText(RegisterActivity.this, "El correo ingresado ya está en uso", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(RegisterActivity.this, "Error al registrar el usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
    }
}
