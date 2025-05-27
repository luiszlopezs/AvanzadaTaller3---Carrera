/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;

/**
 *
 * @author sangr
 */
public class ControlPrincipal {
    private Carrera carrera;
    private ControlCompetidores cCompetidores;
    private ControlHilos cHilos;

    public ControlPrincipal(Carrera carrera, ControlCompetidores cCompetidores, ControlHilos cHilos) {
        this.carrera = carrera;
        this.cCompetidores = cCompetidores;
        this.cHilos = cHilos;
    }

    public void iniciarCarrera(){
        cHilos.iniciarHilos();
    }
    public void aplicarAccidente1(){
        cHilos.aplicarAccidente("Usain Bolt");
    }
    public void aplicarImpulso2(){
        cHilos.impulsar("Periquin");
    }
    
    public void determinarGanadores(){
        
    }
}
