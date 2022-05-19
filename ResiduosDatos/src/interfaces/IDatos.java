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
 * @param <T>
 */
public interface IDatos<T> {
    /**
     * Metodo para agregarResiduo
     * @param residuo 
     */
    public void agregarResiduo(Residuo residuo);
    
    /**
     * Metodo para agregarQuimico
     * @param quimico 
     */
    public void agregarQuimico(Quimico quimico);
    
    /**
     * Metodo para agregarProductor
     * @param productor 
     */
    public void agregarProductor(Productor productor);
    
    /**
     * Metodo para agregarSolicitudTraslado
     * @param solicitud 
     */
    public void agregarSolicitudTraslado(SolicitudTraslado solicitud);
    
    /**
     * Metodo para agregarEmpresaTransportista
     * @param empresaTransportista 
     */
    public void agregarEmpresaTransportista(EmpresaTransportista empresaTransportista);
    
    /**
     * Metodo para agregarAsignacion
     * @param asignacion 
     */
    public void agregarAsignacion(Asignacion asignacion);
    
    /**
     * Metodo para agregarVehiculo
     * @param vehiculo 
     */
    public void agregarVehiculo(Vehiculo vehiculo);
    
    /**
     * Metodo para agregarTraslado
     * @param traslado 
     */
    public void agregarTraslado(Traslado traslado);
    
    /**
     * Metodo para actualizarSolicitudTraslado
     * @param solicitudTraslado 
     */
    public void actualizarSolicitudTraslado(SolicitudTraslado solicitudTraslado);
    
    /**
     * Metodo para actualizar asignaciones
     * @param asignacion 
     */
    public void actualizarAsignacion(Asignacion asignacion);
   
    /**
     * Lista para consultarResiduos
     * @return 
     */
    public List<Residuo> consultarResiduos();
    
    /**
     * Interface para consultarResiduosProductor
     * @param idProductor
     * @return 
     */
    public List<Residuo> consultarResiduosProductor(ObjectId idProductor);

    /**
     * Lista para consultarQuimicos
     * @return 
     */
    public List<Quimico> consultarQuimicos();
    
     /**
     * Lista para consultarProductores
     * @return 
     */
    public List<Productor> consultarProductores();
    
     /**
     * Lista para consultarSolicitudesTraslado
     * @return 
     */
    public List<SolicitudTraslado> consultarSolicitudesTraslado();
    
     /**
     * Lista para consultarSolicitudesTrasladoNoAtendidas
     * @return 
     */
    public List<SolicitudTraslado> consultarSolicitudesTrasladoNoAtendidas();
    
     /**
     * Lista para consultarEmpresasTransportistas
     * @return 
     */
    public List<EmpresaTransportista> consultarEmpresasTransportistas();
    
     /**
     * Lista para consultarAsignaciones
     * @return 
     */
    public List<Asignacion> consultarAsignaciones();
    
     /**
     * Lista para consultarVehiculos
     * @return 
     */
    public List<Vehiculo> consultarVehiculos();
    
    /**
     * Lista para consultarVehiculosEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<Vehiculo> consultarVehiculosEmpresa(ObjectId idEmpresaTransportista);
    
    /**
     * Lista para consultarAsignacionesEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<RelAsignacionEmpresa> consultarAsignacionesEmpresa(ObjectId idEmpresaTransportista);

    /**
     * Lista para consultarResiduo
     * @param idResiduo
     * @return 
     */
    public Residuo consultarResiduo(ObjectId idResiduo);
    
    /**
     * Lista para consultarQuimico
     * @param idQuimico
     * @return 
     */
    public Quimico consultarQuimico(ObjectId idQuimico);
    
    /**
     * Metodo para consultarProductor
     * @param idProductor
     * @return 
     */
    public Productor consultarProductor(ObjectId idProductor);
    
    /**
     * Metodo para consultarSolicitudTraslado
     * @param idSolicitud
     * @return 
     */
    public SolicitudTraslado consultarSolicitudTraslado(ObjectId idSolicitud);
    
    /**
     * Metodo para consultarEmpresaTransportista
     * @param idEmpresaTransportista
     * @return 
     */
    public EmpresaTransportista consultarEmpresaTransportista(ObjectId idEmpresaTransportista);
    
    /**
     * Metodo para consultarAsignacion
     * @param idAsignacion
     * @return 
     */
    public Asignacion consultarAsignacion(ObjectId idAsignacion);   
}