/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Administrativo;

import Modelo.Empleado;
import Modelo.Modelo;
import Servidor.Adaptador;
import Servidor.Repositorio;
import Vista.Administrativo.ModificarColor;
import Vista.Administrativo.VistaCrearModelo;
import Vista.Administrativo.VistaModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class GestorCrearModelo implements ActionListener{
    private VistaCrearModelo crearModelo;
    private GestorCrearColor crearColor;
    private Modelo modelo;
    private GestorModificarColor modificarColor;
    private Adaptador adaptador;
    private Empleado empleado;
    
    public GestorCrearModelo(Adaptador adaptador,Empleado empleado){
        this.empleado=empleado;
        crearModelo = new VistaCrearModelo();
        crearModelo.iniciar();
        crearModelo.setControlador(this);
        modelo  = new Modelo();
        this.adaptador=adaptador;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(crearModelo.CONFIRMARMODELO)){
            if(crearModelo.obtenerDenominacion().equals("") || crearModelo.obtenerSKUA().equals("")|| crearModelo.getObjetivo()!= (-1)){
                modelo.setDenominacion(crearModelo.obtenerDenominacion());
                modelo.setSKU(crearModelo.obtenerSKUA());
                modelo.setObjetivo(crearModelo.getObjetivo());
                adaptador.crearModelo(modelo.getDenominacion(),modelo.getSKU(),modelo.getObjetivo());
                crearModelo.dispose();
                ControladorGestionModelo controlador=new ControladorGestionModelo(adaptador,empleado);
            }else{
                JOptionPane.showMessageDialog(crearModelo,"Error al ingresar datos");
            }
        }
        if(e.getActionCommand().equals(crearModelo.CANCELAR)){
            crearModelo.dispose();
            ControladorGestionModelo controlador=new ControladorGestionModelo(adaptador,empleado);
        }
    }
    
}
