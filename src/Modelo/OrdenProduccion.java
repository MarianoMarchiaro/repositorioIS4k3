/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mariano
 */
public class OrdenProduccion {
    private Modelo modelo;
    private Color color;
    private int numOP = 0;
    private EstadoOP estado;
    private Empleado supervisorL;
    private Date fechaInicio= new Date();
    private Date fechaFin;
    private ArrayList<HorarioTrabajo> horarios;

    public OrdenProduccion(Modelo modelo, Color color, Empleado supervisorL, Date fechaInicio, int numOP) {
        this.modelo = modelo;
        this.color = color;
        this.numOP = numOP;
        this.supervisorL = supervisorL;
        this.fechaInicio = fechaInicio;
        estado = EstadoOP.ACTIVA;
        horarios = new ArrayList<HorarioTrabajo>();
    }

    public OrdenProduccion() {
        modelo= new Modelo();
        color=new Color();
        supervisorL= new Empleado();
        horarios = new ArrayList<HorarioTrabajo>();
        estado=EstadoOP.ACTIVA;
    }
    
    public ArrayList<HorarioTrabajo> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<HorarioTrabajo> horarios) {
        this.horarios = horarios;
    }
    
    

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumOP() {
        return numOP;
    }

    public void setNumOP(int numOP) {
        this.numOP = numOP;
    }

    public EstadoOP getEstado() {
        return estado;
    }

    public void setEstado(EstadoOP estado) {
        this.estado = estado;
    }

    public Empleado getSupervisorL() {
        return supervisorL;
    }

    public void setSupervisorL(Empleado supervisorL) {
        this.supervisorL = supervisorL;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
