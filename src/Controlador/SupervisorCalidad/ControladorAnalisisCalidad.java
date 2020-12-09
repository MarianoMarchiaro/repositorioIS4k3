/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorCalidad;

import Modelo.AnalisisCalidad;
import Modelo.Defecto;
import Modelo.DefectoEncontrado;
import Vista.SupCalidad.VistaAnalisisCalidad;
import Modelo.Empleado;
import Modelo.HorarioTrabajo;
import Modelo.LineaTrabajo;
import Modelo.TipoDefecto;
import Servidor.Adaptador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorAnalisisCalidad implements ActionListener{
    private Adaptador adaptador;
    private Empleado empleado;
    private LineaTrabajo linea;
    private VistaAnalisisCalidad vistaAnalisis;
    private ArrayList<Defecto> defectos;
    private ArrayList<Defecto> defectosO;
    private ArrayList<Defecto> defectosR;
    private ArrayList<DefectoEncontrado> defectosEncontrados;
    private AnalisisCalidad ac;
    
    public ControladorAnalisisCalidad(Adaptador adaptador,Empleado empleado,LineaTrabajo linea){
        this.adaptador=adaptador;
        this.empleado=empleado;
        this.linea=linea;
        vistaAnalisis=new VistaAnalisisCalidad();
        vistaAnalisis.iniciar();
        vistaAnalisis.setControlador(this);
        iniciarAnalisisCalidad();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaAnalisis.SALIR)){
            vistaAnalisis.dispose();
            ControladorVistaLinea controlador=new ControladorVistaLinea(adaptador,empleado,linea);
        }
        if(e.getActionCommand().equals(vistaAnalisis.BOTON_DERECHO)){
            int numero = vistaAnalisis.seleccionarDefecto();
            
            //DefectoEncontrado  defectoEncontrado1 = new DefectoEncontrado();//defecto observado
            //DefectoEncontrado defectoEncontrado2= new DefectoEncontrado();//defecto reproceso
            if(numero !=-1){//crearDefectoEncontradoReprocesoDerecho(numero, ac);
                ingresarDefectoDerecho(numero);
                
            }else{
                JOptionPane.showMessageDialog(vistaAnalisis,"Seleccione un defecto de la tabla");
            }    
        }
        
        if(e.getActionCommand().equals(vistaAnalisis.BOTON_IZQUIERDO)){
            int numero = vistaAnalisis.seleccionarDefecto();
            
            //DefectoEncontrado  defectoEncontrado1 = new DefectoEncontrado();//defecto observado
            //DefectoEncontrado defectoEncontrado2= new DefectoEncontrado();//defecto reproceso
            if(numero !=-1){
                ingresarDefectoIzquierdo(numero);
                
            }else{
                JOptionPane.showMessageDialog(vistaAnalisis,"Seleccione un defecto de la tabla");
            }    
        }
        if(e.getActionCommand().equals(vistaAnalisis.CONFIRMAR)){
            int numero = vistaAnalisis.obtenerParPrimera();
            ac.setCantidadPrimera(numero);
            int hora = adaptador.getHora();
            int dia = adaptador.getDia();
            int mes = adaptador.getMes();
            int año = adaptador.getAño();
            confirmarAnalisisCalidad(ac,hora,dia,mes,año);
            /*guardarAnalisisCalidad(ac,dia,mes,año);
            adaptador.actualizarAnalisisCalidadCantidadPrimera(linea.getNumeroLinea(),ac.getCantidadPrimera(),dia,mes,año,hora,empleado.getDocumento());
            for(DefectoEncontrado de : ac.getDefectoEncontrado()){
                adaptador.actualizarAnalisisCalidadDefectos(linea.getNumeroLinea(),ac.getHora(),dia,mes,año,de.getCantidadDer(),de.getCantidadIzq(),de.getDefecto().getDenominacion(),de.getDefecto().getTipo().toString());
            }
            guardarAnalisisCalidad(ac,dia,mes,año);//adaptador.añadirDatosAnalisisCalidad(numeroLinea,horaAc,cantidadPrimera,dia,mes,año,documento,adaptador.getDefectosEncontradosDeAnalisisCalidad(numeroLinea,horaAc,cantidadPrimera,dia,mes,año,documento))
            JOptionPane.showMessageDialog(vistaAnalisis,"cargo el analisis");
            vistaAnalisis.dispose();
            ControladorVistaLinea controlador=new ControladorVistaLinea(adaptador,empleado,linea);*/
            
        }
    }
    /*public ArrayList<Defecto> buscarDefectosReproceso(ArrayList<Defecto> defectos){
        ArrayList<Defecto> defectosReproceso = new ArrayList<Defecto>();
        for(Defecto d : defectos){
            if(d.getTipo().equals(TipoDefecto.REPROCESO)){
                defectosReproceso.add(d);
            }
        }
        return defectosReproceso;
    }*/
    /*public ArrayList<Defecto> buscarDefectosObservado(ArrayList<Defecto> defectos){
        ArrayList<Defecto> defectosReproceso = new ArrayList<Defecto>();
        for(Defecto d : defectos){
            if(d.getTipo().equals(TipoDefecto.OBSERVADO)){
                defectosReproceso.add(d);
            }
        }
        return defectosReproceso;
    }*/
    
    public AnalisisCalidad buscarAnalisis(int hora,int dia,int mes,int año){
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getDia()==dia &&ht.getMes()==mes && ht.getAño()==año &&ht.getTurno().getHoraInicio()<= hora && ht.getTurno().getHoraFin()>=0){
                for(AnalisisCalidad ac : ht.getAnalisisCalidad()){
                    if(ac.getHora()==hora){
                        return ac;
                    }
                }
            }
        }
        return null;
    }
    
    /*public DefectoEncontrado crearDefectoEncontradoObservadoDerecho(int numeroObservado,AnalisisCalidad ac){    
        for(DefectoEncontrado de : ac.getDefectoEncontrado()){
                        if(de.getDefecto().getDenominacion()== defectosO.get(numeroObservado).getDenominacion()){
                            return de;
                        }
        }
        return null;
    }*/
    
    /*public DefectoEncontrado crearDefectoEncontradoReprocesoDerecho(int numeroReproceso,AnalisisCalidad ac){    
        for(DefectoEncontrado de : ac.getDefectoEncontrado()){
                        if(de.getDefecto().getDenominacion()== defectosO.get(numeroReproceso).getDenominacion()){
                            return de;
                        }
        }
        return null;
    }*/
    public DefectoEncontrado buscarDefecto(int numero,AnalisisCalidad ac){
        for(DefectoEncontrado de : ac.getDefectoEncontrado()){
            if(de.getDefecto().getDenominacion()==defectos.get(numero).getDenominacion()){
                return de;
            }
        }
        return null;
    }
    public void guardarDefecto(DefectoEncontrado de,AnalisisCalidad ac){
        for(DefectoEncontrado def : ac.getDefectoEncontrado()){
            if(def.getDefecto().getDenominacion().equals(de.getDefecto().getDenominacion())){
                def=de;
            }
        }
    }
    
    /*public AnalisisCalidad comprobarAnalisisCalidad(int hora,Date dia){
        
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if((ht.getDia().equals(dia))&&(ht.getTurno().getHoraInicio()>=hora && ht.getTurno().getHoraFin()<=hora)){
                for(AnalisisCalidad ac : ht.getAnalisisCalidad()){
                    if(ac.getHora()==hora){
                        return ac;
                    }
                }
            }
        }
        return null;
    }*/
    
    /*public void guardarAnalisis(AnalisisCalidad ac,int hora,int dia,int mes,int año){
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getTurno().getHoraInicio()<=hora && ht.getTurno().getHoraFin()>=hora && ht.getMes()==mes && ht.getAño()==año){
                for(AnalisisCalidad an : ht.getAnalisisCalidad()){
                    if(an.getHora()==hora){
                        an=ac;
                    }
                }
            }
        }
        adaptador.guardarOP(linea.getOP());
    }*/
    
    public boolean comprobarAnalsisCalidad(int hora,int mes,int año,int dia,LineaTrabajo lineatr){
        for(HorarioTrabajo ht : lineatr.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                System.out.println(lineatr.getOP().getHorarios().size());
                System.out.println("esta el horario en analisis");
                System.out.println(hora);
                for(AnalisisCalidad ac : ht.getAnalisisCalidad()){
                    if(ac.getHora()==hora){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean comprobarDefecto(int numero,AnalisisCalidad ac){
        for(DefectoEncontrado de : ac.getDefectoEncontrado()){
            if(de.getDefecto().getDenominacion()==defectos.get(numero).getDenominacion()){
                return true;
            }
        }
        return false;
    }
    public void guardarAnalisisCalidad(AnalisisCalidad ac,int dia,int mes,int año){
        for(HorarioTrabajo ht:linea.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                System.out.println(ht.getDia()+"   "+ac.getHora()+"  "+ht.getTurno().getHoraInicio()+"  "+ht.getTurno().getHoraFin());
                if(ac.getHora()>=ht.getTurno().getHoraInicio() && ac.getHora()<=ht.getTurno().getHoraFin()){
                    System.out.println("se guardo el analisis calidad");
                    ht.getAnalisisCalidad().add(ac);
                }
            }
        }
    }
    
    public ArrayList<Defecto> obtenerDefectos(){
        ArrayList<Defecto> defectos = new ArrayList<Defecto>();
        int numero = adaptador.getCantidadDefectosEnRepositorio();
        for(int i=0;i<numero;i++){
            Defecto defecto = new Defecto();
            defecto.setDenominacion(adaptador.getDenominacionDefecto(i));
            defecto.setTipo(TipoDefecto.valueOf(adaptador.getTipoDefecto(i)));
            defectos.add(defecto);
        }
        return defectos;
    }
    
    public void iniciarAnalisisCalidad(){
        int hora = adaptador.getHora();
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        defectos = new ArrayList<Defecto>();
        defectos = obtenerDefectos();
        if(comprobarAnalsisCalidad(hora,mes,año,dia,linea)){
            ac=buscarAnalisis(hora, dia,mes,año);
            ac.getSupervisorC().add(empleado);            
            System.out.println("se creo el ac antes");
        }else{
            ac = new AnalisisCalidad();
            ac.getSupervisorC().add(empleado);
            ac.setHora(hora);
            guardarAnalisisCalidad(ac,dia,mes,año);
            adaptador.crearAnalisisCalidad(linea.getNumeroLinea(),hora,dia,mes,año);
            System.out.println("se creo el ac ahora");
        }
        defectosEncontrados=new ArrayList<DefectoEncontrado>();
        vistaAnalisis.jTextField1.setText(String.valueOf(ac.getCantidadPrimera()));              
        vistaAnalisis.cargarTablaDefectos(defectos);
    }
    
    public void confirmarAnalisisCalidad(AnalisisCalidad ac,int hora,int dia,int mes,int año){
        guardarAnalisisCalidad(ac,dia,mes,año);
        adaptador.actualizarAnalisisCalidadCantidadPrimera(linea.getNumeroLinea(),ac.getCantidadPrimera(),dia,mes,año,hora,empleado.getDocumento());
        for(DefectoEncontrado de : ac.getDefectoEncontrado()){
            adaptador.actualizarAnalisisCalidadDefectos(linea.getNumeroLinea(),ac.getHora(),dia,mes,año,de.getCantidadDer(),de.getCantidadIzq(),de.getDefecto().getDenominacion(),de.getDefecto().getTipo().toString());
        }//adaptador.añadirDatosAnalisisCalidad(numeroLinea,horaAc,cantidadPrimera,dia,mes,año,documento,adaptador.getDefectosEncontradosDeAnalisisCalidad(numeroLinea,horaAc,cantidadPrimera,dia,mes,año,documento))
        JOptionPane.showMessageDialog(vistaAnalisis,"cargo el analisis");
        vistaAnalisis.dispose();
        ControladorVistaLinea controlador=new ControladorVistaLinea(adaptador,empleado,linea);
    }
    public void ingresarDefectoIzquierdo(int numero){
        if(comprobarDefecto(numero,ac)){
            DefectoEncontrado defectoEncontrado = buscarDefecto(numero,ac);
            defectoEncontrado.setCantidadIzq(defectoEncontrado.getCantidadIzq()+1);
            guardarDefecto(defectoEncontrado,ac);
            JOptionPane.showMessageDialog(vistaAnalisis,"defecto ok");
        }else{
            DefectoEncontrado defectoEncontrado = new DefectoEncontrado();
            defectoEncontrado.setDefecto(defectos.get(numero));
            defectoEncontrado.setCantidadIzq(1);
            ac.getDefectoEncontrado().add(defectoEncontrado);
            JOptionPane.showMessageDialog(vistaAnalisis,"cargo el defecto");
        }
    }
    public void ingresarDefectoDerecho(int numero){
        if(comprobarDefecto(numero,ac)){
            DefectoEncontrado defectoEncontrado = buscarDefecto(numero,ac);
            defectoEncontrado.setCantidadDer(defectoEncontrado.getCantidadDer()+1);
            guardarDefecto(defectoEncontrado,ac);
            JOptionPane.showMessageDialog(vistaAnalisis,"defecto ok");

        }else{
            DefectoEncontrado defectoEncontrado = new DefectoEncontrado();
            defectoEncontrado.setDefecto(defectos.get(numero));
            defectoEncontrado.setCantidadDer(1);
            ac.getDefectoEncontrado().add(defectoEncontrado);
            JOptionPane.showMessageDialog(vistaAnalisis,"cargo el defecto");
        }
    }
}
