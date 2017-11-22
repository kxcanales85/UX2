package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddExercisesActivity extends AppCompatActivity {

    TabHost tab;
    TabHost.TabSpec spec;
    ListView lista_por_nombre;
    ArrayList<String> ejercicios;
    MyAdapter adaptador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);
        setTitle("Agregar ejercicios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabs();

        SelectAreaFragment saf = new SelectAreaFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container2,saf).commit();

        setListView();
    }

    public boolean setListView(){
        ejercicios = new ArrayList<>();
        lista_por_nombre = (ListView)findViewById(R.id.listViewEjercicios);
        ejercicios.add("Sentadillas");
        ejercicios.add("Crunch");
        ejercicios.add("Abdominales");
        ejercicios.add("Press en banca");
        ejercicios.add("Flexiones de brazo");
        ejercicios.add("Burpees");
        ejercicios.add("Fondos");
        ejercicios.add("Jalones");
        ejercicios.add("Lagartijas");
        adaptador2 = new MyAdapter(this,R.layout.custom_row,ejercicios);
        lista_por_nombre.setAdapter(adaptador2);
        lista_por_nombre.setClickable(true);
        lista_por_nombre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean setTabs(){
        tab = (TabHost)findViewById(R.id.tabhost);
        tab.setup();

        spec = tab.newTabSpec("Por músculo");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Por músculo");
        tab.addTab(spec);

        spec = tab.newTabSpec("Por nombre");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Por nombre");
        tab.addTab(spec);

        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.custom_row, null);
            }

            TextView rutina_view = (TextView) fila.findViewById(R.id.routine);

            String rutina = getItem(position);
            rutina_view.setText(rutina);

            return fila;
        }
    }
}
