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
    private List<Competidor> hilos;

    public ControlHilos(ControlPrincipal cPrinc, List<Competidor> competidores) {
        this.cPrinc = cPrinc;
        this.hilos = competidores;
    }

    public void iniciarHilos() {

        for (Competidor hilo : hilos) {
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlHilos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
     public void aplicarAccidente(String nombre) { //La validacion de si el competidor ya esta en estado de sleep o wait, se hace en el controlPrincipal / carrera, aqui solo se genera el accidente

        for (Competidor c : hilos) {
            if (c.getNombre().equals(nombre)) {
                if(c.isIsAccidentado()){
                   try {
                    c.sleep(1000 + (int) (Math.random() * 1000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.setIsAccidentado(false);  
                } 
                //c.simularAccidente();
            }
        }
    }

    public void impulsar(String nombre) { //
        for (Competidor c : hilos) {
            if (c.getNombre().equals(nombre)) {
                
                int posicionNueva = c.getPosicionActual() + c.getImpulso();
                c.setPosicionActual(posicionNueva);
            
                //c.aplicarImpulso(50); //Hace que avance 50 pasos
            }
        }
    }
    public void reiniciar() {
        for (Competidor c : hilos) {
            c.interrupt();
        }
    }

}
    
    
    

