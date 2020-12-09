/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class ControladorLinea {
    private Repositorio repo;
    
    public ControladorLinea(Repositorio repo){
        this.repo=repo;
    }
    
    public boolean comprobarLineas(int documento){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getSupervisorL()== null){
                
            }else{
                if(lt.getSupervisorL().getDocumento()== documento){
                    return true;
                }
            }
        }
        return false;
    }
    public int obtenerNumeroLineaOcupada(int documento){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getSupervisorL()== null){
                
            }else{
                if(lt.getSupervisorL().getDocumento()==documento){
                    return lt.getNumeroLinea();
                }
            }
        }
        return -1;
    }
    public int obtenerCantidadLineas(){
        int numero=0;
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getSupervisorL()==null){
                numero=numero+1;
            }
        }
        return numero;
    }
    public int getNumeroLineaDesocupadas(int numero){
        ArrayList<LineaTrabajoModel> modelos = new ArrayList<LineaTrabajoModel>();
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getOP()== null && lt.getSupervisorL()==null){
                modelos.add(lt);
            }else{
                
            }
        }
        return modelos.get(numero).getNumeroLinea();
    }
    public OrdenProduccionModel crearOP(String sku,int codigo,int documento,int numeroOP,int dia,int mes,int año,int hora){
        ModeloModel modelo = new ModeloModel();
        ColorModel color = new ColorModel();
        EmpleadoModel empleado = new EmpleadoModel();
        TurnoModel turno = new TurnoModel();
        for(EmpleadoModel emp: repo.getEmpleados()){
            if(emp.getDocumento()==documento){
                empleado=emp;
                System.out.println("se creo empleado");
            }
        }
        for(ColorModel c : repo.getColores()){
            if(c.getCodigoColor()==codigo){
                color=c;
                System.out.println("se creo color");
            }
        }
        for(ModeloModel m : repo.getModelos()){
            if(m.getSKU()==sku){
                modelo=m;
                System.out.println("se creo modelo");
            }
        }
        for(TurnoModel t : repo.getTurnos()){
            if(hora <= t.getHoraFin() && hora>=t.getHoraInicio()){
                turno.setDescripcion(t.getDescripcion());
                turno.setHoraFin(t.getHoraFin());
                turno.setHoraInicio(t.getHoraInicio());
                System.out.println("se creo turno");
            }
        }
        HorarioTrabajoModel horario = new HorarioTrabajoModel();
        horario.setDia(dia);
        horario.setMes(mes);
        horario.setAño(año);
        horario.setTurno(turno);
        System.out.println(turno.getDescripcion());
        System.out.println("se añadio turno a horario");
        OrdenProduccionModel op = new OrdenProduccionModel(horario);
        op.setColor(color);
        op.setModelo(modelo);
        op.setNumOP(numeroOP);
        op.setSupervisorL(empleado);
        op.setEstadoOP("ACTIVA");
        System.out.println(op.getHorarios().size());
        System.out.println(horario.getAnalisis().size());
        System.out.println("se llego hasta aqui");
        repo.setOrden(op);
        return op;
    }
    public void setOP(int numero,OrdenProduccionModel op){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                System.out.println("llego 1");
                lt.setOP(op);
                System.out.println("llego2");
                lt.getOrdenes().add(op);
                System.out.println("llego3");
                lt.setSupervisorL(lt.getOP().getSupervisorL());
                System.out.println("llego4");
                /*for(int i=0;i<repo.getLineas().size();i++){
                    if(repo.getLineas().get(i).getNumeroLinea()==numero){
                    repo.getLineas().set(i, lt);
                    System.out.println("se guardo la linea");
                    }
                }   */         
            }
        }
    }
    
    public String getDenominacionDeModeloDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getModelo().getDenominacion();
            }
        }
        return "";
    }
    public String getSKUDeModeloDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getModelo().getSKU();
            }
        }
        return "";
    }
    public int getObjetivoDeModeloDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getModelo().getObjetivo();
            }
        }
        return -1;
    }
    public String getNombreDeColorDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getColor().getDenominacion();
            }
        }
        return "";
    }
    public int getCodigoDeColorDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getColor().getCodigoColor();
            }
        }
        return -1;
    }
    public int getCantidadHorariosEnOpEnLinea(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                
                return lt.getOP().getHorarios().size();
            }
        }
        return 0;
    }
    public int getCantidadAnalisisEnHorarioEnOpEnLinea(int numero,int indice){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                OrdenProduccionModel orden = lt.getOP();
                //System.out.println("llego 1");
                HorarioTrabajoModel ht = orden.getHorarios().get(indice);
                //System.out.println("llego2");
                int cant= ht.getAnalisis().size();
                //System.out.println("llego3");
                return cant;
            }
        }
        return 0;
    }
    public int getCantidadDefectosEncontradosEnAnalisisEnHorarioEnOpEnLinea(int numero,int indicei,int indicej){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                int contador=lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados().size();
                System.out.println("CANTIDAD DE DEFECTOS: "+contador);
                for(DefectoEncontradoModel de : lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados()){
                    //contador=contador+1;
                }
                return contador;
            }
        }
        return 0;
    }
    public String getDenominacionDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados().get(indicez).getDefecto().getDescripcion();
            }
        }
        return "";
    }
    public String getTipoDeDefectoDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados().get(indicez).getDefecto().getTipoDefecto().toString();
            }
        }
        return "";
    }
    public int getCantidadDeDerechaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados().get(indicez).getCantidadDer();
            }
        }
        return -1;
    }
    public int getCantidadDeIzquierdaDeDefectoEncontradoDeAnalisisDeHorarioDeOpDeLinea(int numeroLinea,int indicei,int indicej,int indicez){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getDefectosEncontrados().get(indicez).getCantidadIzq();
            }
        }
        return -1;
    }
    public int getHoraDeAnalisisDeHorarioDeOpDeLinea(int numero,int indicei,int indicej){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getHora();
            }
        }
        return -1;
    }
    public int getCantidadPrimeraDeAnalisisDeHorarioDeOpDeLinea(int numero,int indicei,int indicej){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getHorarios().get(indicei).getAnalisis().get(indicej).getCantidadPrimera();
            }
        }
        return -1;
    }
    public int getDiaDeHorarioDeOpDeLinea(int numero,int indicei){
        for(LineaTrabajoModel lt : repo.getLineas()){
            //System.out.println("llegue");
            if(lt.getNumeroLinea()==numero){
               // System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indicei).getDia();
            }
        }
        System.out.println("ESTA MAL ES -1");
        return -1;
    }
    public int getMesDeHorarioDeOpDeLinea(int numero,int indicei){
        for(LineaTrabajoModel lt : repo.getLineas()){
            //System.out.println("llegue");
            if(lt.getNumeroLinea()==numero){
               // System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indicei).getMes();
            }
        }
        return repo.getLineas().get(numero).getOP().getHorarios().get(indicei).getMes();
    }
    public int getAñoDeHorarioDeOpDeLinea(int numero,int indicei){
        for(LineaTrabajoModel lt : repo.getLineas()){
            //System.out.println("llegue");
            if(lt.getNumeroLinea()==numero){
                //System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indicei).getAño();
            }
        }
        return repo.getLineas().get(numero).getOP().getHorarios().get(indicei).getAño();
    }
    public String getEstadoDeOpEnLinea(int numero){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getEstadoOP();
            }
        }
        return "";
    }
    public void guardarEstadoDeOpEnLinea(int numero,String estado){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                lt.getOP().setEstadoOP(estado);
                if(lt.getOP().getEstadoOP().equals("FINALIZADA")){
                    repo.getOrdenes().add(lt.getOP());
                    lt.setSupervisorL(null);
                    lt.setOP(null);
                }
            }
        }
    }
    public boolean getLineaEnUsoPorEmpleado(int documento){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getSupervisorC()==null){
                
            }else{
                if(lt.getSupervisorC().getDocumento()==documento){
                    return true;
                }
            }    
        }
        return false;
    }
    public int getNumeroDeLineaEnUsoPorEmpleado(int documento){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getOP()==null){
                
            }else{
            if(lt.getSupervisorC()==null){
                
            }else{
                if(lt.getSupervisorC().getDocumento()==documento){
                    return lt.getNumeroLinea();
                }
            }}
        }
        return -1;
    }
    public void abandonarLineaSupervisorCalidad(int numero){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                lt.setSupervisorC(null);
            }
        }
    }
    public int getCantidadLineasDisponiblesSC(){
        int numero=0;
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getOP()==null){
                
            }else{
                if(lt.getSupervisorC()==null){
                    numero=numero+1;
                }
            }
        }
        return numero;
    }
    public int getLineaConOpSinUso(int numero){
        int contador=0;
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getOP()==null){
                
            }else{
                if(lt.getSupervisorC()==null){
                    contador=contador+1;
                }
            }
        }
        return contador;
    }
    
    public ArrayList<LineaTrabajoModel> getLineasConOpSinSC(){
        ArrayList<LineaTrabajoModel> lineas=new ArrayList<LineaTrabajoModel>();
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getOP()==null){
                
            }else{
                if(lt.getSupervisorC()==null){
                    lineas.add(lt);
                }
            }
        }
        return lineas;
    }
    
    public int getLineaConOpSinUso(ArrayList<LineaTrabajoModel> lineas,int i){
        return lineas.get(i).getNumeroLinea();
    }
    
    public String getDescripcionTurnoDeHorarioDeOpDeLinea(int linea,int indice){
        /*for(LineaTrabajoModel lt : repo.getLineas()){
            System.out.println("llegue");
            if(lt.getNumeroLinea()==linea){
                System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indice).getTurno().getDescripcion();
            }
        }*/
        for(int i=0;i<repo.getLineas().size();i++){
            //System.out.println("buscolinea");
            if(repo.getLineas().get(i).getNumeroLinea()==linea){
                //System.out.println("buscoturno");
                return repo.getLineas().get(i).getOP().getHorarios().get(indice).getTurno().getDescripcion();
            }
        }
        return "";
    }
    
    public int getHoraFinTurnoDeHorarioDeOpDeLinea(int linea,int indice){
        for(LineaTrabajoModel lt : repo.getLineas()){
            //System.out.println("llegue");
            if(lt.getNumeroLinea()==linea){
                //System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indice).getTurno().getHoraFin();
            }
        }
        return repo.getLineas().get(linea).getOP().getHorarios().get(indice).getTurno().getHoraFin();
    }
    public int getHoraInicioTurnoDeHorarioDeOpDeLinea(int linea,int indice){
        for(LineaTrabajoModel lt : repo.getLineas()){
           // System.out.println("llegue");
            if(lt.getNumeroLinea()==linea){
                //System.out.println("llegue2");
                return lt.getOP().getHorarios().get(indice).getTurno().getHoraInicio();
            }
        }
        return repo.getLineas().get(linea).getOP().getHorarios().get(indice).getTurno().getHoraInicio();
    }
    public void cargarHorarioTrabajo(int numeroLinea,int dia,int mes,int año,int hora){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                        if(hora<=ht.getTurno().getHoraFin() && hora>=ht.getTurno().getHoraInicio()){
                            
                        }else{
                            TurnoModel turno=new TurnoModel();
                            for(TurnoModel t: repo.getTurnos()){
                                if(hora<=t.getHoraFin() && hora>=t.getHoraInicio()){
                                    turno=t;
                                }
                            }
                            HorarioTrabajoModel horario = new HorarioTrabajoModel();
                            horario.setDia(dia);
                            horario.setAño(año);
                            horario.setMes(mes);
                            horario.setTurno(turno);
                            lt.getOP().getHorarios().add(horario);
                        }
                    }
                }
            }
        }
    }
    public int getNumeroOP(int numero){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                return lt.getOP().getNumOP();
            }
        }
        return -1;
    }
    
    public void guardarSupervisorCEnLinea(int numero,int documento){
        EmpleadoModel empleado=new EmpleadoModel();
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getDocumento()==documento){
                empleado=emp;
                for(LineaTrabajoModel lt:repo.getLineas()){
                    if(lt.getNumeroLinea()==numero){
                        lt.setSupervisorC(empleado);
                    }
                }
            }
        }        
    }
    
    public void crearAnalisisCalidad(int numero,int hora,int dia,int mes,int año){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getAño()==año && ht.getMes()==mes){
                        if(ht.getTurno().getHoraFin()>=hora && hora>=ht.getTurno().getHoraInicio()){
                            AnalisisCalidadModel analisis = new AnalisisCalidadModel();
                            analisis.setHora(hora);
                            ht.getAnalisis().add(analisis);
                        }
                    }
                }
            }
        }
    }
    public boolean comprobarHorarioTrabajo(int numero,int dia,int mes,int año,int hora){
        for(LineaTrabajoModel lt:repo.getLineas()){
            if(lt.getNumeroLinea()==numero){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getAño()==año && ht.getMes()==mes && ht.getDia()==dia && hora<=ht.getTurno().getHoraFin() &&hora>=ht.getTurno().getHoraInicio()){
                        if(ht.getHermanado().getCantidadPrimera()==0 && ht.getHermanado().getCantidadSegunda()==0){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void crearHermanado(int primera,int segunda,int linea,int dia,int mes,int año,int hora){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==linea){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getAño()==año && ht.getDia()==dia && ht.getMes()==mes && ht.getTurno().getHoraInicio()<=hora && ht.getTurno().getHoraFin()>=hora){
                        ht.setHermanado(new HermanadoModel(primera,segunda));
                    }
                }
            }
        }
    }
    public boolean comprobarSiHayHermanado(int linea,int dia,int mes,int año){
        for(LineaTrabajoModel lt: repo.getLineas()){
            if(lt.getNumeroLinea()==linea){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                        if(ht.getHermanado().getCantidadPrimera()==0 && ht.getHermanado().getCantidadSegunda()==0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public int getCantidadPrimeraHermanado(int linea,int dia,int mes,int año){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==linea){
                for(HorarioTrabajoModel ht: lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                        if(ht.getHermanado().getCantidadPrimera()==0 && ht.getHermanado().getCantidadSegunda()==0){
                            
                        }else{
                            return ht.getHermanado().getCantidadPrimera();
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    public int getCantidadSegundaHermanado(int linea,int dia,int mes,int año){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==linea){
                for(HorarioTrabajoModel ht: lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                        if(ht.getHermanado().getCantidadPrimera()==0 && ht.getHermanado().getCantidadSegunda()==0){
                            
                        }else{
                            return ht.getHermanado().getCantidadSegunda();
                        }
                    }
                }
            }
        }
        return 0;
    }
}
