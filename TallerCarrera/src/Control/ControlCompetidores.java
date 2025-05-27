/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Competidor;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author sangr
 */
public class ControlCompetidores {
    private ArrayList<Competidor> competidores;

    public ControlCompetidores() {
        competidores = new ArrayList<>();
        
        Competidor competidor1 = new Competidor("Usain Bolt"); 
        Competidor competidor2 = new Competidor("Periquin"); 
        Competidor competidor3 = new Competidor("X");
        Competidor competidor4 = new Competidor("Y"); 
        
        competidores.add(competidor1);
        competidores.add(competidor2);
        competidores.add(competidor3);
        competidores.add(competidor4);
    }
    
    public void reiniciarPosiciones(){
        for(Competidor competidor: competidores){
            competidor.interrupt(); //Lo frena si estaba en ejecucion
            competidor.setPosicionActual(0); //Recoloca a los jugadores al inicio de la carrera
        }
    }

    public ArrayList<Competidor> getCompetidores() {
        return competidores;
    }
    
    
    
    
    
    
}
