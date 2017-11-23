package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditRoutineActivity extends AppCompatActivity {

    ListView lista_ejercicios;
    ArrayList<String> ejercicios = new ArrayList<>();
    MyAdapter adaptador;
    Button agregar_ejercicio;
    ImageView delete_exercise;
    private static final int MENU_ITEM_ITEM1 = 0;
    MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);

        Intent intent = getIntent();
        String rutina = intent.getStringExtra("rutina");

        setTitle(rutina);

        setListView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getIntExtra("señal",0)==1){
            addElement();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        item = menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Agregar");
        item.setIcon(android.R.drawable.ic_input_add);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case MENU_ITEM_ITEM1:
                Intent intent = new Intent(EditRoutineActivity.this,AddExercisesActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*public boolean listenerButton(){
        agregar_ejercicio = (Button)findViewById(R.id.btnAddExercises);
        agregar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRoutineActivity.this,AddExercisesActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }*/

    public boolean setListView(){

        lista_ejercicios = (ListView)findViewById(R.id.listViewExercises);
        ejercicios.add("Pull-ups");
        ejercicios.add("Chin-ups");
        ejercicios.add("Remo");
        ejercicios.add("Encogimientos");
        ejercicios.add("Pullover");

        adaptador = new MyAdapter(this,R.layout.list_edit_routine,ejercicios);

        lista_ejercicios.setAdapter(adaptador);

        return true;
    }

    public boolean deleteEelement(int pos){
        ejercicios.remove(pos);
        lista_ejercicios = (ListView)findViewById(R.id.listViewExercises);
        adaptador = new MyAdapter(this,R.layout.list_edit_routine,ejercicios);
        lista_ejercicios.setAdapter(adaptador);
        return true;
    }

    public boolean addElement(){
        ejercicios.add("Flexiones de brazo");
        lista_ejercicios = (ListView)findViewById(R.id.listViewExercises);
        adaptador = new MyAdapter(this,R.layout.list_edit_routine,ejercicios);
        lista_ejercicios.setAdapter(adaptador);
        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(final int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.list_edit_routine, null);
            }

            TextView ejercicio = (TextView) fila.findViewById(R.id.exerciseEdit);
            TextView descripcion = (TextView) fila.findViewById(R.id.exerciseEditDesc);
            final String rutina = getItem(position);
            ejercicio.setText(rutina);
            descripcion.setText(R.string.descripcion_rutina);

            delete_exercise = (ImageView)fila.findViewById(R.id.deleteExercise);
            delete_exercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditRoutineActivity.this).setTitle("Eliminar ejercicio");
                    builder.setMessage("¿Estás seguro que deseas eliminar el ejercicio "+rutina+"?");
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteEelement(position);
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            return fila;
        }
    }
}
