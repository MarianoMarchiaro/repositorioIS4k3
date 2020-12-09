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
public class Repositorio {
    private ArrayList<ModeloModel> modelos;
    private ArrayList<ColorModel> colores;
    private ArrayList<LineaTrabajoModel> lineas;
    private ArrayList<OrdenProduccionModel> ordenes;
    private ArrayList<EmpleadoModel> empleados;
    private ArrayList<DefectoEncontradoModel> listaDefectosEncontrados;
    private ArrayList<DefectoModel> listaDefectos;
    private ArrayList<TurnoModel> turnos;
    
    public Repositorio(){
        modelos = new ArrayList<ModeloModel>();
        colores = new ArrayList<ColorModel>();
        lineas = new ArrayList<LineaTrabajoModel>();
        ordenes = new ArrayList<OrdenProduccionModel>();
        empleados = new ArrayList<EmpleadoModel>();
        listaDefectosEncontrados = new ArrayList<DefectoEncontradoModel>();
        listaDefectos = new ArrayList<DefectoModel>();
        turnos = new ArrayList<TurnoModel>();
        crearEmpleados();
        crearLineas();
        crearTurnos();
        crearModelo();
        crearColores();
        crearDefectos();
    }

    //----------------------------------------------------------------------------------------------------------------------
    //PARTE SERVIDOR
    public ArrayList<EmpleadoModel> getEmpleados() {
        return empleados;
    }
    public ArrayList<ModeloModel> getModelos(){
        return modelos;
    }
    public ArrayList<ColorModel> getColores(){
        return colores;
    }
    public ArrayList<LineaTrabajoModel> getLineas(){
        return lineas;
    }
    public ArrayList<TurnoModel> getTurnos(){
        return turnos;
    }
    public ArrayList<OrdenProduccionModel> getOrdenes(){
        return ordenes;
    }
    public void setOrden(OrdenProduccionModel op){
        ordenes.add(op);
    }
    public ArrayList<DefectoModel> getDefectos(){
        return listaDefectos;
    }
    //----------------------------------------------------------------------------------------------------------------------
   
    
    public void cargarDefectos(){
        
    }

    void cargarModelo(ModeloModel modelo) {
        modelos.add(modelo);
    }
    
    
    
    public void crearLineas(){
        lineas.add(new LineaTrabajoModel(1));
        lineas.add(new LineaTrabajoModel(2));
        lineas.add(new LineaTrabajoModel(3));
        lineas.add(new LineaTrabajoModel(4));
        lineas.add(new LineaTrabajoModel(5));
    }
    public void crearEmpleados(){
        empleados.add(new EmpleadoModel(1,"Mariano","Mendez","dasdasd","supervisorcalidad","supervisorcalidad",TipoEmpleadoModel.SUPERVISORCALIDAD));
        empleados.add(new EmpleadoModel(2,"Martin","Juarez","dasdasd","administrativo","administrativo",TipoEmpleadoModel.ADMINISTRATIVO));
        empleados.add(new EmpleadoModel(3,"Luciano","Gonzales","dasdasd","supervisorlinea","supervisorlinea",TipoEmpleadoModel.SUPERVISORLINEA));
    }
    public void crearTurnos(){
        turnos.add(new TurnoModel(0,14,"MATUTINO"));
        turnos.add(new TurnoModel(15,23,"TARDE"));
    }
    public void crearModelo(){
        modelos.add(new ModeloModel("denominacion1","sku1",123));
        modelos.add(new ModeloModel("denominacion2","sku2",121));
        modelos.add(new ModeloModel("denominacion3","sku3",122));
        modelos.add(new ModeloModel("denominacion4","sku4",124));
    }
    public void crearColores(){
        colores.add(new ColorModel("color1",1));
        colores.add(new ColorModel("color2",2));
        colores.add(new ColorModel("color3",3));
        colores.add(new ColorModel("color4",4));
        colores.add(new ColorModel("color5",5));
    }
    
    public void crearOrdenes(){
        
    }
    public void crearDefectos(){
        listaDefectos.add(new DefectoModel("Defecto1",TipoDefectoModel.OBSERVADO));
        listaDefectos.add(new DefectoModel("Defecto2",TipoDefectoModel.OBSERVADO));
        listaDefectos.add(new DefectoModel("Defecto3",TipoDefectoModel.OBSERVADO));
        listaDefectos.add(new DefectoModel("Defecto4",TipoDefectoModel.OBSERVADO));
        listaDefectos.add(new DefectoModel("Defecto5",TipoDefectoModel.REPROCESO));
        listaDefectos.add(new DefectoModel("Defecto6",TipoDefectoModel.REPROCESO));
        listaDefectos.add(new DefectoModel("Defecto7",TipoDefectoModel.REPROCESO));
        listaDefectos.add(new DefectoModel("Defecto8",TipoDefectoModel.REPROCESO));
        listaDefectos.add(new DefectoModel("Defecto9",TipoDefectoModel.OBSERVADO));
    }
    
    
}
