/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;
import Modelo.Competidor;
import java.util.ArrayList;

/**
 * Clase que actúa como controlador de la lógica principal de la carrera.
 * 
 * Se encarga de gestionar la carrera, controlar a los competidores y 
 * mantener una lista de los hilos que participan.
 * 
 * @author hailen
 */
public class ControlCarrera {

    /**
     * Referencia al controlador principal de la aplicación.
     * Permite la comunicación con otras partes del sistema.
     */
    private ControlPrincipal cPrinc;

    /**
     * Objeto que representa la carrera actual.
     */
    private Carrera carrera;

    /**
     * Constructor de la clase ControlCarrera.
     * Inicializa una nueva carrera y guarda la referencia al controlador principal.
     * 
     * @param cPrinc Referencia al controlador principal.
     */
    public ControlCarrera(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;
        carrera = new Carrera();
        // carrera.setTiempoInicial(System.currentTimeMillis()); // Línea comentada, posiblemente usada para registrar tiempo inicial
    }

    /**
     * Devuelve el objeto Carrera asociado a este controlador.
     * 
     * @return Objeto Carrera que representa la carrera actual.
     */
    public Carrera getCarrera() {
        return carrera;
    }

    

    
    /**
 * Determina los ganadores de la carrera a partir de la lista de competidores.
 * 
 * Recorre cada hilo de competidor y compara su tiempo de llegada con la duración total de la carrera.
 * Si coinciden, se considera que ese competidor llegó en primer lugar.
 * 
 * Además, incrementa el contador de victorias de cada ganador.
 * 
 * @param ListaThread Lista de hilos de competidores que participaron en la carrera.
 * @return Una lista de CompetidorThread que resultaron ganadores.
 */
public ArrayList<CompetidorThread> determinarGanadores(ArrayList<CompetidorThread> ListaThread) {
    ArrayList<CompetidorThread> ganadores = new ArrayList<>();

    for (CompetidorThread c : ListaThread) {
        // Verifica si el tiempo de llegada del competidor coincide con la duración total de la carrera
        if (c.getCompetidorModel().getTiempoLlegada() == carrera.getDuracionCarrera()) {
            
            // Se agrega a la lista de ganadores
            ganadores.add(c);
            
            // Se incrementa el número de victorias del competidor
            c.getCompetidorModel().incrementarVictorias();

            // Mensaje en consola para depuración
            System.out.println("Victorias: " + c.getCompetidorModel().getCantidadVictorias());
        }
    }

    return ganadores;
}

    
    
        /**
 * Finaliza la carrera si alguno de los competidores ha llegado a la meta.
 * 
 * Este método está sincronizado para evitar condiciones de carrera entre hilos,
 * asegurando que solo un competidor pueda marcar el final de la carrera.
 * 
 * Recorre la lista de competidores y, si alguno alcanza o supera la distancia
 * total de la carrera y esta aún no ha sido marcada como finalizada, se registran:
 * - El tiempo de llegada del competidor.
 * - La duración total de la carrera.
 * - Se establece que la carrera ha terminado.
 * 
 * @param ListaThread Lista de hilos de competidores que están corriendo la carrera.
 */
public synchronized void terminarCarrera(ArrayList<CompetidorThread> ListaThread) {
    for (CompetidorThread c : ListaThread) {

        // Verifica si el competidor llegó a la meta y la carrera no ha sido finalizada aún
        if (c.getCompetidorModel().getPosicionActual() >= Carrera.getDistanciaCarrera()
                && !c.getCompetidorModel().getCarrera().isEsFinalizada()) {

            // Calcula el tiempo de llegada en milisegundos
            int tiempoLlegada = (int) (System.currentTimeMillis()
                    - c.getCompetidorModel().getCarrera().getTiempoInicial());

            // Registra el tiempo de llegada del competidor
            c.getCompetidorModel().setTiempoLlegada(tiempoLlegada);

            // Establece la duración total de la carrera
            c.getCompetidorModel().getCarrera().setDuracionCarrera(tiempoLlegada);

            // Marca la carrera como finalizada
            c.getCompetidorModel().getCarrera().setEsFinalizada(true);

            // Mensajes en consola para depuración
            System.out.println("Se terminó la carrera");
            System.out.println("¡" + c.getCompetidorModel().getNombre() + " ganó la carrera!");
            System.out.println("Carrera tiempo inicial: " + cPrinc.getcCarrera().getCarrera().getTiempoInicial());
            System.out.println("Duración de la carrera: " + tiempoLlegada + " ms");
        }
    }
}

    
}
