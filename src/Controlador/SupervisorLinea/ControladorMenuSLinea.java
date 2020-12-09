/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.SupervisorLinea;

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
import Vista.SupervisorLinea.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ControladorMenuSLinea implements ActionListener{
    private Adaptador adaptador;
    private ControladorIniciarOP controladorIniciarOP;
    private Empleado empleado;
    private VistaPrincipal vistaPrincipal;
    private ControladorActualizarOP controladorActualizar;
    private ControladorEstadisticas controladorEstadisticas;
    
    public ControladorMenuSLinea(Adaptador adaptador, Empleado empleado){
        vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.iniciar();
        vistaPrincipal.setControlador(this);
        this.adaptador=adaptador;
        this.empleado=empleado;
        vistaPrincipal.jLabel1.setText(empleado.getApellido()+", "+empleado.getNombre());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(vistaPrincipal.CREAR)){
            if(comprobarLineas()){
                LineaTrabajo linea = cargarLinea();
                JOptionPane.showMessageDialog(vistaPrincipal,"Ya posee una orden de produccion en la linea "+linea.getNumeroLinea());
            }else{                
                controladorIniciarOP = new ControladorIniciarOP(adaptador,empleado);
                vistaPrincipal.dispose();
            }
        }
        if(e.getActionCommand().equals(vistaPrincipal.ACTUALIZAR)){
            
            if(comprobarLineas()){
                LineaTrabajo linea = cargarLinea();
                controladorActualizar = new ControladorActualizarOP(adaptador,empleado,linea);
                vistaPrincipal.dispose();                
            }else{
                JOptionPane.showMessageDialog(vistaPrincipal,"Aun no tiene una orden de produccion asignada");
            }
        }
        if(e.getActionCommand().equals(vistaPrincipal.VISUALIZAR)){
            
            if(comprobarLineas()){
                LineaTrabajo linea = cargarLinea();                
                vistaPrincipal.dispose();
                controladorEstadisticas = new ControladorEstadisticas(adaptador,empleado,linea);               
            }else{
                JOptionPane.showMessageDialog(vistaPrincipal,"Aun no tiene una orden de produccion asignada");
            }
        }
        if(e.getActionCommand().equals(vistaPrincipal.CERRAR)){
            vistaPrincipal.dispose();
            ControladorIniciarSesion controlador = new ControladorIniciarSesion(adaptador);
        }
    }
    
    public boolean comprobarLineas(){
        return adaptador.comprobarLineas(empleado.getDocumento());
    }
    
    public LineaTrabajo cargarLinea(){
        LineaTrabajo linea = new LineaTrabajo();
        linea.setNumeroLinea(adaptador.obtenerNumeroLineaOcupada(empleado.getDocumento()));
        OrdenProduccion op =new OrdenProduccion();
        Color color = new Color();
        Modelo modelo=new Modelo();
        ArrayList<HorarioTrabajo> horarios = new ArrayList<HorarioTrabajo>();
        int numero = adaptador.getCantidadHorariosEnOpEnLinea(linea.getNumeroLinea());
        for(int i=0;i<numero;i++){
            HorarioTrabajo horario  =new HorarioTrabajo();
            ArrayList<AnalisisCalidad> analisis = new ArrayList<AnalisisCalidad>();
            int numeroA=adaptador.getCantidadAnalisisEnHorarioEnOpEnLinea(linea.getNumeroLinea(),i);
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
            //System.out.println("error aqui");
            horario.getTurno().setDescripcion(adaptador.getDescripcionTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
            //System.out.println("error aqui2");
            horario.getTurno().setHoraFin(adaptador.getHoraFinTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
            horario.getTurno().setHoraInicio(adaptador.getHoraInicioTurnoDeHorarioDeOpDeLinea(linea.getNumeroLinea(),i));
            horario.setAnalisisCalidad(analisis);
            Hermanado hermanado = new Hermanado(adaptador.getCantidadPrimeraHermanado(linea.getNumeroLinea(),horario.getDia(),horario.getMes(),horario.getAño()),adaptador.getCantidadSegundaHermanado(linea.getNumeroLinea(),horario.getDia(),horario.getMes(),horario.getAño()));
            horario.setHermanado(hermanado);
            horarios.add(horario);
        }
        modelo.setDenominacion(adaptador.getDenominacionDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setSKU(adaptador.getSKUDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        modelo.setObjetivo(adaptador.getObjetivoDeModeloDeOpEnLinea(linea.getNumeroLinea()));
        color.setCodigo(adaptador.getCodigoDeColorDeOpEnLinea(linea.getNumeroLinea()));
        color.setNombre(adaptador.getNombreDeColorDeOpEnLinea(linea.getNumeroLinea()));
        op.setColor(color);
        op.setModelo(modelo);
        op.setSupervisorL(empleado);
        op.setHorarios(horarios);
        op.setNumOP(adaptador.getNumeroOP(linea.getNumeroLinea()));
        op.setEstado(EstadoOP.valueOf(adaptador.getEstadoDeOpEnLinea(linea.getNumeroLinea())));
        linea.setOP(op);
        linea.getOrdenes().add(op);
        linea.setSupervisorL(empleado);
        return linea;
    }
    
}
