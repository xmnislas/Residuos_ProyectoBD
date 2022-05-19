/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import daos.FabricaDatos;
import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import entidades.EmpresaTransportista;
import entidades.Productor;
import entidades.Quimico;
import entidades.Residuo;
import entidades.SolicitudTraslado;
import entidades.Traslado;
import entidades.Vehiculo;
import interfaces.IDatos;
import interfaces.INegocios;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class FachadaNegocios implements INegocios{
    CtrlQuimicos ctrlQuimico;
    CtrlResiduos ctrlResiduo;
    CtrlProductores ctrlProductores;
    CtrlSolicitudesTraslado ctrlSolicitudesTraslado;
    CtrlEmpresasTransportistas ctrlEmpresasTransportistas;
    CtrlAsignaciones ctrlAsignaciones;
    CtrlVehiculos ctrlVehiculos;
    CtrlTraslados ctrlTraslados;
    IDatos iDatos = FabricaDatos.crearDatos();
    
    /**
     * Metodo de fachada para mandar a llamar a otros
     */
    public FachadaNegocios() {
        this.ctrlQuimico = new CtrlQuimicos(iDatos);
        this.ctrlResiduo= new CtrlResiduos(iDatos);
        this.ctrlProductores = new CtrlProductores(iDatos);
        this.ctrlSolicitudesTraslado = new CtrlSolicitudesTraslado(iDatos);
        this.ctrlEmpresasTransportistas = new CtrlEmpresasTransportistas(iDatos);
        this.ctrlAsignaciones = new CtrlAsignaciones(iDatos);
        this.ctrlVehiculos = new CtrlVehiculos(iDatos);
        this.ctrlTraslados = new CtrlTraslados(iDatos);
    }

    /**
     * Metodo para guardar residuo
     * @param Residuo 
     */
    @Override
    public void guadarResiduo(Residuo Residuo){
        ctrlResiduo.guardarResiduo(Residuo);
    }

    /**
     * Metodo para guardar quimico
     * @param quimico 
     */
    @Override
    public void guadarQuimico(Quimico quimico){
        ctrlQuimico.guardarQuimico(quimico);
    }
    
    /**
     * Metodo para guadarSolicitudTraslado 
     * @param solicitud 
     */
    @Override
    public void guadarSolicitudTraslado(SolicitudTraslado solicitud){
        ctrlSolicitudesTraslado.guardarSolicitudTraslado(solicitud);
    }
    
    /**
     * Metodo para guadarAsignacion
     * @param asignacion 
     */
    @Override
    public void guadarAsignacion(Asignacion asignacion){
        ctrlAsignaciones.guardarAsignacion(asignacion);
    }
    
    /**
     * Metodo para actualizar Asignacion
     * @param asignacion 
     */
    @Override
    public void actualizarAsignacion(Asignacion asignacion){
        ctrlAsignaciones.actualizarAsignacion(asignacion);
    }
    
    /**
     * Metodo para guadarTraslado
     * @param traslado 
     */
    @Override
    public void guadarTraslado(Traslado traslado){
        ctrlTraslados.guardarTraslado(traslado);
    }
    
    /**
     * Metodo para actualizarSolicitudTraslado
     * @param solicitud 
     */
    @Override
    public void actualizarSolicitudTraslado(SolicitudTraslado solicitud){
        ctrlSolicitudesTraslado.actualizarSolicitudTraslado(solicitud);
    }
    
    /**
     * Metodo de tipo residuo para buscarResiduo 
     * @param idResiduo
     * @return 
     */
    @Override
    public Residuo buscarResiduo(ObjectId idResiduo){
        return ctrlResiduo.buscarResiduo(idResiduo);
    }
    
    /**
     * Metotodo de tipo productor para buscarProductor
     * @param idProductor
     * @return 
     */
    @Override
    public Productor buscarProductor(ObjectId idProductor){
        return ctrlProductores.buscarProductor(idProductor);
    }
    
    /**
     * Metodo de SolicitudTraslado para buscarSolicitudTraslado
     * @param idSolicitud
     * @return 
     */
    @Override
    public SolicitudTraslado buscarSolicitudTraslado(ObjectId idSolicitud){
        return ctrlSolicitudesTraslado.buscarSolicitudTraslado(idSolicitud);
    }
    
    /**
     * Metodo de asignacion para buscar asignacion
     * @param idAsignacion 
     * @return 
     */
    @Override
    public Asignacion buscarAsignacion(ObjectId idAsignacion){
        return ctrlAsignaciones.buscarAsignacion(idAsignacion);
    }

    /**
     * Lista para buscar quimicos
     * @return 
     */
    @Override
    public List<Quimico> buscarQuimicos(){
        return ctrlQuimico.buscarQuimicos();
    }

    /**
     * Lista para buscar residuos
     * @return 
     */
    @Override
    public List<Residuo> buscarResiduos(){
        return ctrlResiduo.buscarResiduos();
    }
    
    /**
     * Lista para buscarResiduosProductor
     * @param idProductor
     * @return 
     */
    @Override
    public List<Residuo> buscarResiduosProductor(ObjectId idProductor){
        return ctrlResiduo.buscarResiduosProductor(idProductor);
    }
    
    /**
     * Lista para 
     * @return buscarProductores
     */
    @Override
    public List<Productor> buscarProductores(){
        return ctrlProductores.buscarProductores();
    }
    
    /**
     * Lista para buscarEmpresasTransportistas
     * @return 
     */
    @Override
    public List<EmpresaTransportista> buscarEmpresasTransportistas(){
        return ctrlEmpresasTransportistas.buscarEmpresasTransportistas();
    }
    
    /**
     * Lista para buscarSolicitudesTraslado
     * @return 
     */
    @Override
    public List<SolicitudTraslado> buscarSolicitudesTraslado(){
        return ctrlSolicitudesTraslado.buscarSolicitudesTraslado();
    }
    
    /**
     * Lista para buscarSolicitudesTrasladoNoAtendidas
     * @return 
     */
    @Override
    public List<SolicitudTraslado> buscarSolicitudesTrasladoNoAtendidas(){
        return ctrlSolicitudesTraslado.buscarSolicitudesTrasladoNoAtendidas();
    }
    
    /**
     * Lista para buscarAsignacionesPorEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public List<RelAsignacionEmpresa> buscarAsignacionesPorEmpresa(ObjectId idEmpresaTransportista){
        return this.ctrlAsignaciones.buscarAsignacionesPorEmpresa(idEmpresaTransportista);
    }
    
    /**
     * Lista para buscarVehiculosPorEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public List<Vehiculo> buscarVehiculosPorEmpresa(ObjectId idEmpresaTransportista){
        return this.ctrlVehiculos.buscarVehiculosPorEmpresa(idEmpresaTransportista);
    }
}
