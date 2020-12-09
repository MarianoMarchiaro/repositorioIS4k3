/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class Modelo {
    private String denominacion;
    private String SKU;
    private int objetivo;

    public Modelo(String denominacion, String SKU, int objetivo) {
        this.denominacion = denominacion;
        this.SKU = SKU;
        this.objetivo = objetivo;
    }
    
    

    public Modelo() {

    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    
    
}
