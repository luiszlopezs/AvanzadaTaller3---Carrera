/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Vista.CarreraView;
import Vista.FinalView;
import Vista.GanadorView;
import Vista.InicioView;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author hailen
 */
public class ControlVentana implements ActionListener {

    private ControlPrincipal cPrinc;
    private CarreraView vCarrera;
    private InicioView vView;
    private GanadorView vGanador;
    private FinalView vFinal;
    private int contadorCarreras;

    public ControlVentana(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;

        vView = new InicioView(this);
        cPrinc.inicializarArrayImagenes(vView.rutaJfileChooserImagenes());
        setearImagenesVInicio();
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
                cPrinc.inhabilitarBotonesAcciones();

                break;
            // VENTANA CARRERA
            case "BTN_INICIAR":
                new Thread(() -> {
                    cPrinc.iniciarCarrera();
                }).start();
                contadorCarreras++;
                break;
            case "BTN_ACCIDENTE":
                // cPrinc.setearAccidenteTrue();
                cPrinc.aplicarAccidente1();
                System.out.println("si sirvooooooooooooooo");
                break;
            case "BTN_IMPULSAR":
                // cPrinc.setearImpulsoTrue();
                cPrinc.aplicarImpulso2();
                cPrinc.getcVentana().getvCarrera().getLblImpulso().setVisible(true);
                Timer timer = new Timer(1500, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cPrinc.getcVentana().getvCarrera().getLblImpulso().setVisible(false); // Oculta solo el JLabel
                    }
                });
                timer.setRepeats(false);
                timer.start();
                break;

            case "BTN_SALIR": //Cuando presiona Salir, se mostrará el resumen de cada competidor y sus carreras
                vCarrera.dispose();
                cPrinc.mostrarFinal();

                break;

            // VENTANA GANADORES
            case "VGANADOR_CONTINUAR":
                vGanador.dispose();

                break;
            //VISTA FINAL
            case "BTN_SALIR_FINAL":
                vFinal.dispose();
                break;

        }
    }

    public void cargarVistaCarrera() {
        vCarrera = new CarreraView(this);
        setearImagenesVCarrera();

        vCarrera.getBtnAccidente().setActionCommand("BTN_ACCIDENTE");
        vCarrera.getBtnAccidente().addActionListener(this);

        vCarrera.getBtnImpulsar().setActionCommand("BTN_IMPULSAR");
        vCarrera.getBtnImpulsar().addActionListener(this);

        vCarrera.getBtnIniciar().setActionCommand("BTN_INICIAR");
        vCarrera.getBtnIniciar().addActionListener(this);

        vCarrera.getBtnSalir().setActionCommand("BTN_SALIR");
        vCarrera.getBtnSalir().addActionListener(this);

        vCarrera.getLblAccidente().setVisible(false);
        vCarrera.getLblImpulso().setVisible(false);

        vCarrera.setVisible(true);
    }

    public void cargarVistaGanador() {
        vGanador = new GanadorView(this);
        setearImagenesVGanador();
        vGanador.getBtnContinuar().setActionCommand("VGANADOR_CONTINUAR");
        vGanador.getBtnContinuar().addActionListener(this);
        vGanador.setVisible(true);
    }

    public void cargarVistaFinal() {
        vFinal = new FinalView(this);
        vFinal.getBtnSalirFinal().setActionCommand("BTN_SALIR_FINAL");
        vFinal.getBtnSalirFinal().addActionListener(this);
        vFinal.getBtnGanoGoku().setVisible(false);
        vFinal.getBtnGanoSonic().setVisible(false);
        vFinal.getBtnGanoNyan().setVisible(false);
        vFinal.getBtnGanoPika().setVisible(false);

        vFinal.setVisible(true);

    }

    //Método que muestra un panel del ganador de cada carrera, con su número de victorias hasta el momento y el tiempo de la carrera
    public void mostrarGanador(String nombrePanel) {
        CardLayout cl = (CardLayout) vGanador.getjPANEL_CONTENEDOR().getLayout();
        cl.show(vGanador.getjPANEL_CONTENEDOR(), nombrePanel);
        switch (nombrePanel) {
            case "NyanCat":
                vGanador.getLblVictoriaNyan().setText("VICTORIAS: " + cPrinc.getcHilos().getCompetidoresThread().get(0)
                        .getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoNyan().setText("TIEMPO: "
                        + (cPrinc.formatearTiempo(cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getTiempoLlegada()))); //Se llama al método de cPrinc para mostar el tiempo en segundos, con sus decimales

                break;
            case "Sonic":

                vGanador.getLblVictoriaSonic().setText("VICTORIAS: " + cPrinc.getcHilos().getCompetidoresThread().get(1)
                        .getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoSonic().setText("TIEMPO: "
                        + (cPrinc.formatearTiempo(cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getTiempoLlegada())));
                break;
            case "Goku":
                vGanador.getLblVictoriaGoku().setText("VICTORIAS: " + cPrinc.getcHilos().getCompetidoresThread().get(2)
                        .getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoGoku().setText("TIEMPO: "
                        + (cPrinc.formatearTiempo(cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getTiempoLlegada())));
                break;

            case "Pikachu":
                vGanador.getLblVictoriaPika().setText("VICTORIAS: " + cPrinc.getcHilos().getCompetidoresThread().get(3)
                        .getCompetidorModel().getCantidadVictorias());
                vGanador.getLblTiempoPika().setText("TIEMPO: "
                        + (cPrinc.formatearTiempo(cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getTiempoLlegada())));
                break;
        }

    }

    //Método que hace visible el botón de ganador para aquellos con la mayor cantidad de victorias
    public void determinarGanadorFinal(String nombreBoton) {
        switch (nombreBoton) {
            case "Goku":
                vFinal.getBtnGanoGoku().setVisible(true);
                break;
            case "Sonic":
                vFinal.getBtnGanoSonic().setVisible(true);
                break;
            case "NyanCat":
                vFinal.getBtnGanoNyan().setVisible(true);
                break;
            case "Pikachu":
                vFinal.getBtnGanoPika().setVisible(true);
                break;
        }
//
//      Se edita el texto del label que muestra las victorias totales de cada competidor
        vFinal.getLblVictoriasNyan().setText(Integer.toString(cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getCantidadVictorias()));
        vFinal.getLblVictoriasSonic().setText(Integer.toString(cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getCantidadVictorias()));
        vFinal.getLblVictoriasGoku().setText(Integer.toString(cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getCantidadVictorias()));
        vFinal.getLblVictoriasPika().setText(Integer.toString(cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getCantidadVictorias()));
    }

    public void moverLabels(String nombre) {
        switch (nombre) {
            case "NyanCat":
                vCarrera.getLblNyanCat().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getPosicionActual(),
                        150);
                vCarrera.getLblAccidente().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getPosicionActual() + 120,
                        150);
                break;
            case "Sonic":
                vCarrera.getLblSonic().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getPosicionActual(),
                        250);
                vCarrera.getLblImpulso().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getPosicionActual() -100,
                        250);
                break;
            case "Goku":
                vCarrera.getLblGoku().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getPosicionActual(),
                        340);
                break;
            case "Pikachu":
                vCarrera.getLblPikachu().setLocation(
                        cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getPosicionActual(),
                        400);
                break;

        }
    }

    public void inhabilitarBotonesAcciones() {
        vCarrera.getBtnAccidente().setEnabled(false);
        vCarrera.getBtnImpulsar().setEnabled(false);
        vCarrera.getBtnSalir().setEnabled(false);

    }

    public void habilitarBotonesAcciones() {
        vCarrera.getBtnAccidente().setEnabled(true);
        vCarrera.getBtnImpulsar().setEnabled(true);

    }

    public void inhabilitarBotonIniciarCarrera() {
        vCarrera.getBtnIniciar().setEnabled(false);
    }

    public void habilitarBotonIniciarCarrera() {
        vCarrera.getBtnIniciar().setEnabled(true);
        vCarrera.getBtnSalir().setEnabled(true);
    }

    public void setearImagenesVInicio() {
        System.out.println(cPrinc.getImagenesRutas().get(0));
        ImageIcon imagenFondoInicio = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(0)));
        vView.getLblImagenInicio().setIcon(imagenFondoInicio);
    }

    public void setearImagenesVCarrera() {
        ImageIcon imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(1)));
        vCarrera.getLblImagen().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(2)));
        vCarrera.getLblGoku().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(3)));
        vCarrera.getLblNyanCat().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(4)));
        vCarrera.getLblPikachu().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(5)));
        vCarrera.getLblSonic().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(10)));
        vCarrera.getLblAccidente().setIcon(imagen);

        imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(11)));
        vCarrera.getLblImpulso().setIcon(imagen);
    }

    public void setearImagenesVGanador() {
        vGanador.getLblImagenGoku().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(6))));
        vGanador.getLblImagenNyancat().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(7))));
        vGanador.getLblImagenPikachu().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(8))));
        vGanador.getLblImagenSonic().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(9))));

    }

    public void setearImagenesVFinal() {
        vFinal.getLblGokuFinal().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(2))));
        vFinal.getLblFinalNyan().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(3))));
        vFinal.getLblFinalPikachu().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(4))));
        vFinal.getLblFinalSonic().setIcon(new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(5))));
    }

    public int getContadorCarreras() {
        return contadorCarreras;
    }

    public void setContadorCarreras(int contadorCarreras) {
        this.contadorCarreras = contadorCarreras;
    }

    public CarreraView getvCarrera() {
        return vCarrera;
    }

    public void setvCarrera(CarreraView vCarrera) {
        this.vCarrera = vCarrera;
    }

}
