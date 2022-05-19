/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.Productor;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlProductores {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlProductores(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo para guardar productor
     * @param productor 
     */
    public void guardarProductor(Productor productor){
        iDatos.agregarProductor(productor);
    }
    
    /**
     * Metodo para buscar el productor
     * @param idProductor
     * @return 
     */
    public Productor buscarProductor(ObjectId idProductor){
        return iDatos.consultarProductor(idProductor);
    }
    
    /**
     * Lista de buscar prodcutores
     * @return 
     */
    public List<Productor> buscarProductores(){
       return iDatos.consultarProductores();
    }
}
