/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.dto.DirectorDTO;
import com.paradoxoft.mx.dto.DocenteDTO;
import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Alumno;
import com.paradoxoft.mx.modelo.Proyecto;
import com.paradoxoft.mx.modelo.TuplaAutor;
import com.paradoxoft.mx.modelo.TuplaDirector;
import com.paradoxoft.mx.modelo.TuplaSinodal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ProyectoDAO {
    
    private static final String GUARDA_PROYECTO = "select spGuardaProyecto(?,?,?)";
    private static final String BORRA_PROYECTO = "select spBorraProyecto(?)";
    private static final String AGREGA_AUTOR_PROYECTO = "select spAgregaAutorProyecto(?,?)";
    private static final String AGREGA_DIRECTOR_PROYECTO = "select spAgregaDirectorProyecto(?,?,?)";
    private static final String AGREGA_SINODAL_PROYECTO = "select spAgregaSinodalProyecto(?,?)";
    private static final String TRAE_PROYECTOS = "select * from spTraeProyectos()";
    private static final String TRAE_PROYECTOS_POR_AUTOR = "select * from spTraeProyectosPorAutor(?)";
    private static final String TRAE_PROYECTOS_POR_DIRECTOR = "select * from spTraeProyectosPorDirector(?)";
    private static final String TRAE_P_POR_DIRECTOR_PRINCIPAL = "select * from spTraeProyectosPorDirectorPrincipal(?)";
    private static final String TRAE_PROYECTOS_POR_CODIRECTOR = "select * from spTraeProyectosPorCodirector(?)";
    private static final String TRAE_PROYECTOS_POR_SINODAL = "select * from spTraeProyectosPorSinodal(?)";
    private static final String BUSCA_PROYECTO = "select * from spBuscaProyecto(?)";
    private static final String TRAE_AUTORES_PROYECTO = "select * from spTraeAutoresProyecto(?)";
    private static final String TRAE_DIRECTORES_PROYECTO = "select * from spTraeDirectoresProyecto(?)";
    private static final String TRAE_SINODALES_PROYECTO = "select * from spTraeSinodalesProyecto(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int guardaProyecto(int idSolicitudProyecto, String numIdentificador, String abst) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            ps = con.prepareStatement(GUARDA_PROYECTO);
            ps.setInt(1, idSolicitudProyecto);
            ps.setString(2, numIdentificador);
            ps.setString(3, abst);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
        
        return 1;
    }
    
    public int borraProyecto(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{  
            ps = con.prepareStatement(BORRA_PROYECTO);
            ps.setString(1, numIdentificador);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
        
        return 1;
    }
    
    public void agregaAutor(TuplaAutor ta) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(AGREGA_AUTOR_PROYECTO);
            ps.setString(1, ta.getNumIdentificador());
            ps.setString(2, ta.getIdAutor());
            ps.executeQuery();
        } finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public void agregaDirector(TuplaDirector td) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(AGREGA_DIRECTOR_PROYECTO);
            ps.setString(1, td.getNumIdentificador());
            ps.setString(2, td.getIdDirector());
            ps.setBoolean(3, td.isDirector());
            ps.executeQuery();
        } finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public void agregaSinodal(TuplaSinodal ts) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(AGREGA_SINODAL_PROYECTO);
            ps.setString(1, ts.getNumIdentificador());
            ps.setString(2, ts.getIdSinodal());
            ps.executeQuery();
        } finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectos() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PROYECTOS);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectosPorAutor(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PROYECTOS_POR_AUTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectosPorDirector(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PROYECTOS_POR_DIRECTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectosPorDirectorPrincipal(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_P_POR_DIRECTOR_PRINCIPAL);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectosPorCodirector(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PROYECTOS_POR_CODIRECTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> traeProyectosPorSinodal(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PROYECTOS_POR_SINODAL);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Proyecto> buscaProyecto(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_PROYECTO);
            ps.setString(1, numIdentificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Proyecto proy = new Proyecto();
                proy.setNumIdentificador(rs.getString("numIdentificador"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setFechaRealizacion(rs.getString("fechaRealizacion"));
                proy.setInstitucion(rs.getString("institucion"));
                proy.setUnidadAcademica(rs.getString("unidadAcademica"));
                proy.setPalabrasClave(rs.getString("palabrasClave"));
                proy.setAbst(rs.getString("abstract"));
                proy.setRutaPropuesta(rs.getString("rutaPropuesta"));
                proy.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                proy.setbAcademiaAsig(rs.getBoolean("bAcademiaAsig"));
                proy.setbAsigSinodales(rs.getBoolean("bAsigSinodales"));
                proy.setbAprobSinodalesPropuesta(rs.getShort("bAprobSinodalesPropuesta"));
                proy.setbEnviadoAprobPropuesta(rs.getBoolean("bEnviadoAprobPropuesta"));
                proy.setbAprobSinodalesTT(rs.getShort("bAprobSinodalesTT"));
                proy.setbEnviadoAprobTT(rs.getBoolean("bEnviadoAprobTT"));
                proy.setbTerminado(rs.getBoolean("bTerminado"));
                resultados.add(proy);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<Alumno> traeAutoresProyecto(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumno> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_AUTORES_PROYECTO);
            ps.setString(1, numIdentificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Alumno t = new Alumno();
                t.setIdentificador(rs.getString("identificador"));
                t.setNombres(rs.getString("nombres"));
                t.setApellidos(rs.getString("apellidos"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setCorreo(rs.getString("correo"));
                t.setContra(rs.getString("contra"));
                t.setImagen(rs.getString("imagen"));
                t.setCarrera(rs.getString("carrera"));
                resultados.add(t);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<DirectorDTO> traeDirectoresProyecto(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DirectorDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_DIRECTORES_PROYECTO);
            ps.setString(1, numIdentificador);
            rs = ps.executeQuery();
 
            while (rs.next()){
                DirectorDTO t = new DirectorDTO();
                t.setIdentificador(rs.getString("identificador"));
                t.setNombres(rs.getString("nombres"));
                t.setApellidos(rs.getString("apellidos"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setCorreo(rs.getString("correo"));
                t.setContra(rs.getString("contra"));
                t.setImagen(rs.getString("imagen"));
                t.setAcademiaDocente(rs.getInt("academiaDocente"));
                t.setNombreAcademiaDocente(rs.getString("nombreAcademia"));
                t.setGrado(rs.getString("grado"));
                t.setDirector(rs.getBoolean("director"));
                resultados.add(t);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public List<DocenteDTO> traeSinodalesProyecto(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocenteDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SINODALES_PROYECTO);
            ps.setString(1, numIdentificador);
            rs = ps.executeQuery();
 
            while (rs.next()){
                DocenteDTO t = new DocenteDTO();
                t.setIdentificador(rs.getString("identificador"));
                t.setNombres(rs.getString("nombres"));
                t.setApellidos(rs.getString("apellidos"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setCorreo(rs.getString("correo"));
                t.setContra(rs.getString("contra"));
                t.setImagen(rs.getString("imagen"));
                t.setAcademiaDocente(rs.getInt("academiaDocente"));
                t.setNombreAcademiaDocente(rs.getString("nombreAcademia"));
                t.setGrado(rs.getString("grado"));
                resultados.add(t);
            }
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
    }
    
    public static void main(String[] args) {
        
        /*
        Proyecto proy = new Proyecto();
        proy.setNumIdentificador("MIPROYECTOX");
        proy.setAbst("mi asbtract feik");
         
        System.out.println(proy);
        
        ProyectoDAO dao =  new ProyectoDAO();
        try {
            dao.guardaProyecto(10, proy.getNumIdentificador(), proy.getAbst());
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        ProyectoDAO dao =  new ProyectoDAO();
        try {
            dao.borraProyecto("MIPROYECTOXY");
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        TuplaAutor ta1 = new TuplaAutor();
        ta1.setNumIdentificador("MIPROYECTOX");
        ta1.setIdAutor("hugoandresmachorromelendez");  
        
        TuplaAutor ta2 = new TuplaAutor();
        ta2.setNumIdentificador("MIPROYECTOX");
        ta2.setIdAutor("pedropabloperezpereira");

        ProyectoDAO dao =  new ProyectoDAO();
        try {
            dao.agregaAutor(ta1);
            dao.agregaAutor(ta2);
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TuplaDirector td1 = new TuplaDirector();
        td1.setNumIdentificador("MIPROYECTOX");
        td1.setIdDirector("albusdumbledore");
        td1.setDirector(true);
        
        TuplaDirector td2 = new TuplaDirector();
        td2.setNumIdentificador("MIPROYECTOX");
        td2.setIdDirector("melchorrey");
        td2.setDirector(false);
        
        TuplaDirector td3 = new TuplaDirector();
        td3.setNumIdentificador("MIPROYECTOX");
        td3.setIdDirector("mickey");
        td3.setDirector(false);
        
        ProyectoDAO dao =  new ProyectoDAO();
        try {
            dao.agregaDirector(td1);
            dao.agregaDirector(td2);
            dao.agregaDirector(td3);
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        TuplaSinodal ts1 = new TuplaSinodal();
        TuplaSinodal ts2 = new TuplaSinodal();
        TuplaSinodal ts3 = new TuplaSinodal();
        
        ts1.setNumIdentificador("MIPROYECTOX");
        ts2.setNumIdentificador("MIPROYECTOX");
        ts3.setNumIdentificador("MIPROYECTOX");
        
        ts1.setIdSinodal("minervamcgonagall");
        ts2.setIdSinodal("severussnape");
        ts3.setIdSinodal("stephen");

        ProyectoDAO dao =  new ProyectoDAO();
        try {
            dao.agregaSinodal(ts1);
            dao.agregaSinodal(ts2);
            dao.agregaSinodal(ts3);
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectos();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectosPorAutor("hugoandresmachorromelendez");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectosPorDirector("profutonio");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectosPorDirectorPrincipal("albusdumbledore");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectosPorCodirector("reynaeliamelaraabarca");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.traeProyectosPorSinodal("minervamcgonagall");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Proyecto> lista = dao.buscaProyecto("MIPROYECTOX");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<Alumno> lista = dao.traeAutoresProyecto("MIPROYECTOX");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<DirectorDTO> lista = dao.traeDirectoresProyecto("MIPROYECTOX");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        ProyectoDAO dao = new ProyectoDAO();
        try {
            List<DocenteDTO> lista = dao.traeSinodalesProyecto("MIPROYECTOX");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
