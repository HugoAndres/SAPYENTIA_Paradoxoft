/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.modelo;

/**
 *
 * @author user
 */
public class Academia {
    
    private int idAcademia;
    private String nombre;

    public Academia(){
        
    }
    
    public Academia(int idAcademia, String nombre) {
        this.idAcademia = idAcademia;
        this.nombre = nombre;
    }
    
    public int getIdAcademia() {
        return idAcademia;
    }

    public void setIdAcademia(int idAcademia) {
        this.idAcademia = idAcademia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Academia{" + "idAcademia=" + idAcademia + ", nombre=" + nombre + '}';
    }
    
}
