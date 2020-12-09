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
public class EmpleadoModel {
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String email;
    private int documento;
    private TipoEmpleadoModel tipo;

    public EmpleadoModel() {
    }
    
    public EmpleadoModel(int documento,String nombre,String apellido,String mail,String usuario,String contraseña,TipoEmpleadoModel tipo){
        this.documento=documento;
        this.nombre=nombre;
        this.apellido=apellido;
        this.email=mail;
        this.usuario=usuario;
        this.contraseña=contraseña;
        this.tipo=tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public TipoEmpleadoModel getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleadoModel tipo) {
        this.tipo = tipo;
    }
    
}
