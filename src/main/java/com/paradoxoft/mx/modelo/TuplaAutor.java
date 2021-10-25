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
    private String idAutor;
    
   public TuplaAutor(){

   }

    public TuplaAutor(String idTrabajoTerminal, String idAutor) {
        this.idTrabajoTerminal = idTrabajoTerminal;
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

    @Override
    public String toString() {
        return "TuplaAutor{" + "idTrabajoTerminal=" + idTrabajoTerminal + ", idAutor=" + idAutor + '}';
    }
   
   
    
}
