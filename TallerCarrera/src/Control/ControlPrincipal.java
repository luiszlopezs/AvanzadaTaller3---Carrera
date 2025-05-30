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
    private ControlHilos cHilos;
    private ControlVentana cVentana;

    public ControlPrincipal() {
        cVentana = new ControlVentana(this);
        cCarrera = new ControlCarrera(this);
//        cCompetidores = new ControlCompetidores(this);
        cHilos = new ControlHilos(this);

    }

    public void iniciarCarrera() {
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cVentana.habilitarBotonesAcciones();
        
        cHilos.iniciarHilos();
        inhabilitarBotonesAcciones();
        determinarGanadores();
        cVentana.cargarVistaGanador();
        cVentana.mostrarGanador(cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());
                for (int i = 0; i < cHilos.getCompetidoresThread().size(); i++) {
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setPosicionActual(0);
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setTiempoLlegada(0);
        }

        cCarrera.getCarrera().setDuracionCarrera(0);
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cCarrera.getCarrera().setEsFinalizada(false);
        cVentana.habilitarBotonIniciarCarrera();
       
        
    }


    public void reiniciarCarrera() {
        for (int i = 0; i < cHilos.getCompetidoresThread().size(); i++) {
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setPosicionActual(0);
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setTiempoLlegada(0);
        }

        cCarrera.getCarrera().setDuracionCarrera(0);
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cCarrera.getCarrera().setEsFinalizada(false);
        cHilos.iniciarHilos();
    }

    public void setearAccidenteTrue() {
        cHilos.getCompetidoresThread().get(0).setIsAccidentado(true);
    }

    public void aplicarAccidente1() {
        if (!cCarrera.getCarrera().isEsFinalizada()) {
            cHilos.aplicarAccidente("NyanCat");
        }

    }

    public void setearImpulsoTrue() {
        cHilos.getCompetidoresThread().get(1).setIsImpulsado(true);
    }

    public void aplicarImpulso2() {
        if (!cCarrera.getCarrera().isEsFinalizada()) {
            cHilos.impulsar("Sonic");
        }

    }

    public synchronized void finalizarCarrera() {
        cCarrera.terminarCarrera(cHilos.getCompetidoresThread());

    }

    public void determinarGanadores() {
        cCarrera.determinarGanadores(cHilos.getCompetidoresThread());
        System.out.println("El ganador es: " + cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());

    }

    public void inhabilitarBotonesAcciones() {
        cVentana.inhabilitarBotonesAcciones();
    }

    public void habilitarBotonesAcciones() {
        cVentana.habilitarBotonesAcciones();
    }

    public ControlCarrera getcCarrera() {
        return cCarrera;
    }

    public ControlHilos getcHilos() {
        return cHilos;
    }

    public ControlVentana getcVentana() {
        return cVentana;
    }

    public void setcVentana(ControlVentana cVentana) {
        this.cVentana = cVentana;
    }

}
