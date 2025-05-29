/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Control.ControlPrincipal;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailen
 */
public class Competidor extends Thread {

    private String nombre;
    private int posicionActual = 0;
    private int cantidadVictorias;
    private int velocidad;

    private int tiempoLlegada;
    private Carrera carrera;

    private boolean isAccidentado = false;
    private boolean isImpulsado = false;
    private int impulso = 2;
    private ControlPrincipal cPrinc;
   

    public Competidor(String nombre, Carrera carrera, ControlPrincipal cPrinc) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.cPrinc = cPrinc;
    }

    public void incrementarVictorias() {
        this.cantidadVictorias++;
    }

    Scanner scany = new Scanner(System.in);

    @Override

    public void run() {
        while (!carrera.isEsFinalizada()) {
            
            cPrinc.aplicarAccidente1();
//            if (isAccidentado) {
//                try {
//                    Thread.sleep(1000 + (int) (Math.random() * 1000));
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                isAccidentado = false;
//            }

            cPrinc.aplicarImpulso2();
//            if (nombre.equals("periquin")) {
//                posicionActual += impulso;
//                impulso = 0;
//            }

            posicionActual += velocidad; //Aumentar de posicion de acuerdo al valor de velocidad
            
            try { //Correr con algunos descansos intermedios
                System.out.println("correee" + this.nombre);
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

        } long tiempoFin = System.currentTimeMillis();
//        this.tiempoLlegada = (int) (System.currentTimeMillis() - carrera.getTiempoInicial());

        System.out.println(this.nombre + " termino el run con tiempo " + this.tiempoLlegada);
        

    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public int getCantidadVictorias() {
        return cantidadVictorias;
    }

    public void setCantidadVictorias(int cantidadVictorias) {
        this.cantidadVictorias = cantidadVictorias;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public boolean isIsAccidentado() {
        return isAccidentado;
    }

    public void setIsAccidentado(boolean isAccidentado) {
        this.isAccidentado = isAccidentado;
    }

    public int getImpulso() {
        return impulso;
    }

    public void setImpulso(int impulso) {
        this.impulso = impulso;
    }
    
    


    public boolean isIsImpulsado() {
        return isImpulsado;
    }

    public void setIsImpulsado(boolean isImpulsado) {
        this.isImpulsado = isImpulsado;
    }
    
    

}
