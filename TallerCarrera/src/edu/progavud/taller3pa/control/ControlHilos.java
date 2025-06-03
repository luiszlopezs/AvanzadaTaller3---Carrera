/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3pa.control;

import edu.progavud.taller3pa.modelo.Competidor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de controlar la ejecución y manipulación de los hilos
 * que representan a los competidores en la carrera.
 * 
 * Permite iniciar los hilos, aplicar condiciones especiales como accidentes
 * o impulsos, y coordinar acciones entre los distintos competidores.
 * 
 * @author hailen
 */
public class ControlHilos {

    /**
     * Referencia al controlador principal de la aplicación.
     * Se utiliza para acceder a la lógica general y a otras vistas o controladores.
     */
    private ControlPrincipal cPrinc;

    /**
     * Lista de hilos que representan a los competidores en la carrera.
     * Cada CompetidorThread maneja individualmente a un participante.
     */
    private ArrayList<CompetidorThread> competidoresThread;

    /**
     * Constructor de la clase ControlHilos.
     * Inicializa la lista de hilos de competidores y crea cuatro competidores
     * con nombres y velocidades predefinidas. Luego, los agrega a la lista.
     * 
     * @param cPrinc Referencia al controlador principal de la aplicación.
     */
    public ControlHilos(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;
        competidoresThread = new ArrayList<>();

        // Crear instancias de competidores con nombre y velocidad
        CompetidorThread competidor1 = new CompetidorThread(cPrinc, "NyanCat", 20);
        CompetidorThread competidor2 = new CompetidorThread(cPrinc, "Sonic", 20);
        CompetidorThread competidor3 = new CompetidorThread(cPrinc, "Goku", 20);
        CompetidorThread competidor4 = new CompetidorThread(cPrinc, "Pikachu", 20);

        // Agregar competidores a la lista
        competidoresThread.add(competidor1);
        competidoresThread.add(competidor2);
        competidoresThread.add(competidor3);
        competidoresThread.add(competidor4);
    }


    /**
 * Inicia la ejecución de los hilos de los competidores y espera a que todos finalicen.
 * 
 * - Crea un nuevo hilo (`Thread`) para cada competidor registrado en la lista `competidoresThread`.
 * - Inicia cada hilo mediante `start()`.
 * - Usa `join()` para esperar a que todos los hilos terminen antes de continuar.
 * 
 * Esto asegura que la lógica de carrera se complete antes de pasar al siguiente paso
 * en el flujo del programa.
 */
public void iniciarHilos() {
    ArrayList<Thread> listaHilos = new ArrayList<>();

    // Crear y arrancar cada hilo correspondiente a un competidor
    for (CompetidorThread hilo : competidoresThread) {
        Thread t1 = new Thread(hilo);
        listaHilos.add(t1);
        t1.start();
    }

    // Esperar a que todos los hilos finalicen
    try {
        listaHilos.get(0).join();
        listaHilos.get(1).join();
        listaHilos.get(2).join();
        listaHilos.get(3).join();
    } catch (InterruptedException ex) {
        Logger.getLogger(ControlHilos.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    /**
 * Aplica un accidente al competidor cuyo nombre coincide con el proporcionado.
 * 
 * Recorre la lista de competidores y, si encuentra uno con el nombre indicado,
 * cambia su estado a "accidentado", lo cual afectará su comportamiento en la carrera.
 * 
 * @param nombre Nombre del competidor al que se le aplicará el accidente.
 */
public void aplicarAccidente(String nombre) {
    for (CompetidorThread c : competidoresThread) {
       
        // Si el nombre coincide, se marca al competidor como accidentado
        if (c.getCompetidorModel().getNombre().equals(nombre)) {
            c.setIsAccidentado(true);
        }
    }
}

    /**
 * Aplica un impulso al competidor cuyo nombre coincide con el proporcionado.
 * 
 * Recorre la lista de competidores y, si encuentra uno con el nombre indicado,
 * cambia su estado a "impulsado", lo cual provocará que avance más rápido en su próximo turno.
 * 
 * @param nombre Nombre del competidor al que se le aplicará el impulso.
 */
public void impulsar(String nombre) {
    for (CompetidorThread c : competidoresThread) {
        // Verifica si el nombre del competidor coincide con el indicado
        if (c.getCompetidorModel().getNombre().equals(nombre)) {
            c.setIsImpulsado(true);
        }
    }
}



    /**
 * Devuelve la lista de hilos que representan a los competidores.
 * 
 * @return Lista de objetos CompetidorThread.
 */
public ArrayList<CompetidorThread> getCompetidoresThread() {
    return competidoresThread;
}

/**
 * Establece una nueva lista de hilos de competidores.
 * 
 * @param competidoresThread Lista de CompetidorThread que se asignará.
 */
public void setCompetidoresThread(ArrayList<CompetidorThread> competidoresThread) {
    this.competidoresThread = competidoresThread;
}

    
    
    

}
