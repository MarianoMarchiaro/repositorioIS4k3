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
public class ControladorModelo {
    private Repositorio repo;

    public ControladorModelo(Repositorio repo) {
        this.repo = repo;
    }
    
    public int contarModelos(){
        int i=0;
        for(ModeloModel modelo : repo.getModelos()){
            i=i+1;
        }
        return i;
    }
    public String obtenerDenominacion(int i){
        return repo.getModelos().get(i).getDenominacion();
    }
    public String obtenerSKU(int i){
        return repo.getModelos().get(i).getSKU();
    }
    public int obtenerObjetivo(int i){
        return repo.getModelos().get(i).getObjetivo();
    }
    public void cambiarDenominacion(String denom,int indice){
        repo.getModelos().get(indice).setDenominacion(denom);        
    }
    public void cambiarSKU(String sku,int indice){
        repo.getModelos().get(indice).setSKU(sku);        
    }
    public void cambiarObjetivo(int denom,int indice){
        repo.getModelos().get(indice).setObjetivo(denom);        
    }
    public void crearModelo(String deno,String sku,int num){
        ModeloModel modelo=new ModeloModel();
        modelo.setDenominacion(deno);
        modelo.setSKU(sku);
        modelo.setObjetivo(num);
        repo.getModelos().add(modelo);
    }
    public void eliminarModelo(int numero){
        repo.getModelos().remove(numero);
    }
}
