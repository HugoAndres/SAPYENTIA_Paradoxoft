/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.comparadores;

import com.paradoxoft.mx.dao.TrabajoTerminalDAO;
import com.paradoxoft.mx.dto.TrabajoTerminalDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */

class ComparadorTrabajoTerminalPorTitulo implements Comparator<TrabajoTerminalDTO> {

    @Override
    public int compare(TrabajoTerminalDTO o1, TrabajoTerminalDTO o2) {
        return o1.getTitulo().compareTo(o2.getTitulo());
    }

}

class ComparadorTrabajoTerminalPorFecha implements Comparator<TrabajoTerminalDTO> {

    @Override
    public int compare(TrabajoTerminalDTO o1, TrabajoTerminalDTO o2) {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = null, fecha2 = null;
        try {
            fecha1 = formateador.parse(o1.getFechaRealizacion());
            fecha2 = formateador.parse(o2.getFechaRealizacion());
        } catch (ParseException ex) {
            Logger.getLogger(ComparadorTrabajoTerminalPorFecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fecha1.compareTo(fecha2);
    }

}

public class ComparadoresTrabajoTerminalDTO {
    
    public static void ordenaPorTituloAscendente(List<TrabajoTerminalDTO> lista){
        Collections.sort(lista, new ComparadorTrabajoTerminalPorTitulo());
    }
    
    public static void ordenaPorTituloDescendente(List<TrabajoTerminalDTO> lista){
        Collections.sort(lista, new ComparadorTrabajoTerminalPorTitulo());
        Collections.reverse(lista);
    }
    
    public static void ordenaPorFechaAscendente(List<TrabajoTerminalDTO> lista){
        Collections.sort(lista, new ComparadorTrabajoTerminalPorFecha());
    }
    
    public static void ordenaPorFechaDescendente(List<TrabajoTerminalDTO> lista){
        Collections.sort(lista, new ComparadorTrabajoTerminalPorFecha());
        Collections.reverse(lista);
    }
    
    
    public static void main(String[] args) {
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List <TrabajoTerminalDTO> lista = dao.traeTrabajosTerminales();
            ordenaPorFechaDescendente(lista);
            System.out.println(lista);
            
        } catch (SQLException ex) {
            Logger.getLogger(ComparadoresTrabajoTerminalDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
