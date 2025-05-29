/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Vista.CarreraView;
import Vista.InicioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hailen
 */
public class ControlVentana implements ActionListener {

    private ControlPrincipal cPrinc;
    private CarreraView vCarrera;
    private InicioView vView;

    public ControlVentana(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;

        vView = new InicioView(this);

        vView.getBtnJugar().setActionCommand("BTN_JUGAAR");
        vView.getBtnJugar().addActionListener(this);
        vView.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando) {
            // VENTANA INICIO
            case "BTN_JUGAAR":
                vView.dispose();
                
                cargarVistaCarrera();
                
                break;
            // VENTANA CARRERA    
            case "BTN_INICIAR":
                new Thread(() -> {
                    cPrinc.iniciarCarrera();
                     
                }).start();
                break;
            case "BTN_ACCIDENTE":
//                cPrinc.setearAccidenteTrue();
                cPrinc.aplicarAccidente1();
                System.out.println("si sirvooooooooooooooo");
                break;
            case "BTN_IMPULSAR":
//                cPrinc.setearImpulsoTrue();
                cPrinc.aplicarImpulso2();
                break;
            case "BTN_SALIR":
                vCarrera.dispose();
                break;

        }
    }

    public void cargarVistaCarrera() {
        vCarrera = new CarreraView(this);

        vCarrera.getBtnAccidente().setActionCommand("BTN_ACCIDENTE");
        vCarrera.getBtnAccidente().addActionListener(this);

        vCarrera.getBtnImpulsar().setActionCommand("BTN_IMPULSAR");
        vCarrera.getBtnImpulsar().addActionListener(this);

        vCarrera.getBtnIniciar().setActionCommand("BTN_INICIAR");
        vCarrera.getBtnIniciar().addActionListener(this);

        vCarrera.getBtnSalir().setActionCommand("BTN_SALIR");
        vCarrera.getBtnSalir().addActionListener(this);

        vCarrera.setVisible(true);
    }
    
    public void moverLabels(String nombre){
        switch (nombre){
            case "NyanCat":
                vCarrera.getLblNyanCat().setLocation(cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getPosicionActual(), 150);
                break;
            case "Sonic":
                vCarrera.getLblSonic().setLocation(cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getPosicionActual(), 250);
                break;
            case "Goku":
                vCarrera.getLblGoku().setLocation(cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getPosicionActual(), 340);
                break;
            case "Pikachu":
                vCarrera.getLblPikachu().setLocation(cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getPosicionActual(), 400);
                break;
         
        }
    }
        
    public void inhabilitarBotonesAcciones(){
        vCarrera.getBtnAccidente().setEnabled(false);
        vCarrera.getBtnImpulsar().setEnabled(false);
        
    }
    
    public void habilitarBotonesAcciones(){
        vCarrera.getBtnAccidente().setEnabled(true);
        vCarrera.getBtnImpulsar().setEnabled(true);
    }
    
    public void inhabilitarBotonIniciarCarrera(){
        vCarrera.getBtnIniciar().setEnabled(false);
    }
    
    public void habilitarBotonIniciarCarrera(){
        vCarrera.getBtnIniciar().setEnabled(true);
    }
            
        
        
        
}


