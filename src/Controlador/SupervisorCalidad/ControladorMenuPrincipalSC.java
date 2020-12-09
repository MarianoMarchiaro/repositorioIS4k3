/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorCalidad;

import Controlador.IniciarSesion.ControladorIniciarSesion;
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
import Servidor.Adaptador;
import Vista.SupCalidad.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorMenuPrincipalSC implements ActionListener{
    private ControladorIngresarLinea controlador;
    private MenuPrincipal vistaPrincipal;
    private Adaptador adaptador;
    private Empleado empleado;
    private LineaTrabajo linea1 = new LineaTrabajo();
    
    public ControladorMenuPrincipalSC(Adaptador adaptador, Empleado empleado){
        vistaPrincipal=new MenuPrincipal();
        vistaPrincipal.iniciar();
        vistaPrincipal.setControlador(this);
        this.adaptador = adaptador;
        this.empleado=empleado;
        vistaPrincipal.jLabel2.setText(empleado.getApellido()+", "+empleado.getNombre());
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaPrincipal.INGRESAR)){
            if(adaptador.getLineaEnUsoPorEmpleado(empleado.getDocumento())){
                System.out.println(adaptador.getLineaEnUsoPorEmpleado(empleado.getDocumento()));
                linea1 = cargarLinea();
                linea1.setSupervisorC(empleado);//op.setHorarios(cargarHorariosTrabajo(numero));
                if(linea1.getOP().getEstado().toString().equals("FINALIZADA")){
                    JOptionPane.showMessageDialog(vistaPrincipal,"La orden de produccion fue finalizada");
                }
                vistaPrincipal.dispose();
                ControladorVistaLinea controlador = new ControladorVistaLinea(adaptador,empleado,linea1);
            }else{
            controlador = new ControladorIngresarLinea(adaptador,empleado);
            vistaPrincipal.dispose();
            }
        }
        if(e.getActionCommand().equals(vistaPrincipal.CERRAR)){
            vistaPrincipal.dispose();
            ControladorIniciarSesion controlador=new ControladorIniciarSesion(adaptador);
        }
        
    }
    public boolean comprobarLineaEnUso(ArrayList<LineaTrabajo> lineas, Empleado emp){
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
    public LineaTrabajo obtenerLinea(ArrayList<LineaTrabajo> lineas,Empleado empleado){
        for(LineaTrabajo lt: lineas){
            if(lt.getSupervisorC()==null){
                
            }else{
                if(lt.getSupervisorC().getDocumento()==empleado.getDocumento()){
                    return lt;
                }
            }
        }
        return null;
    }
    //---------------------------------------------------------------------------------------------
    public boolean comprobarLineasEnUso(){
        return true;
    }
    public ArrayList<HorarioTrabajo> cargarHorariosTrabajo(int numero,LineaTrabajo linea){
        System.out.println("CANTIDAD HORARIOS: "+numero);
        ArrayList<HorarioTrabajo> horarios = new ArrayList<HorarioTrabajo>();
        for(int i=0;i<numero;i++){
            System.out.println(i);
            HorarioTrabajo horario  =new HorarioTrabajo();
            ArrayList<AnalisisCalidad> analisis = new ArrayList<AnalisisCalidad>();
            int numeroA = adaptador.getCantidadAnalisisEnHorarioEnOpEnLinea(linea.getNumeroLinea(),i);
            for(int j=0;j<numeroA;j++){
                AnalisisCalidad ac = new AnalisisCalidad();
                ArrayList<DefectoEncontrado> defectos = new ArrayList<DefectoEncontrado>();
                int numeroD=adaptador.getCantidadDefectosEncontradosEnAnalisisEnHorarioEnOpEnLinea(linea.getNumeroLinea(),i,j);
                for(int z=0;z<numeroD;z++){
                    DefectoEncontrado defecto=new DefectoEncontrado();
                    Defecto def = new Defecto();
                    def.setDenominacion(adaptador.getDenominacionDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
                    def.setTipo(TipoDefecto.valueOf(adaptador.getTipoDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z)));
                    defecto.setDefecto(def);
                    defecto.setCantidadDer(adaptador.getCantidadDeDerechaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
                    defecto.setCantidadIzq(adaptador.getCantidadDeIzquierdaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j,z));
                    defectos.add(defecto);
                }
                ac.setDefectoEncontrado(defectos);
                ac.setHora(adaptador.getHoraDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i,j));
                ac.setCantidadPrimera(adaptador.getCantidadPrimeraDeAnalisisDeHorarioDeOpDeLinea(linea.getNumeroLinea(), i, j));
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
            horarios.add(horario);
        }
        return horarios;                
    }
    public LineaTrabajo cargarLinea(){
        LineaTrabajo linea = new LineaTrabajo();                
        OrdenProduccion op =new OrdenProduccion();
        Color color = new Color();
        Modelo modelo=new Modelo();
        linea.setNumeroLinea(adaptador.getNumeroDeLineaEnUsoPorEmpleado(empleado.getDocumento()));
        System.out.println(linea.getNumeroLinea());
        int numero = adaptador.getCantidadHorariosEnOpEnLinea(linea.getNumeroLinea());        
        modelo.setDenominacion(adaptador.getDenominacionDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setSKU(adaptador.getSKUDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setObjetivo(adaptador.getObjetivoDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        color.setCodigo(adaptador.getCodigoDeColorDeOpEnLinea(linea.getNumeroLinea()));
        color.setNombre(adaptador.getNombreDeColorDeOpEnLinea(linea.getNumeroLinea()));
        op.setColor(color);
        op.setModelo(modelo);
        op.setHorarios(cargarHorariosTrabajo(numero,linea));
        op.setNumOP(adaptador.getNumeroOP(linea.getNumeroLinea()));
        op.setEstado(EstadoOP.valueOf(adaptador.getEstadoDeOpEnLinea(linea.getNumeroLinea())));
        linea.setOP(op);
        return linea;
    }
}
