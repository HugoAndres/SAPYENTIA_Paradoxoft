/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.dto.DirectorDTO;
import com.paradoxoft.mx.dto.DocenteDTO;
import com.paradoxoft.mx.dto.TrabajoTerminalDTO;
import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Alumno;
import com.paradoxoft.mx.modelo.TrabajoTerminal;
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
public class TrabajoTerminalDAO {
    
    private static final String GUARDA = "select spGuardaTrabajoTerminal(?,?,?::date,?,?,?,?,?,?)";
    private static final String GUARDA_HISTORICO = "select spGuardaTrabajoTerminalHistorico(?,?,?::date,?,?,?,?,?,?,?,?,?)";
    private static final String BORRA = "select spBorraTrabajoTerminal(?)";
    private static final String AGREGA_AUTOR = "select spAgregaAutorTrabajoTerminal(?, ?)";
    private static final String AGREGA_DIRECTOR = "select spAgregaDirectorTrabajoTerminal(?, ?, ?)";
    private static final String AGREGA_SINODAL = "select spAgregaSinodalTrabajoTerminal(?, ?)";
    private static final String TRAETODO = "select * from spTraeTrabajosTerminales()";
    private static final String BUSCA = "select * from spBuscaTrabajoTerminal(?)";
    private static final String TRAE_AUTORES_TRABAJO_TERMINAL = "select * from spTraeAutoresTrabajoTerminal(?)";
    private static final String TRAE_DIRECTORES_TRABAJO_TERMINAL = "select * from spTraeDirectoresTrabajoTerminal(?)";
    private static final String TRAE_SINODALES_TRABAJO_TERMINAL = "select * from spTraeSinodalesTrabajoTerminal(?)";
    private static final String BUSCA_POR_TITULO = "select * from spBuscaTrabajoTerminalPorTitulo(?)";
    private static final String BUSCA_POR_AUTORES = "select * from spBuscaTrabajoTerminalPorAutores(?)";
    private static final String BUSCA_POR_DIRECTORES = "select * from spBuscaTrabajoTerminalPorDirectores(?)";
    private static final String BUSCA_POR_SINODALES = "select * from spBuscaTrabajoTerminalPorSinodales(?)";
    private static final String BUSCA_POR_FECHA_REALIZACION = "select * from spBuscaTrabajoTerminalPorFechaRealizacion(?)";
    private static final String BUSCA_POR_INSTITUCION = "select * from spBuscaTrabajoTerminalPorInstitucion(?)";
    private static final String BUSCA_POR_UNIDAD_ACADEMICA = "select * from spBuscaTrabajoTerminalPorUnidadAcademica(?)";
    private static final String BUSCA_POR_PALABRAS_CLAVE = "select * from spBuscaTrabajoTerminalPorPalabrasClave(?)";
    private static final String BUSCA_POR_TEXTO_COMPLETO = "select * from spBuscaTrabajoTerminalPorTextoCompleto(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    
    public int guardaTrabajoTerminal(TrabajoTerminal tt, List<TuplaAutor> listaAutores, List<TuplaDirector> listaDirectores, List<TuplaSinodal> listaSinodales) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        int numAutores;
        int numDirectores;
        int numSinodales;
        try{
            
            if(tt == null || listaAutores == null || listaDirectores == null || listaSinodales == null){
                System.out.println("NO SE PUEDE GUARDAR EL TRABAJO TERMINAL HAY VALORES NULOS EN SU INSERCIÓN");
                return -3;
            }
            
            numAutores = listaAutores.size();
            numDirectores = listaDirectores.size();
            numSinodales = listaSinodales.size();
            
            if(numAutores == 0 || numDirectores == 0 || numSinodales == 0 ){
                System.out.println("ERROR DE LONGITUD EN LOS PARÁMETROS DE ENTRADA guardaTrabajoTerminal(Trabajo Terminal, List<TuplaAutor>, List<TuplaDirector>, List<TuplaSinodal>)");
                return -2;
            }
            
            ps = con.prepareStatement(GUARDA);
            ps.setString(1, tt.getNumIdentificador());
            ps.setString(2, tt.getTitulo());
            ps.setString(3, tt.getFechaRealizacion());
            ps.setString(4, tt.getInstitucion());
            ps.setString(5, tt.getUnidadAcademica());
            ps.setString(6, tt.getPalabrasClave());
            ps.setString(7, tt.getAbst());
            ps.setString(8, tt.getRutaPropuesta());
            ps.setString(9, tt.getRutaTrabajoTerminal());
            ps.executeQuery();
            
            for (int i = 0; i < numAutores; i++) {
                TuplaAutor tupla = listaAutores.get(i);
                agregaAutor(tupla);
            }
            
            for (int i = 0; i < numDirectores; i++) {
                TuplaDirector tupla = listaDirectores.get(i);
                agregaDirector(tupla);
            }
            
            for (int i = 0; i < numSinodales; i++) {
                TuplaSinodal tupla = listaSinodales.get(i);
                agregaSinodal(tupla);
            }

        } catch(SQLException ex){
            Logger.getLogger(TrabajoTerminalDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            ps = con.prepareStatement(BORRA);
            ps.setString(1, tt.getNumIdentificador());
            ps.executeQuery();
            System.out.println("ERROR EN EL ALTA DE TESIS");
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
    
    public int guardaTrabajoTerminalHistorico(TrabajoTerminalDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(dto == null){
                System.out.println("NO SE PUEDE GUARDAR EL TRABAJO TERMINAL HAY VALORES NULOS EN SU INSERCIÓN");
                return -3;
            }
            
            ps = con.prepareStatement(GUARDA_HISTORICO);
            ps.setString(1, dto.getNumIdentificador());
            ps.setString(2, dto.getTitulo());
            ps.setString(3, dto.getFechaRealizacion());
            ps.setString(4, dto.getInstitucion());
            ps.setString(5, dto.getUnidadAcademica());
            ps.setString(6, dto.getPalabrasClave());
            ps.setString(7, dto.getAbst());
            ps.setString(8, dto.getRutaPropuesta());
            ps.setString(9, dto.getRutaTrabajoTerminal());
            ps.setString(10, dto.getNombresAutores());
            ps.setString(11, dto.getNombresDirectores());
            ps.setString(12, dto.getNombresSinodales());
            ps.executeQuery();

        } finally{
            if(ps != null){
                ps.close();
            }
            if(this.con != null){
                AlbercaDeConexiones.obtenInstancia().retirarConexion(this.con);
            }
        }
        
        return 1;
    }
    
    public int borraTrabajoTerminal(String numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(numIdentificador == null){
                System.out.println("EL IDENTIFICADOR ES NULO");
                return -3;
            }
            
            ps = con.prepareStatement(BORRA);
            ps.setString(1, numIdentificador);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(TrabajoTerminalDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ps = con.prepareStatement(AGREGA_AUTOR);
            ps.setString(1, ta.getIdTrabajoTerminal());
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
            ps = con.prepareStatement(AGREGA_DIRECTOR);
            ps.setString(1, td.getIdTrabajoTerminal());
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
            ps = con.prepareStatement(AGREGA_SINODAL);
            ps.setString(1, ts.getIdTrabajoTerminal());
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
    
    public List<TrabajoTerminalDTO> traeTrabajosTerminales() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAETODO);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public TrabajoTerminalDTO buscaTrabajoTerminal(String n_numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA);
            ps.setString(1, n_numIdentificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
                resultados.add(t);
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
    
    
    public List<Alumno> traeAutoresTrabajoTerminal(String n_numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumno> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_AUTORES_TRABAJO_TERMINAL);
            ps.setString(1,n_numIdentificador);
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
    
    public List<DirectorDTO> traeDirectoresTrabajoTerminal(String n_numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DirectorDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_DIRECTORES_TRABAJO_TERMINAL);
            ps.setString(1,n_numIdentificador);
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
    
    public List<DocenteDTO> traeSinodalesTrabajoTerminal(String n_numIdentificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocenteDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_SINODALES_TRABAJO_TERMINAL);
            ps.setString(1,n_numIdentificador);
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorTitulo(String n_titulo) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_TITULO);
            ps.setString(1, n_titulo);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorAutores(String n_autores) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_AUTORES);
            ps.setString(1, n_autores);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorDirectores(String n_directores) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_DIRECTORES);
            ps.setString(1, n_directores);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorSinodales(String n_sinodales) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_SINODALES);
            ps.setString(1, n_sinodales);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorFechaRealizacion(String n_fechaRealizacion) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_FECHA_REALIZACION);
            ps.setString(1, n_fechaRealizacion);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorInstitucion(String n_institucion) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_INSTITUCION);
            ps.setString(1, n_institucion);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorUnidadAcademica(String n_unidadAcademica) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_UNIDAD_ACADEMICA);
            ps.setString(1, n_unidadAcademica);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorPalabrasClave(String n_palabrasClave) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_PALABRAS_CLAVE);
            ps.setString(1, n_palabrasClave);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
    
    public List<TrabajoTerminalDTO> buscaTrabajoTerminalPorTextoCompleto(String n_textoCompleto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TrabajoTerminalDTO> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA_POR_TEXTO_COMPLETO);
            ps.setString(1, n_textoCompleto);
            rs = ps.executeQuery();
            
            while (rs.next()){
                TrabajoTerminalDTO t = new TrabajoTerminalDTO();
                t.setNumIdentificador(rs.getString("numIdentificador"));
                t.setTitulo(rs.getString("titulo"));
                t.setFechaRealizacion(rs.getString("fechaRealizacion"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setPalabrasClave(rs.getString("palabrasClave"));
                t.setAbst(rs.getString("abstract"));
                t.setRutaPropuesta(rs.getString("rutaPropuesta"));
                t.setRutaTrabajoTerminal(rs.getString("rutaTrabajoTerminal"));
                t.setNombresAutores(rs.getString("nombresAutores"));
                t.setNombresDirectores(rs.getString("nombresDirectores"));
                t.setNombresSinodales(rs.getString("nombresSinodales"));
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
        TrabajoTerminal tt = new TrabajoTerminal();
        tt.setNumIdentificador("ABCDEFG");
        tt.setTitulo("Semillas que evitan la expansión desordenada de los océanos");
        tt.setFechaRealizacion("2010-07-25");
        tt.setInstitucion("IPN");
        tt.setUnidadAcademica("ESCOM");
        tt.setPalabrasClave("semilla, expansión, océano,,ciudadanos, biodiversidad, supervivencia, tecnología");
        tt.setAbst("La organización de hábitat de los océanos Resolva cruzó el camino "
                + "hacia la innovación y el acceso, para poder proteger a sus ciudadanos y "
                + "poder explorar los camino hacia la supervivencia y la sobrevivencia para "
                + "la biodiversidad de todos. Hemos tenido la oportunidad de experimentar la nueva tecnología");
        tt.setRutaPropuesta("C://propuestaejemplo1.pdf");
        tt.setRutaTrabajoTerminal("C://trabajoterminalejemplo1.pdf");
        
        System.out.println(tt);
        
        List<TuplaAutor> listaAutores = new ArrayList<>();
        
        TuplaAutor ta1 = new TuplaAutor();
        ta1.setIdTrabajoTerminal("ABCDEFG");
        ta1.setIdAutor("hugoandresmachorromelendez");  
        listaAutores.add(ta1);
        
        TuplaAutor ta2 = new TuplaAutor();
        ta2.setIdTrabajoTerminal("ABCDEFG");
        ta2.setIdAutor("pedropabloperezpereira");
        listaAutores.add(ta2);
        
        System.out.println(listaAutores);
        
        List<TuplaDirector> listaDirectores = new ArrayList<>();
        
        TuplaDirector td1 = new TuplaDirector();
        td1.setIdTrabajoTerminal("ABCDEFG");
        td1.setIdDirector("albusdumbledore");
        td1.setDirector(true);
        listaDirectores.add(td1);
        
        TuplaDirector td2 = new TuplaDirector();
        td2.setIdTrabajoTerminal("ABCDEFG");
        td2.setIdDirector("melchorrey");
        td2.setDirector(false);
        listaDirectores.add(td2);
        
        TuplaDirector td3 = new TuplaDirector();
        td3.setIdTrabajoTerminal("ABCDEFG");
        td3.setIdDirector("mickey");
        td3.setDirector(false);
        listaDirectores.add(td3);
        
        System.out.println(listaDirectores);
        
        List<TuplaSinodal> listaSinodales = new ArrayList<>();
        
        TuplaSinodal ts1 = new TuplaSinodal();
        TuplaSinodal ts2 = new TuplaSinodal();
        TuplaSinodal ts3 = new TuplaSinodal();
        
        ts1.setIdTrabajoTerminal("ABCDEFG");
        ts2.setIdTrabajoTerminal("ABCDEFG");
        ts3.setIdTrabajoTerminal("ABCDEFG");
        
        ts1.setIdSinodal("minervamcgonagall");
        ts2.setIdSinodal("severussnape");
        ts3.setIdSinodal("stephen");
        
        listaSinodales.add(ts1);
        listaSinodales.add(ts2);
        listaSinodales.add(ts3);
        System.out.println(listaSinodales);
        
        

        TrabajoTerminalDAO dao =  new TrabajoTerminalDAO();
        try {
            dao.guardaTrabajoTerminal(tt, listaAutores, listaDirectores, listaSinodales);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        TrabajoTerminalDTO dto = new TrabajoTerminalDTO();
        dto.setNumIdentificador("ABCDEFG");
        dto.setTitulo("Semillas que evitan la expansión desordenada de los océanos");
        dto.setFechaRealizacion("2010-07-25");
        dto.setInstitucion("IPN");
        dto.setUnidadAcademica("ESCOM");
        dto.setPalabrasClave("semilla, expansión, océano,,ciudadanos, biodiversidad, supervivencia, tecnología");
        dto.setAbst("La organización de hábitat de los océanos Resolva cruzó el camino "
                + "hacia la innovación y el acceso, para poder proteger a sus ciudadanos y "
                + "poder explorar los camino hacia la supervivencia y la sobrevivencia para "
                + "la biodiversidad de todos. Hemos tenido la oportunidad de experimentar la nueva tecnología");
        dto.setRutaPropuesta("C://propuestaejemplo1.pdf");
        dto.setRutaTrabajoTerminal("C://trabajoterminalejemplo1.pdf");
        dto.setNombresAutores("Hugo Andrés Machorro Meléndez, Pedro Pablo Pérez Pereira");
        dto.setNombresDirectores("Albus Dumbledores, Melchor Rey, Mickey Mouse");
        dto.setNombresSinodales("Minerva McGonagall, Severus Snape, Stephen Strange");
        
        TrabajoTerminalDAO dao =  new TrabajoTerminalDAO();
        try {
            dao.guardaTrabajoTerminalHistorico(dto);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.traeTrabajosTerminales();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            TrabajoTerminalDTO dto = dao.buscaTrabajoTerminal("HISTORICO1");
            System.out.println(dto);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<Alumno> listaAutores = dao.traeAutoresTrabajoTerminal("OPQRST");
            System.out.println(listaAutores);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<DirectorDTO> listaDirectores = dao.traeDirectoresTrabajoTerminal("OPQRST");
            System.out.println(listaDirectores);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<DocenteDTO> listaSinodales = dao.traeSinodalesTrabajoTerminal("OPQRST");
            System.out.println(listaSinodales);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorTitulo("slas y sens");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorAutores("luisito");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorDirectores("minerva macgonagal");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorSinodales("minerva macgonagal");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorFechaRealizacion("2012");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorInstitucion("IPN");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorUnidadAcademica("FAC");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorPalabrasClave("Biod");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
        try {
            List<TrabajoTerminalDTO> lista = dao.buscaTrabajoTerminalPorTextoCompleto("innovacion y tecnología");
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
    }
    
}
