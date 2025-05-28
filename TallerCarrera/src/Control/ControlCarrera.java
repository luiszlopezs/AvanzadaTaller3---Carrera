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
        carrera.setTiempoInicial(System.currentTimeMillis());
        
    }

    public Carrera getCarrera() {
        return carrera;
    }
    
    public void finalizarCarrera(){
        carrera.isEsFinalizada();
    }
    
    public ArrayList<Competidor> determinarGanadores(){ //Se recibe la lista de competidores, y si el tiempo de llegada del competidor coincide con el tiempo de la carrera, entonces este se añadirá a la lista de ganadores
        ArrayList<Competidor> competidores = cPrinc.getcCompetidores().getCompetidores();
        ArrayList<Competidor> ganadores = null;
        for(Competidor c : competidores){
            if(c.getTiempoLlegada() == carrera.getDuracionCarrera()){
                
                ganadores.add(c); //Añadir al competidor a la lista de ganadores
                c.incrementarVictorias(); //Cada ganador incrementa su cantidad de victorias, para contabilizar el número de carreras ganadas al final de la ejecución
                
            }
        }return ganadores;
        
    }
    
}
