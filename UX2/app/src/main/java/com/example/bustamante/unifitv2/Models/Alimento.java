package com.example.bustamante.unifitv2.Models;

/**
 * Created by bustamante on 21-11-17.
 */

public class Alimento {

    // Labels table name
    public static final String TABLE = "Alimento";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nombre = "nombre";
    public static final String KEY_carbos = "carbos";
    public static final String KEY_proteinas = "proteinas";
    public static final String KEY_grasas = "grasas";
    public static final String KEY_porcion = "porcion";

    // property help us to keep data
    public int alimento_ID;
    public String nombre;
    public int carbos;
    public int proteinas;
    public int grasas;
    public int porcion;
}
