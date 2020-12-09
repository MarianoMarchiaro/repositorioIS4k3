/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mariano
 */
public class Administrativo extends Empleado{
    
    
    public Administrativo(int documento, String nombre, String apellido, String email, String usuario, String contraseña, TipoEmpleado tipo) {
        super(documento, nombre, apellido, email, usuario, contraseña, tipo);
    }

    
    
}
