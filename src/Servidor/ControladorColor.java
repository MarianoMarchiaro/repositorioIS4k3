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
public class ControladorColor {
    private Repositorio repo;

    public ControladorColor(Repositorio repo) {
        this.repo = repo;
    }
    public int contarColores(){
        int numero=0;
        for(ColorModel c : repo.getColores()){
            numero=numero+1;
        }
        return numero;
    }
    public int getCodigo(int numero){
        return repo.getColores().get(numero).getCodigoColor();
    }
    public String getDenominacion(int numero){
        return repo.getColores().get(numero).getDenominacion();
    }
    public void eliminarColor(int numero){
        repo.getColores().remove(numero);
    }
    public boolean controlarSiExisteColor(int numero,String nombre){
        for(ColorModel c: repo.getColores()){
            if(c.getCodigoColor()==numero ||c.getDenominacion().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    public void crearColor(int numero,String nombre){
        repo.getColores().add(new ColorModel(nombre,numero));
    }
    public void cambiarCodigoColor(int indice,int codigo){
        repo.getColores().get(indice).setCodigoColor(codigo);
    }
    public void cambiarNombreColor(int indice,String nombre){
        repo.getColores().get(indice).setDenominacion(nombre);
    }
}
