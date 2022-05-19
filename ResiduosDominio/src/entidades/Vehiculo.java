/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class Vehiculo {
    private ObjectId id;
    private String nombre;
    private EmpresaTransportista empresaTransportista;

    /**
     * Constructor por default
     */
    public Vehiculo() {
    }

    /**
     * Constructor con parametros establecidos sin id
     * @param nombre
     * @param empresaTransportista 
     */
    public Vehiculo(String nombre, EmpresaTransportista empresaTransportista) {
        this.nombre = nombre;
        this.empresaTransportista = empresaTransportista;
    }

    /**
     * Constrcutor con parametros establecidos con id
     * @param id
     * @param nombre
     * @param empresaTransportista 
     */
    public Vehiculo(ObjectId id, String nombre, EmpresaTransportista empresaTransportista) {
        this.id = id;
        this.nombre = nombre;
        this.empresaTransportista = empresaTransportista;
    }

    /**
     * Metodo para obtener el id
     * @return 
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Metodo para estabelecer el id
     * @param id 
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Metodo para obtener el nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para establecer el nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener la empresa transportista
     * @return 
     */
    public EmpresaTransportista getEmpresaTransportista() {
        return empresaTransportista;
    }
    
    /**
     * Metodo para establecer la empresa transportista
     * @param empresaTransportista 
     */
    public void setEmpresaTransportista(EmpresaTransportista empresaTransportista) {
        this.empresaTransportista = empresaTransportista;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", nombre=" + nombre + ", empresaTransportista=" + empresaTransportista + '}';
    }
}
