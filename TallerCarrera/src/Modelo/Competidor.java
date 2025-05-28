/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailen
 */
public class Competidor extends Thread {

    private String nombre;
    private int posicionActual = 0;
    private int cantidadVictorias;
    private int velocidad;
    private long tiempoLlegada;
    
    private int impulso;
    private boolean isAccidentado;
    private Carrera carrera;

    public Competidor(String nombre,Carrera carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public void incrementarVictorias() {
        this.cantidadVictorias++;
    }

    @Override
     public void run() {
        long tiempoInicio = System.currentTimeMillis();

        while (!carrera.isEsFinalizada() && posicionActual < carrera.getDistanciaCarrera()) {
            try {
                if (isAccidentado) {
                    Thread.sleep(1000 + (int)(Math.random() * 1000));
                    isAccidentado = false;
                }

                int avance = 1 + (int)(Math.random() * 5);
                if (impulso > 0) {
                    avance += impulso;
                    impulso = 0;
                }

                avanzar(avance);
                //carrera.getControlCarrera().actualizarVista();  // si está delegado así

                if (posicionActual >= carrera.getDistanciaCarrera()) {
                    long tiempoFin = System.currentTimeMillis();
                    carrera.notificarLlegada(this);
                    break;
                }

                Thread.sleep(100 + (int)(Math.random() * 300));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void avanzar(int pasos) {
        posicionActual += pasos;
    }

    public void simularAccidente() {
        isAccidentado = true;
    }

    public void aplicarImpulso(int pasos) {
        impulso += pasos;
    }
     

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public int getCantidadVictorias() {
        return cantidadVictorias;
    }

    public void setCantidadVictorias(int cantidadVictorias) {
        this.cantidadVictorias = cantidadVictorias;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public long getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(long tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public boolean isIsAccidentado() {
        return isAccidentado;
    }

    public void setIsAccidentado(boolean isAccidentado) {
        this.isAccidentado = isAccidentado;
    }
    

}
