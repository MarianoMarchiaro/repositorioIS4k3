/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorCalidad;

import Modelo.AnalisisCalidad;
import Modelo.Color;
import Modelo.Defecto;
import Modelo.DefectoEncontrado;
import Modelo.Empleado;
import Modelo.EstadoOP;
import Modelo.Hermanado;
import Modelo.HorarioTrabajo;
import Modelo.LineaTrabajo;
import Modelo.Modelo;
import Modelo.OrdenProduccion;
import Modelo.TipoDefecto;
import Modelo.Turno;
import Servidor.Adaptador;
import Vista.SupCalidad.IngresarLinea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorIngresarLinea implements ActionListener{
    private IngresarLinea vistaIngresar;
    private ControladorVistaLinea controladorLinea;
    private Adaptador adaptador;
    private ArrayList<LineaTrabajo> lineas;
    private LineaTrabajo linea;
    private Empleado empleado;
    
    public ControladorIngresarLinea(Adaptador adaptador,Empleado empleado){
        this.adaptador=adaptador;
        this.empleado=empleado;
        lineas = new ArrayList<LineaTrabajo>();
        lineas = obtenerLineasDisponiblesSC();//adaptador.obtenerLineasDisponiblesSC();
        vistaIngresar = new IngresarLinea();
        vistaIngresar.iniciar();
        vistaIngresar.setControlador(this);
        if(lineas.size()==0){
            JOptionPane.showMessageDialog(vistaIngresar,"No hay lineas disponibles");
            vistaIngresar.dispose();
            ControladorMenuPrincipalSC controlador = new ControladorMenuPrincipalSC(adaptador,empleado);
        }else{    
        vistaIngresar.cargarTabla(lineas);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaIngresar.INGRESAR)){
            int numero = vistaIngresar.seleccionarLinea();
            linea = lineas.get(numero);
            ingresarLinea(linea);/*
            if(linea.getOP().getEstado().equals(EstadoOP.PAUSADA)||linea.getOP().getEstado().equals(EstadoOP.ACTIVA)){
                linea.setSupervisorC(empleado);
                if(comprobarHorarioTrabajo(linea)){//comprueba si existe el turno en la op
                    HorarioTrabajo ht = buscarHorarioTrabajo(linea);
                    adaptador.guardarSupervisorCEnLinea(linea.getNumeroLinea(),empleado.getDocumento());
                    vistaIngresar.dispose();
                    controladorLinea= new ControladorVistaLinea(adaptador,empleado,linea);
                }else{
                    int hora = adaptador.getHora();
                    int dia = adaptador.getDia();
                    int mes = adaptador.getMes();
                    int año = adaptador.getAño();
                    if(adaptador.comprobarHorarioTurno(hora)){
                        adaptador.cargarHorarioTrabajo(linea.getNumeroLinea(),dia,mes,año,hora);
                        adaptador.guardarSupervisorCEnLinea(linea.getNumeroLinea(),empleado.getDocumento());
                        linea = lineas.get(numero);
                        vistaIngresar.dispose();
                        controladorLinea = new ControladorVistaLinea(adaptador,empleado,linea);
                    }else{
                        JOptionPane.showMessageDialog(vistaIngresar,"No se puede trabajar en este horario");
                    }
                }
                
                
            }else{
                JOptionPane.showMessageDialog(vistaIngresar,"La Linea no esta pausada");
            }  */          
        }
        if(e.getActionCommand().equals(vistaIngresar.SALIR)){
            vistaIngresar.dispose();
            ControladorMenuPrincipalSC controlador = new ControladorMenuPrincipalSC(adaptador,empleado);
        }
    }
    public HorarioTrabajo buscarHorarioTrabajo(LineaTrabajo linea){
        int hora = adaptador.getHora();
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                if(ht.getTurno().getHoraInicio()<=hora && ht.getTurno().getHoraFin()>=hora){
                    return ht;
                }
            }
        }
        /*Turno turno = new Turno();
        turno.setDescripcion(adaptador.getDescripcionTurno(hora));
        turno.setHoraInicio(adaptador.getHoraInicioTurno(hora));
        turno.setHoraFin(adaptador.getHoraFinTurno(hora));*/
        HorarioTrabajo horario = new HorarioTrabajo();
        return horario;        
    }
    public boolean comprobarHorarioTrabajo(LineaTrabajo linea){//comprobar Si existe el turno en la op
        int hora = adaptador.getHora();
        int dia = adaptador.getDia();
        int mes = adaptador.getMes();
        int año = adaptador.getAño();
        for(HorarioTrabajo ht : linea.getOP().getHorarios()){
            if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año && (hora>= ht.getTurno().getHoraInicio() && hora<=ht.getTurno().getHoraFin())){
                return true;
            }
        }
        return false;
    }
    public boolean comprobarLineaEnUso(ArrayList<LineaTrabajo> lineas,Empleado emp){
        for(LineaTrabajo lt : lineas){
            if(lt.getSupervisorC()==null){
                
            }else{
                if(lt.getSupervisorC().getDocumento()==emp.getDocumento()){
                    return true;
                }   
            }    
        }
        return false;
    }
    public ArrayList<LineaTrabajo> obtenerLineasDisponiblesSC(){
        ArrayList<LineaTrabajo> lineas = new ArrayList<LineaTrabajo>();
        int numero=adaptador.getCantidadLineasDisponiblesSC();
        for(int i=0;i<numero;i++){
            LineaTrabajo linea = new LineaTrabajo();
            linea  = cargarLinea(i);
            lineas.add(linea);
        }
        return lineas;
    }
    
    public LineaTrabajo cargarLinea(int indice){
        LineaTrabajo linea = new LineaTrabajo(); 
        linea.setNumeroLinea(adaptador.getLineaConOpSinUso(adaptador.getLineasConOpSinSC(),indice));
        OrdenProduccion op =new OrdenProduccion();
        Color color = new Color();
        Modelo modelo=new Modelo();
        int numero = adaptador.getCantidadHorariosEnOpEnLinea(linea.getNumeroLinea());
        modelo.setDenominacion(adaptador.getDenominacionDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setSKU(adaptador.getSKUDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setObjetivo(adaptador.getObjetivoDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        color.setCodigo(adaptador.getCodigoDeColorDeOpEnLinea(linea.getNumeroLinea()));
        color.setNombre(adaptador.getNombreDeColorDeOpEnLinea(linea.getNumeroLinea()));
        op.setColor(color);
        op.setModelo(modelo);
        op.setNumOP(adaptador.getNumeroOP(linea.getNumeroLinea()));
        op.setHorarios(cargarHorariosTrabajo(numero,linea));
        op.setEstado(EstadoOP.valueOf(adaptador.getEstadoDeOpEnLinea(linea.getNumeroLinea())));
        linea.setOP(op);
        linea.getOrdenes().add(op);
        return linea;
    }
    
    public ArrayList<HorarioTrabajo> cargarHorariosTrabajo(int numero,LineaTrabajo linea){
        ArrayList<HorarioTrabajo> horarios = new ArrayList<HorarioTrabajo>();
        System.out.println("Cantidad horarios:"+numero);
        for(int i=0;i<numero;i++){
            HorarioTrabajo horario  = cargarHorarioTrabajo(i,linea);
            horarios.add(horario);
        }
        return horarios;                
    }
    public void ingresarLinea(LineaTrabajo linea){
        if(linea.getOP().getEstado().equals(EstadoOP.PAUSADA)||linea.getOP().getEstado().equals(EstadoOP.ACTIVA)){
                linea.setSupervisorC(empleado);
                if(comprobarHorarioTrabajo(linea)){//comprueba si existe el turno en la op
                    HorarioTrabajo ht = buscarHorarioTrabajo(linea);
                    adaptador.guardarSupervisorCEnLinea(linea.getNumeroLinea(),empleado.getDocumento());
                    vistaIngresar.dispose();
                    controladorLinea= new ControladorVistaLinea(adaptador,empleado,linea);
                }else{
                    int hora = adaptador.getHora();
                    int dia = adaptador.getDia();
                    int mes = adaptador.getMes();
                    int año = adaptador.getAño();
                    if(adaptador.comprobarHorarioTurno(hora)){
                        adaptador.cargarHorarioTrabajo(linea.getNumeroLinea(),dia,mes,año,hora);
                        adaptador.guardarSupervisorCEnLinea(linea.getNumeroLinea(),empleado.getDocumento());
                        HorarioTrabajo ht = new HorarioTrabajo();
                        ht.setDia(dia);
                        ht.setAño(año);
                        ht.setMes(mes);
                        Turno turno = cargarTurno(hora);
                        ht.setTurno(turno);
                        linea.getOP().getHorarios().add(ht);
                        vistaIngresar.dispose();
                        controladorLinea = new ControladorVistaLinea(adaptador,empleado,linea);
                    }else{
                        JOptionPane.showMessageDialog(vistaIngresar,"No se puede trabajar en este horario");
                    }
                }
                
                
            }else{
                JOptionPane.showMessageDialog(vistaIngresar,"La Linea no esta pausada");
            }
    }
    
    public Turno cargarTurno(int hora){
        if(adaptador.comprobarHorarioTurno(hora)){
            Turno t = new Turno();
            t.setDescripcion(adaptador.getDescripcionTurno(hora));
            t.setHoraFin(adaptador.getHoraFinTurno(hora));
            t.setHoraInicio(adaptador.getHoraInicioTurno(hora));
            return t;
        }
        return null;
    }
    
    public HorarioTrabajo cargarHorarioTrabajo(int i,LineaTrabajo linea){
        HorarioTrabajo horario = new HorarioTrabajo();
        ArrayList<AnalisisCalidad> analisis = new ArrayList<AnalisisCalidad>();
        int numeroA=adaptador.getCantidadAnalisisEnHorarioEnOpEnLinea(linea.getNumeroLinea(),i);
        System.out.println("Cantidad AC:" + numeroA);
        for(int j=0;j<numeroA;j++){
            AnalisisCalidad ac = cargarAnalisisCalidad(i, j,linea);
            analisis.add(ac);
        }
        horario.setDia(adaptador.getDiaDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
        horario.setMes(adaptador.getMesDeHorarioDeOpDeLinea(linea.getNumeroLinea(), i));
        horario.setAño(adaptador.getAñoDeHorarioDeOpDeLinea(linea.getNumeroLinea(), i));
        horario.getTurno().setDescripcion(adaptador.getDescripcionTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
        horario.getTurno().setHoraFin(adaptador.getHoraFinTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
        horario.getTurno().setHoraInicio(adaptador.getHoraInicioTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
        horario.setAnalisisCalidad(analisis);
        Hermanado hermanado = new Hermanado(adaptador.getCantidadPrimeraHermanado(linea.getNumeroLinea(),horario.getDia(),horario.getMes(),horario.getAño()),adaptador.getCantidadSegundaHermanado(linea.getNumeroLinea(),horario.getDia(),horario.getMes(),horario.getAño()));
        horario.setHermanado(hermanado);
        return horario;
    }
    public AnalisisCalidad cargarAnalisisCalidad(int i,int j,LineaTrabajo linea){
        AnalisisCalidad ac= new AnalisisCalidad();
        ArrayList<DefectoEncontrado> defectos = new ArrayList<DefectoEncontrado>();
        int numeroD=adaptador.getCantidadDefectosEncontradosEnAnalisisEnHorarioEnOpEnLinea(linea.getNumeroLinea(),i,j);
        System.out.println(numeroD+"Defectos encontrados");
        for(int z=0;z<numeroD;z++){
            DefectoEncontrado defecto=cargarDefectosEncontrados(i, j, z,linea);
            defectos.add(defecto);
        }
        ac.setDefectoEncontrado(defectos);
        ac.setHora(adaptador.getHoraDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j));
        ac.setCantidadPrimera(adaptador.getCantidadPrimeraDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(), i, j));
        return ac;
    }
    public DefectoEncontrado cargarDefectosEncontrados(int i, int j,int z,LineaTrabajo linea){
        DefectoEncontrado defecto=new DefectoEncontrado();
        Defecto def = new Defecto();
        System.out.println("linea: "+linea.getNumeroLinea()+", i: "+i+", j="+j+", z="+z);
        defecto.setCantidadDer(adaptador.getCantidadDeDerechaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
        defecto.setCantidadDer(adaptador.getCantidadDeIzquierdaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
        def.setDenominacion(adaptador.getDenominacionDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
        def.setTipo(TipoDefecto.valueOf(adaptador.getTipoDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z)));
        defecto.setDefecto(def);
        return defecto;
    }
   /* public ArrayList<LineaTrabajo> iniciarSeleccionLinea(){
        return gestor.obtenerLineasTrabajo();
    }
    public void seleccionarLinea(int numLinea){
        gestor.obtenerLinea(numLinea);
    }
    public void asociar(){
        gestor.asociar();
    }*/
}
