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
public class Traslado {

    private ObjectId idTraslado;
    private ObjectId idEmpresaTransportista;
    private ObjectId idResiduo;
    private Date fechaLlegada;
    private float kilometrosRecorridos;
    private float costo;
    private Tratamiento tratamiento;
    private List<ObjectId> idsVehiculos;

    /**
     * Constructor por default
     */
    public Traslado() {
    }

    /**
     * Constructor con parametros establecidos sin id
     *
     * @param idEmpresaTransportista
     * @param idResiduo
     * @param fechaLlegada
     * @param kilometrosRecorridos
     * @param costo
     * @param tratamiento
     * @param idsVehiculos
     */
    public Traslado(ObjectId idEmpresaTransportista, ObjectId idResiduo, Date fechaLlegada, float kilometrosRecorridos, float costo, Tratamiento tratamiento, List<ObjectId> idsVehiculos) {
        this.idEmpresaTransportista = idEmpresaTransportista;
        this.idResiduo = idResiduo;
        this.fechaLlegada = fechaLlegada;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.costo = costo;
        this.tratamiento = tratamiento;
        this.idsVehiculos = idsVehiculos;
    }

    /**
     * Constructor con parametros establecidos con id
     *
     * @param idTraslado
     * @param idEmpresaTransportista
     * @param idResiduo
     * @param fechaLlegada
     * @param kilometrosRecorridos
     * @param costo
     * @param tratamiento
     * @param idsVehiculos
     */
    public Traslado(ObjectId idTraslado, ObjectId idEmpresaTransportista, ObjectId idResiduo, Date fechaLlegada, float kilometrosRecorridos, float costo, Tratamiento tratamiento, List<ObjectId> idsVehiculos) {
        this.idTraslado = idTraslado;
        this.idEmpresaTransportista = idEmpresaTransportista;
        this.idResiduo = idResiduo;
        this.fechaLlegada = fechaLlegada;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.costo = costo;
        this.tratamiento = tratamiento;
        this.idsVehiculos = idsVehiculos;
    }

    /**
     * Metodo para obtener el idTraslado
     *
     * @return
     */
    public ObjectId getIdTraslado() {
        return idTraslado;
    }

    /**
     * Metodo para obtener el idTraslado
     *
     * @param idTraslado
     */
    public void setIdTraslado(ObjectId idTraslado) {
        this.idTraslado = idTraslado;
    }

    /**
     * Metodo para obtener el idEmpresaTransportista
     *
     * @return
     */
    public ObjectId getIdEmpresaTransportista() {
        return idEmpresaTransportista;
    }

    /**
     * Metodo para establecer el idEmpresaTransportista
     *
     * @param idEmpresaTransportista
     */
    public void setIdEmpresaTransportista(ObjectId idEmpresaTransportista) {
        this.idEmpresaTransportista = idEmpresaTransportista;
    }

    /**
     * Metodo para obtener el residuo
     *
     * @return
     */
    public ObjectId getIdResiduo() {
        return idResiduo;
    }

    /**
     * Metodo para estabelecer el idResiduo
     *
     * @param idResiduo
     */
    public void setIdResiduo(ObjectId idResiduo) {
        this.idResiduo = idResiduo;
    }

    /**
     * Metodo para obtener la fecha
     *
     * @return
     */
    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Metodo para establecer la fecha de llegada
     *
     * @param fechaLlegada
     */
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * Metodo para obtener los kilometros recorridos
     *
     * @return
     */
    public float getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    /**
     * Metodo para establecer los kilometros recorridos
     *
     * @param kilometrosRecorridos
     */
    public void setKilometrosRecorridos(float kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    /**
     * Metodo para obtener el costo
     *
     * @return
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Metodo para estabelcer el costo
     * @param costo 
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }

    /**
     * Metodo para obtener el tratamiento
     * @return 
     */
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * Metodo para establecer el tratamiento
     * @param tratamiento 
     */
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Lista para obtener el id de vehiculos
     * @return 
     */
    public List<ObjectId> getIdsVehiculos() {
        return idsVehiculos;
    }

    /**
     * Listta para estabelcer los vehiculos establecidos
     * @param idsVehiculos 
     */
    public void setIdsVehiculos(List<ObjectId> idsVehiculos) {
        this.idsVehiculos = idsVehiculos;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return "Traslado{" + "idTraslado=" + idTraslado + ", idEmpresaTransportista=" + idEmpresaTransportista + ", idResiduo=" + idResiduo + ", fechaLlegada=" + fechaLlegada + ", kilometrosRecorridos=" + kilometrosRecorridos + ", costo=" + costo + ", tratamiento=" + tratamiento + ", idsVehiculos=" + idsVehiculos + '}';
    }
}
