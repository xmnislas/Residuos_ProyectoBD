/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.Vehiculo;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlVehiculos {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlVehiculos(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Lista para buscarVehiculosPorEmpresa
     * @param idEmpresaTransportista
     * @return 
     */
    public List<Vehiculo> buscarVehiculosPorEmpresa(ObjectId idEmpresaTransportista){
       return iDatos.consultarVehiculosEmpresa(idEmpresaTransportista);
    }
}
