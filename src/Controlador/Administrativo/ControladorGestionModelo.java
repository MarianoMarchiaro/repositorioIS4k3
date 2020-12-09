/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Administrativo;

import Modelo.Empleado;
import Modelo.Modelo;
import Servidor.*;
import Vista.Administrativo.MenuPrincipal;
import Vista.Administrativo.VistaCrearModelo;
import Vista.Administrativo.VistaModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorGestionModelo implements ActionListener{
    private VistaModelo vistaModelo;
    private VistaCrearModelo vistaCrearModelo;
    private GestorCrearModelo crearModelo;
    private GestorModificarModelo modificarModelo;
    private Adaptador adaptador;
    private Empleado empleado;
    private ArrayList<Modelo> modelos;
    
    public ControladorGestionModelo(Adaptador adaptador,Empleado empleado){
        this.empleado=empleado;
        this.adaptador = adaptador;
        modelos = new ArrayList<Modelo>();
        modelos = cargarModelos();
        vistaModelo = new VistaModelo();
        vistaModelo.iniciar();
        vistaModelo.setControlador(this);
        vistaModelo.cargarTabla(modelos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaModelo.CREARMODELO)){
            crearModelo = new GestorCrearModelo(adaptador,empleado);
            vistaModelo.dispose();
        }
        if(e.getActionCommand().equals(vistaModelo.MODIFICARMODELO)){            
            int indice = vistaModelo.seleccionarIndice();
            Modelo modelo = new Modelo();
            modelo.setDenominacion(adaptador.getDenominacionModelo(indice));
            modelo.setObjetivo(adaptador.getObjetivo(indice));
            modelo.setSKU(adaptador.getSKU(indice));
            modificarModelo = new GestorModificarModelo(modelo,adaptador,indice,empleado);
            vistaModelo.dispose();
        }
        if(e.getActionCommand().equals(vistaModelo.ELIMINARMODELO)){
            int indice =vistaModelo.seleccionarIndice();
            adaptador.eliminarModelo(indice);
            JOptionPane.showMessageDialog(vistaModelo,"Se ha eliminado el modelo");
            vistaModelo.cargarTabla(cargarModelos());
        }
        if(e.getActionCommand().equals(vistaModelo.SALIR)){
            vistaModelo.dispose();
            ControladorMenuPrincipal controlador = new ControladorMenuPrincipal(adaptador,empleado);
        }
    }
    
    public ArrayList<Modelo> cargarModelos(){
        int numero = adaptador.obtenerNumModelos();
        ArrayList<Modelo> modelos =new ArrayList<Modelo>();
        for(int i=0;i<numero;i++){
            Modelo modelo= new Modelo();
            modelo.setDenominacion(adaptador.getDenominacionModelo(i));
            modelo.setSKU(adaptador.getSKU(i));
            modelo.setObjetivo(adaptador.getObjetivo(i));
            modelos.add(modelo);
        }
        return modelos;
    }
    
} 
