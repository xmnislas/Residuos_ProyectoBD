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
 * @author Israel Parra
 */
public class SolicitudTraslado {

    private ObjectId id;
    private Date Fecha;
    private List<ResiduoSolicitud> residuos;
    private Productor productor;
    private boolean atendida;

    /**
     * Constructor por default
     */
    public SolicitudTraslado() {
    }

    /**
     * Constructor con parametros establecidos sin id
     * @param Fecha
     * @param residuos
     * @param productor
     * @param atendida 
     */
    public SolicitudTraslado(Date Fecha, List<ResiduoSolicitud> residuos, Productor productor, boolean atendida) {
        this.Fecha = Fecha;
        this.residuos = residuos;
        this.productor = productor;
        this.atendida = atendida;
    }

    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param Fecha
     * @param residuos
     * @param productor
     * @param atendida 
     */
    public SolicitudTraslado(ObjectId id, Date Fecha, List<ResiduoSolicitud> residuos, Productor productor, boolean atendida) {
        this.id = id;
        this.Fecha = Fecha;
        this.residuos = residuos;
        this.productor = productor;
        this.atendida = atendida;
    }

    /**
     * Constructor para obtener el id
     * @return 
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Constructor para establecer el id
     * @param id 
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Metodo para obtener la fecha
     * @return 
     */
    public Date getFecha() {
        return Fecha;
    }

    /**
     * Constrcutor para establecer la fecha
     * @param Fecha 
     */
    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * Lista para obtener los residuos
     * @return 
     */
    public List<ResiduoSolicitud> getResiduos() {
        return residuos;
    }

    /**
     * Lista para estabelecer los residuos
     * @param residuos 
     */
    public void setResiduos(List<ResiduoSolicitud> residuos) {
        this.residuos = residuos;
    }

    /**
     * Metodo para obtener el productor
     * @return 
     */
    public Productor getProductor() {
        return productor;
    }

    /**
     * Metodo para establecer el productor
     * @param productor 
     */
    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    /**
     * Metodo para obtener si es atendida
     * @return 
     */
    public boolean isAtendida() {
        return atendida;
    }

    /**
     * Metodo para estabelecer si esta atendida
     * @param atendida 
     */
    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return "SolicitudTraslado{" + "id=" + id + ", Fecha=" + Fecha + ", residuos=" + residuos + ", productor=" + productor + ", atendida=" + atendida + '}';
    }
}
