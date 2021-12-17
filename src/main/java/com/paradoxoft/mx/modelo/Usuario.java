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
public class Usuario {
    
    private String identificador;
    private String nombres;
    private String apellidos;
    private String institucion;
    private String unidadAcademica;
    private String correo;
    private String contra;
    private String imagen;

    public Usuario(){
        
    }

    public Usuario(String identificador, String nombres, String apellidos, String institucion, String unidadAcademica, String correo, String contra, String imagen) {
        this.identificador = identificador;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.institucion = institucion;
        this.unidadAcademica = unidadAcademica;
        this.correo = correo;
        this.contra = contra;
        this.imagen = imagen;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(String unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Usuario{" + "identificador=" + identificador + ", nombres=" + nombres + ", apellidos=" + apellidos + ", institucion=" + institucion + ", unidadAcademica=" + unidadAcademica + ", correo=" + correo + ", contra=" + contra + ", imagen=" + imagen + '}';
    }
    
}
