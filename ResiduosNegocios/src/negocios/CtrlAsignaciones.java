/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlAsignaciones {
    private IDatos iDatos;
    
    /**
     * Metodo de control de asignacion
     * @param iDatos 
     */
    public CtrlAsignaciones(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo de guardarAsignacion
     * @param asignacion 
     */
    public void guardarAsignacion(Asignacion asignacion){
        iDatos.agregarAsignacion(asignacion);
    }
    
    /**
     * Metodo de actualizar Asignacion
     * @param asignacion 
     */
    public void actualizarAsignacion(Asignacion asignacion){
        iDatos.actualizarAsignacion(asignacion);
    }
    
    /**
     * Metodo de buscar asignacion
     * @param idAsignacion
     * @return 
     */
    public Asignacion buscarAsignacion(ObjectId idAsignacion){
        return iDatos.consultarAsignacion(idAsignacion);
    }
    
    /**
     * Lista para buscar asignaciones
     * @return 
     */
    public List<Asignacion> buscarAsignaciones(){
       return iDatos.consultarAsignaciones();
    }
    
    /**
     * Lista para buscar las asgnaciones por empresas
     * @param idEmpresaTransportista
     * @return 
     */
    public List<RelAsignacionEmpresa> buscarAsignacionesPorEmpresa(ObjectId idEmpresaTransportista){
       return iDatos.consultarAsignacionesEmpresa(idEmpresaTransportista);
    }
}
