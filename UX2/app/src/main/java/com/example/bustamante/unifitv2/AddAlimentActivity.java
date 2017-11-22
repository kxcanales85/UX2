package com.example.bustamante.unifitv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddAlimentActivity extends AppCompatActivity {
    private Menu menu;
    private List<String> busquedasRecientes;
    private EditText search;

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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(View v){
        //PROBLEMAS ACÁ NO SE REALIZA EL TOSTRING()
        final String elemento = search.getText().toString();
        //String[] busquedas = {elemento};
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(elemento);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lista);
        final ListView listView1 = (ListView) findViewById(R.id.alimentos_recientes);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String selectedFromList = (String) listView1.getItemAtPosition(position);
                Intent intent = new Intent(AddAlimentActivity.this, FoodActivity.class);
                intent.putExtra("Comida", elemento);
                startActivity(intent);
                //search.setText(selectedFromList);
            }
        });


        //Intent intent = new Intent(AddAlimentActivity.this, FoodActivity.class);
        //startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aliment);
        String str = getIntent().getStringExtra("myString");
        setTitle(str);
        search = (EditText) findViewById(R.id.search_text);
        String[] items = { "Leche", "Mantequilla", "Yogurt", "Café" };

    }
}
