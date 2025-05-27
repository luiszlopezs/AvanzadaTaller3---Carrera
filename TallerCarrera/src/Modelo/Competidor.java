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
    private Carrera carrera;

    public Competidor(String nombre, Carrera carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public void incrementarVictorias() {
        this.cantidadVictorias++;
    }

    @Override
    public void run() {
        while (!carrera.isEsFinalizada()) {
            posicionActual += velocidad;
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
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

}
