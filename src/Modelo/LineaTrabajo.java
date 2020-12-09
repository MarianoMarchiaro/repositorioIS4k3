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
public class LineaTrabajo {
    private int numeroLinea;
    private Empleado supervisorL;
    private Empleado supervisorC;
    private OrdenProduccion OP=new OrdenProduccion();
    private ArrayList<OrdenProduccion> ordenes;

    public LineaTrabajo(int numeroLinea, Empleado supervisorL, Empleado supervisorC,OrdenProduccion op ) {
        this.numeroLinea = numeroLinea;
        OP=op;
        this.supervisorL = supervisorL;
        this.supervisorC = supervisorC;
        ordenes=new ArrayList<OrdenProduccion>();
    }

    public LineaTrabajo() {
        supervisorL=new Empleado();
        supervisorC=new Empleado();
        OP= new OrdenProduccion();
        ordenes=new ArrayList<OrdenProduccion>();
    }
    public LineaTrabajo(int numeroLinea) {
        this.numeroLinea=numeroLinea;
        ordenes = new ArrayList<OrdenProduccion>();
    }


    public ArrayList<OrdenProduccion> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<OrdenProduccion> ordenes) {
        this.ordenes = ordenes;
    }
    
    public OrdenProduccion getOP() {
        return OP;
    }

    public void setOP(OrdenProduccion OP) {
        this.OP = OP;
    }

    
    
    public int getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public Empleado getSupervisorL() {
        return supervisorL;
    }

    public void setSupervisorL(Empleado supervisorL) {
        this.supervisorL = supervisorL;
    }

    public Empleado getSupervisorC() {
        return supervisorC;
    }

    public void setSupervisorC(Empleado supervisorC) {
        this.supervisorC = supervisorC;
    }

    
    public OrdenProduccion obtenerOrdenActiva(){
        for(OrdenProduccion op : ordenes){
            if(op.getEstado()==EstadoOP.ACTIVA||op.getEstado()==EstadoOP.PAUSADA){
                System.out.println("retorna op  ASDASDASD");
                return op;
            }
        }
        return null;
    }
    public void guardarEstado(OrdenProduccion OP){
        for(OrdenProduccion op : ordenes){
            if(op.getNumOP()==OP.getNumOP()){
                op=OP;
            }
        }
    }
    
}
