package com.example.betacell.BaseDatos;

import java.io.Serializable;

public class Presion  {

    public String id;
    public String usuario;
    public String registro;
    public String pulsos;
    public String fecha;

    public Presion(String id, String registro, String pulsos, String fecha,String usuario) {
        this.id = id;
        this.registro = registro;
        this.pulsos = pulsos;
        this.fecha = fecha;
        this.usuario=usuario;
    }

    public Presion(){

    }

    public String getRegistro(){return registro;}
    public void setRegistro(String registro){this.registro=registro;}

    public String getPulsos(){return pulsos;}
    public void setPulsos(String pulsos){this.pulsos=pulsos;}

    public String getFecha(){return fecha;}
    public void setFecha(String fecha){this.fecha=fecha;}



}
