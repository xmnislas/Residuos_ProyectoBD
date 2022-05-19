/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import entidades.EmpresaTransportista;
import entidades.Productor;
import entidades.Quimico;
import entidades.Residuo;
import entidades.SolicitudTraslado;
import entidades.Traslado;
import entidades.Vehiculo;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public interface INegocios {
    /**
     * Metodo para guadarResiduo
     * @param Residuo 
     */
    public void guadarResiduo(Residuo Residuo);
    
    /**
     * Metodo para guadarQuimico
     * @param quimico 
     */
    public void guadarQuimico(Quimico quimico);
    
    /**
     * Metodo para guadarSolicitudTraslado
     * @param solicitud 
     */
    public void guadarSolicitudTraslado(SolicitudTraslado solicitud);
    
    /**
     * Metodo para guadarAsignacion
     * @param asignacion 
     */
    public void guadarAsignacion(Asignacion asignacion);
    
    /**
     * Metodo para guadarTraslado
     * @param traslado 
     */
    public void guadarTraslado(Traslado traslado);
    
    /**
     * Metodo para actualizarSolicitudTraslado
     * @param solicitud 
     */
    public void actualizarSolicitudTraslado(SolicitudTraslado solicitud);
    
    /**
     * Metodo para actualizarSolicitudTraslado
     * @param asignacion 
     */
    public void actualizarAsignacion(Asignacion asignacion);
    
    /**
     * Metodo para buscarResiduo
     * @param idResiduo
     * @return 
     */
    public Residuo buscarResiduo(ObjectId idResiduo);
    
    /**
     * Metodo para buscarProductor
     * @param idProductor
     * @return 
     */
    public Productor buscarProductor(ObjectId idProductor);
    
    /**
     * Metodo para buscarSolicitudTraslado
     * @param idSolicitud
     * @return 
     */
    public SolicitudTraslado buscarSolicitudTraslado(ObjectId idSolicitud);
    
    /**
     * Metodo para buscar asignacion
     * @param idAsignacion 
     * @return 
     */
    public Asignacion buscarAsignacion(ObjectId idAsignacion);
    
    /**
     * Lista para buscarQuimicos
     * @return 
     */
    public List<Quimico> buscarQuimicos();
  
      /**
     * Lista para buscarResiduos
     * @return 
     */
    public List<Residuo> buscarResiduos();
    
    /**
     * Lista para buscarResiduosProductor
     * @param idProductor
     * @return 
     */
    public List<Residuo> buscarResiduosProductor(ObjectId idProductor);
    
       /**
     * Lista para buscarProductores
     * @return 
     */
    public List<Productor> buscarProductores();
    
    /**
     * Lista para buscarSolicitudesTraslado
     * @return 
     */
    public List<SolicitudTraslado> buscarSolicitudesTraslado();
    
    /**
     * Lista para buscarSolicitudesTrasladoNoAtendidas
     * @return 
     */
    public List<SolicitudTraslado> buscarSolicitudesTrasladoNoAtendidas();
    
    /**
     * Lista para buscarEmpresasTransportistas
     * @return 
     */
    public List<EmpresaTransportista> buscarEmpresasTransportistas();
    
    /**
     * Lista para buscarAsignacionesPorEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<RelAsignacionEmpresa> buscarAsignacionesPorEmpresa(ObjectId idEmpresaTransportista);
    
    /**
     * Lista para buscarVehiculosPorEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<Vehiculo> buscarVehiculosPorEmpresa(ObjectId idEmpresaTransportista);
  
}
