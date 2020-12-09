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
public class ColorModel {
    public int codigoColor;
    public String denominacion;

    public ColorModel() {
    }

    public ColorModel(String denominacion, int codigoColor) {
        this.codigoColor = codigoColor;
        this.denominacion = denominacion;
    }
    
    public int getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(int codigoColor) {
        this.codigoColor = codigoColor;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    
}
