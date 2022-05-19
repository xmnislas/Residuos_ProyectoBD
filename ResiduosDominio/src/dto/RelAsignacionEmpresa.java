/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import entidades.AsignacionEmpresa;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class RelAsignacionEmpresa {
    private ObjectId idAsignacion;
    private double cantidad;
    private AsignacionEmpresa asignacionEmpresa;
    private Date fecha;
    private ObjectId idResiduo;

    public RelAsignacionEmpresa() {
    }

    public RelAsignacionEmpresa(ObjectId idAsignacion, double cantidad, AsignacionEmpresa asignacionEmpresa, Date fecha, ObjectId idResiduo) {
        this.idAsignacion = idAsignacion;
        this.cantidad = cantidad;
        this.asignacionEmpresa = asignacionEmpresa;
        this.fecha = fecha;
        this.idResiduo = idResiduo;
    }

    public ObjectId getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(ObjectId idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public AsignacionEmpresa getAsignacionEmpresa() {
        return asignacionEmpresa;
    }

    public void setAsignacionEmpresa(AsignacionEmpresa asignacionEmpresa) {
        this.asignacionEmpresa = asignacionEmpresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ObjectId getIdResiduo() {
        return idResiduo;
    }

    public void setIdResiduo(ObjectId idResiduo) {
        this.idResiduo = idResiduo;
    }

    @Override
    public String toString() {
        return "RelAsignacionEmpresa{" + "idAsignacion=" + idAsignacion + ", cantidad=" + cantidad + ", asignacionEmpresa=" + asignacionEmpresa + ", fecha=" + fecha + ", idResiduo=" + idResiduo + '}';
    }
}
