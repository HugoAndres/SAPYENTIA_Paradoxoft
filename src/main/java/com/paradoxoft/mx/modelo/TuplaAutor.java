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
public class TuplaAutor {
    
    private String idTrabajoTerminal;
    private int idSolicitudProyecto;
    private String numIdentificador;
    private String idAutor;
    
   public TuplaAutor(){

   }

    public TuplaAutor(String idTrabajoTerminal, int idSolicitudProyecto, String numIdentificador, String idAutor) {
        this.idTrabajoTerminal = idTrabajoTerminal;
        this.idSolicitudProyecto = idSolicitudProyecto;
        this.numIdentificador = numIdentificador;
        this.idAutor = idAutor;
    }

    public String getIdTrabajoTerminal() {
        return idTrabajoTerminal;
    }

    public void setIdTrabajoTerminal(String idTrabajoTerminal) {
        this.idTrabajoTerminal = idTrabajoTerminal;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
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
        return "TuplaAutor{" + "idTrabajoTerminal=" + idTrabajoTerminal + ", idSolicitudProyecto=" + idSolicitudProyecto + ", numIdentificador=" + numIdentificador + ", idAutor=" + idAutor + '}';
    }
    
}
