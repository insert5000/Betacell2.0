package com.example.betacell.BaseDatos;

public class GlucosaDB {


    public String id;
    public String usuario;
    public String medicionGlucosa;
    public String condicion;
    public String fecha;

    public GlucosaDB(String id, String registro, String pulsos, String fecha,String usuario) {
        this.id = id;
        this.medicionGlucosa = medicionGlucosa;
        this.condicion = condicion;
        this.fecha = fecha;
        this.usuario=usuario;
    }

    public GlucosaDB(){

    }

    public String getMedicionGlucosa(){return medicionGlucosa;}
    public void setMedicionGlucosa(String registro){this.medicionGlucosa=medicionGlucosa;}

    public String getCondicion(){return condicion;}
    public void setCondicion(String pulsos){this.condicion=condicion;}

    public String getFecha(){return fecha;}
    public void setFecha(String fecha){this.fecha=fecha;}

}
