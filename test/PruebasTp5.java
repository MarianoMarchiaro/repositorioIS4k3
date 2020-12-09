/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controlador.SupervisorLinea.ControladorIniciarOP;
import Modelo.Empleado;
import Modelo.TipoEmpleado;
import Servidor.Adaptador;
import org.junit.Test;
import org.junit.Assert;
import org.junit.*;
/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Mariano
 */
public class PruebasTp5 {
    
    public PruebasTp5() {
    }
    
    
    @Test
    public void comprobarQueElEmpleadoSeaAdministrativo(){
        Empleado empleado = new Empleado(1, "Mariano", "Marchiaro", "asdasd","mariano","123456",TipoEmpleado.ADMINISTRATIVO);
        boolean condicion = empleado.getTipo().equals(TipoEmpleado.ADMINISTRATIVO);
        Assert.assertSame("Son iguales",TipoEmpleado.ADMINISTRATIVO,empleado.getTipo());
    }
    @Test
    public void comprobarQueElEmpleadoSeaSupervisorDeCalidad(){
        Empleado empleado = new Empleado(1, "Mariano", "Marchiaro", "asdasd","mariano","123456",TipoEmpleado.SUPERVISORCALIDAD);
        boolean condicion = empleado.getTipo().equals(TipoEmpleado.SUPERVISORCALIDAD);
        Assert.assertSame("Son iguales",TipoEmpleado.SUPERVISORCALIDAD, empleado.getTipo());
    }
    @Test
    public void comprobarQueElEmpleadoSeaSupervisorDeLinea(){
        Empleado empleado = new Empleado(1, "Mariano", "Marchiaro", "asdasd","mariano","123456",TipoEmpleado.SUPERVISORLINEA);
        boolean condicion = empleado.getTipo().equals(TipoEmpleado.SUPERVISORLINEA);
        Assert.assertSame("Son iguales",TipoEmpleado.SUPERVISORLINEA, empleado.getTipo());
    }
    
    @Test
    public void comprobarQueSeCreaLaOpEnUnTurnoValido(){
        Adaptador adaptador = new Adaptador();
        Assert.assertTrue("Creacion de OP en un turno correcto: =>", adaptador.comprobarHorarioTurno(19));
    }
    
    @Test
    public void comprobarQueElNumeroDeOPNoSeRepite(){
        int numeroAComprobar = 123456; //Se comprueba que no haya una op con ese numero de id
        Adaptador adaptador = new Adaptador();
        Assert.assertTrue("Creacion de OP con un numero que se repite", adaptador.controlarNumeroOP(numeroAComprobar));
    }
    
    @Test
    public void comprobarQueElEmpleadoExisteYTieneCuentaEnElSistema(){
        Adaptador adaptador = new Adaptador();
        Assert.assertTrue(adaptador.comprobarEmpleado("supervisorcalidad", "supervisorcalidad"));
    }
    
    @Test
    public void comprobarQueUnSupervisorDeLineaSoloPuedeTenerUnaSolaLineaALaVez(){
        Adaptador adaptador = new Adaptador();
        Assert.assertFalse(adaptador.comprobarLineas(1));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
