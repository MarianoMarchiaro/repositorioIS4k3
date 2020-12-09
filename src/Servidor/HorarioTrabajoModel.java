/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mariano
 */
public class HorarioTrabajoModel {
    public int dia;
    public int mes;
    public int año;
    public TurnoModel turno = new TurnoModel();
    public HermanadoModel hermanado  = new HermanadoModel();
    public ArrayList<AnalisisCalidadModel> analisis=new ArrayList<AnalisisCalidadModel>();
    
    public HorarioTrabajoModel(){
        analisis=new ArrayList<AnalisisCalidadModel>();
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public TurnoModel getTurno() {
        return turno;
    }

    public void setTurno(TurnoModel turno) {
        this.turno = turno;
    }

    public HermanadoModel getHermanado() {
        return hermanado;
    }

    public void setHermanado(HermanadoModel hermanado) {
        this.hermanado = hermanado;
    }

    public ArrayList<AnalisisCalidadModel> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(ArrayList<AnalisisCalidadModel> analisis) {
        this.analisis = analisis;
    }
    
}
