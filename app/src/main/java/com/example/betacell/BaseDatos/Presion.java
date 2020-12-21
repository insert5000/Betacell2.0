package com.example.betacell.BaseDatos;

import java.io.Serializable;

public class Presion  {

    public String id;
    public String usuario;
    public String diastola;
    public String pulsos;
    public String fecha;
    public String sistola;

    public Presion(String id, String diastola, String pulsos, String fecha,String usuario,String sistola) {
        this.id = id;
        this.diastola = diastola;
        this.sistola =sistola;
        this.pulsos = pulsos;
        this.fecha = fecha;
        this.usuario=usuario;

    }

    public Presion(){

    }

    public String getDiastola(){return diastola;}
    public void setDiastola(String diastola){this.diastola=diastola;}

    public String getSistola(){return sistola;}
    public void setSistola(String sistola){this.sistola=sistola;}

    public String getPulsos(){return pulsos;}
    public void setPulsos(String pulsos){this.pulsos=pulsos;}

    public String getFecha(){return fecha;}
    public void setFecha(String fecha){this.fecha=fecha;}



}
