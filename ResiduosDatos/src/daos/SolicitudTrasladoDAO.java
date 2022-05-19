/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.SolicitudTraslado;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class SolicitudTrasladoDAO extends PrincipalDAO<SolicitudTraslado>{
    private MongoDatabase basedatos;
    
    /**
     * Constructor con parametros establecidos 
     * @param baseDatos
     */
    public SolicitudTrasladoDAO(MongoDatabase baseDatos) {
        this.basedatos = baseDatos;
    }

    /**
     * Metodo para obtener la coleccion
     * @return 
     */
    @Override
    protected MongoCollection<SolicitudTraslado> getColeccion(){
        return this.basedatos.getCollection("solicitudesTraslado", SolicitudTraslado.class);
    }

    /**
     * Metodo para agregar
     * @param solicitud 
     */
    @Override
    public void agregar(SolicitudTraslado solicitud){
        MongoCollection<SolicitudTraslado> coleccion = this.getColeccion();
        coleccion.insertOne(solicitud);
    }

    /**
     * Metodo para actualizar
     * @param solicitudTraslado 
     */
    @Override
    public void actualizar(SolicitudTraslado solicitudTraslado){
        MongoCollection<SolicitudTraslado> coleccion = this.getColeccion();
        SolicitudTraslado solicitudActualizada = coleccion.find(Filters.eq("_id", solicitudTraslado.getId())).first();
        solicitudActualizada.setAtendida(solicitudTraslado.isAtendida());
        solicitudActualizada.setFecha(solicitudTraslado.getFecha());
        solicitudActualizada.setProductor(solicitudTraslado.getProductor());
        solicitudActualizada.setResiduos(solicitudTraslado.getResiduos());
        coleccion.findOneAndReplace(Filters.eq("_id", solicitudTraslado.getId()), solicitudActualizada);
    }

    /**
     * Metodo para solictud traslado
     * @param id
     * @return 
     */
    @Override
    public SolicitudTraslado consultar(ObjectId id){
        MongoCollection<SolicitudTraslado> coleccion = this.getColeccion();
        SolicitudTraslado solicitud = coleccion.find(Filters.eq("_id", id)).first();
        return solicitud;
    }

    /**
     * Lista para consultar todos
     * @return 
     */
    @Override
    public List<SolicitudTraslado> consultarTodos(){
        MongoCollection<SolicitudTraslado> coleccion = this.getColeccion();
        List<SolicitudTraslado> listaSolicitudes = new LinkedList<>();
        coleccion.find().into(listaSolicitudes);
        return listaSolicitudes;
    }
    
    /**
     * Lista para constular no atendidas
     * @return 
     */
    public List<SolicitudTraslado> consultarNoAtendidas(){
        MongoCollection<SolicitudTraslado> coleccion = this.getColeccion();
        List<SolicitudTraslado> listaSolicitudes = new LinkedList<>();
        coleccion.find(Filters.eq("atendida", false)).into(listaSolicitudes);
        return listaSolicitudes;
    }
}
