package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bustamante.unifitv2.Models.Alimento;

public class ListFoodActivity extends AppCompatActivity {

    private static final int MENU_ITEM_ITEM1 = 0;
    private Alimento alimento, ejemplo;
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
        System.out.println(tittle);
        switch (tittle){
            case "\t\tDesayuno":
                adapter.add("Pan");
                adapter.add("Mantequilla");
                adapter.add("Queso");
                break;
            case "\t\tAlmuerzo":
                adapter.add("Charquican");
                adapter.add("Lechuga");
                adapter.add("Huevo");
                break;
            case "\t\tCena":
                adapter.add("Pan");
                adapter.add("Jamón");
                break;
            case "\t\tOtros":
                adapter.add("Papas fritas");
                adapter.add("Bebida");
                adapter.add("Super 8");
                break;
            default:
                System.out.println("Default option in ListFoodActivuty");
                break;
        }
        ejemplo = new Alimento(1,"Ejemplo", 300, 300, 300, "100gr", 500);
        listaComidas.setAdapter(adapter);
        listaComidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(ListFoodActivity.this)
                        .setTitle(adapter.getItem(position))
                        .setMessage("Porción: "+ejemplo.getPorcion()+
                        "\nCalorías: "+ejemplo.getCalorias()+"gr")
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
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
