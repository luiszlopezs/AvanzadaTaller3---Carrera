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
        determinarGanadores();
    }

    public void iniciarCarrera() {
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cHilos.iniciarHilos();
    }

    public void reiniciarCarrera() {
        for (int i = 0; i <= cCompetidores.getCompetidores().size(); i++) {
            cCompetidores.getCompetidores().get(i).setPosicionActual(0);
            cCompetidores.getCompetidores().get(i).setTiempoLlegada(0);
        }

        cCarrera.getCarrera().setDuracionCarrera(0);
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cCarrera.getCarrera().setEsFinalizada(false);
        cHilos.iniciarHilos();
    }

    public void setearAccidenteTrue() {
        cCompetidores.getCompetidores().get(0).setIsAccidentado(true);
    }

    public void aplicarAccidente1() {
        if (cCompetidores.getCompetidores().get(0).isIsAccidentado()) {
            cHilos.aplicarAccidente("Usain Bolt");
        }

    }
    
    public void setearImpulsoTrue(){
        cCompetidores.getCompetidores().get(1).setIsImpulsado(true);
    }

    public void aplicarImpulso2() {
        if (cCompetidores.getCompetidores().get(1).isIsImpulsado()) {
            cHilos.impulsar("Periquin");
        }
    }

    public synchronized void finalizarCarrera() {
        cCompetidores.terminarCarrera();

    }

    public void determinarGanadores() {
        cCarrera.determinarGanadores();
        System.out.println("El ganador es: " + cCarrera.determinarGanadores().get(0).getNombre());

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
