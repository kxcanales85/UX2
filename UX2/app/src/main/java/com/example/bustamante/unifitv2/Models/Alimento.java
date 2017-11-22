package com.example.bustamante.unifitv2.Models;

import java.io.Serializable;

/**
 * Created by bustamante on 21-11-17.
 */

public class Alimento implements Serializable{

    // Labels table name
    public static final String TABLE = "Alimento";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nombre = "nombre";
    public static final String KEY_carbos = "carbos";
    public static final String KEY_proteinas = "proteinas";
    public static final String KEY_grasas = "grasas";
    public static final String KEY_porcion = "porcion";
    public static final String KEY_calorias = "calorias";

    // property help us to keep data
    private int alimento_ID;
    private String nombre;
    private int carbos;
    private int proteinas;
    private int grasas;
    private String porcion;
    private int calorias;

    public Alimento(int alimento_ID, String nombre, int carbos, int proteinas, int grasas, String porcion, int calorias) {
        this.alimento_ID = alimento_ID;
        this.nombre = nombre;
        this.carbos = carbos;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.porcion = porcion;
        this.calorias = calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setAlimento_ID(int alimento_ID) {
        this.alimento_ID = alimento_ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarbos(int carbos) {
        this.carbos = carbos;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public void setGrasas(int grasas) {
        this.grasas = grasas;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }

    public int getAlimento_ID() {
        return alimento_ID;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCarbos() {
        return carbos;
    }

    public int getProteinas() {
        return proteinas;
    }

    public int getGrasas() {
        return grasas;
    }

    public String getPorcion() {
        return porcion;
    }

    public int getCalorias() {
        return calorias;
    }
}
