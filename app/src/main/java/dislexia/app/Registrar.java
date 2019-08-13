package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;
import dislexia.app.Modelo.*;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Registrar extends AppCompatActivity {

    private EditText nombreTxt,apellidoTxt,dniTxt,edadTxt,usuarioTxt,password,especialidadTxt,matriculaTxt;
    private RadioButton masculinoRb,femeninoRb;
    private CheckBox especialistaCb;
    private Button botonRegistrar;
    private View viewEspecialista;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        nombreTxt = (EditText) findViewById(R.id.nombreTxt);
        apellidoTxt = (EditText) findViewById(R.id.apellidoTxt);
        dniTxt = (EditText) findViewById(R.id.dniTxt);
        edadTxt= (EditText) findViewById(R.id.edadTxt);
        usuarioTxt = (EditText) findViewById(R.id.usuarioTxt);
        password = (EditText) findViewById(R.id.pass);
        especialidadTxt = (EditText) findViewById(R.id.especialidadTxt);
        matriculaTxt = (EditText) findViewById(R.id.matriculaTxt);
        masculinoRb = (RadioButton) findViewById(R.id.masculinorb);
        femeninoRb = (RadioButton) findViewById(R.id.femeninorb);
        especialistaCb = (CheckBox) findViewById(R.id.especialistaCb);
        viewEspecialista =(View) findViewById(R.id.viewEspecialista) ;
        viewEspecialista.setVisibility(View.GONE);

        especialistaCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    viewEspecialista.setVisibility(View.VISIBLE);
                }
                else{
                    viewEspecialista.setVisibility(View.INVISIBLE);
                }
            }
        });
        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);





        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }



    public void registrarBoton(View view){

        Persona persona = new Persona();
        Usuario usuario = new Usuario();

        String idPersona = UUID.randomUUID().toString();
        String user = usuarioTxt.getText().toString();
        String pass = password.getText().toString();
        String nombre = nombreTxt.getText().toString();
        String apellido = apellidoTxt.getText().toString();
        String dni = dniTxt.getText().toString();
        int edad = Integer.parseInt(edadTxt.getText().toString());

        boolean especialista_nino;
        String especialidad;
        String matricula;
        boolean sexo=false;
        if(masculinoRb.isChecked()){
            sexo=true;
        }

        if(viewEspecialista.getVisibility() == View.VISIBLE){

            especialista_nino=true; // es especialista
            matricula = matriculaTxt.getText().toString();
            especialidad = especialidadTxt.getText().toString();

        }
        else{
            especialista_nino= false; // es ninio
            matricula= null;
            especialidad= null;
        }
        boolean respuesta = usuario.registrarUsuario(user,pass,idPersona,databaseReference, this);
        Toast.makeText(this,"paso",Toast.LENGTH_LONG);
        if( respuesta=true) {
            persona.registrarPersona(idPersona, nombre, apellido, dni, edad, sexo, especialista_nino, especialidad, matricula, databaseReference);
        }




    }








}
