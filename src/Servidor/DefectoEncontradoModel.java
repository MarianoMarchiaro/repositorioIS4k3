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
public class DefectoEncontradoModel {
    private DefectoModel defecto;
    private int cantidadDer;
    private int cantidadIzq;

    public DefectoEncontradoModel(DefectoModel defecto, int cantidadDer, int cantidadIzq) {
        this.defecto = defecto;
        this.cantidadDer = cantidadDer;
        this.cantidadIzq = cantidadIzq;
    }
    public DefectoEncontradoModel(){
        cantidadDer=0;
        cantidadIzq=0;
        defecto= new DefectoModel();
    }
    public DefectoModel getDefecto() {
        return defecto;
    }

    public void setDefecto(DefectoModel defecto) {
        this.defecto = defecto;
    }

    public int getCantidadDer() {
        return cantidadDer;
    }

    public void setCantidadDer(int cantidadDer) {
        this.cantidadDer = cantidadDer;
    }

    public int getCantidadIzq() {
        return cantidadIzq;
    }

    public void setCantidadIzq(int cantidadIzq) {
        this.cantidadIzq = cantidadIzq;
    }
    
}
