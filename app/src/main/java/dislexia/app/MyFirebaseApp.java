package dislexia.app;

import com.google.firebase.database.FirebaseDatabase;


public class MyFirebaseApp extends android.app.Application {
//Agregar Persistencia a Firebase
  @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
