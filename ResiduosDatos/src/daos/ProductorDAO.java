/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Productor;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class ProductorDAO extends PrincipalDAO<Productor>{
    private MongoDatabase basedatos;
    
    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public ProductorDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Productor> getColeccion(){
       return this.basedatos.getCollection("productores", Productor.class);
    }

    /**
     * Metodo para agregar
     * @param productor 
     */
    @Override
    public void agregar(Productor productor){
        MongoCollection<Productor> coleccion = this.getColeccion();
        coleccion.insertOne(productor);
    }

    /**
     * Metodo para actualizar
     * @param productor 
     */
    @Override
    public void actualizar(Productor productor){
        MongoCollection<Productor> coleccion = this.getColeccion();
        Productor productorActualizado = coleccion.find(Filters.eq("_id", productor.getId())).first();
        productorActualizado.setNombre(productor.getNombre());
        productorActualizado.setEmail(productor.getEmail());
        productorActualizado.setDireccion(productor.getDireccion());
        coleccion.findOneAndReplace(Filters.eq("_id", productor.getId()), productorActualizado);
    }
    
    /**
     * Lista para consutlar todos
     * @return 
     */
    @Override
    public List<Productor> consultarTodos(){
        MongoCollection<Productor> coleccion = this.getColeccion();
        List<Productor> listaProductores = new LinkedList<>();
        coleccion.find().into(listaProductores);
        return listaProductores;
    }

    /**
     * Metodo para consultar
     * @param id
     * @return 
     */
    @Override
    public Productor consultar(ObjectId id){
        MongoCollection<Productor> coleccion = this.getColeccion();
        Productor productor = coleccion.find(Filters.eq("_id", id)).first();
        return productor;
    }
}
