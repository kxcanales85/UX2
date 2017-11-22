package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bustamante.unifitv2.Models.Alimento;

import org.w3c.dom.Text;

public class FoodActivity extends AppCompatActivity {

    Button reportar, guardar;
    TextView nombreAlimento, porcion, carbos, proteinas, grasas, calorias;
    Menu menu;
    private Alimento alimento;

    public void guardar(View v){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.cancelar:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        alimento = (Alimento) getIntent().getSerializableExtra("objetoAlimento");
        setTitle(alimento.getNombre());
        reportar = (Button) findViewById(R.id.botonreportar);
        guardar = (Button) findViewById(R.id.botonguardar);
        nombreAlimento = (TextView) findViewById(R.id.nombre_alimento);
        System.out.println(alimento.getNombre());
        nombreAlimento.setText((String) alimento.getNombre());

        porcion = (TextView) findViewById(R.id.porciones);
        porcion.setText(alimento.getPorcion());

        carbos = (TextView) findViewById(R.id.cantidadcarbos);
        carbos.setText(String.valueOf(alimento.getCarbos()));

        proteinas = (TextView) findViewById(R.id.cantidadproteinas);
        proteinas.setText(String.valueOf(alimento.getProteinas()));

        grasas = (TextView) findViewById(R.id.cantidadgrasas);
        grasas.setText(String.valueOf(alimento.getGrasas()));

        calorias = (TextView) findViewById(R.id.cantidadcalorias);
        calorias.setText(String.valueOf(alimento.getCalorias()));

        reportar.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                // Declare your builder here -
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        FoodActivity.this);
                builder.setMessage(R.string.mensajesReportar);
                // Seting an EditText view to get user input
                final EditText input = new EditText(FoodActivity.this);
                builder.setView(input);
                builder.setPositiveButton(R.string.send,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });

                builder.show();
                return true;
            }
        });

     }
}
