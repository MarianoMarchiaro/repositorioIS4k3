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
public class ControladorIngreso {
    private Repositorio repo;

    public ControladorIngreso(Repositorio repo) {
        this.repo = repo;
    }
    public boolean comprobarEmpleado(String usuario,String contraseña){
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getUsuario().equals(usuario) && emp.getContraseña().equals(contraseña)){
                return true;
            }
        }
        return false;
    }
    public String buscarApellidoEmpleado(String usuario,String contraseña){
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getUsuario().equals(usuario) && emp.getContraseña().equals(contraseña))
                return emp.getApellido();
        }
        return "";
    }
    public String buscarNombreEmpleado(String usuario,String contraseña){
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getUsuario().equals(usuario) && emp.getContraseña().equals(contraseña))
                return emp.getNombre();
        }
        return "";
    }
    public int buscarDocumentoEmpleado(String usuario,String contraseña){
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getUsuario().equals(usuario) && emp.getContraseña().equals(contraseña))
                return emp.getDocumento();
        }
        return 0;
    }
    public String buscarTipoEmpleado(String usuario,String contraseña){
        for(EmpleadoModel emp : repo.getEmpleados()){
            if(emp.getUsuario().equals(usuario) && emp.getContraseña().equals(contraseña))
                return emp.getTipo().toString();
        }
        return "";
    }
}
