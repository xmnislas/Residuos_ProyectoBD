/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.Residuo;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlResiduos {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlResiduos(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo para guardar residuo
     * @param residuo 
     */
    public void guardarResiduo(Residuo residuo){
        iDatos.agregarResiduo(residuo);
    }
    
    /**
     * Metodo para buscar residuo
     * @param idResiduo
     * @return 
     */
    public Residuo buscarResiduo(ObjectId idResiduo){
        return iDatos.consultarResiduo(idResiduo);
    }
    
    /**
     * Lista de buscar residuos
     * @return 
     */
    public List<Residuo> buscarResiduos(){
        return iDatos.consultarResiduos();
    }
    
    /**
     * Lista para buscar residuosProductor
     * @param idProductor
     * @return 
     */
    public List<Residuo> buscarResiduosProductor(ObjectId idProductor){
        return iDatos.consultarResiduosProductor(idProductor);
    }
}
