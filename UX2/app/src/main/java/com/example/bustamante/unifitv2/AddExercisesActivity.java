package com.example.bustamante.unifitv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class AddExercisesActivity extends AppCompatActivity {

    TabHost tab;
    TabHost.TabSpec spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);
        setTitle("Agregar ejercicios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabs();

        SelectAreaFragment saf = new SelectAreaFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container2,saf).commit();
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
}
