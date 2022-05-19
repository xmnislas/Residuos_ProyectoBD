/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.EmpresaTransportista;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class EmpresasTransportistasDAO extends PrincipalDAO<EmpresaTransportista>{
    private MongoDatabase basedatos;
    
    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public EmpresasTransportistasDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<EmpresaTransportista> getColeccion(){
        return this.basedatos.getCollection("empresasTransportistas", EmpresaTransportista.class);
    }

    /**
     * Metodo para agregar
     * @param empresaTransportista 
     */
    @Override
    public void agregar(EmpresaTransportista empresaTransportista){
        MongoCollection<EmpresaTransportista> coleccion = this.getColeccion();
        coleccion.insertOne(empresaTransportista);
    }

    /**
     * Metodo para actualizar
     * @param entidad 
     */
    @Override
    public void actualizar(EmpresaTransportista entidad){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Metodo para consultar
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public EmpresaTransportista consultar(ObjectId idEmpresaTransportista){
        MongoCollection<EmpresaTransportista> coleccion = this.getColeccion();
        EmpresaTransportista empresaTransportista = coleccion.find(Filters.eq("_id", idEmpresaTransportista)).first();
        return empresaTransportista;
    }

    /**
     * Lista para consultarTodos
     * @return 
     */
    @Override
    public List<EmpresaTransportista> consultarTodos(){
        MongoCollection<EmpresaTransportista> coleccion = this.getColeccion();
        List<EmpresaTransportista> listaEmpresasTransportistas = new LinkedList<>();
        coleccion.find().into(listaEmpresasTransportistas);
        return listaEmpresasTransportistas;
    }
}