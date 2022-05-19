 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Quimico;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;


/**
 *
 * @author Usuario
 */
public class QuimicosDAO extends PrincipalDAO<Quimico>{
    private MongoDatabase basedatos;

    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public QuimicosDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Quimico> getColeccion(){
       return this.basedatos.getCollection("quimicos", Quimico.class);
    }

    /**
     * Metodo para agregar
     * @param quimico 
     */
    @Override
    public void agregar(Quimico quimico){
        MongoCollection<Quimico> coleccion = this.getColeccion();
        coleccion.insertOne(quimico);
    }
    
    /**
     * Metodo para actualizar
     * @param quimico 
     */
    @Override
    public void actualizar(Quimico quimico){
        MongoCollection<Quimico> coleccion = this.getColeccion();
        Quimico quimicoActualizado = coleccion.find(Filters.eq("_id", quimico.getId())).first();
        quimicoActualizado.setNombre(quimico.getNombre());
        coleccion.findOneAndReplace(Filters.eq("_id", quimico.getId()),quimicoActualizado);
    }

    /**
     * Metodo para consultar
     * @param id
     * @return 
     */
    @Override
    public Quimico consultar(ObjectId id){
        MongoCollection<Quimico> coleccion = this.getColeccion();
        Quimico quimico = coleccion.find(Filters.eq("_id", id)).first();
        return quimico;
    }

    /**
     * Lista para consultar todos
     * @return 
     */
    @Override
    public List<Quimico> consultarTodos(){
        MongoCollection<Quimico> coleccion = this.getColeccion();
        List<Quimico> listaQuimico = new LinkedList<>();
        coleccion.find().into(listaQuimico);
        return listaQuimico;
    }
}
