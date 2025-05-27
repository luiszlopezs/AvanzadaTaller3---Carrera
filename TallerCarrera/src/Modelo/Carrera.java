/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author hailen
 */
public class Carrera {
    
    private long tiempoInicial;
    private long tiempoFinal;
    private boolean esFinalizada = false;

    public Carrera(long tiempoInicial, long tiempoFinal, boolean esFinalizada) {
        this.tiempoInicial = tiempoInicial;
        this.tiempoFinal = tiempoFinal;
        this.esFinalizada = esFinalizada;
    }
    
    

    public Carrera() {
    }
    
    
    public long asignarTiempo(){
        return this.tiempoFinal - this.tiempoInicial;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public long getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(long tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public boolean isEsFinalizada() {
        return esFinalizada;
    }

    public void setEsFinalizada(boolean esFinalizada) {
        this.esFinalizada = esFinalizada;
    }
    
    
    
}
