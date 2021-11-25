/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.dto.DirectorDTO;
import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Alumno;
import com.paradoxoft.mx.modelo.SolicitudProyecto;
import com.paradoxoft.mx.modelo.TuplaAutor;
import com.paradoxoft.mx.modelo.TuplaDirector;
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
public class SolicitudProyectoDAO {
    
    private static final String GUARDA_SOLICITUD_PROYECTO = "select spGuardaSolicitudProyecto(?,?,?,?,?)";
    private static final String BORRA_SOLICITUD_PROYECTO = "select spBorraSolicitudProyecto(?)";
    private static final String AGREGA_AUTOR_SOLICITUD_PROYECTO = "select spAgregaAutorSolicitudProyecto(?,?)";
    private static final String AGREGA_DIRECTOR_SOLICITUD_PROYECTO = "select spAgregaDirectorSolicitudProyecto(?,?,?)";
    private static final String TRAE_SOLICITUDES_PROYECTO = "select * from spTraeSolicitudesProyecto()";
    private static final String TRAE_SOLICITUDES_PROYECTO_EN_ESPERA = "select * from spTraeSolicitudesProyectoEnEspera()";
    private static final String TRAE_SP_RECHAZADAS = "select * from spTraeSolicitudesProyectoRechazadas()";
    private static final String TRAE_SOLICITUDES_PROYECTO_APROBADAS = "select * from spTraeSolicitudesProyectoAprobadas()";
    private static final String TRAE_SOLICITUDES_PROYECTO_POR_AUTOR = "select * from spTraeSolicitudesProyectoPorAutor(?)";
    private static final String TRAE_SP_POR_DIRECTOR = "select * from spTraeSolicitudesProyectoPorDirector(?)";
    private static final String TRAE_SP_POR_DIRECTOR_PRINCIPAL = "select * from spTraeSolicitudesProyectoPorDirectorPrincipal(?)";
    private static final String TRAE_SP_POR_CODIRECTOR = "select * from spTraeSolicitudesProyectoPorCodirector(?)";
    private static final String BUSCA_SOLICITUD_PROYECTO = "select * from spBuscaSolicitudProyecto(?)";
    private static final String TRAE_AUTORES_SOLICITUD_PROYECTO = "select * from spTraeAutoresSolicitudProyecto(?)";
    private static final String TRAE_DIRECTORES_SOLICITUD_PROYECTO = "select * from spTraeDirectoresSolicitudProyecto(?)";
    private static final String EVALUA_SOLICITUD_PROYECTO = "select * from spEvaluaSolicitudProyecto(?,?,?,?,?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int guardaSolicitudProyecto(SolicitudProyecto sol) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(sol == null){
                System.out.println("NO SE PUEDE GUARDAR LA SOLICITUD, ES NULA");
                return -3;
            }
            
            ps = con.prepareStatement(GUARDA_SOLICITUD_PROYECTO);
            ps.setString(1, sol.getTitulo());
            ps.setString(2, sol.getInstitucion());
            ps.setString(3, sol.getUnidadAcademica());
            ps.setString(4, sol.getPalabrasClave());
            ps.setString(5, sol.getRutaPropuesta());
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public int borraSolicitudProyecto(int idSolicitudProyecto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{  
            ps = con.prepareStatement(BORRA_SOLICITUD_PROYECTO);
            ps.setInt(1, idSolicitudProyecto);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ps = con.prepareStatement(AGREGA_AUTOR_SOLICITUD_PROYECTO);
            ps.setInt(1, ta.getIdSolicitudProyecto());
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
            ps = con.prepareStatement(AGREGA_DIRECTOR_SOLICITUD_PROYECTO);
            ps.setInt(1, td.getIdSolicitudProyecto());
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
    
    public List<SolicitudProyecto> traeSolicitudesProyecto() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SOLICITUDES_PROYECTO);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoEnEspera() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SOLICITUDES_PROYECTO_EN_ESPERA);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoRechazadas() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SP_RECHAZADAS);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoAprobadas() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SOLICITUDES_PROYECTO_APROBADAS);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoPorAutor(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SOLICITUDES_PROYECTO_POR_AUTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoPorDirector(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SP_POR_DIRECTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoPorDirectorPrincipal(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SP_POR_DIRECTOR_PRINCIPAL);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public List<SolicitudProyecto> traeSolicitudesProyectoPorCodirector(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SP_POR_CODIRECTOR);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
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
    
    public SolicitudProyecto buscaSolicitudProyecto(int idSolicitudProyecto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SolicitudProyecto> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_SOLICITUD_PROYECTO);
            ps.setInt(1, idSolicitudProyecto);
            rs = ps.executeQuery();
            
            while (rs.next()){
                SolicitudProyecto sol = new SolicitudProyecto();
                sol.setIdSolicitudProyecto(rs.getInt("idSolicitudProyecto"));
                sol.setTitulo(rs.getString("titulo"));
                sol.setFechaRealizacion(rs.getString("fechaRealizacion"));
                sol.setInstitucion(rs.getString("institucion"));
                sol.setUnidadAcademica(rs.getString("unidadAcademica"));
                sol.setPalabrasClave(rs.getString("palabrasClave"));
                sol.setRutaPropuesta(rs.getString("rutaPropuesta"));
                sol.setbRechazado(rs.getShort("bRechazado"));
                sol.setObservaciones(rs.getString("observaciones"));
                resultados.add(sol);
            }
            if(resultados.size() > 0){
                return resultados.get(0);
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
    
    public List<Alumno> traeAutoresSolicitudProyecto(int idSolicitudProyecto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumno> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_AUTORES_SOLICITUD_PROYECTO);
            ps.setInt(1,idSolicitudProyecto);
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
    
    public List<DirectorDTO> traeDirectoresSolicitudProyecto(int idSolicitudProyecto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DirectorDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_DIRECTORES_SOLICITUD_PROYECTO);
            ps.setInt(1,idSolicitudProyecto);
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
    
    public int evaluaSolicitudProyecto(int idSolicitudProyecto, boolean aprobado, String observaciones, String numIdentificador, String abst) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            ps = con.prepareStatement(EVALUA_SOLICITUD_PROYECTO);
            ps.setInt(1, idSolicitudProyecto);
            ps.setBoolean(2, aprobado);
            ps.setString(3, observaciones);
            ps.setString(4, numIdentificador);
            ps.setString(5, abst);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void main(String[] args) {
        /*
        SolicitudProyecto sol1 = new SolicitudProyecto();
        sol1.setTitulo("Diagnóstico de personas con enfermedades visuales usando técnicas de aprendizaje automático");
        sol1.setInstitucion("IPN");
        sol1.setUnidadAcademica("UPIICSA");
        sol1.setPalabrasClave("enfermedades terminales, aprendizaje automático, diagnóstico");
        sol1.setRutaPropuesta("C://propuestaejemploX1.pdf");
        
        SolicitudProyecto sol2 = new SolicitudProyecto();
        sol2.setTitulo("Estudio de nuevas disposiciones estructurales en los edificios del Valle de México");
        sol2.setInstitucion("IPN");
        sol2.setUnidadAcademica("ESIA");
        sol2.setPalabrasClave("estructuras, edificios, Valle de México, sismos");
        sol2.setRutaPropuesta("C://propuestaejemploX2.pdf");
        
        SolicitudProyecto sol3 = new SolicitudProyecto();
        sol3.setTitulo("Implementación de simuladores en la enseñanza de fundamentos de mecatrónica a nivel medio-superior");
        sol3.setInstitucion("IPN");
        sol3.setUnidadAcademica("UPIITA");
        sol3.setPalabrasClave("simuladores, mecatrónica, medio superior, educación");
        sol3.setRutaPropuesta("C://propuestaejemploX3.pdf");
        
        System.out.println(sol1);
        System.out.println(sol2);
        System.out.println(sol3);
        
        SolicitudProyectoDAO dao =  new SolicitudProyectoDAO();
        try {
            dao.guardaSolicitudProyecto(sol1);
            dao.guardaSolicitudProyecto(sol2);
            dao.guardaSolicitudProyecto(sol3);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao =  new SolicitudProyectoDAO();
        try {
            dao.borraSolicitudProyecto(11);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        
        TuplaAutor ta1 = new TuplaAutor();
        ta1.setIdSolicitudProyecto(8);
        ta1.setIdAutor("hugoandresmachorromelendez");  
        
        TuplaAutor ta2 = new TuplaAutor();
        ta2.setIdSolicitudProyecto(8);
        ta2.setIdAutor("pedropabloperezpereira");
        
        TuplaAutor ta3 = new TuplaAutor();
        ta3.setIdSolicitudProyecto(9);
        ta3.setIdAutor("hugoandresmachorromelendez");  
        
        TuplaAutor ta4 = new TuplaAutor();
        ta4.setIdSolicitudProyecto(9);
        ta4.setIdAutor("pedropabloperezpereira");
        
        TuplaAutor ta5 = new TuplaAutor();
        ta5.setIdSolicitudProyecto(10);
        ta5.setIdAutor("jesusmaciascarmona");  
        
        TuplaAutor ta6 = new TuplaAutor();
        ta6.setIdSolicitudProyecto(10);
        ta6.setIdAutor("luisangelmaciascarmona");
        
        SolicitudProyectoDAO dao =  new SolicitudProyectoDAO();
        try {
            dao.agregaAutor(ta1);
            dao.agregaAutor(ta2);
            dao.agregaAutor(ta3);
            dao.agregaAutor(ta4);
            dao.agregaAutor(ta5);
            dao.agregaAutor(ta6);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        TuplaDirector td1 = new TuplaDirector();
        td1.setIdSolicitudProyecto(8);
        td1.setIdDirector("albusdumbledore");
        td1.setDirector(true);
        
        TuplaDirector td2 = new TuplaDirector();
        td2.setIdSolicitudProyecto(8);
        td2.setIdDirector("melchorrey");
        td2.setDirector(false);
        
        TuplaDirector td3 = new TuplaDirector();
        td3.setIdSolicitudProyecto(8);
        td3.setIdDirector("mickey");
        td3.setDirector(false);
        
        TuplaDirector td4 = new TuplaDirector();
        td4.setIdSolicitudProyecto(9);
        td4.setIdDirector("albusdumbledore");
        td4.setDirector(true);
        
        TuplaDirector td5 = new TuplaDirector();
        td5.setIdSolicitudProyecto(9);
        td5.setIdDirector("melchorrey");
        td5.setDirector(false);
        
        TuplaDirector td6 = new TuplaDirector();
        td6.setIdSolicitudProyecto(9);
        td6.setIdDirector("mickey");
        td6.setDirector(false);
        
        TuplaDirector td7 = new TuplaDirector();
        td7.setIdSolicitudProyecto(10);
        td7.setIdDirector("profutonio");
        td7.setDirector(true);
        
        TuplaDirector td8 = new TuplaDirector();
        td8.setIdSolicitudProyecto(10);
        td8.setIdDirector("reynaeliamelaraabarca");
        td8.setDirector(false);
        
        TuplaDirector td9 = new TuplaDirector();
        td9.setIdSolicitudProyecto(10);
        td9.setIdDirector("inocenciojirafales");
        td9.setDirector(false);
        
        SolicitudProyectoDAO dao =  new SolicitudProyectoDAO();
        try {
            dao.agregaDirector(td1);
            dao.agregaDirector(td2);
            dao.agregaDirector(td3);
            dao.agregaDirector(td4);
            dao.agregaDirector(td5);
            dao.agregaDirector(td6);
            dao.agregaDirector(td7);
            dao.agregaDirector(td8);
            dao.agregaDirector(td9);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyecto();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoEnEspera();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoRechazadas();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoAprobadas();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoPorAutor("hugoandresmachorromelendez");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoPorDirector("reynaeliamelaraabarca");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoPorDirectorPrincipal("mickey");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.traeSolicitudesProyectoPorCodirector("reynaeliamelaraabarca");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<SolicitudProyecto> lista = dao.buscaSolicitudProyecto(8);
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<Alumno> lista = dao.traeAutoresSolicitudProyecto(9);
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            List<DirectorDTO> lista = dao.traeDirectoresSolicitudProyecto(10);
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            dao.evaluaSolicitudProyecto(8, true, "Solicitud completa", "PROYECTO1", "Aplicaciones o software\n" +
"							Técnicas de aprendizaje e intuitividad (gestores de aprendizaje)\n" +
"							Métodos de medicina entre los cuales está incluido el \n" +
"							estudio de pacientes y medicamentos típicos de ventas");
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
        try {
            dao.evaluaSolicitudProyecto(9, false, "La solicitud tiene errores", null, null);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
