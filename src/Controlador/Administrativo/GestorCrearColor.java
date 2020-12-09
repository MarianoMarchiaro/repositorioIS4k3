/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Administrativo;

import Modelo.Color;
import Modelo.Empleado;
import Modelo.Modelo;
import Servidor.Adaptador;
import Servidor.Repositorio;
import Vista.Administrativo.CrearColor;
import Vista.Administrativo.VistaCrearModelo;
import Vista.Administrativo.VistaModificarModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class GestorCrearColor implements ActionListener{
    private CrearColor crearColor;
    private Adaptador adaptador;
    private ControladorGestionColor controladorColor;
    private Empleado empleado;
    
    public GestorCrearColor(Adaptador adaptador,Empleado empleado){
        this.empleado=empleado;
        crearColor = new CrearColor();
        crearColor.iniciar();
        crearColor.setControlador(this);
        this.adaptador=adaptador;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(crearColor.BOTON_CONFIRMAR)){
            String nombre = crearColor.obtenerNombre();
            int codigo = crearColor.obtenerCodigo();
            boolean existe=adaptador.controlarSiExisteColor(codigo,nombre);
            if(existe==false){
                Color color = new Color(nombre,codigo);
                adaptador.crearColor(color.getCodigo(),color.getNombre());
                crearColor.dispose();
                controladorColor = new ControladorGestionColor(adaptador,empleado);
            }else{
                crearColor.existeColor();
            }
            
        }
        if(e.getActionCommand().equals(crearColor.BOTON_CANCELAR)){
            crearColor.dispose();
            controladorColor = new ControladorGestionColor(adaptador,empleado);
        }
    }
    
}
