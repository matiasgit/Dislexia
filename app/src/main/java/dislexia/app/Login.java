package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dislexia.app.Modelo.Usuario;

public class Login extends AppCompatActivity {

    private EditText nombreUsuario,password;
    private Button ingresarBoton;
    private Button registrarBoton;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        nombreUsuario= (EditText) findViewById(R.id.nombreUsuariotxt);
        password= (EditText) findViewById(R.id.pass);
        ingresarBoton = (Button) findViewById(R.id.buttonIngresar) ;
        registrarBoton = (Button) findViewById(R.id.botonRegistarLogin) ;
        inicializarFirebase();

    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }


    public void ingresar(View v){

        String userName = nombreUsuario.getText().toString();
        String pass = password.getText().toString();
        Usuario usuario = new Usuario();
        usuario.userExiste(userName,this,databaseReference);


    }


    public void registrarLogin(View v){

        Intent intent = new Intent(this,Registrar.class);
        startActivity(intent);

    }









}
