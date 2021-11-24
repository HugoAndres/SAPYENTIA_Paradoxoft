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
public class SolicitudProyecto {
    
    private int idSolicitudProyecto;
    private String titulo;
    private String fechaRealizacion;
    private String institucion;
    private String unidadAcademica;
    private String palabrasClave;
    private String rutaPropuesta;
    private short bRechazado;
    private String observaciones;

    public SolicitudProyecto() {
    
    }

    public SolicitudProyecto(int idSolicitudProyecto, String titulo, String fechaRealizacion, String institucion, String unidadAcademica, String palabrasClave, String rutaPropuesta, short bRechazado, String observaciones) {
        this.idSolicitudProyecto = idSolicitudProyecto;
        this.titulo = titulo;
        this.fechaRealizacion = fechaRealizacion;
        this.institucion = institucion;
        this.unidadAcademica = unidadAcademica;
        this.palabrasClave = palabrasClave;
        this.rutaPropuesta = rutaPropuesta;
        this.bRechazado = bRechazado;
        this.observaciones = observaciones;
    }

    public int getIdSolicitudProyecto() {
        return idSolicitudProyecto;
    }

    public void setIdSolicitudProyecto(int idSolicitudProyecto) {
        this.idSolicitudProyecto = idSolicitudProyecto;
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

    public String getRutaPropuesta() {
        return rutaPropuesta;
    }

    public void setRutaPropuesta(String rutaPropuesta) {
        this.rutaPropuesta = rutaPropuesta;
    }

    public short getbRechazado() {
        return bRechazado;
    }

    public void setbRechazado(short bRechazado) {
        this.bRechazado = bRechazado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "SolicitudProyecto{" + "idSolicitudProyecto=" + idSolicitudProyecto + ", titulo=" + titulo + ", fechaRealizacion=" + fechaRealizacion + ", institucion=" + institucion + ", unidadAcademica=" + unidadAcademica + ", palabrasClave=" + palabrasClave + ", rutaPropuesta=" + rutaPropuesta + ", bRechazado=" + bRechazado + ", observaciones=" + observaciones + '}';
    }

}
