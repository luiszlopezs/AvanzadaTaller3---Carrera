/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase que representa los datos de una carrera.
 * 
 * Contiene información relacionada con el estado de la carrera, como el tiempo
 * de inicio, duración y si ha finalizado o no. Esta clase es utilizada por los
 * controladores y los competidores para coordinar el progreso y resultados.
 * 
 * @author hailen
 */
public class Carrera {

    /**
     * Momento en que inicia la carrera, representado como tiempo en milisegundos desde la época.
     */
    private long tiempoInicial;

    /**
     * Duración total de la carrera en milisegundos.
     */
    private int duracionCarrera;

    /**
     * Indica si la carrera ha finalizado.
     */
    private boolean esFinalizada = false;

    /**
     * Distancia fija que debe recorrer cada competidor para completar la carrera.
     * Es un valor compartido por todas las instancias de Carrera.
     */
    private static int distanciaCarrera = 720;

    /**
     * Constructor con parámetros que permite crear una carrera con datos iniciales definidos.
     * 
     * @param tiempoInicial Tiempo de inicio de la carrera (en milisegundos).
     * @param duracionCarrera Duración de la carrera (en milisegundos).
     * @param esFinalizada Estado inicial de la carrera (true si ya está finalizada).
     */
    public Carrera(long tiempoInicial, int duracionCarrera, boolean esFinalizada) {
        this.tiempoInicial = tiempoInicial;
        this.duracionCarrera = duracionCarrera;
        this.esFinalizada = esFinalizada;
    }

    
    

        /**
     * Constructor por defecto de la clase Carrera.
     * 
     * Crea una instancia vacía con los valores por defecto.
     */
    public Carrera() {  
    }

    /**
     * Calcula el tiempo transcurrido entre el inicio de la carrera y su duración.
     * 
     * Nota: Este método asume que `duracionCarrera` representa un tiempo absoluto.
     * Si se utiliza para restar el tiempo inicial, devuelve el tiempo transcurrido.
     * 
     * @return Diferencia entre duración y tiempo inicial.
     */
    public long asignarTiempo() {
        return this.duracionCarrera - this.tiempoInicial;
    }

    /**
     * Devuelve el tiempo en que inició la carrera.
     * 
     * @return Tiempo inicial en milisegundos.
     */
    public long getTiempoInicial() {
        return tiempoInicial;
    }

    /**
     * Establece el tiempo en que inició la carrera.
     * 
     * @param tiempoInicial Tiempo en milisegundos desde el inicio de la carrera.
     */
    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }


    /**
 * Devuelve la duración total de la carrera.
 * 
 * @return Duración de la carrera en milisegundos.
 */
public int getDuracionCarrera() {
    return duracionCarrera;
}

/**
 * Establece la duración total de la carrera.
 * 
 * @param duracionCarrera Duración en milisegundos.
 */
public void setDuracionCarrera(int duracionCarrera) {
    this.duracionCarrera = duracionCarrera;
}

/**
 * Indica si la carrera ha finalizado.
 * 
 * @return true si la carrera ha terminado, false en caso contrario.
 */
public boolean isEsFinalizada() {
    return esFinalizada;
}

/**
 * Establece si la carrera ha finalizado.
 * 
 * @param esFinalizada true para marcar la carrera como finalizada.
 */
public void setEsFinalizada(boolean esFinalizada) {
    this.esFinalizada = esFinalizada;
}

/**
 * Devuelve la distancia total que deben recorrer los competidores.
 * 
 * @return Distancia de la carrera en unidades arbitrarias.
 */
public static int getDistanciaCarrera() {
    return distanciaCarrera;
}

/**
 * Establece la distancia que deben recorrer los competidores para completar la carrera.
 * 
 * @param distanciaCarrera Nueva distancia a recorrer.
 */
public static void setDistanciaCarrera(int distanciaCarrera) {
    Carrera.distanciaCarrera = distanciaCarrera;
}

    

    
    
    
}
