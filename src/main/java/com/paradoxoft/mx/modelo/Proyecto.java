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
public class Proyecto {
    
    private String numIdentificador;
    private String titulo;
    private String fechaRealizacion;
    private String institucion;
    private String unidadAcademica;
    private String palabrasClave;
    private String abst;
    private String rutaPropuesta;
    private String rutaTrabajoTerminal;
    private boolean bAcademiaAsig;
    private boolean bAsigSinodales;
    private short bAprobSinodalesPropuesta;
    private boolean bEnviadoAprobPropuesta;
    private short bAprobSinodalesTT;
    private boolean bEnviadoAprobTT;
    private boolean bTerminado;

    public Proyecto() {
    }

    public Proyecto(String numIdentificador, String titulo, String fechaRealizacion, String institucion, String unidadAcademica, String palabrasClave, String abst, String rutaPropuesta, String rutaTrabajoTerminal, boolean bAcademiaAsig, boolean bAsigSinodales, short bAprobSinodalesPropuesta, boolean bEnviadoAprobPropuesta, short bAprobSinodalesTT, boolean bEnviadoAprobTT, boolean bTerminado) {
        this.numIdentificador = numIdentificador;
        this.titulo = titulo;
        this.fechaRealizacion = fechaRealizacion;
        this.institucion = institucion;
        this.unidadAcademica = unidadAcademica;
        this.palabrasClave = palabrasClave;
        this.abst = abst;
        this.rutaPropuesta = rutaPropuesta;
        this.rutaTrabajoTerminal = rutaTrabajoTerminal;
        this.bAcademiaAsig = bAcademiaAsig;
        this.bAsigSinodales = bAsigSinodales;
        this.bAprobSinodalesPropuesta = bAprobSinodalesPropuesta;
        this.bEnviadoAprobPropuesta = bEnviadoAprobPropuesta;
        this.bAprobSinodalesTT = bAprobSinodalesTT;
        this.bEnviadoAprobTT = bEnviadoAprobTT;
        this.bTerminado = bTerminado;
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

    public boolean isbAcademiaAsig() {
        return bAcademiaAsig;
    }

    public void setbAcademiaAsig(boolean bAcademiaAsig) {
        this.bAcademiaAsig = bAcademiaAsig;
    }

    public boolean isbAsigSinodales() {
        return bAsigSinodales;
    }

    public void setbAsigSinodales(boolean bAsigSinodales) {
        this.bAsigSinodales = bAsigSinodales;
    }

    public short getbAprobSinodalesPropuesta() {
        return bAprobSinodalesPropuesta;
    }

    public void setbAprobSinodalesPropuesta(short bAprobSinodalesPropuesta) {
        this.bAprobSinodalesPropuesta = bAprobSinodalesPropuesta;
    }

    public boolean isbEnviadoAprobPropuesta() {
        return bEnviadoAprobPropuesta;
    }

    public void setbEnviadoAprobPropuesta(boolean bEnviadoAprobPropuesta) {
        this.bEnviadoAprobPropuesta = bEnviadoAprobPropuesta;
    }

    public short getbAprobSinodalesTT() {
        return bAprobSinodalesTT;
    }

    public void setbAprobSinodalesTT(short bAprobSinodalesTT) {
        this.bAprobSinodalesTT = bAprobSinodalesTT;
    }

    public boolean isbEnviadoAprobTT() {
        return bEnviadoAprobTT;
    }

    public void setbEnviadoAprobTT(boolean bEnviadoAprobTT) {
        this.bEnviadoAprobTT = bEnviadoAprobTT;
    }

    public boolean isbTerminado() {
        return bTerminado;
    }

    public void setbTerminado(boolean bTerminado) {
        this.bTerminado = bTerminado;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "numIdentificador=" + numIdentificador + ", titulo=" + titulo + ", fechaRealizacion=" + fechaRealizacion + ", institucion=" + institucion + ", unidadAcademica=" + unidadAcademica + ", palabrasClave=" + palabrasClave + ", abst=" + abst + ", rutaPropuesta=" + rutaPropuesta + ", rutaTrabajoTerminal=" + rutaTrabajoTerminal + ", bAcademiaAsig=" + bAcademiaAsig + ", bAsigSinodales=" + bAsigSinodales + ", bAprobSinodalesPropuesta=" + bAprobSinodalesPropuesta + ", bEnviadoAprobPropuesta=" + bEnviadoAprobPropuesta + ", bAprobSinodalesTT=" + bAprobSinodalesTT + ", bEnviadoAprobTT=" + bEnviadoAprobTT + ", bTerminado=" + bTerminado + '}';
    } 
    
}
