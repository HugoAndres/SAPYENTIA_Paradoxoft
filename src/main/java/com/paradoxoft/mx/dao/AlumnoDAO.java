/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.dao;

import com.paradoxoft.mx.modelo.AlbercaDeConexiones;
import com.paradoxoft.mx.modelo.Alumno;
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
public class AlumnoDAO {
    
    private static final String GUARDA_ALUMNO = "select spGuardaAlumno(?,?,?,?,?,?,?,?,?)";
    private static final String TRAE_ALUMNOS = "select * from spTraeAlumnos()";
    private static final String BUSCA_ALUMNO = "select * from spBuscaAlumno(?)";
    
    private Connection con = null;
    
    public void conectar(){
       this.con = AlbercaDeConexiones.obtenInstancia().obtenConexion();
    }
    
    public int guardaAlumno(Alumno a) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            
            if(a == null){
                System.out.println("NO SE PUEDE GUARDAR EL ALUMNO, ES NULO");
                return -3;
            }
            
            ps = con.prepareStatement(GUARDA_ALUMNO);
            ps.setString(1, a.getIdentificador());
            ps.setString(2, a.getNombres());
            ps.setString(3, a.getApellidos());
            ps.setString(4, a.getInstitucion());
            ps.setString(5, a.getUnidadAcademica());
            ps.setString(6, a.getCorreo());
            ps.setString(7, a.getContra());
            ps.setString(8, a.getImagen());
            ps.setString(9, a.getCarrera());
            ps.executeQuery();
            
        } catch(SQLException ex){
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<Alumno> traeAlumnos() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumno> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(TRAE_ALUMNOS);
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
    
    public Alumno buscaAlumno(String identificador) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumno> resultados = new ArrayList<>();
        try{
            ps = con.prepareStatement(BUSCA_ALUMNO);
            ps.setString(1, identificador);
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
        AlumnoDAO dao = new AlumnoDAO();
        Alumno a = new Alumno();
        a.setIdentificador("miusuariofeik");
        a.setNombres("Mi Usuario");
        a.setApellidos("Feik");
        a.setInstitucion("InstitutoFEIK");
        a.setUnidadAcademica("FEEIIK");
        a.setCorreo("feik@alumno.if.mx");
        a.setContra("miuu");
        a.setImagen("imgfeik");
        a.setCarrera("Ingeniería FEIK");
        try {
            dao.guardaAlumno(a);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        AlumnoDAO dao = new AlumnoDAO();
        
        try {
            List<Alumno> lista = dao.traeAlumnos();
            
            for(Alumno a : lista){
                System.out.println("\n\nIdentificador: " + a.getIdentificador());
                System.out.println("Nombres: " + a.getNombres());
                System.out.println("Apellidos: " + a.getApellidos());
                System.out.println("Institucion: " + a.getInstitucion());
                System.out.println("Unidad Academica: " + a.getUnidadAcademica());
                System.out.println("Correo: " + a.getCorreo());
                System.out.println("Contra: " + a.getContra());
                System.out.println("Imagen: " + a.getImagen());
                System.out.println("Carrera: " + a.getCarrera());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        AlumnoDAO dao = new AlumnoDAO();
        
        try {
            Alumno a = dao.buscaAlumno("miusuariofeik");

            System.out.println("\n\nIdentificador: " + a.getIdentificador());
            System.out.println("Nombres: " + a.getNombres());
            System.out.println("Apellidos: " + a.getApellidos());
            System.out.println("Institucion: " + a.getInstitucion());
            System.out.println("Unidad Academica: " + a.getUnidadAcademica());
            System.out.println("Correo: " + a.getCorreo());
            System.out.println("Contra: " + a.getContra());
            System.out.println("Imagen: " + a.getImagen());
            System.out.println("Carrera: " + a.getCarrera());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
