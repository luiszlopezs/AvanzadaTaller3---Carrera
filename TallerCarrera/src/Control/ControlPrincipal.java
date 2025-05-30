/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;
import Modelo.Competidor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
        // cCompetidores = new ControlCompetidores(this);
        cHilos = new ControlHilos(this);

    }

    public void iniciarCarrera() {
        int contador = cVentana.getContadorCarreras();

        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cVentana.habilitarBotonesAcciones();

        cHilos.iniciarHilos();
        inhabilitarBotonesAcciones();
        determinarGanadores();
        cVentana.cargarVistaGanador();
        cVentana.mostrarGanador(
                cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());

        for (int i = 0; i < cHilos.getCompetidoresThread().size(); i++) {
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setPosicionActual(0);
            cHilos.getCompetidoresThread().get(i).getCompetidorModel().setTiempoLlegada(0);
        }

        cCarrera.getCarrera().setDuracionCarrera(0);
        getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        cCarrera.getCarrera().setEsFinalizada(false);
        cVentana.habilitarBotonIniciarCarrera();

        // mostrarFinal();
        // cVentana.cargarVistaFinal();
        // getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        // cVentana.habilitarBotonesAcciones();
        //
        // cHilos.iniciarHilos();
        // inhabilitarBotonesAcciones();
        // determinarGanadores();
        // cVentana.cargarVistaGanador();
        // cVentana.mostrarGanador(cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());
        //
        // for (int i = 0; i < cHilos.getCompetidoresThread().size(); i++) {
        // cHilos.getCompetidoresThread().get(i).getCompetidorModel().setPosicionActual(0);
        // cHilos.getCompetidoresThread().get(i).getCompetidorModel().setTiempoLlegada(0);
        // }
        //
        // cCarrera.getCarrera().setDuracionCarrera(0);
        // getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
        // cCarrera.getCarrera().setEsFinalizada(false);
        // cVentana.habilitarBotonIniciarCarrera();
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

    // Método que valida la cantidad de victorias de cada uno de los competidores y
    // retorna su nombre (o una lista de nombres si hay empates), además llama al
    // método determinarGanador final
    public void mostrarFinal() {
        List<CompetidorThread> hilos = cHilos.getCompetidoresThread();
        List<Competidor> ganadores = new ArrayList<>();

        // Encontrar el valor máximo de victorias
        int maxVictorias = 0;
        for (CompetidorThread hilo : hilos) {
            int victorias = hilo.getCompetidorModel().getCantidadVictorias();
            if (victorias > maxVictorias) { // Si el numero de victorias del competidor es mayor que el máximo hasta el
                                            // momento, entonces este pasa a ser el máximo
                maxVictorias = victorias;
            }
        }

        // Agregar todos los que tengan ese número de victorias a la lista de ganadores
        for (CompetidorThread hilo : hilos) {
            Competidor c = hilo.getCompetidorModel();
            if (c.getCantidadVictorias() == maxVictorias) { // valida que el competidor tiene el mismo número de
                                                            // victorias que el máximo calculado anteriormente
                ganadores.add(c); // Si es así, se añade a la lista de ganadores
            }
        }

        // Mostrar vista final
        cVentana.cargarVistaFinal();

        // Activar botones según cantidad de ganadores
        if (ganadores.size() == 1) {
            // Un solo ganador
            cVentana.determinarGanadorFinal(ganadores.get(0).getNombre()); // Se pasa como parámetro al método que
                                                                           // muestra el símbolo de ganador el nombre
                                                                           // del ganador
        } else {
            // Si hay empate (el tamaño de la lista de ganadores es mayor que 1): mostrar
            // todos los ganadores
            for (Competidor c : ganadores) {
                cVentana.determinarGanadorFinal(c.getNombre());
            }
        }
    }

    public void determinarGanadores() {
        cCarrera.determinarGanadores(cHilos.getCompetidoresThread());
        System.out.println("El ganador es: "
                + cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());

    }
    //Método para convertir el tiempo dado en milisegundos a segundos (mas entendible para el usuario)
    public String formatearTiempo(long tiempoMs) {
        double tiempoSeg = tiempoMs / 1000.0;
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(tiempoSeg) + " s";
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
