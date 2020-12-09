/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mariano
 */
public class Adaptador {
    private Repositorio repo;
    private ControladorIngreso controladorIngreso;
    private ControladorModelo controladorModelo;
    private ControladorColor controladorColor;
    private ControladorLinea controladorLinea;
    private ControladorDefectos controladorDefectos;
    
    public Adaptador(){
        repo = new Repositorio();
        controladorIngreso=new ControladorIngreso(repo);
        controladorModelo = new ControladorModelo(repo);
        controladorColor= new ControladorColor(repo);
        controladorLinea = new ControladorLinea(repo);
        controladorDefectos= new ControladorDefectos(repo);
    }
    public Adaptador(Repositorio repo){
        this.repo=repo;
    }

    public Repositorio getRepo() {
        return repo;
    }

    public void setRepo(Repositorio repo) {
        this.repo = repo;
    }
    
    /*public void cargarModelo(Modelo modelo){
        repo.cargarModelo(modelo);
    }*/
    /*public void eliminarModelo(int indice){
        repo.getModelos().remove(indice);
    }*/
    /*public boolean revisarSiExisteColor(int codigo, String nombre){
        for(Color c : repo.getColores()){
            if(c.getCodigo()==codigo && c.getNombre()==nombre){
                return true;
            }
        }
        return false;
    }*/
    /*public void crearColor(Color color){
        repo.getColores().add(color);
    }*/
    /*public Color obtenerColor(int indice){
        return repo.getColores().get(indice);
    }*/
    /*public void guardarColor(int indice,Color color){
        repo.getColores().set(indice, color);        
    }*/
    /*public void eliminarColor(int indice){
        repo.getColores().remove(indice);
    }*/
    /*public void cambiarModelo(Modelo modelo,int indice){
        repo.getModelos().set(indice,modelo);
    }
    public LineaTrabajo checkearLineas(Empleado empleado){
        for(LineaTrabajo lt : repo.getLineas()){
            if(lt.getSupervisorL()== null){
                return lt;
            }
        }
        return null;
    }
    public LineaTrabajo checkearLineaOcupada(Empleado empleado){
        for(LineaTrabajo lt : repo.getLineas()){
            if(lt.getSupervisorL()== null){
                
            }else{
                if(lt.getSupervisorL().getDocumento()==empleado.getDocumento()){
                    return lt;
                }
            }
        }
        return null;
    }
    public boolean checkearLineas1(Empleado empleado){
        for(LineaTrabajo lt : repo.getLineas()){
            if(lt.getSupervisorL()==null){
                
            }else{
                if(lt.getSupervisorL().getDocumento() == empleado.getDocumento()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList<LineaTrabajo> obtenerLineasDisponibles(){
        ArrayList<LineaTrabajo> lineas=new ArrayList<LineaTrabajo>();
        for(LineaTrabajo lt:repo.getLineas()){
            if(lt.getSupervisorL()==null){
                lineas.add(lt);
            }
        }
        return lineas;
    }
    
    public ArrayList<LineaTrabajo> obtenerLineasDisponiblesSC(){
        ArrayList<LineaTrabajo> lineas=new ArrayList<LineaTrabajo>();
        for(LineaTrabajo lt:repo.getLineas()){
            if(lt.getSupervisorC()==null && lt.getOP()!=null){
                lineas.add(lt);
            }
        }
        return lineas;
    }
    
    public Date obtenerFechaInicio(){
        Calendar fecha = Calendar.getInstance();
        Date dia = fecha.getTime();
        return dia;
    }
    
    public void guardarLinea(LineaTrabajo lineaT){
        for(LineaTrabajo linea: repo.getLineas()){
            if(linea.getNumeroLinea()==lineaT.getNumeroLinea()){
                linea=lineaT;
            }
        }
    }*/
//-------------------------------------------------------------------------------------------------------------------
    public int getHora() {
        int hora=0;
        Calendar fecha = Calendar.getInstance();
        hora=Calendar.HOUR_OF_DAY;
        return hora;
    }

