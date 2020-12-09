/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorLinea;

import Modelo.Empleado;
import Modelo.EstadoOP;
import Modelo.LineaTrabajo;
import Modelo.OrdenProduccion;
import Servidor.Adaptador;
import Vista.SupervisorLinea.ActualizarOrden;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorActualizarOP implements ActionListener{
    private ActualizarOrden vistaActualizar;
    private Adaptador adaptador;
    private OrdenProduccion OP=new OrdenProduccion();
    private Empleado empleado;
    private LineaTrabajo linea=new LineaTrabajo();
    
    public ControladorActualizarOP(Adaptador adaptador,Empleado empleado,LineaTrabajo linea){
        this.adaptador=adaptador;
        this.empleado=empleado;
        vistaActualizar=new ActualizarOrden();
        vistaActualizar.iniciar();
        vistaActualizar.setControlador(this);      
        this.linea=linea;
        comprobarOrden(this.linea.obtenerOrdenActiva());
        this.OP = this.linea.obtenerOrdenActiva();        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaActualizar.SALIR)){
            vistaActualizar.dispose();
            ControladorMenuSLinea controladorLinea=new ControladorMenuSLinea(adaptador,empleado);
        }
        if(e.getActionCommand().equals(vistaActualizar.ACTIVAR)){
            String estado = "ACTIVA";
            actualizarEstado(estado);/*
            if(OP.getEstado().equals(EstadoOP.FINALIZADA)){
                JOptionPane.showMessageDialog(vistaActualizar,"La Orden esta finalizada");
            }else{
                OP.setEstado(EstadoOP.ACTIVA);
                linea.guardarEstado(OP);
                adaptador.guardarEstadoDeOpEnLinea(linea.getNumeroLinea(),linea.getOP().getEstado().toString());
                vistaActualizar.jLabel11.setText(EstadoOP.ACTIVA.toString());
                
            }*/
        }
        if(e.getActionCommand().equals(vistaActualizar.PAUSAR)){
            String estado = "PAUSADA";
            actualizarEstado(estado);/*
            if(OP.getEstado().equals(EstadoOP.FINALIZADA)){
                JOptionPane.showMessageDialog(vistaActualizar,"La Orden esta finalizada");
            }else{
                
                OP.setEstado(EstadoOP.PAUSADA);
                linea.guardarEstado(OP);
                adaptador.guardarEstadoDeOpEnLinea(linea.getNumeroLinea(),linea.getOP().getEstado().toString());
                vistaActualizar.jLabel11.setText(EstadoOP.PAUSADA.toString());
            }*/
        }
        if(e.getActionCommand().equals(vistaActualizar.FINALIZAR)){
            String estado = "FINALIZADA";
            actualizarEstado(estado);
            linea.setSupervisorL(null);
            linea.setOP(null);/*
            OP.setEstado(EstadoOP.FINALIZADA);
            linea.guardarEstado(OP);
            adaptador.guardarEstadoDeOpEnLinea(linea.getNumeroLinea(),linea.getOP().getEstado().toString());
            vistaActualizar.jLabel11.setText(EstadoOP.FINALIZADA.toString());*/
        }
    }
    
    public void comprobarOrden(OrdenProduccion OP){
        if(OP == null){
            JOptionPane.showMessageDialog(vistaActualizar,"No se creo una OP aun");
            vistaActualizar.dispose();
            ControladorMenuSLinea controladorLinea=new ControladorMenuSLinea(adaptador,empleado);
        }else{            
            vistaActualizar.jLabel3.setText(String.valueOf(OP.getNumOP()));
            vistaActualizar.jLabel5.setText(OP.getModelo().getSKU());
            vistaActualizar.jLabel7.setText(String.valueOf(OP.getColor().getCodigo()));
            vistaActualizar.jLabel9.setText(convertirDate(OP.getFechaInicio()));
            vistaActualizar.jLabel11.setText(OP.getEstado().toString());
            
        }
    }
    /*public OrdenProduccion buscarOrden(){
        return gestor.obtenerOrden();
    }*/
    
    public String convertirDate(Date fecha){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(fecha);
    }
    
    /*public void actualizarEstado(EstadoOP estado,int numLinea){
        gestor.actualizarEstado(estado,numLinea);
    }*/
    public void actualizarEstado(String estado){
        if(OP.getEstado().equals(EstadoOP.FINALIZADA)){
            JOptionPane.showMessageDialog(vistaActualizar,"La Orden esta finalizada");
        }else{
            OP.setEstado(EstadoOP.valueOf(estado));
            linea.guardarEstado(OP);
            adaptador.guardarEstadoDeOpEnLinea(linea.getNumeroLinea(),linea.getOP().getEstado().toString());
            vistaActualizar.jLabel11.setText(EstadoOP.valueOf(estado).toString());
        }
    }
}
