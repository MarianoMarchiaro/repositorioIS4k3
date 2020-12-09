/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Administrativo;

import Controlador.IniciarSesion.ControladorIniciarSesion;
import Modelo.Empleado;
import Servidor.*;
import Vista.Administrativo.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mariano
 */
public class ControladorMenuPrincipal implements ActionListener {
    private ControladorGestionModelo controladorModelo;
    private MenuPrincipal menuPrincipal;
    private Adaptador adaptador;
    private Empleado empleado;
    private ControladorGestionColor controladorColor;
    
    public ControladorMenuPrincipal(Adaptador adaptador,Empleado empleado){
        this.empleado=empleado;
        menuPrincipal = new MenuPrincipal();
        menuPrincipal.iniciar();
        menuPrincipal.setControlador(this);
        menuPrincipal.cargarNombre(empleado);
        this.adaptador = adaptador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(menuPrincipal.BOTON_MODELO)){
            controladorModelo = new ControladorGestionModelo(adaptador,empleado);
            menuPrincipal.dispose();
        }
        if(e.getActionCommand().equals(menuPrincipal.BOTON_CERRAR)){
            empleado = null;
            menuPrincipal.dispose();
            ControladorIniciarSesion controlador=new ControladorIniciarSesion(adaptador);
        }
        if(e.getActionCommand().equals(menuPrincipal.BOTON_COLOR)){
            menuPrincipal.dispose();
            controladorColor = new ControladorGestionColor(adaptador,empleado);
        }
    }
}
