/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Competidor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailen
 */
public class ControlHilos {

    private ControlPrincipal cPrinc;
    private ArrayList<CompetidorThread> competidoresThread;

    public ControlHilos(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;
        competidoresThread = new ArrayList();

        CompetidorThread competidor1 = new CompetidorThread(cPrinc, "NyanCat", 20);
        CompetidorThread competidor2 = new CompetidorThread(cPrinc, "Sonic", 21);

        CompetidorThread competidor3 = new CompetidorThread(cPrinc, "Goku", 18);
        CompetidorThread competidor4 = new CompetidorThread(cPrinc, "Pikachu",19);

        competidoresThread.add(competidor1);
        competidoresThread.add(competidor2);
        competidoresThread.add(competidor3);
        competidoresThread.add(competidor4);

    }

    public void iniciarHilos() {
        ArrayList<Thread> listaHilos = new ArrayList();
        
        for (CompetidorThread hilo : competidoresThread) {
            Thread t1 = new Thread(hilo);
            listaHilos.add(t1);
            t1.start();
            
        }

        try {
            listaHilos.get(0).join();
            listaHilos.get(1).join();
            listaHilos.get(2).join();
            listaHilos.get(3).join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlHilos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void aplicarAccidente(String nombre) {
        for (CompetidorThread c : competidoresThread) {
             System.out.println("Comparando con: " + c.getCompetidorModel().getNombre());
            if (c.getCompetidorModel().getNombre().equals(nombre)) {
                c.setIsAccidentado(true);
                System.out.println("ay me pegueeeeeeeee");
            }
        }
    }

    public void impulsar(String nombre) {
        for (CompetidorThread c : competidoresThread) {
            if (c.getCompetidorModel().getNombre().equals(nombre)) {
                c.setIsImpulsado(true);
                System.out.println("siuuuuuuuuuuuuu");
            }
        }
    }



    public ArrayList<CompetidorThread> getCompetidoresThread() {
        return competidoresThread;
    }

    public void setCompetidoresThread(ArrayList<CompetidorThread> competidoresThread) {
        this.competidoresThread = competidoresThread;
    }
    
    
    

}
