/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.modelo.Academia;
import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
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
public class AcademiaDAO {
    
    private static final String GUARDA = "select spGuardaAcademia(?)";
    private static final String TRAETODO = "select * from spTraeAcademias()";
    private static final String BUSCA = "select * from spBuscaAcademia(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public void guardaAcademia(Academia ac) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(GUARDA);
            ps.setString(1, ac.getNombre());
            rs = ps.executeQuery();
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
    
    public List<Academia> traeAcademias() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Academia> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(TRAETODO);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Academia t = new Academia();
                t.setIdAcademia(rs.getInt("idAcademia"));
                t.setNombre(rs.getString("nombre"));
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
    
    public Academia buscaAcademia(int n_nombre) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Academia> resultados = new ArrayList<>();
        try{
            ps = this.con.prepareStatement(BUSCA);
            ps.setInt(1, n_nombre);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Academia t = new Academia();
                t.setIdAcademia(rs.getInt("idAcademia"));
                t.setNombre(rs.getString("nombre"));
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
        /*Connection con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
        if(con != null){
            System.out.println("CONECTADO");
            try {
                AlbercaDeConexiones.obtenInstancia().retirarConexion(con);
            } catch (SQLException ex) {
                Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO CONECTADO");
        }*/
        
        /*Academia ac = new Academia();
        ac.setNombre("Nueva");
        System.out.println(ac);
        AcademiaDAO dao =  new AcademiaDAO();
        try {
            dao.guardaAcademia(ac);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        /*
        AcademiaDAO ac = new AcademiaDAO();
        try {
            List<Academia> lista = ac.traeAcademias();
            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        AcademiaDAO ac = new AcademiaDAO();
        try {
            Academia x = ac.buscaAcademia(6);
            System.out.println(x);
        } catch (SQLException ex) {
            Logger.getLogger(AcademiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
}
