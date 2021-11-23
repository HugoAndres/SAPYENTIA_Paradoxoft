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
public class TuplaSinodal {
 
    private String idTrabajoTerminal;
    private int idSolicitudProyecto;
    private String numIdentificador;
    private String idSinodal;
    
    public TuplaSinodal(){
        
    }

    public TuplaSinodal(String idTrabajoTerminal, int idSolicitudProyecto, String numIdentificador, String idSinodal) {
        this.idTrabajoTerminal = idTrabajoTerminal;
        this.idSolicitudProyecto = idSolicitudProyecto;
        this.numIdentificador = numIdentificador;
        this.idSinodal = idSinodal;
    }
    
    public String getIdTrabajoTerminal() {
        return idTrabajoTerminal;
    }

    public void setIdTrabajoTerminal(String idTrabajoTerminal) {
        this.idTrabajoTerminal = idTrabajoTerminal;
    }

    public String getIdSinodal() {
        return idSinodal;
    }

    public void setIdSinodal(String idSinodal) {
        this.idSinodal = idSinodal;
    }

    public int getIdSolicitudProyecto() {
        return idSolicitudProyecto;
    }

    public void setIdSolicitudProyecto(int idSolicitudProyecto) {
        this.idSolicitudProyecto = idSolicitudProyecto;
    }

    public String getNumIdentificador() {
        return numIdentificador;
    }

    public void setNumIdentificador(String numIdentificador) {
        this.numIdentificador = numIdentificador;
    }

    @Override
    public String toString() {
        return "TuplaSinodal{" + "idTrabajoTerminal=" + idTrabajoTerminal + ", idSolicitudProyecto=" + idSolicitudProyecto + ", numIdentificador=" + numIdentificador + ", idSinodal=" + idSinodal + '}';
    }
    
}
