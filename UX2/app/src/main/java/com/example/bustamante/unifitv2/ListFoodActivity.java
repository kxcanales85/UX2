package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bustamante.unifitv2.Models.Alimento;

public class ListFoodActivity extends AppCompatActivity {

    private static final int MENU_ITEM_ITEM1 = 0;
    private Alimento alimento;
    LocalAdapter adapter;
    ListView listaComidas;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                alimento = (Alimento) data.getSerializableExtra("result");
                System.out.println("print desde el lisftfood "+alimento.getNombre());
                adapter.add(alimento.getNombre());
                adapter.notifyDataSetChanged();
                //comida1.add(alimento.getNombre());
                //listAdapter.notifyDataSetChanged();
                //System.out.println(alimento.getNombre());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        String tittle = getIntent().getStringExtra("comida");
        setTitle(tittle);
        listaComidas = (ListView) findViewById(R.id.listComidas);
        adapter = new LocalAdapter(ListFoodActivity.this, android.R.layout.simple_list_item_1);
        listaComidas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Agregar");
        item.setIcon(android.R.drawable.ic_input_add);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ITEM1:
                Intent intent = new Intent(ListFoodActivity.this, AddAlimentActivity.class);
                intent.putExtra("myString", "Desayuno");
                startActivityForResult(intent, 1);
                //startActivity(intent);
                return true;

            default:
                return false;
        }
    }

    public void finalizar(View view) {
        finish();
    }


    class LocalAdapter extends ArrayAdapter<String> {

        public LocalAdapter(Context context, int resource) {
            super(context, resource);
        }
    }
}
