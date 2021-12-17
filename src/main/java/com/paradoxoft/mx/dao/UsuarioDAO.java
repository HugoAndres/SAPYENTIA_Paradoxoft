/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Usuario;
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
public class UsuarioDAO {
    
    private static final String BORRA_USUARIO = "select spBorraUsuario(?)";
    private static final String TRAE_USUARIOS = "select * from spTraeUsuarios()";
    private static final String BUSCA_USUARIO = "select * from spBuscaUsuario(?)";
    private static final String BUSCA_USUARIO_CS = "select * from spBuscaUsuarioCorreoSesion(?,?);";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int borraUsuario(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{  
            ps = con.prepareStatement(BORRA_USUARIO);
            ps.setString(1, identificador);
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<Usuario> traeUsuarios() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_USUARIOS);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario us = new Usuario();
                us.setIdentificador(rs.getString("identificador"));
                us.setNombres(rs.getString("nombres"));
                us.setApellidos(rs.getString("apellidos"));
                us.setInstitucion(rs.getString("institucion"));
                us.setUnidadAcademica(rs.getString("unidadAcademica"));
                us.setCorreo(rs.getString("correo"));
                us.setContra(rs.getString("contra"));
                us.setImagen(rs.getString("imagen"));
                
                resultados.add(us);
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
    
    public Usuario buscaUsuario(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_USUARIO);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario us = new Usuario();
                us.setIdentificador(rs.getString("identificador"));
                us.setNombres(rs.getString("nombres"));
                us.setApellidos(rs.getString("apellidos"));
                us.setInstitucion(rs.getString("institucion"));
                us.setUnidadAcademica(rs.getString("unidadAcademica"));
                us.setCorreo(rs.getString("correo"));
                us.setContra(rs.getString("contra"));
                us.setImagen(rs.getString("imagen"));
                
                resultados.add(us);
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
    
    public Usuario buscaUsuarioParaLogin(String correo, String contra) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_USUARIO_CS);
            ps.setString(1, correo);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario us = new Usuario();
                us.setIdentificador(rs.getString("identificador"));
                us.setNombres(rs.getString("nombres"));
                us.setApellidos(rs.getString("apellidos"));
                us.setInstitucion(rs.getString("institucion"));
                us.setUnidadAcademica(rs.getString("unidadAcademica"));
                us.setCorreo(rs.getString("correo"));
                us.setContra(rs.getString("contra"));
                us.setImagen(rs.getString("imagen"));
                
                resultados.add(us);
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
        UsuarioDAO dao = new UsuarioDAO();
        
        try {
            List<Usuario> lista = dao.traeUsuarios();
            
            for(Usuario us : lista){
                System.out.println("\n\nIdentificador: " + us.getIdentificador());
                System.out.println("Nombres: " + us.getNombres());
                System.out.println("Apellidos: " + us.getApellidos());
                System.out.println("Institucion: " + us.getInstitucion());
                System.out.println("Unidad Academica: " + us.getUnidadAcademica());
                System.out.println("Correo: " + us.getCorreo());
                System.out.println("Contra: " + us.getContra());
                System.out.println("Imagen: " + us.getImagen());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        UsuarioDAO dao = new UsuarioDAO();
        
        try {
            Usuario us = dao.buscaUsuario("miusuariofeik");

            System.out.println("\n\nIdentificador: " + us.getIdentificador());
            System.out.println("Nombres: " + us.getNombres());
            System.out.println("Apellidos: " + us.getApellidos());
            System.out.println("Institucion: " + us.getInstitucion());
            System.out.println("Unidad Academica: " + us.getUnidadAcademica());
            System.out.println("Correo: " + us.getCorreo());
            System.out.println("Contra: " + us.getContra());
            System.out.println("Imagen: " + us.getImagen());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        UsuarioDAO dao = new UsuarioDAO();
        
        try {
            dao.borraUsuario("miusuariofeik"); 
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        UsuarioDAO dao = new UsuarioDAO();
        
        try {
            Usuario us = dao.buscaUsuarioParaLogin("albus@hw.ar","albusd");
            System.out.println(us);
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
