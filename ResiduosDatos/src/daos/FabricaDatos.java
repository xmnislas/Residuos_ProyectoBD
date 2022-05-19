/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import interfaces.IDatos;

/**
 *
 * @author xmnislas
 */
public class FabricaDatos {
    
    public static IDatos crearDatos(){
        //Fachada de datos
        return new FachadaDatos();
    }
}
