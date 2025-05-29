/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author hailen
 */
public class  Carrera {
    
    private long tiempoInicial;
    private int duracionCarrera;
    private boolean esFinalizada = false;
    private static int distanciaCarrera = 720;

    public Carrera(long tiempoInicial, int duracionCarrera, boolean esFinalizada) {
        this.tiempoInicial = tiempoInicial;
        this.duracionCarrera = duracionCarrera;
        this.esFinalizada = esFinalizada;
    }
    
    

    public Carrera() {  
    }
    
    
    public long asignarTiempo(){
        return this.duracionCarrera - this.tiempoInicial;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    public void setDuracionCarrera(int duracionCarrera) {
        this.duracionCarrera = duracionCarrera;
    }

    public boolean isEsFinalizada() {
        return esFinalizada;
    }

    public void setEsFinalizada(boolean esFinalizada) {
        this.esFinalizada = esFinalizada;
    }

    public static int getDistanciaCarrera() {
        return distanciaCarrera;
    }

    public static void setDistanciaCarrera(int distanciaCarrera) {
        Carrera.distanciaCarrera = distanciaCarrera;
    }
    
    

    
    
    
}
