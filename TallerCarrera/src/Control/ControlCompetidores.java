/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;
import Modelo.Competidor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sangr
 */
public class ControlCompetidores {

    private ControlPrincipal cPrinc;
    private ArrayList<Competidor> competidores;

    public ControlCompetidores(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;
        competidores = new ArrayList<>();

        Competidor competidor1 = new Competidor("Usain Bolt", cPrinc.getcCarrera().getCarrera(), cPrinc);
        Competidor competidor2 = new Competidor("Periquin", cPrinc.getcCarrera().getCarrera(), cPrinc);
        Competidor competidor3 = new Competidor("Sonic", cPrinc.getcCarrera().getCarrera(), cPrinc);
        Competidor competidor4 = new Competidor("Pikachu", cPrinc.getcCarrera().getCarrera(), cPrinc);

        competidor1.setVelocidad(59);
        competidor2.setVelocidad(35);
        competidor3.setVelocidad(61);
        competidor4.setVelocidad(60);

        competidores.add(competidor1);
        competidores.add(competidor2);
        competidores.add(competidor3);
        competidores.add(competidor4);
    }

    public synchronized void terminarCarrera() {
        for (Competidor c : competidores) {
            if (c.getPosicionActual() >= Carrera.getDistanciaCarrera() && !c.getCarrera().isEsFinalizada()) {

                int tiempoLlegada = (int) (System.currentTimeMillis() - c.getCarrera().getTiempoInicial());
                c.setTiempoLlegada(tiempoLlegada);

                c.getCarrera().setDuracionCarrera(tiempoLlegada);
                c.getCarrera().setEsFinalizada(true);

                System.out.println("¡" + c.getNombre() + " ganó la carrera!");
                System.out.println("Carrera tiempo inicial: " + cPrinc.getcCarrera().getCarrera().getTiempoInicial());
                System.out.println("Duración de la carrera: " + tiempoLlegada + " ms");
            }
        }
    }

    public ArrayList<Competidor> getCompetidores() {
        return competidores;
    }

}
