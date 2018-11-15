package co.potes.icesi.startagrocol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.potes.icesi.startagrocol.model.AdaptadorListaHome;
import co.potes.icesi.startagrocol.model.Proyecto;

public class Home extends AppCompatActivity {


    private ImageButton btnBusqueda;
    private EditText txtBusqueda;
    private ListView lista;
    private AdaptadorListaHome adaptadorListas;
    private ArrayList<Proyecto> proyectos;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String applicationID = "301664";

        btnBusqueda = findViewById(R.id.btnBusqueda);
        txtBusqueda = findViewById(R.id.txtBusqueda);
        lista = findViewById(R.id.lista);
        adaptadorListas = new AdaptadorListaHome(this);
        lista.setAdapter(adaptadorListas);
        proyectos = new ArrayList<Proyecto>();

        db = FirebaseDatabase.getInstance();

        DatabaseReference reference = db.getReference().child("Proyectos");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String titulo =(String) postSnapshot.child("Titulo").getValue();
                    String url = (String) postSnapshot.child("url").getValue();
                    String descripcion = (String) postSnapshot.child("Descripcion").getValue();


                    Proyecto proyecto = new Proyecto();

                    proyecto.setDescripcion(descripcion);
                    proyecto.setTitulo(titulo);
                    proyecto.setUrl(url);


                    adaptadorListas.agregarLista(proyecto);





                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                adaptadorListas.limpiar();

                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
