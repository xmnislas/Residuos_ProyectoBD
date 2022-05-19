/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Residuo;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class ResiduosDAO extends PrincipalDAO<Residuo> {
    private MongoDatabase basedatos;
    
    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public ResiduosDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Residuo> getColeccion(){
       return this.basedatos.getCollection("residuos", Residuo.class);
    }

    /**
     * Metodo para agregar
     * @param residuo 
     */
    @Override
    public void agregar(Residuo residuo){
        MongoCollection<Residuo> coleccion = this.getColeccion();
        coleccion.insertOne(residuo);
    }
    
    /**
     * Metodo para actualizar
     * @param residuo 
     */
    @Override
    public void actualizar(Residuo residuo){
        MongoCollection<Residuo> coleccion = this.getColeccion();
        Residuo residuoActualizado = coleccion.find(Filters.eq("_id", residuo.getId())).first();
        residuoActualizado.setNombre(residuo.getNombre());
        residuoActualizado.setCodigo(residuo.getCodigo());
        residuoActualizado.setIdsQuimicos(residuo.getIdsQuimicos());
        if (!residuoActualizado.getIdsQuimicos().isEmpty()) {
            residuoActualizado.setIdsQuimicos(residuo.getIdsQuimicos());
        }
        coleccion.findOneAndReplace(Filters.eq("_id", residuo.getId()), residuoActualizado);
    }
    
    /**
     * Metodo para consultar
     * @param id
     * @return 
     */
    @Override
    public Residuo consultar(ObjectId id){
        MongoCollection<Residuo> coleccion = this.getColeccion();
        Residuo residuo = coleccion.find(Filters.eq("_id", id)).first();
        return residuo;
    }
    
    /**
     * List para consultar todos
     * @return 
     */
    @Override
    public List<Residuo> consultarTodos(){
        MongoCollection<Residuo> coleccion = this.getColeccion();
        List<Residuo> listaResiduos = new LinkedList<>();
        coleccion.find().into(listaResiduos);
        return listaResiduos;
    }
    
    /**
     * Lista para consultar productor
     * @param idProductor
     * @return 
     */
    public List<Residuo> consultarProductor(ObjectId idProductor){
        MongoCollection<Residuo> coleccion = this.getColeccion();
        List<Residuo> listaResiduos = new LinkedList<>();
        coleccion.find(Filters.eq("idProductor", idProductor)).into(listaResiduos);
        return listaResiduos;
    }
}
