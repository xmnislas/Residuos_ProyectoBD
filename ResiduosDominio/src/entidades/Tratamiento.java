/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author xmnislas
 */
public class Tratamiento {
    private String nombre;
    private String descripcion;

    /**
     * Constructor por default
     */
    public Tratamiento() {
    }

    /**
     * Constructor con parametros establecidos
     * @param nombre
     * @param descripcion 
     */
    public Tratamiento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Metodo para obtener el nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para estabelcer el nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener la descripcion
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo para estabelecer la descripcion
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return "Tratamiento{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
}
