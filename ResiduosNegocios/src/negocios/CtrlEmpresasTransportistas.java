/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import entidades.EmpresaTransportista;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class CtrlEmpresasTransportistas {
    private IDatos iDatos;
    
    /**
     * Constructor con parametros establecidos
     * @param iDatos 
     */
    public CtrlEmpresasTransportistas(IDatos iDatos) {
        this.iDatos = iDatos;
    }
    
    /**
     * Metodo para guardar empresaTransportista
     * @param empresaTransportista 
     */
    public void guardarEmpresaTransportista(EmpresaTransportista empresaTransportista){
        iDatos.agregarEmpresaTransportista(empresaTransportista);
    }
    
    /**
     * Metodo para buscar empresa transportista
     * @param idEmpresaTransportista
     * @return 
     */
    public EmpresaTransportista buscarEmpresaTransportista(ObjectId idEmpresaTransportista){
        return iDatos.consultarEmpresaTransportista(idEmpresaTransportista);
    }
    
    /**
     * Lista para buscar empresas transportistas
     * @return 
     */
    public List<EmpresaTransportista> buscarEmpresasTransportistas(){
       return iDatos.consultarEmpresasTransportistas();
    }
}
