/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Control.ControlPrincipal;
import java.util.Random;
import java.util.Scanner;
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

    private int tiempoLlegada;
    private Carrera carrera;


    private int impulso = 50;
   

    public Competidor(Carrera carrera, String nombre, int velocidad) {
        this.carrera = carrera;
        this.nombre = nombre;
        this.velocidad = velocidad;
        
    }

    public void incrementarVictorias() {
        this.cantidadVictorias++;
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

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }



    public int getImpulso() {
        return impulso;
    }

    public void setImpulso(int impulso) {
        this.impulso = impulso;
    }
    
    



    

}
