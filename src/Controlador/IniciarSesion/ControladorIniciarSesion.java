/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.IniciarSesion;

import Controlador.Administrativo.ControladorMenuPrincipal;
import Controlador.SupervisorCalidad.ControladorMenuPrincipalSC;
import Controlador.SupervisorLinea.ControladorMenuSLinea;
import Modelo.Empleado;
import Servidor.Adaptador;
import Modelo.TipoEmpleado;
import Vista.IniciarSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorIniciarSesion implements ActionListener{
    private IniciarSesion vistaIniciar;
    private Adaptador adaptador;
    private Empleado empleado;
    private ControladorMenuPrincipal controladorAdmin;
    private ControladorMenuPrincipalSC controladorSupCali;
    private ControladorMenuSLinea controladorSupLinea;
    
    public ControladorIniciarSesion(Adaptador adaptador){
        vistaIniciar = new IniciarSesion();
        vistaIniciar.iniciar();
        vistaIniciar.setControlador(this);
        this.adaptador = adaptador; 
        empleado=new Empleado();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaIniciar.BOTON_INGRESAR)){
            String usuario = vistaIniciar.obtenerUsuario();
            String contraseña = vistaIniciar.obtenerContraseña();
            boolean controlar = adaptador.comprobarEmpleado(usuario, contraseña);
            if(controlar==true){
            empleado.setApellido(adaptador.buscarApellidoEmpleado(usuario,contraseña));
            empleado.setNombre(adaptador.buscarNombreEmpleado(usuario, contraseña));
            empleado.setDocumento(adaptador.buscarDocumentoEmpleado(usuario, contraseña));
            empleado.setTipo(TipoEmpleado.valueOf(adaptador.buscarTipoEmpleado(usuario,contraseña)));
            
            if(empleado.getTipo().equals(TipoEmpleado.SUPERVISORCALIDAD)){
                controladorSupCali = new ControladorMenuPrincipalSC(adaptador,empleado);
                vistaIniciar.dispose();
            }else{
                if(empleado.getTipo().equals(TipoEmpleado.SUPERVISORLINEA)){
                    controladorSupLinea = new ControladorMenuSLinea(adaptador,empleado);
                    vistaIniciar.dispose();
                }else{
                    if(empleado.getTipo().equals(TipoEmpleado.ADMINISTRATIVO)){
                       controladorAdmin = new ControladorMenuPrincipal(adaptador,empleado);
                       vistaIniciar.dispose();
                    }else{
                        JOptionPane.showMessageDialog(vistaIniciar,"Login incorrecto");
                    }
                }
            }
            }else{
                JOptionPane.showMessageDialog(vistaIniciar,"Login incorrecto");
            }
        }
        if(e.getActionCommand().equals(vistaIniciar.BOTON_SALIR)){
            vistaIniciar.dispose();
        }
    }
    
}
