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
public class Alumno extends Usuario{
    
    private String carrera;
            
    public Alumno(){
        super();
    }
    
    public Alumno(Usuario usuario){
        super(usuario.getIdentificador(), usuario.getNombres(), usuario.getApellidos(), usuario.getInstitucion(), usuario.getUnidadAcademica(), usuario.getCorreo(), usuario.getContra(), usuario.getImagen());
    }

    public Alumno(String carrera) {
        super();
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" + super.toString() + " carrera=" + carrera + '}';
    }
    
    
}
