package co.potes.icesi.startagrocol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Proyectos extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private Button cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyectos);
        cerrarSesion = findViewById(R.id.btn_cerrarsesion);
        cerrarSesion.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {

        if (v.equals(cerrarSesion)) {

            if(auth.getCurrentUser()!=null){
                auth.signOut();
            }
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }


    }
}
