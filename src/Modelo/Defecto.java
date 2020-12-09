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
public class Defecto {
    private String denominacion;
    private TipoDefecto tipo;
    
    public Defecto(String denominacion,TipoDefecto tipo){
        this.denominacion=denominacion;
        this.tipo=tipo;
    }

    public Defecto() {
        
    }
    
    public TipoDefecto getTipo() {
        return tipo;
    }

    public void setTipo(TipoDefecto tipo) {
        this.tipo = tipo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    
    
}
