package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends FragmentActivity {

    public static String ID_USUARIO;
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    DatabaseHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasehelper = new DatabaseHelper(this);
        crearCuenta();
        iniciarVistas();
        final Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validamos usuario
                if (validar()) {

                    //Obtenemos valores de los editText
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Autentificamos al usuario
                    User currentUser = databasehelper.Authenticate(new User(null, null, Email, Password));

                    //Se comprueba si la autentificacion fue exitosa o no
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Bienvenido!", Snackbar.LENGTH_LONG).show();

                        ID_USUARIO = editTextEmail.getText().toString();
                        Intent intent=new Intent(MainActivity.this,Principal.class);
                        startActivity(intent);
                        finish();
                    } else {

                        //Inicio de sesion fallido
                        Snackbar.make(buttonLogin, "Email no registrado", Snackbar.LENGTH_LONG).show();


                    }
                }
            }
        });


    }

    //Metodo para abrir el activity de registro
    private void crearCuenta() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>No tengo una cuenta creada </font><font color='#26bed6'>Crear una cuenta</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //Metodo para iniciar vistas
    private void iniciarVistas() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
    public boolean validar() {
        boolean valido = false;

        //Obtenemos los valores de los editText
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Validacion para el correo
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valido = false;
            textInputLayoutEmail.setError("Ingrese email valido!");
        } else {
            valido = true;
            textInputLayoutEmail.setError(null);
        }

        //Validacion para la contraseña
        if (Password.isEmpty()) {
            valido = false;
            textInputLayoutPassword.setError("Ingrese su contraseña!");
        } else {
            if (Password.length() > 5) {
                valido = true;
                textInputLayoutPassword.setError(null);
            } else {
                valido = false;
                textInputLayoutPassword.setError("Contraseña demasiado corta!");
            }
        }

        return valido;
    }
}