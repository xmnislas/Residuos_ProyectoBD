/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author Israel Parra
 */
public class Empresa {
    
     private ObjectId id;
     private String nombre;
     private String email;
     private String direccion;
     
    /**
     * Constructor por default
     */
    public Empresa() {
        
    }
    
    /**
     * Constructor parametros establecidos sin id
     * @param nombre
     * @param email
     * @param direccion 
     */
    public Empresa(String nombre, String email, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }
    
    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param nombre
     * @param email
     * @param direccion 
     */
    public Empresa(ObjectId id, String nombre, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
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
     * Metodo para obtener el email
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo para establecer el email
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

     /**
     * Metodo para obtener la direccion
     * @return 
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para establecer la direccion
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Hashcode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para obtener la cadena con el nombre
     * @return 
     */
    @Override
    public String toString() {
        return nombre;
    }
}
