/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class AsignacionEmpresa {
    private ObjectId idEmpresaTransportista;
    private boolean trasladado;

    public AsignacionEmpresa() {
    }

    public AsignacionEmpresa(ObjectId idEmpresaTransportista, boolean trasladado) {
        this.idEmpresaTransportista = idEmpresaTransportista;
        this.trasladado = trasladado;
    }

    public ObjectId getIdEmpresaTransportista() {
        return idEmpresaTransportista;
    }

    public void setIdEmpresaTransportista(ObjectId idEmpresaTransportista) {
        this.idEmpresaTransportista = idEmpresaTransportista;
    }

    public boolean isTrasladado() {
        return trasladado;
    }

    public void setTrasladado(boolean trasladado) {
        this.trasladado = trasladado;
    }

    @Override
    public String toString() {
        return "AsignacionEmpresa{" + "idEmpresaTransportista=" + idEmpresaTransportista + ", trasladado=" + trasladado + '}';
    }
}
