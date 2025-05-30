/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;
import Modelo.Competidor;
import java.util.ArrayList;

/**
 *
 * @author hailen
 */
public class ControlCarrera {
    
    
    
    private ControlPrincipal cPrinc;
    private Carrera carrera;
    
    
    public ControlCarrera(ControlPrincipal cPrinc){
        this.cPrinc = cPrinc;
        carrera = new Carrera();
        //carrera.setTiempoInicial(System.currentTimeMillis());
        
    }

    public Carrera getCarrera() {
        return carrera;
    }
    

    
    public ArrayList<CompetidorThread> determinarGanadores(ArrayList<CompetidorThread> ListaThread){ //Se recibe la lista de competidores, y si el tiempo de llegada del competidor coincide con el tiempo de la carrera, entonces este se añadirá a la lista de ganadores
        ArrayList<CompetidorThread> ganadores = new ArrayList<>();
        for(CompetidorThread c : ListaThread){
            if(c.getCompetidorModel().getTiempoLlegada() == carrera.getDuracionCarrera()){
                
                ganadores.add(c); //Añadir al competidor a la lista de ganadores
                c.getCompetidorModel().incrementarVictorias(); //Cada ganador incrementa su cantidad de victorias, para contabilizar el número de carreras ganadas al final de la ejecución
                
            }
        }return ganadores;
        
    }
    
    
        public synchronized void terminarCarrera(ArrayList<CompetidorThread> ListaThread) {
        for (CompetidorThread c : ListaThread) {
            if (c.getCompetidorModel().getPosicionActual() >= Carrera.getDistanciaCarrera() && !c.getCompetidorModel().getCarrera().isEsFinalizada()) {

                int tiempoLlegada = (int) (System.currentTimeMillis() - c.getCompetidorModel().getCarrera().getTiempoInicial());
                c.getCompetidorModel().setTiempoLlegada(tiempoLlegada);

                c.getCompetidorModel().getCarrera().setDuracionCarrera(tiempoLlegada);
                c.getCompetidorModel().getCarrera().setEsFinalizada(true);

                System.out.println("¡" + c.getCompetidorModel().getNombre() + " ganó la carrera!");
                System.out.println("Carrera tiempo inicial: " + cPrinc.getcCarrera().getCarrera().getTiempoInicial());
                System.out.println("Duración de la carrera: " + tiempoLlegada + " ms");
            }
        }
    }
    
}
