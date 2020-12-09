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
import Vista.Administrativo.ModificarColor;
import Vista.Administrativo.VistaCrearModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class GestorModificarColor implements ActionListener{
    private ModificarColor vistaModificar;
    private Color color;
    private Adaptador adaptador;
    private int indice;
    private Empleado empleado;
    
    public GestorModificarColor(Adaptador adaptador,Color color,int indice,Empleado empleado){
        this.adaptador=adaptador;
        this.empleado=empleado;
        this.color=color;
        this.indice=indice;
        vistaModificar = new ModificarColor();
        vistaModificar.iniciar();
        vistaModificar.setControlador(this);
        vistaModificar.jLabel5.setText(color.getNombre());
        vistaModificar.jLabel7.setText(String.valueOf(color.getCodigo()));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaModificar.BOTON_MODIFICAR)){
            int numero = vistaModificar.obtenerCodigo();
            String nombre = vistaModificar.obtenerNombre();
            if(nombre.isEmpty() && numero == -1){
                JOptionPane.showMessageDialog(vistaModificar,"Se ingreso un valor erroneo");
            }else{
                if(numero==-1){
                    color.setNombre(nombre);
                    adaptador.cambiarNombreColor(indice,color.getNombre());
                }else{
                    if(nombre.isEmpty()){
                        color.setCodigo(numero);
                        adaptador.cambiarCodigoColor(indice,color.getCodigo());
                    }else{
                        color.setNombre(nombre);
                        color.setCodigo(numero);
                        adaptador.cambiarCodigoColor(indice,color.getCodigo());
                        adaptador.cambiarNombreColor(indice,color.getNombre());
                    }
                }
            }
            vistaModificar.dispose();
            ControladorGestionColor controlador = new ControladorGestionColor(adaptador,empleado);
            /*if(vistaModificar.obtenerCodigo()==(-1)|| vistaModificar.obtenerNombre().isEmpty()){
                
            }else{
                    if(vistaModificar.obtenerNombre().isEmpty() && vistaModificar.obtenerCodigo()!=(-1)){
                        color.setCodigo(vistaModificar.obtenerCodigo());
                        adaptador.guardarColor(indice,color);
                    }else{
                        if(vistaModificar.obtenerCodigo()==(-1)){
                            color.setNombre(vistaModificar.obtenerNombre());
                            adaptador.guardarColor(indice,color);
                        }else{
                            adaptador.guardarColor(indice,color);
                        }
                    }     
                }*/
                
        }
        if(e.getActionCommand().equals(vistaModificar.BOTON_CANCELAR)){
            vistaModificar.dispose();
            ControladorGestionColor controlador = new ControladorGestionColor(adaptador,empleado);
        }
    }
    
}
