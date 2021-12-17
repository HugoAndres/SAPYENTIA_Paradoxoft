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
public class Docente extends Usuario{
    
    private int academiaDocente;
    private String grado;
    
    public Docente(){
        super();
    }
    
    public Docente(Usuario usuario){
        super(usuario.getIdentificador(), usuario.getNombres(), usuario.getApellidos(), usuario.getInstitucion(), usuario.getUnidadAcademica(), usuario.getCorreo(), usuario.getContra(), usuario.getImagen());
    }

    public Docente(int academiaDocente, String grado) {
        super();
        this.academiaDocente = academiaDocente;
        this.grado = grado;
    }

    public int getAcademiaDocente() {
        return academiaDocente;
    }

    public void setAcademiaDocente(int academiaDocente) {
        this.academiaDocente = academiaDocente;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return "Docente{" + super.toString() + " academiaDocente=" + academiaDocente + ", grado=" + grado + '}';
    }
    
    
    
    
}
