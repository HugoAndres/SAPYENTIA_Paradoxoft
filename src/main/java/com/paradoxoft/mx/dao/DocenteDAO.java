/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.dto.DocenteDTO;
import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Docente;
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
public class DocenteDAO {
    
    private static final String GUARDA_DOCENTE = "select spGuardaDocente(?,?,?,?,?,?,?,?,?,?)";
    private static final String TRAE_DOCENTES = "select * from spTraeDocentes()";
    private static final String BUSCA_DOCENTE = "select * from spBuscaDocente(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int guardaDocente(Docente d) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(d == null){
                System.out.println("NO SE PUEDE GUARDAR EL DOCENTE, ES NULO");
                return -3;
            }
            
            ps = con.prepareStatement(GUARDA_DOCENTE);
            ps.setString(1, d.getIdentificador());
            ps.setString(2, d.getNombres());
            ps.setString(3, d.getApellidos());
            ps.setString(4, d.getInstitucion());
            ps.setString(5, d.getUnidadAcademica());
            ps.setString(6, d.getCorreo());
            ps.setString(7, d.getContra());
            ps.setString(8, d.getImagen());
            ps.setInt(9, d.getAcademiaDocente());
            ps.setString(10, d.getGrado());
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<DocenteDTO> traeDocentes() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocenteDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_DOCENTES);
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
    
    public DocenteDTO buscaDocente(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocenteDTO> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_DOCENTE);
            ps.setString(1, identificador);
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
    
    public static void main(String[] args) {
        
        /*
        DocenteDAO dao = new DocenteDAO();
        Docente d = new Docente();
        d.setIdentificador("docentefeik");
        d.setNombres("Docente");
        d.setApellidos("Feik");
        d.setInstitucion("InstitutoFEIK");
        d.setUnidadAcademica("FEEIIK");
        d.setCorreo("feik@docente.if.mx");
        d.setContra("docentef");
        d.setImagen("imgfeikd");
        d.setAcademiaDocente(1);
        d.setGrado("GradoFEIK");
        try {
            dao.guardaDocente(d);
        } catch (SQLException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        DocenteDAO dao = new DocenteDAO();
        
        try {
            List<DocenteDTO> lista = dao.traeDocentes();
            
            for(DocenteDTO d : lista){
                System.out.println("\n\nIdentificador: " + d.getIdentificador());
                System.out.println("Nombres: " + d.getNombres());
                System.out.println("Apellidos: " + d.getApellidos());
                System.out.println("Institucion: " + d.getInstitucion());
                System.out.println("Unidad Academica: " + d.getUnidadAcademica());
                System.out.println("Correo: " + d.getCorreo());
                System.out.println("Contra: " + d.getContra());
                System.out.println("Imagen: " + d.getImagen());
                System.out.println("AcademiaDocente: "+ d.getAcademiaDocente());
                System.out.println("NombreAcademiaDocente: " + d.getNombreAcademiaDocente());
                System.out.println("Grado: " + d.getGrado());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        DocenteDAO dao = new DocenteDAO();
        
        try {
            DocenteDTO d = dao.buscaDocente("docentefeik");
            
            System.out.println("\n\nIdentificador: " + d.getIdentificador());
            System.out.println("Nombres: " + d.getNombres());
            System.out.println("Apellidos: " + d.getApellidos());
            System.out.println("Institucion: " + d.getInstitucion());
            System.out.println("Unidad Academica: " + d.getUnidadAcademica());
            System.out.println("Correo: " + d.getCorreo());
            System.out.println("Contra: " + d.getContra());
            System.out.println("Imagen: " + d.getImagen());
            System.out.println("AcademiaDocente: "+ d.getAcademiaDocente());
            System.out.println("NombreAcademiaDocente: " + d.getNombreAcademiaDocente());
            System.out.println("Grado: " + d.getGrado());
            
        } catch (SQLException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
