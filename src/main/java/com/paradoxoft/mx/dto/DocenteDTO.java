/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dto;

import com.paradoxoft.mx.modelo.Usuario;

/**
 *
 * @author user
 */
public class DocenteDTO extends Usuario{
    
    private int academiaDocente;
    private String nombreAcademiaDocente;
    private String grado;
    
    public DocenteDTO(){
        super();
    }

    public DocenteDTO(int academiaDocente, String nombreAcademiaDocente, String grado) {
        super();
        this.academiaDocente = academiaDocente;
        this.nombreAcademiaDocente = nombreAcademiaDocente;
        this.grado = grado;
    }

    public int getAcademiaDocente() {
        return academiaDocente;
    }

    public void setAcademiaDocente(int academiaDocente) {
        this.academiaDocente = academiaDocente;
    }

    public String getNombreAcademiaDocente() {
        return nombreAcademiaDocente;
    }

    public void setNombreAcademiaDocente(String nombreAcademiaDocente) {
        this.nombreAcademiaDocente = nombreAcademiaDocente;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return "DocenteDTO{" + super.toString() + " academiaDocente=" + academiaDocente + ", nombreAcademiaDocente=" + nombreAcademiaDocente + ", grado=" + grado + '}';
    }
     
}
