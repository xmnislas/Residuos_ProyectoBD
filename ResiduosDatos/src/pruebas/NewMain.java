/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import daos.FabricaDatos;
import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import entidades.EmpresaTransportista;
import entidades.Vehiculo;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        IDatos iDatos = FabricaDatos.crearDatos();
        List<RelAsignacionEmpresa> list = iDatos.consultarAsignacionesEmpresa(new ObjectId("627f79089330ce3e8798abaf"));
        list.forEach(objeto ->{
            System.out.println(objeto);
        });
        
//        Asignacion asignacion = iDatos.consultarAsignacion(new ObjectId("628581b7e2fbd42cb2daf131"));
//        asignacion.getEmpresasTransportistas().get(0).setTrasladado(true);
//        
//        iDatos.actualizarAsignacion(asignacion);
                
//        Productor productor1 = new Productor("Residuos IC", "icresiduos@gmail.com", "Sonora 965");
//        Productor productor2 = new Productor("Hogwarts", "hogwarts80@gmail.com", "Tabasco 3213");
//        iDatos.agregarProductor(productor1);
//        iDatos.agregarProductor(productor2);
//
//        Quimico quimico1 = new Quimico("Amoniaco");
//        Quimico quimico2 = new Quimico("Cianuro");
//        Quimico quimico3 = new Quimico("Bromo");
//        Quimico quimico4 = new Quimico("Zinc");
//        Quimico quimico5 = new Quimico("Kriptón");
//        Quimico quimico6 = new Quimico("Yodo");
//        iDatos.agregarQuimico(quimico1);
//        iDatos.agregarQuimico(quimico2);
//        iDatos.agregarQuimico(quimico3);
//        iDatos.agregarQuimico(quimico4);
//        iDatos.agregarQuimico(quimico5);
//        iDatos.agregarQuimico(quimico6);
//
//        EmpresaTransportista empresa1 = new EmpresaTransportista("Tufesa", "tufesas@hotmail.com", "Quintana Roo 342");
//        EmpresaTransportista empresa2 = new EmpresaTransportista("Transportes SA", "transportess21@gmail.com", "Hidalgo 842");
//        iDatos.agregarEmpresaTransportista(empresa1);
//        iDatos.agregarEmpresaTransportista(empresa2);

//        EmpresaTransportista empresa1 = iDatos.consultarEmpresaTransportista(new ObjectId("627f79089330ce3e8798abaf"));
//        EmpresaTransportista empresa2 = iDatos.consultarEmpresaTransportista(new ObjectId("627f79089330ce3e8798abb0"));
//        Vehiculo vehiculo1 = new Vehiculo("Carro",  empresa1);
//        Vehiculo vehiculo2 = new Vehiculo("Camión", empresa1);
//        Vehiculo vehiculo3 = new Vehiculo("Avión", empresa2);
//        Vehiculo vehiculo4 = new Vehiculo("Tren", empresa2);
//        iDatos.agregarVehiculo(vehiculo1);
//        iDatos.agregarVehiculo(vehiculo2);
//        iDatos.agregarVehiculo(vehiculo3);
//        iDatos.agregarVehiculo(vehiculo4);
    }
    
}
