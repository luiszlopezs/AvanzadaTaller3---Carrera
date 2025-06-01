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
 * Clase que representa a un competidor en la carrera.
 * 
 * Cada objeto de esta clase hereda de `Thread`, lo que permite ejecutar
 * su comportamiento en paralelo con otros competidores. Contiene información 
 * como nombre, velocidad, posición, tiempo de llegada y número de victorias.
 * 
 * Su lógica de movimiento y comportamiento puede incluir impulsos, accidentes
 * u otras condiciones especiales controladas externamente.
 * 
 * @author hailen
 */
public class Competidor extends Thread {

    /**
     * Nombre del competidor.
     */
    private String nombre;

    /**
     * Posición actual del competidor en la carrera.
     */
    private int posicionActual = 0;

    /**
     * Número de victorias que ha obtenido este competidor.
     */
    private int cantidadVictorias;

    /**
     * Velocidad a la que se desplaza el competidor.
     */
    private int velocidad;

    /**
     * Tiempo que tardó en llegar a la meta (en milisegundos).
     */
    private int tiempoLlegada;

    /**
     * Carrera en la que participa este competidor.
     */
    private Carrera carrera;

    /**
     * Valor del impulso que puede recibir el competidor (avance adicional).
     */
    private int impulso = 50;

    /**
     * Constructor de la clase Competidor.
     * 
     * @param carrera Objeto Carrera al que pertenece el competidor.
     * @param nombre Nombre del competidor.
     * @param velocidad Velocidad inicial asignada al competidor.
     */
    public Competidor(Carrera carrera, String nombre, int velocidad) {
        this.carrera = carrera;
        this.nombre = nombre;
        this.velocidad = velocidad;
    }


    /**
 * Incrementa en uno la cantidad de victorias del competidor.
 * 
 * Se utiliza cada vez que el competidor gana una carrera.
 */
public void incrementarVictorias() {
    this.cantidadVictorias++;
}

/**
 * Devuelve el nombre del competidor.
 * 
 * @return Nombre del competidor.
 */
public String getNombre() {
    return nombre;
}

/**
 * Establece el nombre del competidor.
 * 
 * @param nombre Nuevo nombre del competidor.
 */
public void setNombre(String nombre) {
    this.nombre = nombre;
}

/**
 * Devuelve la posición actual del competidor en la carrera.
 * 
 * @return Valor entero de la posición actual.
 */
public int getPosicionActual() {
    return posicionActual;
}


    /**
 * Establece la posición actual del competidor en la carrera.
 * 
 * @param posicionActual Nueva posición del competidor.
 */
public void setPosicionActual(int posicionActual) {
    this.posicionActual = posicionActual;
}

/**
 * Devuelve la cantidad de carreras ganadas por el competidor.
 * 
 * @return Número de victorias acumuladas.
 */
public int getCantidadVictorias() {
    return cantidadVictorias;
}

/**
 * Establece la cantidad de victorias del competidor.
 * 
 * @param cantidadVictorias Número de victorias a asignar.
 */
public void setCantidadVictorias(int cantidadVictorias) {
    this.cantidadVictorias = cantidadVictorias;
}

/**
 * Devuelve la velocidad actual del competidor.
 * 
 * @return Valor entero que representa la velocidad.
 */
public int getVelocidad() {
    return velocidad;
}


    /**
 * Establece la velocidad del competidor.
 * 
 * @param velocidad Nueva velocidad a asignar.
 */
public void setVelocidad(int velocidad) {
    this.velocidad = velocidad;
}

/**
 * Devuelve el tiempo que tardó el competidor en llegar a la meta.
 * 
 * @return Tiempo de llegada en milisegundos.
 */
public int getTiempoLlegada() {
    return tiempoLlegada;
}

/**
 * Establece el tiempo de llegada del competidor.
 * 
 * @param tiempoLlegada Tiempo en milisegundos que tardó en completar la carrera.
 */
public void setTiempoLlegada(int tiempoLlegada) {
    this.tiempoLlegada = tiempoLlegada;
}

/**
 * Devuelve el objeto `Carrera` al que pertenece este competidor.
 * 
 * @return Objeto Carrera asociado.
 */
public Carrera getCarrera() {
    return carrera;
}


    /**
 * Establece la carrera a la que pertenece el competidor.
 * 
 * @param carrera Objeto Carrera que se asignará al competidor.
 */
public void setCarrera(Carrera carrera) {
    this.carrera = carrera;
}

/**
 * Devuelve el valor actual del impulso del competidor.
 * 
 * El impulso representa un avance adicional que puede recibir durante la carrera.
 * 
 * @return Valor entero del impulso.
 */
public int getImpulso() {
    return impulso;
}

/**
 * Establece el valor del impulso para el competidor.
 * 
 * @param impulso Valor del avance adicional que se le asignará.
 */
public void setImpulso(int impulso) {
    this.impulso = impulso;
}

    
    



    

}
