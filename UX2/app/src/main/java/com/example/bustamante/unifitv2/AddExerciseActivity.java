package com.example.bustamante.unifitv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddExerciseActivity extends AppCompatActivity {

    Button rutina;
    ImageView image;
    Spinner series;
    Spinner repeticiones;
    Spinner peso;
    MenuItem item;
    Intent intent;
    String nombre_rutina;
    Integer[] n_series;
    Integer[] n_repeticiones;
    Integer[] pesos;
    Button add_routine;
    AlertDialog.Builder builder;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        setTitle("Agregar ejercicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRoutineName();

        setRoutineImage();

        setSpinners();

        addRoutineBtn();
    }

    public boolean addRoutineBtn(){
        add_routine = (Button)findViewById(R.id.addRoutine);
        builder = new AlertDialog.Builder(this);
        final Intent intent = new Intent(AddExerciseActivity.this,MainActivity.class);
        intent.putExtra("señal",1);
        builder.setMessage("Rutina agregada exitosamente.").setTitle("Aviso");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
                finish();
            }
        });
        dialog = builder.create();
        add_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return true;
    }

    public boolean setRoutineName(){
        intent = getIntent();
        nombre_rutina = intent.getStringExtra("nombre");
        rutina = (Button)findViewById(R.id.routineName);
        rutina.setText(nombre_rutina);
        return true;
    }

    public boolean setRoutineImage(){
        image = (ImageView)findViewById(R.id.routineImage);
        //image.setImageResource(R.mipmap.flexiones);

        Glide.with(AddExerciseActivity.this).load(R.mipmap.flexiones).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(image);

        return true;
    }

    public boolean setSpinners(){
        series = (Spinner)findViewById(R.id.seriesSpinner);
        n_series = new Integer[]{5,10,15,20};
        series.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,n_series));

        repeticiones = (Spinner)findViewById(R.id.repeticionesSpinner);
        n_repeticiones = new Integer[]{10,15,20,25,30};
        repeticiones.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,n_repeticiones));

        peso = (Spinner)findViewById(R.id.pesoSpinner);
        pesos = new Integer[]{20,25,30,35,40};
        peso.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,pesos));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
