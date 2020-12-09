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
public class Hermanado {
    private int cantidadPrimera;
    private int cantidadSegunda;
    
    public Hermanado(int cantidadPrimera,int cantidadSegunda){
        this.cantidadPrimera=cantidadPrimera;
        this.cantidadSegunda=cantidadSegunda;
    }    

    public int getCantidadPrimera() {
        return cantidadPrimera;
    }

    public void setCantidadPrimera(int cantidadPrimera) {
        this.cantidadPrimera = cantidadPrimera;
    }

    public int getCantidadSegunda() {
        return cantidadSegunda;
    }

    public void setCantidadSegunda(int cantidadSegunda) {
        this.cantidadSegunda = cantidadSegunda;
    }
    
}
