/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Vista.CarreraView;
import Vista.GanadorView;
import Vista.InicioView;
import java.awt.CardLayout;
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
    private GanadorView vGanador;

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
//              cPrinc.setearAccidenteTrue();
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
                
            //VENTANA GANADORES
            
            case "VGANADOR_CONTINUAR":
                vGanador.dispose();

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
    
    public void cargarVistaGanador(){
        vGanador = new GanadorView(this);

        vGanador.getBtnContinuar().setActionCommand("VGANADOR_CONTINUAR");
        vGanador.getBtnContinuar().addActionListener(this);
        vGanador.setVisible(true);
    }
    
    public void mostrarGanador(String nombrePanel){
        CardLayout cl = (CardLayout) vGanador.getjPANEL_CONTENEDOR().getLayout();
        cl.show(vGanador.getjPANEL_CONTENEDOR(),nombrePanel );
        switch(nombrePanel){
            case "Goku":
                vGanador.getLblVictoriaGoku().setText("VICTORIAS: "+ cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoGoku().setText("TIEMPO: "+ cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getTiempoLlegada());
                break;
            case"Sonic":
                vGanador.getLblVictoriaSonic().setText("VICTORIAS: "+ cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoSonic().setText("TIEMPO: "+ cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getTiempoLlegada());
                break;
            case "NyanCat":
                vGanador.getLblVictoriaNyan().setText("VICTORIAS: "+ cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoNyan().setText("TIEMPO: "+ cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getTiempoLlegada());
                break;
            case "Pikachu":
                vGanador.getLblVictoriaPika().setText("VICTORIAS: "+ cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoPika().setText("TIEMPO: "+ cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getTiempoLlegada());
                break;
        }
            
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


