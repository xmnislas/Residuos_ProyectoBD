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
public class Productor extends Empresa{

    /**
     * Constructor por default
     */
    public Productor() {
    }

    /**
     * Construcor con parametros establecidos sin id
     * @param nombre
     * @param email
     * @param direccion 
     */
    public Productor(String nombre, String email, String direccion) {
        super(nombre, email, direccion);
    }
    
    /**
     * Constructor con parametros establecidos con id
     * @param id
     * @param nombre
     * @param email
     * @param direccion 
     */
    public Productor(ObjectId id, String nombre, String email, String direccion) {
        super(id, nombre, email, direccion);
    }
    
    /**
     * Metodo para obtener la cadena
     * @return 
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
}
