/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author xmnislas
 */
public class ResiduoSolicitud {
    private Residuo residuo;
    private float cantidad;
    private UnidadMedida unidadMedida;
    private boolean asignado;

    /**
     * Constructor por default
     */
    public ResiduoSolicitud() {
    }

    /**
     * Constructor con parametros establecidos
     * @param cantidad
     * @param unidadMedida
     * @param asignado 
     */
    public ResiduoSolicitud(float cantidad, UnidadMedida unidadMedida, boolean asignado) {
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.asignado = asignado;
    }

    /**
     * Constructor con parametros establecidos 
     * @param residuo
     * @param cantidad
     * @param unidadMedida
     * @param asignado 
     */
    public ResiduoSolicitud(Residuo residuo, float cantidad, UnidadMedida unidadMedida, boolean asignado) {
        this.residuo = residuo;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.asignado = asignado;
    }

   /**
     * Metodo para obtener el residuo
     * @return 
     */
    public Residuo getResiduo() {
        return residuo;
    }

    /**
     * Metodo para establecer el residuo
     * @param residuo 
     */
    public void setResiduo(Residuo residuo) {
        this.residuo = residuo;
    }

    /**
     * Metodo para obtener la cantidad
     * @return 
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Metodo para obtener la cantidad
     * @param cantidad 
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Metodo para obtener la unidad de medida
     * @return 
     */
    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Metodo para establecer la unidad de medidad
     * @param unidadMedida 
     */
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Metodo para saber si esta asignado
     * @return 
     */
    public boolean isAsignado() {
        return asignado;
    }

    /**
     * Metodo para estabeler lo asignado
     * @param asignado 
     */
    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return residuo.toString();
    }
}
