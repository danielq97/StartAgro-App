package co.potes.icesi.startagrocol;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MisProyectos extends AppCompatActivity {

    LinearLayout contenedor;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);

        contenedor = findViewById(R.id.contene);


        db = FirebaseDatabase.getInstance();
        for (int i = 0; i <= 1; i++) {
            LinearLayout nuevo = new LinearLayout(getApplicationContext());
            //nuevo.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            nuevo.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(5, 15, 5, 0);


            Button btn = new Button(getApplicationContext());

            btn.setText("Editar");
            Button btn1 = new Button(getApplicationContext());

            btn1.setText("Inversiones");

            Button btn2 = new Button(getApplicationContext());

            btn2.setText("Eliminar");
            Button btn3 = new Button(getApplicationContext());

            btn3.setText("Publicar");

            nuevo.addView(btn);
            nuevo.addView(btn1);
            nuevo.addView(btn2);
            nuevo.addView(btn3);
            contenedor.addView(nuevo);


            }
            //    contenedor = findViewById(R.id.contene);
            // ArrayList<check> lista = new ArrayList<check>();
            //   lista.add(new check(1,"Opcion1"));
            //  lista.add(new check(3,"Opcion2"));
            //  lista.add(new check(4,"Opcion3"));
            //lista.add(new check(41,"Opcion4"));
            // lista.add(new check(15,"Opcion5"));

            //  for (check c : lista){
            //  CheckBox cb = new CheckBox(getApplicationContext());
            //cb.setText(c.nombre);
            //cb.setId(c.cod);
            //  cb.setTextColor(Color.BLACK);
            // contenedor.addView(cb);
            //  cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //     @Override
            //    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //       Toast.makeText(getApplicationContext(),"Checkbox: " + buttonView.getId(),Toast.LENGTH_SHORT).show();
            //     }
            //   });
            // }
        }

        class check {
            public int cod;
            public String nombre;

            public check(int cod, String nombre) {
                this.cod = cod;
                this.nombre = nombre;
            }


        }

    }
