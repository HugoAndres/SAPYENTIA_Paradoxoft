/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.comparadores;

//import com.paradoxoft.mx.dao.SolicitudProyectoDAO;
import com.paradoxoft.mx.modelo.SolicitudProyecto;
//import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author user
 */

class ComparadorSolicitudProyectoPorTitulo implements Comparator<SolicitudProyecto>{

    @Override
    public int compare(SolicitudProyecto o1, SolicitudProyecto o2) {
        return o1.getTitulo().compareTo(o2.getTitulo());
    }
}


public class ComparadoresSolicitudProyecto {

    public static void ordenaPorTituloAscendente(List<SolicitudProyecto> lista){
        Collections.sort(lista, new ComparadorSolicitudProyectoPorTitulo());
    }
    
    public static void ordenaPorTituloDescendente(List<SolicitudProyecto> lista){
        Collections.sort(lista, new ComparadorSolicitudProyectoPorTitulo());
        Collections.reverse(lista);
    }

    public static void main(String[] args) {
        
        /*
        SolicitudProyectoDAO dao =  new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyecto();
            System.out.println(lista);
            
            ordenaPorTituloAscendente(lista);
            System.out.println(lista);
            
            ordenaPorTituloDescendente(lista);
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(ComparadoresSolicitudProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}

