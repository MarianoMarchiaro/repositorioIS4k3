/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorLinea;

import Modelo.AnalisisCalidad;
import Modelo.Color;
import Modelo.Empleado;
import Modelo.EstadoOP;
import Modelo.HorarioTrabajo;
import Modelo.LineaTrabajo;
import Modelo.Modelo;
import Modelo.OrdenProduccion;
import Modelo.Turno;
import Servidor.Adaptador;
import Servidor.Repositorio;
import Vista.SupervisorLinea.CrearOrden;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorIniciarOP implements ActionListener{
    private CrearOrden vistaCrear;
    private Adaptador adaptador;
    private Empleado empleado;
    private LineaTrabajo linea;
    private Date fecha;
    private Modelo modelo;
    private Color color;
    private ArrayList<LineaTrabajo> lineas;
    
    public ControladorIniciarOP(Adaptador adaptador,Empleado empleado){
        this.adaptador=adaptador;
        this.empleado=empleado;
        vistaCrear= new CrearOrden();
        vistaCrear.iniciar();
        vistaCrear.setControlador(this);
        if(cargarModelos().size()==0){
            JOptionPane.showMessageDialog(vistaCrear,"No hay modelos cargados");
            vistaCrear.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado); 
        }
        if(cargarLineas().size()==0){
            JOptionPane.showMessageDialog(vistaCrear,"No hay lineas disponibles");
            vistaCrear.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado); 
        }
        if(cargarColores().size()==0){
            JOptionPane.showMessageDialog(vistaCrear,"No hay colores cargados");
            vistaCrear.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado); 
        }else{
            iniciarOP();
            
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(vistaCrear.CONFIRMAR)){
            int indice1 = vistaCrear.obtenerLinea();
            int indice2 = vistaCrear.obtenerModelo();
            int indice3 = vistaCrear.obtenerColor();
            if(indice1 != -1 && indice2 !=-1 && indice3!=-1){
                linea= cargarLineas().get(indice1);
                modelo= cargarModelos().get(indice2);
                color= cargarColores().get(indice3);
                int numeroOP = vistaCrear.obtenerNumeroOrden();
                if(numeroOP!=0){
                    confirmarOP(numeroOP,modelo,color,linea);/*
                    if(adaptador.controlarNumeroOP(numeroOP)){
                        crearOP(linea.getNumeroLinea(),numeroOP,modelo.getSKU(),color.getCodigo(),empleado.getDocumento());
                        vistaCrear.dispose();
                        ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado);
                    }else{
                        JOptionPane.showMessageDialog(vistaCrear,"Ya existe una op con ese numero");
                    }*/                    
                }else{
                    JOptionPane.showMessageDialog(vistaCrear, "Porfavor seleccione Linea,Color,modelo y numero de orden antes de continuar");
                }
            }else{
                JOptionPane.showMessageDialog(vistaCrear, "Porfavor seleccione Linea,Color,modelo y numero de orden antes de continuar");
            }                 
        }
        if(e.getActionCommand().equals(vistaCrear.CANCELAR)){
            vistaCrear.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado);
        }
    }
    public ArrayList<Modelo> cargarModelos(){
        int numero = adaptador.obtenerNumModelos();
        ArrayList<Modelo> modelos = new ArrayList<Modelo>();
        for(int i=0;i<numero;i++){
            Modelo modelo=new Modelo();
            modelo.setDenominacion(adaptador.getDenominacionModelo(i));
            modelo.setSKU(adaptador.getSKU(i));
            modelo.setObjetivo(adaptador.getObjetivo(i));
            modelos.add(modelo);
        }
        return modelos;
    }
    public ArrayList<LineaTrabajo> cargarLineas(){
        int numero = adaptador.obtenerCantidadLineas();
        ArrayList<LineaTrabajo> lineas = new ArrayList<LineaTrabajo>();
        for(int i=0;i<numero;i++){
            LineaTrabajo linea=new LineaTrabajo();
            linea.setNumeroLinea(adaptador.getNumeroLineaDesocupadas(i));
            lineas.add(linea);
        }
        return lineas;
    }
    
    public ArrayList<Color> cargarColores(){
        int numero = adaptador.obtenerNumeroColores();
        ArrayList<Color> colores = new ArrayList<Color>();
        for(int i=0;i<numero;i++){
            Color color=new Color();
            color.setCodigo(adaptador.getCodigoColor(i));
            color.setNombre(adaptador.getDenominacionColor(i));
            colores.add(color);
        }
        return colores;
    }
    
    public void crearOP(int numeroLinea,int numeroOP,String sku,int codigoColor,int documento){
        int hora = adaptador.getHora();
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        if(adaptador.comprobarHorarioTurno(hora)){
            adaptador.setOP(linea.getNumeroLinea(),adaptador.crearOP(modelo.getSKU(),color.getCodigo(),empleado.getDocumento(),numeroOP,dia,mes,año,hora));
        }else{
            JOptionPane.showMessageDialog(vistaCrear,"No existe un turno en este horario\n No se creo la OP");            
        }
        
    }
    public void confirmarOP(int numeroOP,Modelo modelo,Color color,LineaTrabajo linea){
        if(adaptador.controlarNumeroOP(numeroOP)){
            crearOP(linea.getNumeroLinea(),numeroOP,modelo.getSKU(),color.getCodigo(),empleado.getDocumento());
            vistaCrear.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado);
        }else{
            JOptionPane.showMessageDialog(vistaCrear,"Ya existe una op con ese numero");
        }  
    }
    public void iniciarOP(){
        vistaCrear.cargarTablaModelo(cargarModelos());       
        vistaCrear.cargarTablaLinea(cargarLineas());
        vistaCrear.cargarTablaColor(cargarColores());
    }
    /*public void ingresarDatos(String sku,int codigoColor,Date diaHora,int documento){
        gestor.ingresarDatos(sku,codigoColor,diaHora,documento);
    }*/
}
