/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


/**
 *
 * @author Mariano
 */
public class DefectoModel {
    public String descripcion;
    public TipoDefectoModel tipoDefecto;

    public DefectoModel() {
        
    }

    public DefectoModel(String descripcion, TipoDefectoModel tipoDefecto) {
        this.descripcion = descripcion;
        this.tipoDefecto = tipoDefecto;
    }

    public DefectoModel(String descripcion, String tipoDefecto) {
        this.descripcion = descripcion;
        this.tipoDefecto = TipoDefectoModel.valueOf(tipoDefecto);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDefectoModel getTipoDefecto() {
        return tipoDefecto;
    }

    public void setTipoDefecto(TipoDefectoModel tipoDefecto) {
        this.tipoDefecto = tipoDefecto;
    }

        
}
