/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author Mariano
 */
public class ControladorDefectos {
    private Repositorio repo;

    public ControladorDefectos(Repositorio repo) {
        this.repo = repo;
    }
    public int getCantidadDefectosEnRepositorio(){
        int contador=0;
        System.out.println("defectos");
        for(DefectoModel de : repo.getDefectos()){
            contador=contador+1;
        }
        return contador;
    }
    public String getDenominacionDefecto(int i){
        return repo.getDefectos().get(i).getDescripcion();
    }
    //SE OBTIENE EL TIPO DEFECTO
    public String getTipoDefecto(int i){
        return repo.getDefectos().get(i).getTipoDefecto().toString();
    }
    //SE REGISTRA LA CANTIDAD PRIMERA
    public void actualizarAnalisisCalidadCantidadPrimera(int numeroLinea,int cantidadPrimera,int dia,int mes,int año,int hora,int documento){
        for(LineaTrabajoModel lt : repo.getLineas()){
            if(lt.getNumeroLinea()==numeroLinea){
                //System.out.println("SE ENCONTRO LA LINEA");
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getMes()==mes && ht.getAño()==año){
                        if(ht.getTurno().getHoraInicio()<=hora && ht.getTurno().getHoraFin()>=hora){
                            //System.out.println("SE ENCONTRO EL TURNO Y EL HT");
                            boolean control=false;
                            //System.out.println("LA CANTIDAD DE AC QUE HAY EN EL HT ES: "+ht.getAnalisis().size());
                            for(AnalisisCalidadModel ac: ht.getAnalisis()){
                                if(ac.getHora()==hora){
                                    //System.out.println("SE ENCONTRO EL AC");
                                    ac.setCantidadPrimera(cantidadPrimera);                                
                                }else{
                                    control=true;
                                }
                            }
                            if(control){
                                System.out.println("SE CREO EL AC");
                                AnalisisCalidadModel analisis = new AnalisisCalidadModel();
                                analisis.setCantidadPrimera(cantidadPrimera);
                                analisis.setHora(hora);
                                EmpleadoModel empleado= new EmpleadoModel();
                                for(EmpleadoModel emp : repo.getEmpleados()){
                                    if(emp.getDocumento()==documento){
                                        empleado=emp;
                                    }
                                }
                                analisis.getSupervisorC().add(empleado);
                            }
                        }
                    }
                }
            }
        }
    }
    
    //SE REGISTRA EL DEFECTO ENCONTRADO
    public void actualizarAnalisisCalidadDefectos(int numeroLinea,int hora,int dia,int mes,int año,int cantidadDerecha,int cantidadIzquierda,String denominacion,String tipoDefecto){
            for(int i=0;i<repo.getLineas().size();i++){
                if(repo.getLineas().get(i).getNumeroLinea()==numeroLinea){
                    for(int j=0;j<repo.getLineas().get(i).getOP().getHorarios().size();j++){
                        if(repo.getLineas().get(i).getOP().getHorarios().get(j).getDia()==dia && repo.getLineas().get(i).getOP().getHorarios().get(j).getAño()==año && repo.getLineas().get(i).getOP().getHorarios().get(j).getMes()==mes && repo.getLineas().get(i).getOP().getHorarios().get(j).getTurno().getHoraFin()>=hora && repo.getLineas().get(i).getOP().getHorarios().get(j).getTurno().getHoraInicio()<=hora){
                            
                            for(int z=0;z<repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().size();z++){
                                int cantidad = repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().size();
                                System.out.println(cantidad+"Lista defectos");
                                System.out.println(cantidadDerecha);
                                System.out.println(cantidadIzquierda);
                                if(cantidad ==0){
                                    DefectoEncontradoModel defecto = new DefectoEncontradoModel();
                                    DefectoModel defectoModel = new DefectoModel(denominacion, tipoDefecto);
                                    defecto.setCantidadDer(cantidadDerecha);
                                    defecto.setCantidadIzq(cantidadIzquierda);
                                    defecto.setDefecto(defectoModel);
                                    repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().add(defecto);
                                    System.out.println("se guardo el defecto");
                                }else{
                                    boolean control=false;
                                    for(int k=0;k<repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().size();k++){
                                        if(repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().get(k).getDefecto().getDescripcion().equals(denominacion)){
                                            repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().get(k).setCantidadDer(cantidadDerecha);
                                            repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().get(k).setCantidadIzq(cantidadIzquierda);
                                            k = repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().size();
                                            control  = false;
                                        }else{
                                            control=true;
                                        }
                                    }
                                    if(control){
                                        DefectoEncontradoModel defecto = new DefectoEncontradoModel();
                                        DefectoModel defectoModel = new DefectoModel(denominacion, tipoDefecto);
                                        defecto.setCantidadDer(cantidadDerecha);
                                        defecto.setCantidadIzq(cantidadIzquierda);
                                        defecto.setDefecto(defectoModel);
                                        repo.getLineas().get(i).getOP().getHorarios().get(j).getAnalisis().get(z).getDefectosEncontrados().add(defecto);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            /*if(lt.getNumeroLinea()==numeroLinea){
                for(HorarioTrabajoModel ht : lt.getOP().getHorarios()){
                    if(ht.getDia()==dia && ht.getAño()==año && ht.getMes()==mes && ht.getTurno().getHoraInicio()<=hora &&ht.getTurno().getHoraFin()>=hora){
                        for(AnalisisCalidadModel ac : ht.getAnalisis()){
                            if(ac.getHora()==hora){
                                System.out.println("llegue al ANALISSI CALIDAD");
                                if(ac.getDefectosEncontrados().size()==0){
                                    DefectoEncontradoModel defecto = new DefectoEncontradoModel();
                                    DefectoModel defectoModel = new DefectoModel(denominacion, tipoDefecto);
                                    defecto.setCantidadDer(cantidadDerecha);
                                    defecto.setCantidadIzq(cantidadIzquierda);
                                    defecto.setDefecto(defectoModel);
                                    ac.getDefectosEncontrados().add(defecto);
                                    System.out.println("se guardo el defecto");
                                }else{
                                    for(DefectoEncontradoModel def : ac.getDefectosEncontrados()){
                                        if(def.getDefecto().getDescripcion().equals(denominacion)){

                                            def.setCantidadDer(def.getCantidadDer()+cantidadDerecha);
                                            def.setCantidadIzq(def.getCantidadIzq()+cantidadIzquierda);
                                        }else{
                                            DefectoEncontradoModel defecto = new DefectoEncontradoModel();
                                            DefectoModel defectoModel = new DefectoModel(denominacion, tipoDefecto);
                                            defecto.setCantidadDer(cantidadDerecha);
                                            defecto.setCantidadIzq(cantidadIzquierda);
                                            defecto.setDefecto(defectoModel);
                                            ac.getDefectosEncontrados().add(defecto);
                                            System.out.println("se guardo el defecto");
                                        }
                                    }
                                }
                                
                                /*for(DefectoEncontradoModel def : ac.getDefectosEncontrados()){
                                    System.out.println(def.getDefecto().getDescripcion());
                                    System.out.println(def.getCantidadDer());
                                    System.out.println(def.getCantidadIzq());
                                }*/
                            /*}
                            
                        }
                    }
                }
            }*/
        
        
    }
}
