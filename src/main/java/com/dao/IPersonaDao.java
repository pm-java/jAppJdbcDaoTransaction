/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dto.PersonaDto;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para los métodos de Persona
 * @author PM
 */
public interface IPersonaDao {
    
    /***
     * Consulta personas
     * @return lista de personasdto
     * @throws SQLException 
     */
    List<PersonaDto> select() throws SQLException;
    
    /***
     * insertar una nueva persona
     * @param p objeto persona
     * @return entero con la respuesta de la ejecución
     * @throws SQLException 
     */
    int inserta(PersonaDto p) throws SQLException;

    /***
     * actualiza un registro de persona
     * @param p objeto persona
     * @return entero con la respuesta de la ejecución
     * @throws SQLException 
     */
    int actualiza(PersonaDto p) throws SQLException;

    /***
     * Elimina una persona
     * @param id clave de la persona a eliminar
     * @return entero con la respuesta de la ejecución
     * @throws SQLException 
     */
    int elimina(int id) throws SQLException;
}
