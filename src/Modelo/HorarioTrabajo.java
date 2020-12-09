/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Mariano
 */
public class HorarioTrabajo {
    private int dia;
    private int mes;
    private int año;
    private Turno turno;
    private Hermanado hermanado;
    private ArrayList<AnalisisCalidad> analisisCalidad;
    
    public HorarioTrabajo(){
        analisisCalidad= new ArrayList<AnalisisCalidad>();
        turno=new Turno();
    }
    
    public HorarioTrabajo(int dia,int mes,int año, Turno turno){
        this.turno=turno;
        this.mes=mes;
        this.año=año;
        this.dia=dia;
        analisisCalidad= new ArrayList<AnalisisCalidad>();
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Hermanado getHermanado() {
        return hermanado;
    }

    public void setHermanado(Hermanado hermanado) {
        this.hermanado = hermanado;
    }

    public ArrayList<AnalisisCalidad> getAnalisisCalidad() {
        return analisisCalidad;
    }

    public void setAnalisisCalidad(ArrayList<AnalisisCalidad> analisisCalidad) {
        this.analisisCalidad = analisisCalidad;
    }
    
    
    
       
}
