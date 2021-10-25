/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dto;

/**
 *
 * @author user
 */
public class TrabajoTerminalDTO {
    
    private String numIdentificador;
    private String titulo;
    private String fechaRealizacion;
    private String institucion;
    private String unidadAcademica;
    private String palabrasClave;
    private String abst;
    private String rutaPropuesta;
    private String rutaTrabajoTerminal; 
    private String nombresAutores;
    private String nombresDirectores;
    private String nombresSinodales;

    public TrabajoTerminalDTO(){
        
    }
    
    public TrabajoTerminalDTO(String numIdentificador, String titulo, String fechaRealizacion, String institucion, String unidadAcademica, String palabrasClave, String abst, String rutaPropuesta, String rutaTrabajoTerminal, String nombresAutores, String nombresDirectores, String nombresSinodales) {
        this.numIdentificador = numIdentificador;
        this.titulo = titulo;
        this.fechaRealizacion = fechaRealizacion;
        this.institucion = institucion;
        this.unidadAcademica = unidadAcademica;
        this.palabrasClave = palabrasClave;
        this.abst = abst;
        this.rutaPropuesta = rutaPropuesta;
        this.rutaTrabajoTerminal = rutaTrabajoTerminal;
        this.nombresAutores = nombresAutores;
        this.nombresDirectores = nombresDirectores;
        this.nombresSinodales = nombresSinodales;
    }

    public String getNumIdentificador() {
        return numIdentificador;
    }

    public void setNumIdentificador(String numIdentificador) {
        this.numIdentificador = numIdentificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
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

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getRutaPropuesta() {
        return rutaPropuesta;
    }

    public void setRutaPropuesta(String rutaPropuesta) {
        this.rutaPropuesta = rutaPropuesta;
    }

    public String getRutaTrabajoTerminal() {
        return rutaTrabajoTerminal;
    }

    public void setRutaTrabajoTerminal(String rutaTrabajoTerminal) {
        this.rutaTrabajoTerminal = rutaTrabajoTerminal;
    }

    public String getNombresAutores() {
        return nombresAutores;
    }

    public void setNombresAutores(String nombresAutores) {
        this.nombresAutores = nombresAutores;
    }

    public String getNombresDirectores() {
        return nombresDirectores;
    }

    public void setNombresDirectores(String nombresDirectores) {
        this.nombresDirectores = nombresDirectores;
    }

    public String getNombresSinodales() {
        return nombresSinodales;
    }

    public void setNombresSinodales(String nombresSinodales) {
        this.nombresSinodales = nombresSinodales;
    }

    @Override
    public String toString() {
        return "TrabajoTerminalDTO{" + "numIdentificador=" + numIdentificador + ", titulo=" + titulo + ", fechaRealizacion=" + fechaRealizacion + ", institucion=" + institucion + ", unidadAcademica=" + unidadAcademica + ", palabrasClave=" + palabrasClave + ", abst=" + abst + ", rutaPropuesta=" + rutaPropuesta + ", rutaTrabajoTerminal=" + rutaTrabajoTerminal + ", nombresAutores=" + nombresAutores + ", nombresDirectores=" + nombresDirectores + ", nombresSinodales=" + nombresSinodales + "}\n";
    }
    
    
}
