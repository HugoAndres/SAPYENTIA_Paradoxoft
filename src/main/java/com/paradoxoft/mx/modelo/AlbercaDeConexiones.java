/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author user
 */
public class AlbercaDeConexiones {
    
    private static AlbercaDeConexiones conPool;
    private BasicDataSource fuenteDatos = null; 
    
    private static final String user= "mfwjtvgnzxnozw"; 
    private static final String pwd = "c4dab313f9c10abdf9e3eee61baba8a89f813f4f86d47006bb05dd1db1ed12de";
    private static final String url ="jdbc:postgresql://ec2-34-233-187-36.compute-1.amazonaws.com:5432/d732rhkn112mj6";
    private static final String pgDriver = "org.postgresql.Driver";
    
    private AlbercaDeConexiones(){
        
        fuenteDatos = new BasicDataSource();
        fuenteDatos.setDriverClassName(pgDriver);
        fuenteDatos.setUsername(user);
        fuenteDatos.setPassword(pwd);
        fuenteDatos.setUrl(url);
        
        fuenteDatos.setMinIdle(5);
        fuenteDatos.setMaxIdle(10);
        fuenteDatos.setMaxTotal(20);
        fuenteDatos.setMaxWaitMillis(10000);

    }
    
    public static AlbercaDeConexiones obtenInstancia(){
        if(conPool == null){
            conPool = new AlbercaDeConexiones();
        }
        return conPool; 
    }
    
    public Connection obtenConexion(){
        Connection con = null;
        try {
            con = this.fuenteDatos.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AlbercaDeConexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void retirarConexion(Connection con) throws SQLException{
        con.close();
    }
}
