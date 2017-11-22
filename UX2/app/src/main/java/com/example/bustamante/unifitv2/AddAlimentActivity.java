package com.example.bustamante.unifitv2;

import android.app.Activity;
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

import com.example.bustamante.unifitv2.Models.Alimento;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AddAlimentActivity extends AppCompatActivity {
    private Menu menu;
    private List<String> busquedasRecientes;
    private EditText search;
    public final static int REQ_CODE_CHILD = 1;
    private Alimento alimento;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",alimento);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) listView1.getItemAtPosition(position);
                Intent intent = new Intent(AddAlimentActivity.this, FoodActivity.class);
                alimento = new Alimento(1,"Leche", 300, 300, 300, "una taza", 400);
                intent.putExtra("objetoAlimento", (Serializable) alimento);
                startActivityForResult(intent, REQ_CODE_CHILD);
                //startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aliment);
        String str = getIntent().getStringExtra("myString");
        setTitle(str);
        search = (EditText) findViewById(R.id.search_text);
    }
}