    public  int getDia() {
       Calendar fecha = Calendar.getInstance();
       int dia = fecha.getTime().getDate();
       return dia;
    }
    public  int getMes() {
       Calendar fecha = Calendar.getInstance();
       int dia = fecha.getTime().getMonth();
       return dia;
    }
    public int getAño() {
       Calendar fecha = Calendar.getInstance();
       int dia = fecha.getTime().getYear();
       return dia;
    }
    public boolean comprobarHorarioTurno(int hora){
        boolean bandera=false;
        for(TurnoModel turno : repo.getTurnos()){
            if(hora>=turno.getHoraInicio() && hora<=turno.getHoraFin()){
                bandera = true;
            }
        }
        return bandera;
    }
    public String getDescripcionTurno(int hora){
        for(TurnoModel t:repo.getTurnos()){
            if(hora<=t.getHoraFin()&&hora>=t.getHoraInicio()){
                return t.getDescripcion();
            }
        }
        return "";
    }
    public int getHoraInicioTurno(int hora){
        for(TurnoModel t:repo.getTurnos()){
            if(hora<=t.getHoraFin()&&hora>=t.getHoraInicio()){
                return t.getHoraInicio();
            }
        }
        return -1;
    }
    public int getHoraFinTurno(int hora){
        for(TurnoModel t:repo.getTurnos()){
            if(hora<=t.getHoraFin()&&hora>=t.getHoraInicio()){
                return t.getHoraFin();
            }
        }
        return -1;
    }
    public boolean controlarNumeroOP(int numero){
        for(OrdenProduccionModel op: repo.getOrdenes()){
            if(op.getNumOP()==numero){
                return false;
            }
        }
        return true;
    }
    
    
 //-------------------------------------------------------------------------------------------------------------------   
    /*public void guardarOP(OrdenProduccion op){
        boolean comprobar = false;
        for(OrdenProduccion oper:getRepo().getOrdenes()){
            if(oper.getNumOP()==op.getNumOP()){
                oper=op;
                comprobar=true;
            }
        }
        if(comprobar==false){
            getRepo().getOrdenes().add(op);
        }
    }
    
    public boolean comprobarLineas(SupervisorLinea supervisor){
        for(LineaTrabajo lt : repo.getLineas()){
            if(lt.getSupervisorL()== null){
                
            }else{
                if(lt.getSupervisorL().getDocumento()== supervisor.getDocumento()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Turno getTurno(int hora){
        for(Turno t : repo.getTurnos()){
            if(hora>=t.getHoraInicio() && hora<=t.getHoraFin()){
                return t;
            }
        }
        return null;
    }*/
    //-------------------------------------------------------------------------------------------------------------------------
    //AQUI COMIENZA LOS MENSAJES DE EVENTOS
    public boolean comprobarEmpleado(String usuario,String contraseña){
        return controladorIngreso.comprobarEmpleado(usuario,contraseña);
    }
    public String buscarApellidoEmpleado(String usuario,String contraseña){
        return controladorIngreso.buscarApellidoEmpleado(usuario,contraseña);
    }
    public String buscarNombreEmpleado(String usuario,String contraseña){
        return controladorIngreso.buscarNombreEmpleado(usuario,contraseña);
    }
    public int buscarDocumentoEmpleado(String usuario,String contraseña){
        return controladorIngreso.buscarDocumentoEmpleado(usuario,contraseña);
    }
    public String buscarTipoEmpleado(String usuario,String contraseña){
        return controladorIngreso.buscarTipoEmpleado(usuario,contraseña);
    }
    
    
    
    
    //CONTROLADOR MODELO
    public int obtenerNumModelos(){
        return controladorModelo.contarModelos();
    }
    public String getDenominacionModelo(int numero){
        return controladorModelo.obtenerDenominacion(numero);
    }
    public String getSKU(int numero){
        return controladorModelo.obtenerSKU(numero);
    }
    public int getObjetivo(int numero){
        return controladorModelo.obtenerObjetivo(numero);
    }
    public void cambiarDenominacion(String denom,int indice){
        controladorModelo.cambiarDenominacion(denom,indice);
    }
    public void cambiarSKU(String sku,int indice){
        controladorModelo.cambiarSKU(sku,indice);
    }
    public void cambiarObjetivo(int obj,int indice){
        controladorModelo.cambiarObjetivo(obj,indice);
    }
    public void crearModelo(String deno,String sku,int num){
        controladorModelo.crearModelo(deno,sku,num);
    }
    public void eliminarModelo(int numero){
        controladorModelo.eliminarModelo(numero);
    }
    
    
    
    
    //CONTROLADOR COLOR
    public int obtenerNumeroColores(){
        return controladorColor.contarColores();
    }
    public int getCodigoColor(int numero){
        return controladorColor.getCodigo(numero);
    }
    public String getDenominacionColor(int numero){
        return controladorColor.getDenominacion(numero);
    }
    public void eliminarColor(int numero){
        controladorColor.eliminarColor(numero);
    }
    public boolean controlarSiExisteColor(int codigo,String nombre){
        return controladorColor.controlarSiExisteColor(codigo,nombre);
    }
    public void crearColor(int codigo,String nombre){
        controladorColor.crearColor(codigo,nombre);
    }
    public void cambiarCodigoColor(int indice,int codigo){
        controladorColor.cambiarCodigoColor(indice,codigo);
    }
    public void cambiarNombreColor(int indice,String nombre){
        controladorColor.cambiarNombreColor(indice,nombre);
    }
    
    
    
