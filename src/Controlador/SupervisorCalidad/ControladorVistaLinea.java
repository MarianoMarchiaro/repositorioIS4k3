/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorCalidad;

import Controlador.IniciarSesion.ControladorIniciarSesion;
import Modelo.Empleado;
import Vista.SupCalidad.VistaLineaTrabajo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.LineaTrabajo;
import Servidor.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorVistaLinea implements ActionListener{
    private LineaTrabajo linea;
    private VistaLineaTrabajo vistaLinea;
    private Adaptador adaptador;
    private Empleado empleado;
    private ControladorAnalisisCalidad controladorAnalisis;
    
    
    public ControladorVistaLinea(Adaptador adaptador,Empleado empleado,LineaTrabajo linea){
        this.adaptador=adaptador;
        this.empleado=empleado;
        this.linea=linea;
        vistaLinea = new VistaLineaTrabajo();
        vistaLinea.iniciar();
        vistaLinea.jLabel5.setText(String.valueOf(linea.getNumeroLinea()));
        vistaLinea.jLabel6.setText(String.valueOf(linea.getOP().getNumOP()));
        vistaLinea.jLabel7.setText(linea.getOP().getEstado().toString());
        vistaLinea.jLabel8.setText(String.valueOf(linea.getOP().getModelo().getObjetivo()));
        //vistaLinea.jLabel10.setText(linea.getSupervisorL().getApellido()+", "+linea.getSupervisorL().getNombre());
        vistaLinea.setControlador(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaLinea.ABANDONAR)){
            abandonarLinea();/*
            if(linea.getOP().getEstado().toString().equals("PAUSADA")){
            linea.setSupervisorC(null);
            adaptador.abandonarLineaSupervisorCalidad(linea.getNumeroLinea());
            vistaLinea.dispose();  
            ControladorMenuPrincipalSC controlador = new ControladorMenuPrincipalSC(adaptador,empleado);
            }else{
                JOptionPane.showMessageDialog(vistaLinea,"No se puede abandonar la linea si no esta pausada");
            }*/
        }
        if(e.getActionCommand().equals(vistaLinea.ANALISIS)){
            if(linea.getOP().getEstado().toString().equals("PAUSADA")||linea.getOP().getEstado().toString().equals("FINALIZADA")){
                JOptionPane.showMessageDialog(vistaLinea,"no debe ser pausada o finalizada la linea para ingresar");
            }else{
            vistaLinea.dispose();
            controladorAnalisis = new ControladorAnalisisCalidad(adaptador,empleado,linea);
            }
        }
        if(e.getActionCommand().equals(vistaLinea.cerrar)){
            vistaLinea.dispose();
            ControladorIniciarSesion controlador = new ControladorIniciarSesion(adaptador);
        }
        if(e.getActionCommand().equals(vistaLinea.HERMANADO)){
            int dia = adaptador.getDia();
            int mes = adaptador.getMes();
            int año = adaptador.getAño();
            if(adaptador.comprobarSiHayHermanado(linea.getNumeroLinea(),dia,mes,año)){//si es true entonces no hay hermanado
                vistaLinea.dispose();
                ControladorHermanado controlador = new ControladorHermanado(adaptador,empleado,linea);
            }else{
                JOptionPane.showMessageDialog(vistaLinea,"Ya hay un hermanado");
            }
        }
    }
    public void abandonarLinea(){
        if(linea.getOP().getEstado().toString().equals("PAUSADA")){
            linea.setSupervisorC(null);
            adaptador.abandonarLineaSupervisorCalidad(linea.getNumeroLinea());
            vistaLinea.dispose();  
            ControladorMenuPrincipalSC controlador = new ControladorMenuPrincipalSC(adaptador,empleado);
            }else{
                JOptionPane.showMessageDialog(vistaLinea,"No se puede abandonar la linea si no esta pausada");
            }
    }
}
