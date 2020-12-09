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
public class OrdenProduccionModel {
    public ModeloModel modelo;
    public ColorModel color;
    public String estadoOP;
    public int numOP;
    public EmpleadoModel supervisorL;
    public ArrayList<HorarioTrabajoModel> horarios=new ArrayList<HorarioTrabajoModel>();
    
    public OrdenProduccionModel(HorarioTrabajoModel horario){
        horarios = new ArrayList<HorarioTrabajoModel>();
        horarios.add(horario);
    }

    public OrdenProduccionModel(ModeloModel modelo, ColorModel color, String estadoOP, int numOP, EmpleadoModel supervisorL) {
        this.modelo = modelo;
        this.color = color;
        this.estadoOP = estadoOP;
        this.numOP = numOP;
        this.supervisorL = supervisorL;
        this.horarios = new ArrayList<HorarioTrabajoModel>();
    }
    
    public ModeloModel getModelo() {
        return modelo;
    }

    public void setModelo(ModeloModel modelo) {
        this.modelo = modelo;
    }

    public ColorModel getColor() {
        return color;
    }

    public void setColor(ColorModel color) {
        this.color = color;
    }

    public String getEstadoOP() {
        return estadoOP;
    }

    public void setEstadoOP(String estadoOP) {
        this.estadoOP = estadoOP;
    }

    public int getNumOP() {
        return numOP;
    }

    public void setNumOP(int numOP) {
        this.numOP = numOP;
    }

    public EmpleadoModel getSupervisorL() {
        return supervisorL;
    }

    public void setSupervisorL(EmpleadoModel supervisorL) {
        this.supervisorL = supervisorL;
    }

    public ArrayList<HorarioTrabajoModel> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<HorarioTrabajoModel> horarios) {
        this.horarios = horarios;
    }
    
}
