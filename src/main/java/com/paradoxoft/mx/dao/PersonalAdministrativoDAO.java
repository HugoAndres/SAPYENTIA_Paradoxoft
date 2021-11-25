/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.PersonalAdministrativo;
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
public class PersonalAdministrativoDAO {
    
    private static final String GUARDA_PERSONAL_ADMINISTRATIVO = "select spGuardaPersonalAdministrativo(?,?,?,?,?,?,?,?)";
    private static final String TRAE_PERSONAL_ADMINISTRATIVO = "select * from spTraePersonalAdministrativo()";
    private static final String BUSCA_PERSONAL_ADMINISTRATIVO = "select * from spBuscaPersonalAdministrativo(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int guardaPersonalAdministrativo(PersonalAdministrativo p) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(p == null){
                System.out.println("NO SE PUEDE GUARDAR EL PERSONAL ADMINISTRATIVO, ES NULO");
                return -3;
            }
            
            ps = con.prepareStatement(GUARDA_PERSONAL_ADMINISTRATIVO);
            ps.setString(1, p.getIdentificador());
            ps.setString(2, p.getNombres());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getInstitucion());
            ps.setString(5, p.getUnidadAcademica());
            ps.setString(6, p.getCorreo());
            ps.setString(7, p.getContra());
            ps.setString(8, p.getImagen());
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(PersonalAdministrativoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<PersonalAdministrativo> traePersonalAdministrativo() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PersonalAdministrativo> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_PERSONAL_ADMINISTRATIVO);
            rs = ps.executeQuery();
 
            while (rs.next()){
                PersonalAdministrativo t = new PersonalAdministrativo();
                t.setIdentificador(rs.getString("identificador"));
                t.setNombres(rs.getString("nombres"));
                t.setApellidos(rs.getString("apellidos"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setCorreo(rs.getString("correo"));
                t.setContra(rs.getString("contra"));
                t.setImagen(rs.getString("imagen"));
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
    
    public PersonalAdministrativo buscaPersonalAdministrativo(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PersonalAdministrativo> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_PERSONAL_ADMINISTRATIVO);
            ps.setString(1, identificador);
            rs = ps.executeQuery();
 
            while (rs.next()){
                PersonalAdministrativo t = new PersonalAdministrativo();
                t.setIdentificador(rs.getString("identificador"));
                t.setNombres(rs.getString("nombres"));
                t.setApellidos(rs.getString("apellidos"));
                t.setInstitucion(rs.getString("institucion"));
                t.setUnidadAcademica(rs.getString("unidadAcademica"));
                t.setCorreo(rs.getString("correo"));
                t.setContra(rs.getString("contra"));
                t.setImagen(rs.getString("imagen"));
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
        PersonalAdministrativoDAO dao = new PersonalAdministrativoDAO();
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setIdentificador("personaladministrativofeik");
        p.setNombres("Personal");
        p.setApellidos("Feik");
        p.setInstitucion("InstitutoFEIK");
        p.setUnidadAcademica("FEEIIK");
        p.setCorreo("feik@personal.if.mx");
        p.setContra("personalf");
        p.setImagen("imgfeikp");
        try {
            dao.guardaPersonalAdministrativo(p);
        } catch (SQLException ex) {
            Logger.getLogger(PersonalAdministrativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        /*
        PersonalAdministrativoDAO dao = new PersonalAdministrativoDAO();
        
        try {
            List<PersonalAdministrativo> lista = dao.traePersonalAdministrativo();
            
            for(PersonalAdministrativo p : lista){
                System.out.println("\n\nIdentificador: " + p.getIdentificador());
                System.out.println("Nombres: " + p.getNombres());
                System.out.println("Apellidos: " + p.getApellidos());
                System.out.println("Institucion: " + p.getInstitucion());
                System.out.println("Unidad Academica: " + p.getUnidadAcademica());
                System.out.println("Correo: " + p.getCorreo());
                System.out.println("Contra: " + p.getContra());
                System.out.println("Imagen: " + p.getImagen());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonalAdministrativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        PersonalAdministrativoDAO dao = new PersonalAdministrativoDAO();
        
        try {
            PersonalAdministrativo p = dao.buscaPersonalAdministrativo("personaladministrativofeik");
            
            System.out.println("\n\nIdentificador: " + p.getIdentificador());
            System.out.println("Nombres: " + p.getNombres());
            System.out.println("Apellidos: " + p.getApellidos());
            System.out.println("Institucion: " + p.getInstitucion());
            System.out.println("Unidad Academica: " + p.getUnidadAcademica());
            System.out.println("Correo: " + p.getCorreo());
            System.out.println("Contra: " + p.getContra());
            System.out.println("Imagen: " + p.getImagen());
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonalAdministrativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
    }
}
