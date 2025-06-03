/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3pa.control;

import edu.progavud.taller3pa.modelo.Competidor;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase representa un hilo individual que simula el comportamiento
 * de un competidor dentro de la carrera.
 * 
 * Cada instancia de esta clase corre en un hilo separado y controla el estado 
 * de su competidor (como si ha sido accidentado o si ha recibido un impulso).
 * 
 * Implementa la interfaz Runnable para poder ser ejecutada en un hilo.
 * 
 * @author hailen
 */
public class CompetidorThread implements Runnable {

    /**
     * Modelo que representa al competidor controlado por este hilo.
     */
    private Competidor competidorModel;

    /**
     * Referencia al controlador principal de la aplicación.
     * Se utiliza para acceder a la carrera y otros elementos globales.
     */
    private ControlPrincipal cPrinc;

    /**
     * Indica si el competidor ha sufrido un accidente.
     * Se declara como 'volatile' para asegurar la visibilidad entre hilos.
     */
    private volatile boolean isAccidentado = false;

    /**
     * Indica si el competidor ha recibido un impulso.
     * Se declara como 'volatile' para asegurar la visibilidad entre hilos.
     */
    private volatile boolean isImpulsado = false;

    /**
     * Constructor de la clase CompetidorThread.
     * Inicializa el modelo del competidor con los datos recibidos y
     * guarda la referencia al controlador principal.
     * 
     * @param cPrinc Referencia al controlador principal de la aplicación.
     * @param nombre Nombre del competidor.
     * @param velocidad Velocidad asignada al competidor.
     */
    public CompetidorThread(ControlPrincipal cPrinc, String nombre, int velocidad) {
        competidorModel = new Competidor(cPrinc.getcCarrera().getCarrera(), nombre, velocidad);
        this.cPrinc = cPrinc;
    }


    @Override

    /**
 * Método principal que ejecuta el hilo del competidor.
 * 
 * Mientras la carrera no haya finalizado, este hilo:
 * - Inhabilita el botón "Iniciar Carrera" en la interfaz (una sola vez).
 * - Aplica condiciones especiales (como accidentes o impulsos).
 * - Permite que el modelo del competidor avance según su lógica.
 * 
 * Este método se ejecuta automáticamente cuando se inicia el hilo.
 */
    public void run() {

        while (!competidorModel.getCarrera().isEsFinalizada()) {
            cPrinc.getcVentana().inhabilitarBotonIniciarCarrera();

            aplicarCondicionesEspeciales();

            competidorModel.setPosicionActual(competidorModel.getPosicionActual() + competidorModel.getVelocidad());  //Aumentar de posicion de acuerdo al valor de velocidad

            cPrinc.getcVentana().moverLabels(competidorModel.getNombre());

            try { //Correr con algunos descansos intermedios
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);

            }

            cPrinc.finalizarCarrera();

        }
        long tiempoFin = System.currentTimeMillis();
    }

    /**
 * Devuelve el modelo del competidor asociado a este hilo.
 * 
 * @return Objeto Competidor que representa al competidor actual.
 */
public Competidor getCompetidorModel() {
    return competidorModel;
}

/**
 * Establece un nuevo modelo de competidor para este hilo.
 * 
 * @param competidorModel Objeto Competidor que se asignará al hilo.
 */
public void setCompetidorModel(Competidor competidorModel) {
    this.competidorModel = competidorModel;
}


    /**
 * Aplica condiciones especiales al competidor si se encuentra en estado
 * de accidente o si ha recibido un impulso.
 * 
 * - Si está accidentado, se muestra un mensaje en pantalla y el hilo
 *   se detiene por un tiempo simulado de recuperación.
 * - Si está impulsado, se aumenta la posición actual según el valor del impulso.
 */
private void aplicarCondicionesEspeciales() {
    if (isAccidentado) {
        try {
            // Muestra la etiqueta de accidente en la interfaz a través del controlPrincipal
            cPrinc.getcVentana().getvCarrera().getLblAccidente().setVisible(true);

            // Pausa el hilo entre 2 y 3 segundos para simular el accidente
            Thread.sleep(2000 + new Random().nextInt(1000));

            // Oculta la etiqueta de accidente a través del controlPrincipal
            cPrinc.getcVentana().getvCarrera().getLblAccidente().setVisible(false);

        } catch (InterruptedException ex) {
            Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se desactiva el estado de accidente
        isAccidentado = false;
    }

    if (isImpulsado) {
        // Aumenta la posición del competidor según su impulso
        competidorModel.setPosicionActual(
            competidorModel.getPosicionActual() + competidorModel.getImpulso()
        );

        // Se desactiva el estado de impulso 
        isImpulsado = false;
    }
}


    /**
 * Indica si el competidor está actualmente accidentado.
 * 
 * @return true si el competidor ha sufrido un accidente, false en caso contrario.
 */
public boolean isIsAccidentado() {
    return isAccidentado;
}

/**
 * Establece el estado de accidente del competidor.
 * 
 * @param isAccidentado true si el competidor debe considerarse accidentado.
 */
public void setIsAccidentado(boolean isAccidentado) {
    this.isAccidentado = isAccidentado;
}

/**
 * Indica si el competidor está actualmente impulsado.
 * 
 * @return true si el competidor ha recibido un impulso, false en caso contrario.
 */
public boolean isIsImpulsado() {
    return isImpulsado;
}

/**
 * Establece el estado de impulso del competidor.
 * 
 * @param isImpulsado true si el competidor debe recibir un impulso.
 */
public void setIsImpulsado(boolean isImpulsado) {
    this.isImpulsado = isImpulsado;
}


}
