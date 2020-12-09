/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigotp1.cliente.servidor;

import Controlador.IniciarSesion.ControladorIniciarSesion;
import Servidor.Adaptador;

/**
 *
 * @author Mariano
 */
public class CodigoTP1ClienteServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Adaptador adaptador = new Adaptador();
        ControladorIniciarSesion controlador = new ControladorIniciarSesion(adaptador);
    }
    
}
