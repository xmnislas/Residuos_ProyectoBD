/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocios;

import interfaces.INegocios;

/**
 *
 * @author xmnislas
 */
public class FabricaNegocios {
    
    public static INegocios crearNegocios(){
        //Fachada Negocios
        return new FachadaNegocios();
    }
}
