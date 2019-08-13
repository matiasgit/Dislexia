package dislexia.app.Modelo;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Usuario {

    String userId;
    String user;
    String password;
    String idPersona;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



    public boolean userExiste(final String user, final Context context, DatabaseReference databaseReference){

        final boolean[] resultado = new boolean[1];

        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);

                    if(usuario.getUser() == user){
                        resultado[0] =true;


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(context, ""+resultado[0], Toast.LENGTH_SHORT).show();
        return resultado[0];
    }


    public boolean registrarUsuario(String user, String password, String idPersona, DatabaseReference databaseReference, Context context){
          boolean resultado =false;
          Usuario usuario = new Usuario();
          usuario.setUserId(UUID.randomUUID().toString());
          usuario.setUser(user);
          usuario.setIdPersona(idPersona);
          usuario.setPassword(password);

          boolean existe =usuario.userExiste(user,context, databaseReference);

          if( existe=false){

              databaseReference.child("usuario").child(usuario.getUserId()).setValue(usuario);
              resultado=true; // lo guardo
          }
          else{

              Toast.makeText(context,"Ya existe el usuario",Toast.LENGTH_LONG).show();
              resultado=false;
          }
        return resultado;
    }





}

