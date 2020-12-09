/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class AnalisisCalidadModel {
    public int cantidadPrimera;
    public int hora;
    public ArrayList<EmpleadoModel> supervisorC=new ArrayList<EmpleadoModel>();
    public ArrayList<DefectoEncontradoModel> defectosEncontrados=new ArrayList<DefectoEncontradoModel>();

    public AnalisisCalidadModel() {
        supervisorC=new ArrayList<EmpleadoModel>();
        defectosEncontrados=new ArrayList<DefectoEncontradoModel>();
        cantidadPrimera=0;
        hora=0;
    }

    public int getCantidadPrimera() {
        return cantidadPrimera;
    }

    public void setCantidadPrimera(int cantidadPrimera) {
        this.cantidadPrimera = cantidadPrimera;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public ArrayList<EmpleadoModel> getSupervisorC() {
        return supervisorC;
    }

    public void setSupervisorC(ArrayList<EmpleadoModel> supervisorC) {
        this.supervisorC = supervisorC;
    }

    public ArrayList<DefectoEncontradoModel> getDefectosEncontrados() {
        return defectosEncontrados;
    }

    public void setDefectosEncontrados(ArrayList<DefectoEncontradoModel> defectosEncontrados) {
        this.defectosEncontrados = defectosEncontrados;
    }
    
    
}
