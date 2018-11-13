package co.potes.icesi.startagrocol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class MisProyectos extends AppCompatActivity {

   LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);

        contenedor = findViewById(R.id.contene);

        for (int i=0;i <=1;i++){
            LinearLayout nuevo = new LinearLayout(getApplicationContext());
            nuevo.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            nuevo.setOrientation(LinearLayout.VERTICAL);
            Button btn = new Button(getApplicationContext());
            btn.setText("prueba");

            nuevo.addView(btn);

            contenedor.addView(contenedor);
        }


    }
}
