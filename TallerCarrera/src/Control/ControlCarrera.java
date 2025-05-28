/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;

/**
 *
 * @author hailen
 */
public class ControlCarrera {

    private ControlPrincipal cPrinc;
    private Carrera carrera;

    public ControlCarrera(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;
        carrera = new Carrera();

    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void determinarGanadores() {

    }

}
