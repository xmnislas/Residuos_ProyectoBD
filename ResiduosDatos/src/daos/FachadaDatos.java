/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import entidades.EmpresaTransportista;
import entidades.Productor;
import entidades.Quimico;
import entidades.Residuo;
import entidades.SolicitudTraslado;
import entidades.Traslado;
import entidades.Vehiculo;
import interfaces.IConexionBD;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class FachadaDatos implements IDatos{
    private final QuimicosDAO quimicosDAO;
    private final ResiduosDAO residuosDAO;
    private final ProductorDAO productorDAO;
    private final SolicitudTrasladoDAO solicitudTrasladoDAO;
    private final EmpresasTransportistasDAO empresasTransportistasDAO;
    private final AsignacionesDAO asignacionesDAO;
    private final VehiculosDAO vehiculosDAO;
    private final TrasladosDAO trasladosDAO;
    IConexionBD conexionBD;
    /**
     * Metodo para mandar a llamar a otros
     */
    public FachadaDatos(){
        conexionBD = new ConexionBD();
        this.quimicosDAO = new QuimicosDAO(conexionBD.getConnection());
        this.residuosDAO = new ResiduosDAO(conexionBD.getConnection());
        this.productorDAO = new ProductorDAO(conexionBD.getConnection());
        this.solicitudTrasladoDAO = new SolicitudTrasladoDAO(conexionBD.getConnection());
        this.empresasTransportistasDAO = new EmpresasTransportistasDAO(conexionBD.getConnection());
        this.asignacionesDAO = new AsignacionesDAO(conexionBD.getConnection());
        this.vehiculosDAO = new VehiculosDAO(conexionBD.getConnection());
        this.trasladosDAO = new TrasladosDAO(conexionBD.getConnection());
    }
    
    /**
     * Metodo para agregar residuo
     * @param residuo 
     */
    @Override
    public void agregarResiduo(Residuo residuo){
        this.residuosDAO.agregar(residuo);
    }
    
    /**
     * Metodo para agregarQuimico
     * @param quimico 
     */
    @Override
    public void agregarQuimico(Quimico quimico){
        this.quimicosDAO.agregar(quimico);
    }
    
    /**
     * Metodo para agregarProductor
     * @param productor 
     */
    @Override
    public void agregarProductor(Productor productor){
        this.productorDAO.agregar(productor);
    }
    
    /**
     * Metodo para agregarSolicituTraslado
     * @param solicitud 
     */
    @Override
    public void agregarSolicitudTraslado(SolicitudTraslado solicitud){
        this.solicitudTrasladoDAO.agregar(solicitud);
    }
    
    /**
     * Metodo para agregarEmpresaTransportista
     * @param empresaTransportista 
     */
    @Override
    public void agregarEmpresaTransportista(EmpresaTransportista empresaTransportista){
        this.empresasTransportistasDAO.agregar(empresaTransportista);
    }

    /**
     * Metodo para agregarAsignacion
     * @param asignacion 
     */
    @Override
    public void agregarAsignacion(Asignacion asignacion){
        this.asignacionesDAO.agregar(asignacion);
    }
    
    /**
     * Metodo para agregarTraslado
     * @param traslado 
     */
    @Override
    public void agregarTraslado(Traslado traslado){
        this.trasladosDAO.agregar(traslado);
    }
    
    /**
     * Metodo para actualizarSolicitudTraslado
     * @param solicitud 
     */ 
    @Override
    public void actualizarSolicitudTraslado(SolicitudTraslado solicitud){
        this.solicitudTrasladoDAO.actualizar(solicitud);
    }
    
    /**
     * Metodo para actualizar asignaciones
     * @param asignacion 
     */ 
    @Override
    public void actualizarAsignacion(Asignacion asignacion){
        this.asignacionesDAO.actualizar(asignacion);
    }

    /**
     * Lista para consultarResiduos
     * @return 
     */
    @Override
    public List<Residuo> consultarResiduos(){
        return this.residuosDAO.consultarTodos();
    }
    
    /**
     * Lista para consultarResiduosProductor
     * @param idProductor
     * @return 
     */
    @Override
    public List<Residuo> consultarResiduosProductor(ObjectId idProductor){
        return this.residuosDAO.consultarProductor(idProductor);
    }

    /**
     * Lista para consultarQuimicos
     * @return 
     */
    @Override
    public List<Quimico> consultarQuimicos(){
        return this.quimicosDAO.consultarTodos();
    }
    
    /**
     * Lista para consultarProductores
     * @return 
     */
    @Override
    public List<Productor> consultarProductores(){
        return this.productorDAO.consultarTodos();
    }
    
    /**
     * Lista para consultarSolicitudesTraslado
     * @return 
     */
    @Override
    public List<SolicitudTraslado> consultarSolicitudesTraslado(){
        return this.solicitudTrasladoDAO.consultarTodos();
    }
    
    /**
     * Lista para consultarSolicitudesTrasladoNoAtendidas
     * @return 
     */
    @Override
    public List<SolicitudTraslado> consultarSolicitudesTrasladoNoAtendidas(){
        return this.solicitudTrasladoDAO.consultarNoAtendidas();
    }
    
    /**
     * Lista para consultarEmpresasTransportistas
     * @return 
     */
    @Override
    public List<EmpresaTransportista> consultarEmpresasTransportistas(){
        return this.empresasTransportistasDAO.consultarTodos();
    }

    /**
     * Lista para consultarAsignaciones
     * @return 
     */
    @Override
    public List<Asignacion> consultarAsignaciones(){
        return this.asignacionesDAO.consultarTodos();
    }
    
    /**
     * Lista para consultarAsignacionesEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public List<RelAsignacionEmpresa> consultarAsignacionesEmpresa(ObjectId idEmpresaTransportista){
        return this.asignacionesDAO.consultarTodosEmpresa(idEmpresaTransportista);
    }

    /**
     * Metodo para consultarResiduo
     * @param idResiduo
     * @return 
     */
    @Override
    public Residuo consultarResiduo(ObjectId idResiduo){
        return this.residuosDAO.consultar(idResiduo);
    }

    /**
     * metodo para consultarQuimico
     * @param idQuimico
     * @return 
     */
    @Override
    public Quimico consultarQuimico(ObjectId idQuimico){
        return this.quimicosDAO.consultar(idQuimico);
    }

    /**
     * Metodo para consultarProductor
     * @param idProductor
     * @return 
     */
    @Override
    public Productor consultarProductor(ObjectId idProductor){
        return this.productorDAO.consultar(idProductor);
    } 
    
    /**
     * Metodo para consultarSolicitudTraslado
     * @param idSolicitud
     * @return 
     */
    @Override
    public SolicitudTraslado consultarSolicitudTraslado(ObjectId idSolicitud){
        return this.solicitudTrasladoDAO.consultar(idSolicitud);
    } 

    /**
     * Metodo para consultarEmpresaTransportista
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public EmpresaTransportista consultarEmpresaTransportista(ObjectId idEmpresaTransportista){
        return this.empresasTransportistasDAO.consultar(idEmpresaTransportista);
    }

    /**
     * Metodo para consultarAsignacion
     * @param idAsignacion
     * @return 
     */
    @Override
    public Asignacion consultarAsignacion(ObjectId idAsignacion){
        return this.asignacionesDAO.consultar(idAsignacion);
    }

    /**
     * Metodo para agregarVehiculo
     * @param vehiculo 
     */
    @Override
    public void agregarVehiculo(Vehiculo vehiculo){
        this.vehiculosDAO.agregar(vehiculo);
    }

    /**
     * Lista para consultarVehiculos
     * @return 
     */
    @Override
    public List<Vehiculo> consultarVehiculos(){
        return this.vehiculosDAO.consultarTodos();
    }

    /**
     * Lista para consultarVehiculosEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    @Override
    public List<Vehiculo> consultarVehiculosEmpresa(ObjectId idEmpresaTransportista){
        return this.vehiculosDAO.consultarTodosEmpresa(idEmpresaTransportista);
    }
}
