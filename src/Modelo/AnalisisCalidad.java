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
public class AnalisisCalidad {
    private ArrayList<Empleado> supervisorC;
    int hora;
    private ArrayList<DefectoEncontrado> defectoEncontrado;
    private int cantidadPrimera=0;

    public AnalisisCalidad() {
        defectoEncontrado=new ArrayList<DefectoEncontrado>();
        supervisorC=new ArrayList<Empleado>();
        hora=0;     
    }

    public AnalisisCalidad(int hora) {
        this.hora=hora;
        supervisorC=new ArrayList<Empleado>();
        defectoEncontrado=new ArrayList<DefectoEncontrado>();
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
    public AnalisisCalidad(ArrayList<DefectoEncontrado> defectoEncontrado) {
        this.defectoEncontrado = defectoEncontrado;
    }

    
    public ArrayList<Empleado> getSupervisorC() {
        return supervisorC;
    }

    public void setSupervisorC(ArrayList<Empleado> supervisorC) {
        this.supervisorC = supervisorC;
    }
    
    

    public ArrayList<DefectoEncontrado> getDefectoEncontrado() {
        return defectoEncontrado;
    }

    public void setDefectoEncontrado(ArrayList<DefectoEncontrado> defectoEncontrado) {
        this.defectoEncontrado = defectoEncontrado;
    }

    public int getCantidadPrimera() {
        return cantidadPrimera;
    }

    public void setCantidadPrimera(int cantidadPrimera) {
        this.cantidadPrimera = cantidadPrimera;
    }
    
    public void guardarDefectoEncontrado(DefectoEncontrado defecto){
        for(DefectoEncontrado de : getDefectoEncontrado()){
            if(de.getDefecto().getDenominacion()==defecto.getDefecto().getDenominacion()){
                de=defecto;
            }
        }
    }
}
