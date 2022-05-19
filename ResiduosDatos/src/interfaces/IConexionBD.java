/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author xmnislas
 */
public interface IConexionBD {
    //Metodo para crear conexion
    public MongoDatabase getConnection();
    
}
