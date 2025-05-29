/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Competidor;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailen
 */
public class CompetidorThread implements Runnable {

    private Competidor competidorModel;
    private ControlPrincipal cPrinc;

    private volatile boolean isAccidentado = false;
    private volatile boolean isImpulsado = false;

    public CompetidorThread(ControlPrincipal cPrinc, String nombre, int velocidad) {
        competidorModel = new Competidor(cPrinc.getcCarrera().getCarrera(), nombre, velocidad);
        this.cPrinc = cPrinc;
    }

    @Override

    public void run() {
        while (!competidorModel.getCarrera().isEsFinalizada()) {
            cPrinc.getcVentana().inhabilitarBotonIniciarCarrera();
//            cPrinc.aplicarAccidente1();
//            if (isAccidentado) {
//                try {
//                    Thread.sleep(1000 + (int) (Math.random() * 1000));
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                isAccidentado = false;
//            }

//            cPrinc.aplicarImpulso2();
//            if (nombre.equals("periquin")) {
//                posicionActual += impulso;
//                impulso = 0;
//            }
            aplicarCondicionesEspeciales();

            competidorModel.setPosicionActual(competidorModel.getPosicionActual() + competidorModel.getVelocidad());  //Aumentar de posicion de acuerdo al valor de velocidad
            
            cPrinc.getcVentana().moverLabels(competidorModel.getNombre());

            try { //Correr con algunos descansos intermedios
                System.out.println("correee" + this.competidorModel.getNombre());
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);

            }

            cPrinc.finalizarCarrera();
//            if (posicionActual >= Carrera.getDistanciaCarrera()) {
//                this.tiempoLlegada = (int) (System.currentTimeMillis() - carrera.getTiempoInicial());
//                break;
//            }

            //carrera.getControlCarrera().actualizarVista();  // si está delegado así
//            if (posicionActual >= carrera.getDistanciaCarrera()) {
//                long tiempoFin = System.currentTimeMillis();
//
//                break;
            //}
        }
        long tiempoFin = System.currentTimeMillis();
//        this.tiempoLlegada = (int) (System.currentTimeMillis() - carrera.getTiempoInicial());

        System.out.println(competidorModel.getNombre() + " termino el run con tiempo " + competidorModel.getTiempoLlegada());

    }

    public Competidor getCompetidorModel() {
        return competidorModel;
    }

    public void setCompetidorModel(Competidor competidorModel) {
        this.competidorModel = competidorModel;
    }

    private void aplicarCondicionesEspeciales() {
        if (isAccidentado) {
            try {
                Thread.sleep(1000 + new Random().nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            isAccidentado = false;
        }

        if (isImpulsado) {
            competidorModel.setPosicionActual(competidorModel.getPosicionActual() +competidorModel.getImpulso()) ;
            System.out.println("impulsando wiiiiiiiiiiiiii");
            isImpulsado = false;
        }
    }

    public boolean isIsAccidentado() {
        return isAccidentado;
    }

    public void setIsAccidentado(boolean isAccidentado) {
        this.isAccidentado = isAccidentado;
    }

    public boolean isIsImpulsado() {
        return isImpulsado;
    }

    public void setIsImpulsado(boolean isImpulsado) {
        this.isImpulsado = isImpulsado;
    }
    
    

}
