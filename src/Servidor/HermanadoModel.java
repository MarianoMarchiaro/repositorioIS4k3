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
public class HermanadoModel {
    public int cantidadPrimera;
    public int cantidadSegunda;
    
    public HermanadoModel(int primera,int segunda){
        cantidadPrimera=primera;
        cantidadSegunda=segunda;
    }

    public HermanadoModel() {
        cantidadPrimera=0;
        cantidadSegunda=0;
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
