/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mariano
 */
public class DefectoEncontrado {
    private int cantidadIzq;
    private int cantidadDer;
    private Defecto defecto;
    
    public DefectoEncontrado(){
        cantidadDer=0;
        cantidadIzq=0;
    }
    public DefectoEncontrado(Defecto defecto){
        this.defecto=defecto;
    }

    public Defecto getDefecto() {
        return defecto;
    }

    public void setDefecto(Defecto defecto) {
        this.defecto = defecto;
    }

    public int getCantidadIzq() {
        return cantidadIzq;
    }

    public void setCantidadIzq(int cantidadIzq) {
        this.cantidadIzq = cantidadIzq;
    }

    public int getCantidadDer() {
        return cantidadDer;
    }

    public void setCantidadDer(int cantidadDer) {
        this.cantidadDer = cantidadDer;
    }
    
    
}
