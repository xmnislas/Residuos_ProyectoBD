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
public class Quimico {
    
    private ObjectId id;
    private String nombre;
    
    /**
     * Constructor por default
     */
    public Quimico() {
    
    }
    
    /**
     * Constructor con parametros establecidos sin id
     * @param nombre 
     */
    public Quimico(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param nombre 
     */
    public Quimico(ObjectId id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
     * Metodo para generar un id
     */
    public void generarId(){
        this.setId(new ObjectId());
    }

    /**
     * Hashcode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Quimico other = (Quimico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return nombre;
    } 
}
