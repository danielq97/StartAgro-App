package co.potes.icesi.startagrocol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Publicar extends AppCompatActivity {

    private FirebaseDatabase db;

    private EditText et_titulo;
    private EditText et_descripcion;
    private EditText et_caracteristicas;
    private Button btnAgregarFotoPrimaria;
    private Button btnAgregarFotoSecundaria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar);
        db = FirebaseDatabase.getInstance();

        et_titulo = findViewById(R.id.et_titulo);

        btnAgregarFotoPrimaria = findViewById(R.id.btn_agregarfotoPrim);

        et_descripcion = findViewById(R.id.et_descripcion);





        DatabaseReference reference = db.getReference();


    }
}
