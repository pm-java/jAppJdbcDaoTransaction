/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dto.PersonaDto;
import com.persist.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author PM
 */
public class PersonaDaoJDBC implements IPersonaDao {
    private static final Logger LOG = Logger.getLogger(PersonaDaoJDBC.class.getName());
    
    private Connection conexionTransaccional;
    private static String SELECT = "SELECT id_persona, nombre, apellido, email, telefono from persona";
    private static String INSERT = "INSERT into persona (nombre, apellido, email, telefono) values(?,?,?,?)";
    private static String UPDATE = "UPDATE persona set nombre=?, apellido=?, email=?, telefono=? where id_persona=?";
    private static String DELETE = "DELETE from persona where id_persona=?";
    
    
    public PersonaDaoJDBC(){}
    
    public PersonaDaoJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    
    /***
     * @see
     * @return
     * @throws SQLException 
     */
    @Override
    public List<PersonaDto> select() throws SQLException{
        LOG.info("consultar personas");
        List<PersonaDto> personas = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT);
            while(rs.next()){
                PersonaDto p = new PersonaDto(rs.getInt("id_persona"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"));
                personas.add(p);
            }
        } finally{
            Conexion.close(rs);
            Conexion.close(st);
            if(this.conexionTransaccional == null){
                Conexion.close(con);
            }
        }
        return personas;
    }

    /***
     * @see 
     * @param p
     * @return
     * @throws SQLException 
     */
    @Override
    public int inserta(PersonaDto p) throws SQLException{
        LOG.info("inserta persona");
        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            con = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            rows = ps.executeUpdate();
            
        } finally{
            Conexion.close(ps);
            if(this.conexionTransaccional == null){
                Conexion.close(con);
            }
        }
        return rows;
    }

    
    /***
     * @see 
     * @param p
     * @return
     * @throws SQLException 
     */
    @Override
    public int actualiza(PersonaDto p) throws SQLException{
        LOG.info("actualiza persona");
        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            con = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = con.prepareStatement(UPDATE);            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getId());
            rows = ps.executeUpdate();
            
        } finally{
            Conexion.close(ps);
            if(this.conexionTransaccional == null){
                Conexion.close(con);
            }
        }
        return rows;
    }


    /***
     * @see 
     * @param id
     * @return
     * @throws SQLException 
     */
    @Override
    public int elimina(int id) throws SQLException{
        LOG.info("elimina persona " + id);
        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            con = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = con.prepareStatement(DELETE);            
            ps.setInt(1, id);
            rows = ps.executeUpdate();
            
        } finally{
            Conexion.close(ps);
            if(this.conexionTransaccional == null){
                Conexion.close(con);
            }
        }
        return rows;
    }
    
}
