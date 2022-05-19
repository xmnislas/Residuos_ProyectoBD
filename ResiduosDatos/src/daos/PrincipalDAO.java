/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mongodb.client.MongoCollection;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 * @param <T>
 */
public abstract class PrincipalDAO<T> {
    
    /**
     * Mretodo para obtener la coleccion
     * @return 
     */
    protected abstract MongoCollection<T> getColeccion();

    /**
     * Metodo para agregar
     * @param entidad 
     */
    public abstract void agregar(T entidad);
    
    /**
     * Metodo para actualizar
     * @param entidad 
     */
    public abstract void actualizar(T entidad);
    
    /**
     * Metodo para consultar
     * @param id
     * @return 
     */
    public abstract T consultar(ObjectId id);

    /**
     * Metodo para consultar todos
     * @return 
     */
    public abstract List<T> consultarTodos();

}
