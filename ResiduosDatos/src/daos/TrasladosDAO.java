/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Traslado;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class TrasladosDAO extends PrincipalDAO<Traslado>{
    private MongoDatabase basedatos;
    
    /**
     * Constructor para establecer metodos establecidos
     * @param baseDatos
     */
    public TrasladosDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Traslado> getColeccion(){
       return this.basedatos.getCollection("traslados", Traslado.class);
    }

    /**
     * Metodo para agregar 
     * @param traslado 
     */
    @Override
    public void agregar(Traslado traslado){
        MongoCollection<Traslado> coleccion = this.getColeccion();
        coleccion.insertOne(traslado);
    }
    
    /**
     * Metodo para actualizar
     * @param traslado 
     */
    @Override
    public void actualizar(Traslado traslado){
        
    }

    /**
     * Metodo para consultar
     * @param idTraslado
     * @return 
     */
    @Override
    public Traslado consultar(ObjectId idTraslado){
        MongoCollection<Traslado> coleccion = this.getColeccion();
        Traslado traslado = coleccion.find(Filters.eq("_id", idTraslado)).first();
        return traslado;
    }
    
    /**
     * Metodo para consultar todos
     * @return 
     */
    @Override
    public List<Traslado> consultarTodos(){
        MongoCollection<Traslado> coleccion = this.getColeccion();
        List<Traslado> listaTraslados = new LinkedList<>();
        coleccion.find().into(listaTraslados);
        return listaTraslados;
    }
}