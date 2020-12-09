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
public class LineaTrabajoModel {
    private EmpleadoModel supervisorC;
    private EmpleadoModel supervisorL;
    private OrdenProduccionModel OP;
    private ArrayList<OrdenProduccionModel> ordenes  = new ArrayList<OrdenProduccionModel>();
    private int numeroLinea;

    public LineaTrabajoModel(EmpleadoModel supervisorC, EmpleadoModel supervisorL, OrdenProduccionModel OP, ArrayList<OrdenProduccionModel> ordenes, int numeroLinea) {
        this.supervisorC = supervisorC;
        this.supervisorL = supervisorL;
        this.OP = OP;
        this.ordenes = ordenes;
        this.numeroLinea = numeroLinea;
    }

    public LineaTrabajoModel(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }
    

    public EmpleadoModel getSupervisorC() {
        return supervisorC;
    }

    public void setSupervisorC(EmpleadoModel supervisorC) {
        this.supervisorC = supervisorC;
    }

    public EmpleadoModel getSupervisorL() {
        return supervisorL;
    }

    public void setSupervisorL(EmpleadoModel supervisorL) {
        this.supervisorL = supervisorL;
    }

    public OrdenProduccionModel getOP() {
        return OP;
    }

    public void setOP(OrdenProduccionModel OP) {
        this.OP = OP;
    }

    public ArrayList<OrdenProduccionModel> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<OrdenProduccionModel> ordenes) {
        this.ordenes = ordenes;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }
    
}
