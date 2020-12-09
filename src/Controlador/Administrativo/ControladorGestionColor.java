/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Administrativo;

import Modelo.Color;
import Modelo.Empleado;
import Servidor.Adaptador;
import Vista.Administrativo.CrearColor;
import Vista.Administrativo.VistaColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class ControladorGestionColor implements ActionListener{
    private Adaptador adaptador;
    private VistaColor vistaColor;
    private GestorCrearColor gestorCrear;
    private GestorModificarColor gestorModificar;
    private Empleado empleado;
    
    public ControladorGestionColor(Adaptador adaptador,Empleado empleado){
        this.empleado=empleado;
        this.adaptador=adaptador;
        vistaColor= new VistaColor();
        vistaColor.iniciar();
        vistaColor.setControlador(this);
        vistaColor.cargarTabla(cargarColores());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaColor.BOTON_CREAR)){
            gestorCrear = new GestorCrearColor(adaptador,empleado);
            vistaColor.dispose();
        }
        if(e.getActionCommand().equals(vistaColor.BOTON_MODIFICAR)){
            int numero = vistaColor.seleccionarColor();
            Color color = new Color();
            color.setCodigo(adaptador.getCodigoColor(numero));
            color.setNombre(adaptador.getDenominacionColor(numero));
            gestorModificar= new GestorModificarColor(adaptador,color,vistaColor.seleccionarColor(),empleado);
            vistaColor.dispose();
        }
        if(e.getActionCommand().equals(vistaColor.BOTON_ELIMINAR)){
            adaptador.eliminarColor(vistaColor.seleccionarColor());
            vistaColor.cargarTabla(cargarColores());
        }
        if(e.getActionCommand().equals(vistaColor.BOTON_SALIR)){
            vistaColor.dispose();
            ControladorMenuPrincipal controlador = new ControladorMenuPrincipal(adaptador,empleado);
        }
    }
    public ArrayList<Color> cargarColores(){
        int numero = adaptador.obtenerNumeroColores();
        ArrayList<Color> colores=new ArrayList<Color>();
        for(int i=0;i<numero;i++){
            Color color = new Color();
            color.setCodigo(adaptador.getCodigoColor(i));
            color.setNombre(adaptador.getDenominacionColor(i));
            colores.add(color);
        }
        return colores;
    }
    
    
}
