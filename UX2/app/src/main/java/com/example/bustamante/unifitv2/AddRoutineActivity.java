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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddRoutineActivity extends AppCompatActivity {

    ListView lista_rutinas;
    ArrayList<String> lista;
    MyAdapter adapter;
    Button search;
    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        setTitle("Agregar rutina");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search = (Button)findViewById(R.id.buscar_rutina);
        nombre = (EditText)findViewById(R.id.routine_name);
        lista = new ArrayList<>();
        searchBtn();
    }

    public boolean searchBtn(){

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setListView();

            }
        });
        return true;
    }

    public boolean setListView(){
        lista.add(nombre.getText().toString());
        lista_rutinas = (ListView)findViewById(R.id.rutinas);
        adapter = new MyAdapter(AddRoutineActivity.this,R.layout.custom_row,lista);
        lista_rutinas.setAdapter(adapter);
        lista_rutinas.setClickable(true);
        lista_rutinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddRoutineActivity.this).setTitle("Agregar rutina");
                builder.setMessage("¿Estás seguro que deseas agregar la rutina "+nombre.getText().toString()+"?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddRoutineActivity.this,MainActivity.class);
                        intent.putExtra("señal2",1);
                        intent.putExtra("routine",nombre.getText().toString());
                        startActivity(intent);
                        finish();
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

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(final int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.custom_row, null);
            }

            TextView rutina = (TextView) fila.findViewById(R.id.routine);
            final String rutina2 = getItem(position);
            rutina.setText(rutina2);


            return fila;
        }
    }
}
