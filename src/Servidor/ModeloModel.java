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
public class ModeloModel {
    public String SKU;
    public String denominacion;
    public int objetivo;

    public ModeloModel() {
    }

    public ModeloModel(String SKU, String denominacion, int objetivo) {
        this.SKU = SKU;
        this.denominacion = denominacion;
        this.objetivo = objetivo;
    }
    
    

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }
    
    
}
