/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.Traslado;
import interfaces.IDatos;

/**
 *
 * @author xmnislas
 */
public class CtrlTraslados {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlTraslados(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodopara guardar traslado
     * @param traslado 
     */
    public void guardarTraslado(Traslado traslado){
        iDatos.agregarTraslado(traslado);
    }
}
