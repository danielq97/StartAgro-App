package co.potes.icesi.startagrocol;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.potes.icesi.startagrocol.model.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseDatabase db;
    FirebaseAuth auth;

    private EditText et_user;
    private EditText et_age;
    private EditText et_mail;
    private EditText et_pass;
    private Button btn_reg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


     //   et_user = findViewById(R.id.et_user);
      //  et_age = findViewById(R.id.et_edad);
      //  et_mail = findViewById(R.id.et_mail);
        et_pass = findViewById(R.id.et_pass);
       // btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);


        DatabaseReference reference = db.getReference().child("estudiantes");
        reference.setValue("Victor");






    }

    @Override
    public void onClick(View v) {
        if(v.equals(btn_reg)){
            String nombre = et_user.getText().toString();
            String email = et_mail.getText().toString();
            String edad = et_age.getText().toString();

          //  Usuario usuario = new Usuario("", nombre, email,edad);

            //registrarUsuario(usuario);

        }
    }

    private void registrarUsuario(final Usuario usuario) {
        String password = et_pass.getText().toString();
        if(password.length()<=5){
            Toast.makeText(this,"La contraseña debe ser mayor o igual a 6", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(usuario.getEmail(), password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    usuario.setUid(auth.getCurrentUser().getUid());
                    DatabaseReference reference = db.getReference()
                            .child("usuarios").child(usuario.getUid());
                    reference.setValue(usuario);

                    //Aquí me voy para el perfil

                }
                else{
                    Toast.makeText(MainActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
