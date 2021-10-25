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
public class TuplaDirector {
    
    private String idTrabajoTerminal;
    private String idDirector;
    private boolean director;

    public TuplaDirector(){
        
    }
    
    public TuplaDirector(String idTrabajoTerminal, String idDirector, boolean director) {
        this.idTrabajoTerminal = idTrabajoTerminal;
        this.idDirector = idDirector;
        this.director = director;
    }

    public String getIdTrabajoTerminal() {
        return idTrabajoTerminal;
    }

    public void setIdTrabajoTerminal(String idTrabajoTerminal) {
        this.idTrabajoTerminal = idTrabajoTerminal;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "TuplaDirector{" + "idTrabajoTerminal=" + idTrabajoTerminal + ", idDirector=" + idDirector + ", director=" + director + '}';
    }
    
    
    
}
