/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class AsignacionesDAO extends PrincipalDAO<Asignacion>{
    private MongoDatabase basedatos;
    
    /**
     * Constructor con parametros establecidos
     * @param baseDatos
     */
    public AsignacionesDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }
    
    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<Asignacion> getColeccion(){
        return this.basedatos.getCollection("asignaciones", Asignacion.class);
    }

    /**
     * Metodo para agregar
     * @param asignacion 
     */
    @Override
    public void agregar(Asignacion asignacion){
        MongoCollection<Asignacion> coleccion = this.getColeccion();
        coleccion.insertOne(asignacion);
    }

    /**
     * Metodo para actualizar
     * @param asignacion 
     */
    @Override
    public void actualizar(Asignacion asignacion){
        MongoCollection<Asignacion> coleccion = this.getColeccion();
        Asignacion asignacionActualizada = coleccion.find(Filters.eq("_id", asignacion.getId())).first();
        asignacionActualizada.setCantidad(asignacion.getCantidad());
        asignacionActualizada.setFecha(asignacion.getFecha());
        asignacionActualizada.setIdResiduo(asignacion.getIdResiduo());
        asignacionActualizada.setEmpresasTransportistas(asignacion.getEmpresasTransportistas());
        coleccion.findOneAndReplace(Filters.eq("_id", asignacion.getId()), asignacionActualizada);
    }

    /**
     * Metodo para consutlar
     * @param id
     * @return 
     */
    @Override
    public Asignacion consultar(ObjectId id){
        MongoCollection<Asignacion> coleccion = this.getColeccion();
        Asignacion asignacion = coleccion.find(Filters.eq("_id", id)).first();
        return asignacion;
    }
    

    /**
     * Lista para consultarTodos
     * @return 
     */
    @Override
    public List<Asignacion> consultarTodos(){
        MongoCollection<Asignacion> coleccion = this.getColeccion();
        List<Asignacion> listaAsignaciones = new LinkedList<>();
        coleccion.find().into(listaAsignaciones);
        return listaAsignaciones;
    }
    
    /**
     * Lista para consultarTodosEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<RelAsignacionEmpresa> consultarTodosEmpresa(ObjectId idEmpresaTransportista){
        MongoCollection<RelAsignacionEmpresa> coleccion = this.basedatos.getCollection("asignaciones", RelAsignacionEmpresa.class);
        List<Document> etapas = new ArrayList<>(); 
        etapas.add(new Document("$unwind", new Document()
            .append("path", "$empresasTransportistas")));

        etapas.add(new Document("$match", new Document()
            .append("empresasTransportistas.trasladado", false)));
        
        etapas.add(new Document("$project", new Document()
                .append("_id", 0)
                .append("idAsignacion", "$_id")
                .append("asignacionEmpresa", "$empresasTransportistas")
                .append("cantidad", 1)
                .append("fecha", 1)
                .append("idResiduo", 1)));
        
        etapas.add(new Document("$match", new Document()
            .append("asignacionEmpresa.idEmpresaTransportista", idEmpresaTransportista)));
        
        List<RelAsignacionEmpresa> resultados = new LinkedList<>();
        coleccion.aggregate(etapas).into(resultados);
        return resultados;
    }
}