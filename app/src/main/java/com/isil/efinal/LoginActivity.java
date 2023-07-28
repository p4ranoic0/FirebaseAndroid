package com.isil.efinal;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText txtCorreo, txtPassword;
    private Button btnLogin, btnRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        txtCorreo = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnIniciarSesion);
        btnRegister = findViewById(R.id.btnRegistrarUsuario);
        btnLogin.setOnClickListener(v -> iniciarSesion());
        btnRegister.setOnClickListener(v -> abrirRegistroUsuario());
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText().toString().trim();
        String contraseña = txtPassword.getText().toString().trim();

        if (correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el correo y la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this,HomeActivity.class));
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al iniciar sesión: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void mostrarRecuperarContraseña(View view) {
        String correo = txtCorreo.getText().toString().trim();

        if (!correo.isEmpty()) {
            mAuth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Se envió un correo para recuperar la contraseña", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Error al enviar el correo: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirRegistroUsuario() {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
