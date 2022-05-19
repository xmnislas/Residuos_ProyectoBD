/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.SolicitudTraslado;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlSolicitudesTraslado {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlSolicitudesTraslado(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo para guardar solicitud de traslado
     * @param solicitud 
     */
    public void guardarSolicitudTraslado(SolicitudTraslado solicitud){
        this.iDatos.agregarSolicitudTraslado(solicitud);
    }
    
    /**
     * Metodo para actualizar solicitud de traslado
     * @param solicitud 
     */
    public void actualizarSolicitudTraslado(SolicitudTraslado solicitud){
        this.iDatos.actualizarSolicitudTraslado(solicitud);
    }
    
    /**
     * Metodo para
     * @param idSolicitud buscarSolicitudTraslado
     * @return 
     */
    public SolicitudTraslado buscarSolicitudTraslado(ObjectId idSolicitud){
        return this.iDatos.consultarSolicitudTraslado(idSolicitud);
    }
    
    /**
     * Lista para buscarSolicitudesTraslado
     * @return 
     */
    public List<SolicitudTraslado> buscarSolicitudesTraslado(){
        return this.iDatos.consultarSolicitudesTraslado();
    }
    
    /**
     * Lista para buscarSolicitudesTrasladoNoAtendidas
     * @return 
     */
    public List<SolicitudTraslado> buscarSolicitudesTrasladoNoAtendidas(){
        return this.iDatos.consultarSolicitudesTrasladoNoAtendidas();
    }
}
