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
import Vista.Administrativo.VistaModelo;
import Vista.Administrativo.VistaModificarModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class GestorModificarModelo implements ActionListener{
    private Modelo modelo;
    private VistaModificarModelo vistaModificar;
    private Adaptador adaptador;
    private GestorCrearColor crearColor;
    private GestorModificarColor modificarColor;
    private int indice;
    private Empleado empleado;
    
    public GestorModificarModelo(Modelo modelo,Adaptador adaptador,int indice,Empleado empleado){
        this.empleado=empleado;
        this.modelo=modelo;
        this.indice=indice;
        vistaModificar = new VistaModificarModelo();
        vistaModificar.iniciar();
        vistaModificar.setControlador(this);
        vistaModificar.jLabel4.setText(modelo.getDenominacion());
        vistaModificar.jLabel5.setText(modelo.getSKU());
        vistaModificar.jLabel9.setText(String.valueOf(modelo.getObjetivo()));
        this.adaptador=adaptador;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(vistaModificar.BOTON_CONFIRMAR)){
            String deno=vistaModificar.obtenerDenominacion();
            String sku= vistaModificar.obtenerSKU();
            int obj = vistaModificar.obtenerObjetivo();
            if(deno.isEmpty() && sku.isEmpty() && obj ==0){
                JOptionPane.showMessageDialog(vistaModificar,"Ingrese Algun valor");
            }else{
                    if(deno.isEmpty()){

                    }else{
                        modelo.setDenominacion(deno);
                    }
                    if(sku.isEmpty()){

                    }else{
                        modelo.setSKU(sku);
                    }
                    if(obj == 0){

                    }else{
                        modelo.setObjetivo(obj);
                    }
                    adaptador.cambiarDenominacion(modelo.getDenominacion(),indice);
                    adaptador.cambiarSKU(modelo.getSKU(),indice);
                    adaptador.cambiarObjetivo(modelo.getObjetivo(),indice);
                    vistaModificar.dispose();
                    ControladorGestionModelo controlador = new ControladorGestionModelo(adaptador,empleado);
            }
            /*if(deno.equals("")&& obj==0 && sku.isEmpty()){
                modelo.setSKU(sku);
                adaptador.cambiarModelo(modelo,indice);
            }else{
                if(sku==""&&obj==0 && deno!=""){
                    modelo.setDenominacion(deno);
                    adaptador.cambiarModelo(modelo,indice);
                }else{
                    if(deno==""&&sku=="" && obj!=0){
                        modelo.setObjetivo(obj);
                        adaptador.cambiarModelo(modelo,indice);
                    }else{
                        if(deno=="" && sku!="" && obj!=0){
                            modelo.setSKU(sku);
                            modelo.setObjetivo(obj);
                            adaptador.cambiarModelo(modelo,indice);
                        }else{
                            if(deno!="" && sku!="" && obj==0){
                                modelo.setSKU(sku);
                                modelo.setDenominacion(deno);
                                adaptador.cambiarModelo(modelo,indice);
                            }else{
                                if(deno!="" && sku=="" && obj!=0){
                                    modelo.setDenominacion(deno);
                                    modelo.setObjetivo(obj);
                                    adaptador.cambiarModelo(modelo,indice);
                                }else{
                                    if(deno!="" && sku!="" && obj!=0){
                                        modelo.setDenominacion(deno);
                                        modelo.setSKU(sku);
                                        modelo.setObjetivo(obj);
                                        adaptador.cambiarModelo(modelo,indice);
                                    }else{
                                        
                                    }
                                }
                            }
                        }
                    }
                    modelo.setDenominacion(deno);
                    modelo.setSKU(sku);
                }
            }*/
        }
        if(e.getActionCommand().equals(vistaModificar.BOTON_CANCELAR)){
            vistaModificar.dispose();
            ControladorGestionModelo controlador = new ControladorGestionModelo(adaptador,empleado);
        }
    }
    
}
