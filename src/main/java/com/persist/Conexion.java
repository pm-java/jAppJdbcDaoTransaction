/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Clase para manejar la conexion a la base de datos y el pool de conexiones
 * @author PM
 */
public class Conexion {

    private static String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASS = "passReg1";

    
    /***
     * Configuracion del Pool de Conexiones
     * @return 
     */
    public static DataSource getDataSource(){
        BasicDataSource bDS = new BasicDataSource();
        //bDS.setDriverClassName("com.mysql.jdbc.Driver");
        bDS.setUrl(URL);
        bDS.setUsername(USER);
        bDS.setPassword(PASS);
        
        // definimos el tamaño inicial del Pool de Conexiones
        // numero de conexiones que manejará y se harán hacia la base de datos como máximo
        // y las cuales serán manejadas y reasignadas por el propio pool
        bDS.setInitialSize(5);
        return bDS;
    }
    
    
    /***
     * Este metodo es igual que el que teniamos con el Driver Manager solo que ahora el 
     * Pool de conexiones es el que obtiene dicha conexion
     * @return
     * @throws SQLException 
     */
    public static final Connection getConnection() throws SQLException{
       // return DriverManager.getConnection(URL, USER, PASS);
       return getDataSource().getConnection();
    }
    
    public static final void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static final void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static final void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static final void close(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


