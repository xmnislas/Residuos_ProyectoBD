/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.Quimico;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlQuimicos {
    private IDatos iDatos;
    
    /**
     *Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlQuimicos(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo para gurdar quimico
     * @param quimico 
     */
    public void guardarQuimico(Quimico quimico){
        iDatos.agregarQuimico(quimico);
    }
    
    /**
     * Metodo para buscar quimico
     * @param idQuimico
     * @return 
     */
    public Quimico buscarQuimico(ObjectId idQuimico){
        return iDatos.consultarQuimico(idQuimico);
    }
    
    /**
     * Lista de buscar quimicos
     * @return 
     */
    public List<Quimico> buscarQuimicos(){
       return iDatos.consultarQuimicos();
    }
}