    //CONTROLADOR LINEA
    public void guardarSupervisorCEnLinea(int numero,int documento){
         controladorLinea.guardarSupervisorCEnLinea(numero,documento);
    }
    public boolean comprobarLineas(int documento){
        return controladorLinea.comprobarLineas(documento);
    }
    public int obtenerNumeroLineaOcupada(int documento){
        return controladorLinea.obtenerNumeroLineaOcupada(documento);
    }
    public int obtenerCantidadLineas(){
        return controladorLinea.obtenerCantidadLineas();
    }
    public int getNumeroLineaDesocupadas(int i){
        return controladorLinea.getNumeroLineaDesocupadas(i);
    }
    public OrdenProduccionModel crearOP(String sku,int codigo,int documento,int numeroOP,int dia,int mes,int año,int hora){
        return controladorLinea.crearOP(sku,codigo,documento,numeroOP,dia,mes,año,hora);
    }
    public void setOP(int numeroLinea,OrdenProduccionModel op){
        controladorLinea.setOP(numeroLinea,op);
    }
    public String getDenominacionDeModeloDeOpEnLinea(int numeroLinea){
        return controladorLinea.getDenominacionDeModeloDeOpEnLinea(numeroLinea);
    }
    public String getSKUDeModeloDeOpEnLinea(int numeroLinea){
        return controladorLinea.getSKUDeModeloDeOpEnLinea(numeroLinea);
    }
    public int getObjetivoDeModeloDeOpEnLinea(int numeroLinea){
        return controladorLinea.getObjetivoDeModeloDeOpEnLinea(numeroLinea);
    }
    public String getNombreDeColorDeOpEnLinea(int numeroLinea){
        return controladorLinea.getNombreDeColorDeOpEnLinea(numeroLinea);
    }
    public int getCodigoDeColorDeOpEnLinea(int numeroLinea){
        return controladorLinea.getCodigoDeColorDeOpEnLinea(numeroLinea);
    }
    public int getCantidadHorariosEnOpEnLinea(int numero){
        return controladorLinea.getCantidadHorariosEnOpEnLinea(numero);
    }
    public int getCantidadAnalisisEnHorarioEnOpEnLinea(int numeroLinea,int indice){
        return controladorLinea.getCantidadAnalisisEnHorarioEnOpEnLinea(numeroLinea,indice);
    }
    public int getCantidadDefectosEncontradosEnAnalisisEnHorarioEnOpEnLinea(int numeroLinea,int indicei,int indicej){
        return controladorLinea.getCantidadDefectosEncontradosEnAnalisisEnHorarioEnOpEnLinea(numeroLinea,indicei,indicej);
    }
    public String getDenominacionDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        return controladorLinea.getDenominacionDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej,indicez);
    }
    public String getTipoDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        return controladorLinea.getTipoDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej,indicez);
    }
    public int getCantidadDeDerechaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        return controladorLinea.getCantidadDeDerechaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej,indicez);
    }
    public int getCantidadDeIzquierdaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        return controladorLinea.getCantidadDeIzquierdaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej,indicez);
    }
    public int getHoraDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej){
        return controladorLinea.getHoraDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej);
    }
    public int getCantidadPrimeraDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej){
        return controladorLinea.getCantidadPrimeraDeAnalisisDeHorarioDeOpDeLinea(numeroLinea,indicei,indicej);
    }
    public int getDiaDeHorarioDeOpDeLinea(int numero,int indicei){
        return controladorLinea.getDiaDeHorarioDeOpDeLinea(numero,indicei);
    }
    public int getMesDeHorarioDeOpDeLinea(int numero,int indicei){
        return controladorLinea.getMesDeHorarioDeOpDeLinea(numero,indicei);
    }
    public int getAñoDeHorarioDeOpDeLinea(int numero,int indicei){
        return controladorLinea.getAñoDeHorarioDeOpDeLinea(numero,indicei);
    }
    public String getEstadoDeOpEnLinea(int numero){
        return controladorLinea.getEstadoDeOpEnLinea(numero);
    }
    public void guardarEstadoDeOpEnLinea(int numeroLinea,String estado){
        controladorLinea.guardarEstadoDeOpEnLinea(numeroLinea,estado);
    }
    public boolean getLineaEnUsoPorEmpleado(int documento){
        return controladorLinea.getLineaEnUsoPorEmpleado(documento);
    }
    public int getNumeroDeLineaEnUsoPorEmpleado(int documento){
        return controladorLinea.getNumeroDeLineaEnUsoPorEmpleado(documento);
    }
    public void abandonarLineaSupervisorCalidad(int numero){
        controladorLinea.abandonarLineaSupervisorCalidad(numero);
    }
    public int getCantidadLineasDisponiblesSC(){
        return controladorLinea.getCantidadLineasDisponiblesSC();
    }
    
    public int getLineaConOpSinUso(int numero){//cantidad lineas sin supervisorCalidad
        return controladorLinea.getLineaConOpSinUso(numero);
    }
    
    public ArrayList<LineaTrabajoModel> getLineasConOpSinSC(){//obtiene array de lineas sin supervisor calidad
        return controladorLinea.getLineasConOpSinSC();
    }
    public int getLineaConOpSinUso(ArrayList<LineaTrabajoModel> lineas,int i){
        return controladorLinea.getLineaConOpSinUso(lineas,i);
    }
    public String getDescripcionTurnoDeHorarioDeOpDeLinea(int numeroLinea,int indice){
        return controladorLinea.getDescripcionTurnoDeHorarioDeOpDeLinea(numeroLinea,indice);
    }
    public int getHoraFinTurnoDeHorarioDeOpDeLinea(int numeroLinea,int indice){
        return controladorLinea.getHoraFinTurnoDeHorarioDeOpDeLinea(numeroLinea,indice);
    }
    public int getHoraInicioTurnoDeHorarioDeOpDeLinea(int numeroLinea,int indice){
        return controladorLinea.getHoraInicioTurnoDeHorarioDeOpDeLinea(numeroLinea,indice);
    }
    public void cargarHorarioTrabajo(int numeroLinea,int dia,int mes,int año,int hora){
        controladorLinea.cargarHorarioTrabajo(numeroLinea,dia,mes,año,hora);
    }
    public int getNumeroOP(int numero){
        return controladorLinea.getNumeroOP(numero);
    }
    public void crearAnalisisCalidad(int numero,int hora,int dia,int mes,int año){
        controladorLinea.crearAnalisisCalidad(numero,hora,dia,mes,año);
    }
    public boolean comprobarHorarioTrabajo(int numeroLinea,int dia,int mes,int año,int hora){
        return controladorLinea.comprobarHorarioTrabajo(numeroLinea,dia,mes,año,hora);
    }
    public void crearHermanado(int primera,int segunda,int linea,int dia,int mes,int año,int hora){
        controladorLinea.crearHermanado(primera,segunda,linea,dia,mes,año,hora);
    }
    public boolean comprobarSiHayHermanado(int linea,int dia,int mes,int año){
        return controladorLinea.comprobarSiHayHermanado(linea,dia,mes,año);
    }
    public int getCantidadPrimeraHermanado(int linea,int dia,int mes,int año){
        return controladorLinea.getCantidadPrimeraHermanado(linea,dia,mes,año);
    }
    public int getCantidadSegundaHermanado(int linea,int dia,int mes,int año){
        return controladorLinea.getCantidadSegundaHermanado(linea,dia,mes,año);
    }
    
    
    //CONTROLADOR DEFECTO
    public int getCantidadDefectosEnRepositorio(){
        return controladorDefectos.getCantidadDefectosEnRepositorio();
    }
    public String getDenominacionDefecto(int i){
        return controladorDefectos.getDenominacionDefecto(i);
    }
    public String getTipoDefecto(int i){
        return controladorDefectos.getTipoDefecto(i);
    }
    public void actualizarAnalisisCalidadCantidadPrimera(int numeroLinea,int cantidadPrimera,int dia,int mes,int año,int hora,int documento){
        controladorDefectos.actualizarAnalisisCalidadCantidadPrimera(numeroLinea,cantidadPrimera,dia,mes,año,hora,documento);
    }
    public void actualizarAnalisisCalidadDefectos(int numeroLinea,int hora,int dia, int mes,int año,int cantidadDerecha,int cantidadIzquierda,String denominacion,String tipoDefecto){
        controladorDefectos.actualizarAnalisisCalidadDefectos(numeroLinea,hora,dia,mes,año,cantidadDerecha,cantidadIzquierda,denominacion,tipoDefecto);
    }
    //public ArrayList<DefectoEncontradModel> getDefectosEncontradosDeAnalisisCalidad(int numeroLinea,int horaAc,int cantidadPrimera,int dia,int mes,int año,int documento)

}
