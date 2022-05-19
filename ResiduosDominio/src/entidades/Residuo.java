/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author Israel Parra
 */
public class Residuo {

    private ObjectId id;
    private String nombre;
    private String codigo;
    private List<ObjectId> idsQuimicos;
    private ObjectId idProductor;
    
    /**
     * Constructor por default
     */
    public Residuo() {
    }

    /**
     * Constructor para obtener el id
     * @param id 
     */
    public Residuo(ObjectId id) {
        this.id = id;
    }
    
    /**
     * Constructor con parametros establecidos sin id
     * @param nombre
     * @param codigo 
     */
    public Residuo(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param nombre
     * @param codigo 
     */
    public Residuo(ObjectId id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    /**
     * Constructor con parametros establecidos
     * @param nombre
     * @param codigo
     * @param idsQuimicos
     * @param idProductor 
     */
    public Residuo(String nombre, String codigo, List<ObjectId> idsQuimicos, ObjectId idProductor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.idsQuimicos = idsQuimicos;
        this.idProductor = idProductor;
    }

    /**
     * Constructor con parametros establecidos
     * @param id
     * @param nombre
     * @param codigo
     * @param idsQuimicos
     * @param idProductor 
     */
    public Residuo(ObjectId id, String nombre, String codigo, List<ObjectId> idsQuimicos, ObjectId idProductor) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.idsQuimicos = idsQuimicos;
        this.idProductor = idProductor;
    }

    /**
     * Metodo pata obtener el id
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
     * Metodo para obtener el nombre
     * @return 
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo para estabelecer el codigo
     * @param codigo 
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Metodo para generar un id
     */
    public void generarId() {
        this.setId(new ObjectId());
    }

    /**
     * Lista para obtener los id de quimicos
     * @return 
     */
    public List<ObjectId> getIdsQuimicos() {
        return idsQuimicos;
    }

    /**
     * Lista para establecer los id de quimicos
     * @param idsQuimicos 
     */
    public void setIdsQuimicos(List<ObjectId> idsQuimicos) {
        this.idsQuimicos = idsQuimicos;
    }

    /**
     * Metodo para obtener el id de productor
     * @return 
     */
    public ObjectId getIdProductor() {
        return idProductor;
    }

    /**
     * Metodo para estabelecer el id del productor
     * @param idProductor 
     */
    public void setIdProductor(ObjectId idProductor) {
        this.idProductor = idProductor;
    }

    /**
     * Hashcode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Residuo other = (Residuo) obj;
        return Objects.equals(this.id, other.id);
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
