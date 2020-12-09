/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorCalidad;

import Modelo.Empleado;
import Modelo.Hermanado;
import Modelo.HorarioTrabajo;
import Modelo.LineaTrabajo;
import Servidor.Adaptador;
import Vista.SupCalidad.VistaHermanado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorHermanado implements ActionListener{
    private VistaHermanado vistaHermanado;
    private Empleado empleado;
    private Adaptador adaptador;
    private LineaTrabajo linea;
    
    public ControladorHermanado(Adaptador adaptador,Empleado empleado,LineaTrabajo linea){
        this.linea=linea;
        this.empleado=empleado;
        this.adaptador=adaptador;
        vistaHermanado = new VistaHermanado();
        vistaHermanado.iniciar();
        vistaHermanado.setControlador(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaHermanado.BOTON_SALIR)){
            vistaHermanado.dispose();
            ControladorVistaLinea controlador = new ControladorVistaLinea(adaptador,empleado,linea);
        }
        if(e.getActionCommand().equals(vistaHermanado.BOTON_CONFIRMAR)){
            confirmarHermanado(vistaHermanado.obtenerPrimera(),vistaHermanado.obtenerSegunda());
            
        }
    }
    public void cargarHermanado(Hermanado her,int dia,int mes,int año){
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getAño()==año && ht.getDia()==dia && ht.getMes()==mes){
                ht.setHermanado(her);
            }
        }
    }
    public void ingresarHermanado(int canitidadPrimea,int cantidadSegunda,int dia,int mes,int año,int hora){
        Hermanado hermanado = new Hermanado(vistaHermanado.obtenerPrimera(),vistaHermanado.obtenerSegunda());
        cargarHermanado(hermanado,dia,mes,año);
        adaptador.crearHermanado(vistaHermanado.obtenerPrimera(),vistaHermanado.obtenerSegunda(),linea.getNumeroLinea(),dia,mes,año,hora);
        vistaHermanado.dispose();
        ControladorVistaLinea controlador = new ControladorVistaLinea(adaptador,empleado,linea);
    }
    
    public void confirmarHermanado(int primera,int segunda){
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        int hora= adaptador.getHora();
        if(adaptador.comprobarHorarioTrabajo(linea.getNumeroLinea(),dia,mes,año,hora)){
            if(primera!=0 && segunda!=0){
                ingresarHermanado(primera,segunda,dia,mes,año,hora);
                /*Hermanado hermanado = new Hermanado(vistaHermanado.obtenerPrimera(),vistaHermanado.obtenerSegunda());
                cargarHermanado(hermanado,dia,mes,año);
                adaptador.crearHermanado(vistaHermanado.obtenerPrimera(),vistaHermanado.obtenerSegunda(),linea.getNumeroLinea(),dia,mes,año);
                vistaHermanado.dispose();
                ControladorVistaLinea controlador = new ControladorVistaLinea(adaptador,empleado,linea);
                */
            }else{
                JOptionPane.showMessageDialog(vistaHermanado,"Ingrese cantidad a primera y a segunda");
            }
        }else{
        JOptionPane.showMessageDialog(vistaHermanado,"Ya Existe un hermanado");
        }
    }
    
}
