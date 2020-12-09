/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorLinea;

import Modelo.AnalisisCalidad;
import Modelo.Defecto;
import Modelo.DefectoEncontrado;
import Modelo.Empleado;
import Modelo.HorarioTrabajo;
import Modelo.LineaTrabajo;
import Servidor.Adaptador;
import Vista.SupervisorLinea.VistaEstadisticas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class ControladorEstadisticas implements ActionListener{
    private VistaEstadisticas vistaEstadisticas;
    private Empleado empleado;
    private Adaptador adaptador;
    private LineaTrabajo linea;
    
    public ControladorEstadisticas(Adaptador adaptador,Empleado empleado,LineaTrabajo linea){
        this.empleado=empleado;
        this.adaptador=adaptador;
        this.linea=linea;
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        /*System.out.println(linea.getOP().getHorarios().get(0).getDia());
        System.out.println(linea.getOP().getHorarios().get(0).getAnalisisCalidad().get(0).getHora());*/
        vistaEstadisticas = new VistaEstadisticas();
        vistaEstadisticas.iniciar();
        vistaEstadisticas.setControlador(this);
        vistaEstadisticas.jLabel6.setText(String.valueOf(linea.getOP().getModelo().getObjetivo()));
        int numero = calcularCantidadPrimera(linea,dia,mes,año);
        vistaEstadisticas.jLabel3.setText(String.valueOf(numero));
        vistaEstadisticas.cargarTablaDefectos(buscar3Defectos(buscarHorarioTrabajo(linea)));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaEstadisticas.SALIR)){
            vistaEstadisticas.dispose();
            ControladorMenuSLinea controlador = new ControladorMenuSLinea(adaptador,empleado);
        }
    }
    
    public int calcularCantidadPrimera(LineaTrabajo linea,int dia,int mes,int año){
        int numero = (linea.getOP().getHorarios().size())-1;
        int contador = 0;
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getAño()==año && ht.getMes()==mes){
                if(ht.getHermanado().getCantidadPrimera()==0 &&ht.getHermanado().getCantidadSegunda()==0){
                    
                }else{
                    contador=ht.getHermanado().getCantidadPrimera();
                }
                for(AnalisisCalidad ac : ht.getAnalisisCalidad()){
                    contador = contador + ac.getCantidadPrimera();
                }
            }
        }       
        return contador;
    }
    
    public ArrayList<DefectoEncontrado> buscar3Defectos(HorarioTrabajo ht){
        int numero = ht.getAnalisisCalidad().size();
        numero=numero-1;
        int hora = ht.getAnalisisCalidad().get(numero).getHora();
        int horaMenos = hora-4;
        ArrayList<DefectoEncontrado> defectos = new ArrayList<DefectoEncontrado>();
        for(AnalisisCalidad ac : ht.getAnalisisCalidad()){
            if(ac.getHora()>= horaMenos && ac.getHora()<=hora){
                for(DefectoEncontrado e : ac.getDefectoEncontrado()){
                    defectos.add(e);
                }
            }
        }
        ArrayList<DefectoEncontrado> defectosFinal = new ArrayList<DefectoEncontrado>();
        return defectosFinal=buscarDefectos2(defectos);
        //return defectos;
    }
    
    public HorarioTrabajo buscarHorarioTrabajo(LineaTrabajo linea){
        int numero = linea.getOP().getHorarios().size()-1;
        return linea.getOP().getHorarios().get(numero);
    }
    
    
    public ArrayList<DefectoEncontrado> buscarDefectos2(ArrayList<DefectoEncontrado> defectos){
        int cantidad1 = 0;
        int cantidad2 = 0;
        int cantidad3 = 0;
        DefectoEncontrado defecto = new DefectoEncontrado();
        DefectoEncontrado defecto2 = new DefectoEncontrado();
        DefectoEncontrado defecto3 = new DefectoEncontrado();
        /*defecto = defectos.get(0);
        defecto2 = defectos.get(0);
        defecto3 = defectos.get(0);*/
        ArrayList<DefectoEncontrado> defectosFinal = new ArrayList<DefectoEncontrado>();
        for(int i=0;i<defectos.size();i++){
            int numero = defectos.get(i).getCantidadDer()+defectos.get(i).getCantidadIzq();
            System.out.println("Defectos : "+numero);
        }
        for(int i = 0; i<defectos.size();i++){
            for(int j=0;j<defectos.size()-1;j++){                
                int cant = defectos.get(j+1).getCantidadDer()+defectos.get(j+1).getCantidadIzq();
                System.out.println(cant);
                defecto=defectos.get(j+1);
                int cant1= defectos.get(j).getCantidadDer()+defectos.get(j).getCantidadIzq();
                System.out.println(cant1);
                defecto2=defectos.get(j);
                if(cant<cant1){
                    defecto3= defecto;
                    defectos.set(j+1,defecto2);
                    defectos.set(j,defecto3);
                    /*defectos.get(j+1).setDefecto(defecto2.getDefecto());
                    defectos.get(j+1).setCantidadDer(defecto2.getCantidadDer());
                    defectos.get(j+1).setCantidadIzq(defecto2.getCantidadIzq());
                    defectos.get(j).setDefecto(defecto.getDefecto());
                    defectos.get(j).setCantidadDer(defecto.getCantidadDer());
                    defectos.get(j).setCantidadIzq(defecto.getCantidadIzq());*/
                    
                }                
            }
            for(int z=0;z<defectos.size();z++){
                    int numero = defectos.get(z).getCantidadDer()+defectos.get(z).getCantidadIzq();
                    System.out.println("Defectos : "+numero);
                }
        }
        for(int i=0;i<defectos.size();i++){
            int numero = defectos.get(i).getCantidadDer()+defectos.get(i).getCantidadIzq();
            System.out.println("Defectos : "+numero);
        }
        int indice = defectos.size();
        defectosFinal.add(defectos.get(indice-1));
        defectosFinal.add(defectos.get(indice-2));
        defectosFinal.add(defectos.get(indice-3));
        return defectosFinal;
        
        /*if(defectos.size()<4){
            for(int i=0;i<defectos.size();i++){
                int cantidad = defectos.get(i).getCantidadDer()+defectos.get(i).getCantidadIzq();
                if(cantidad>cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                    cantidad1=cantidad;
                    defecto=defectos.get(i);
                }else{
                    if(cantidad==cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                        cantidad2=cantidad;
                        defecto2=defectos.get(i);
                    }else{
                        if(cantidad<cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                            cantidad2=cantidad;
                            defecto2=defectos.get(i);
                        }else{
                            if(cantidad<cantidad1 && cantidad==cantidad2 && cantidad>cantidad3){
                                cantidad3=cantidad;
                                defecto3=defectos.get(i);
                            }else{
                                if(cantidad<cantidad1 && cantidad<cantidad2 && cantidad>cantidad3){
                                    cantidad3=cantidad;
                                    defecto3=defectos.get(i);
                                }
                            }
                        }
                    }
                }
            }
        }*/
        /*for(int i=0;i<=defectos.size();i++){
            int cantidad = defectos.get(i).getCantidadDer()+defectos.get(i).getCantidadIzq();
            if(cantidad > cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                cantidad1 = cantidad;
                defecto = defectos.get(i);
            }else{
                    if(cantidad == cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                        cantidad2=cantidad;
                        defecto2=defectos.get(i);
                    }else{
                            if(cantidad<cantidad1 && cantidad>cantidad2 && cantidad>cantidad3){
                                cantidad2=cantidad;
                                defecto2=defectos.get(i);
                            }else{
                                if(cantidad<cantidad1 && cantidad==cantidad2 && cantidad>cantidad3){
                                    cantidad3=cantidad;
                                    defecto3=defectos.get(i);
                                }else{
                                    if(cantidad<cantidad1 && cantidad<cantidad2 && cantidad>cantidad3){
                                        cantidad3=cantidad;
                                        defecto=defectos.get(i);
                                    }
                                }
                            }
                    }
            }
        }*/
        
        
    }
    
}
