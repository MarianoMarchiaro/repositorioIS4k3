/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mariano
 */
public class Turno {
    private int horaInicio;
    private int horaFin;
    private String descripcion;

    public Turno(int horaInicio, int horaFin,String descripcion) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion=descripcion;
    }

    public Turno() {
        
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public int obtenerHora(){
        Calendar horario = Calendar.getInstance();
        int hora = horario.HOUR_OF_DAY;
        return hora;
    }
}
