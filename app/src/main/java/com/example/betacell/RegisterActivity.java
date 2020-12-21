package com.example.betacell;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.betacell.BaseDatos.DatabaseHelper;
import com.example.betacell.BaseDatos.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends FragmentActivity {

    //Declaracion de los EditTexts
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;

    //Dlecaracion de los  TextInputLayout
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonRegister;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);
        initTextViewLogin();

        initViews();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDatos()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Se verifica si el email ya se registro con otro usuario
                    if (!databaseHelper.isEmailExists(Email)) {

                        //Si no existe el correo se agrega el usuario nuevo
                        databaseHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "Usuario creado! Ingresar ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        Snackbar.make(buttonRegister, "Usuario ya registrado", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }


    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //Validar los text
    public boolean validarDatos() {
        boolean validar = false;

        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Validacion campo usuario
        if (UserName.isEmpty()) {
            validar = false;
            textInputLayoutUserName.setError("Ingrese usuario valido!");
        } else {
            if (UserName.length() > 5) {
                validar = true;
                textInputLayoutUserName.setError(null);
            } else {
                validar = false;
                textInputLayoutUserName.setError("nombre de usuario muy corto!");
            }
        }

        //Validacion para el correo
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            validar = false;
            textInputLayoutEmail.setError("Ingrese correo valido!");
        } else {
            validar = true;
            textInputLayoutEmail.setError(null);
        }

        //Validacion de contraseña
        if (Password.isEmpty()) {
            validar = false;
            textInputLayoutPassword.setError("Ingrese contraseña valida!");
        } else {
            if (Password.length() > 5) {
                validar = true;
                textInputLayoutPassword.setError(null);
            } else {
                validar = false;
                textInputLayoutPassword.setError("Contraseña muy corta!");
            }
        }


        return validar;
    }


}