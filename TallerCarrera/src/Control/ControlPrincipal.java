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

    private ControlCarrera cCarrera;
    private ControlCompetidores cCompetidores;
    private ControlHilos cHilos;

    public ControlPrincipal() {
        cCarrera = new ControlCarrera(this);
        cCompetidores = new ControlCompetidores(this);
        cHilos = new ControlHilos(this, cCompetidores.getCompetidores());
        iniciarCarrera();
    }

    public void iniciarCarrera() {
        cHilos.iniciarHilos();
    }

    public void aplicarAccidente1() {
        cHilos.aplicarAccidente("Usain Bolt");
    }

    public void aplicarImpulso2() {
        cHilos.impulsar("Periquin");
    }

    public void finalizarCarrera() {
       cCompetidores.terminarCarrera();
       //determinarGanadores();
       
    }

    public void determinarGanadores() {
       cCarrera.determinarGanadores();
       System.out.println("El ganador es: "+cCarrera.determinarGanadores().get(0));
       
    }

    public ControlCarrera getcCarrera() {
        return cCarrera;
    }

    public ControlCompetidores getcCompetidores() {
        return cCompetidores;
    }

    public ControlHilos getcHilos() {
        return cHilos;
    }

}
