/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class Asignacion {
    private ObjectId id;
    private List<AsignacionEmpresa> empresasTransportistas; 
    private double cantidad;
    private Date fecha;
    private ObjectId idResiduo;

    
    /**
     * Constructor por default
     */
    public Asignacion() {
    }

    
    /**
     * Constructor con parametros establecidos sin id
     * @param empresasTransportistas
     * @param cantidad
     * @param fecha
     * @param idResiduo
     */
    public Asignacion(List<AsignacionEmpresa> empresasTransportistas, double cantidad, Date fecha, ObjectId idResiduo) {
        this.empresasTransportistas = empresasTransportistas;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.idResiduo = idResiduo;
    }

    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param empresasTransportistas
     * @param cantidad
     * @param fecha
     * @param idResiduo 
     */
    public Asignacion(ObjectId id, List<AsignacionEmpresa> empresasTransportistas, double cantidad, Date fecha, ObjectId idResiduo) {
        this.id = id;
        this.empresasTransportistas = empresasTransportistas;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.idResiduo = idResiduo;
    }

    /**
     * Metodo para obtener el id
     * @return 
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Metodo para establecer el id
     * @param id 
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Lista para obtener el id de empresas transportistas
     * @return 
     */
    public List<AsignacionEmpresa> getEmpresasTransportistas() {
        return empresasTransportistas;
    }

    /**
     * Metodo para estabelecer el id de las empresas transportistas 
     * @param empresasTransportistas
     */
    public void setEmpresasTransportistas(List<AsignacionEmpresa> empresasTransportistas) {
        this.empresasTransportistas = empresasTransportistas;
    }

    /**
     * Metodo para obtener la cantidad
     * @return 
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Metodo para establecer la cantidad
     * @param cantidad 
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Metodo para obtener la fecha
     * @return 
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo para establecer la fecha
     * @param fecha 
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo para obtener el residuo
     * @return 
     */
    public ObjectId getIdResiduo() {
        return idResiduo;
    }
    
    /**
     * Metodo para establecer el residuo
     * @param idResiduo 
     */
    public void setIdResiduo(ObjectId idResiduo) {
        this.idResiduo = idResiduo;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return "Asignacion{" + "id=" + id + ", empresasTransportistas=" + empresasTransportistas + ", cantidad=" + cantidad + ", fecha=" + fecha + ", idResiduo=" + idResiduo + '}';
    }
}
