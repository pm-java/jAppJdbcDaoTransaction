
import com.dao.IPersonaDao;
import com.dao.PersonaDaoJDBC;
import com.dto.PersonaDto;
import com.persist.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PM
 */
public class MainClass {
    
    private static final Logger LOG = Logger.getLogger(MainClass.class.getName());
    
    
    
    /***
     * Metodo main donde se invocan todos los ejemplos de CRUD hacia personas
     * @param args 
     */
    public static void main(String[] args) {        
        
        Connection conection = null;
        try {
            conection = Conexion.getConnection();
            if(conection.getAutoCommit()){
                conection.setAutoCommit(false);
            }
        
            IPersonaDao personaDao = new PersonaDaoJDBC(conection);
         
            // listar las personas de la base de datos
            // mostrar(personaDao.select());

            // inserta una persona
            //PersonaDto p = new PersonaDto(0, "Luis II", "Medina", "juanm@outlook.com", "6677887766");
            //personaDao.inserta(p);
            
            // elimina una persona
            int id_persona = 7;
            personaDao.elimina(id_persona);
            
            // actualiza una persona
            //PersonaDto pUpdate = new PersonaDto(2, "Karla", "Gomezz", "kgomez@outlook.com", "5544667755");
            //personaDao.actualiza(pUpdate);
            
            
            //LOG.info(personaJdbc.select().stream().filter(per -> per.getId()==pUpdate.getId()).findFirst().get().toString());
            mostrar(personaDao.select());
            
            // hacer commit de la transacción
            conection.commit();
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            try {
                conection.rollback();
            } catch (SQLException ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    private static void mostrar(List<PersonaDto> listaPersonas){
        for (PersonaDto persona : listaPersonas) {
         LOG.info(persona.toString());
        }
    }    
    
    
}
