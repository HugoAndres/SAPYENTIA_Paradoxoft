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
    private String idSinodal;
    
    public TuplaSinodal(){
        
    }

    public TuplaSinodal(String idTrabajoTerminal, String idSinodal) {
        this.idTrabajoTerminal = idTrabajoTerminal;
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

    @Override
    public String toString() {
        return "TuplaSinodal{" + "idTrabajoTerminal=" + idTrabajoTerminal + ", idSinodal=" + idSinodal + '}';
    }
    
    
    
}
