/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Vehiculo;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class VehiculosDAO extends PrincipalDAO<Vehiculo>{
    private MongoDatabase basedatos;

    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public VehiculosDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para llamar a la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Vehiculo> getColeccion(){
       return this.basedatos.getCollection("vehiculos", Vehiculo.class);
    }
    
    /**
     * Metodo para agregar 
     * @param vehiculo 
     */
    @Override
    public void agregar(Vehiculo vehiculo){
        MongoCollection<Vehiculo> coleccion = this.getColeccion();
        coleccion.insertOne(vehiculo);
    }
    
    /**
     * Metodo para actualizar
     * @param vehiculo 
     */
    @Override
    public void actualizar(Vehiculo vehiculo){
    }

    /**
     * Metodo para consultar
     * @param idVehiculo
     * @return 
     */
    @Override
    public Vehiculo consultar(ObjectId idVehiculo){
        MongoCollection<Vehiculo> coleccion = this.getColeccion();
        Vehiculo vehiculo = coleccion.find(Filters.eq("_id", idVehiculo)).first();
        return vehiculo;
        
    }

    /**
     * Lista para consultar todos 
     * @return 
     */
    @Override
    public List<Vehiculo> consultarTodos(){
        MongoCollection<Vehiculo> coleccion = this.getColeccion();
        List<Vehiculo> listaVehiculos = new LinkedList<>();
        coleccion.find().into(listaVehiculos);
        return listaVehiculos;
    }
    
    /**
     * Lista para consultar todos por empresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<Vehiculo> consultarTodosEmpresa(ObjectId idEmpresaTransportista){
        MongoCollection<Vehiculo> coleccion = this.getColeccion();
        List<Vehiculo> listaVehiculos = new LinkedList<>();
        coleccion.find(Filters.eq("empresaTransportista._id", idEmpresaTransportista)).into(listaVehiculos);
        return listaVehiculos;
    }
}
