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
 * Clase que actúa como controlador de la interfaz gráfica de la aplicación.
 * 
 * Administra el cambio entre vistas (Inicio, Carrera, Ganador, Final) y 
 * responde a eventos de acción generados por botones u otros elementos gráficos.
 * 
 * Implementa la interfaz ActionListener para manejar eventos en la GUI.
 * 
 * @author hailen
 */
public class ControlVentana implements ActionListener {

    /**
     * Referencia al controlador principal de la aplicación.
     */
    private ControlPrincipal cPrinc;

    /**
     * Vista correspondiente a la carrera en ejecución.
     */
    private CarreraView vCarrera;

    /**
     * Vista de inicio, donde el usuario puede configurar y comenzar la carrera.
     */
    private InicioView vView;

    /**
     * Vista que muestra al ganador de la carrera.
     */
    private GanadorView vGanador;

    /**
     * Vista final, posiblemente usada para terminar o reiniciar el programa.
     */
    private FinalView vFinal;

    /**
     * Contador que registra cuántas carreras se han jugado.
     */
    private int contadorCarreras;

    /**
     * Constructor de la clase ControlVentana.
     * 
     * Inicializa la vista de inicio, configura los botones de acción,
     * carga las imágenes seleccionadas por el usuario y muestra la ventana inicial.
     * 
     * @param cPrinc Referencia al controlador principal.
     */
    public ControlVentana(ControlPrincipal cPrinc) {
        this.cPrinc = cPrinc;

        // Inicializa la vista de inicio
        vView = new InicioView(this);

        // Carga las imágenes desde el archivo seleccionado por el usuario
        cPrinc.inicializarArrayImagenes(vView.rutaJfileChooserImagenes());

        // Asigna las imágenes a la vista de inicio
        setearImagenesVInicio();

        // Configura el botón de jugar
        vView.getBtnJugar().setActionCommand("BTN_JUGAAR");
        vView.getBtnJugar().addActionListener(this);

        // Muestra la ventana
        vView.setVisible(true);
    }


    @Override
    /**
 * Maneja los eventos generados por los botones de la interfaz gráfica.
 * 
 * Este método es invocado automáticamente cuando se hace clic en un botón
 * que tiene asignado un `ActionListener`. Según el comando recibido, realiza
 * diferentes acciones como cargar vistas, iniciar la carrera, aplicar eventos,
 * o salir de la aplicación.
 * 
 * @param e Evento de acción generado por un componente gráfico (por ejemplo, un botón).
 */
public void actionPerformed(ActionEvent e) {
    String comando = e.getActionCommand();

    switch (comando) {

        // 👉 VISTA INICIO
        case "BTN_JUGAAR":
            vView.dispose(); // Cierra la vista de inicio
            cargarVistaCarrera(); // Muestra la vista de carrera
            cPrinc.inhabilitarBotonesAcciones(); // Desactiva botones al iniciar
            break;

        // 👉 VISTA CARRERA: Iniciar la carrera en un hilo separado
        case "BTN_INICIAR":
            new Thread(() -> {
                cPrinc.iniciarCarrera();
            }).start();
            contadorCarreras++;
            break;

        // 👉 VISTA CARRERA: Aplicar accidente
        case "BTN_ACCIDENTE":
            cPrinc.aplicarAccidente1(); // Aplica accidente a "NyanCat"
            System.out.println("si sirvooooooooooooooo");
            break;

        // 👉 VISTA CARRERA: Aplicar impulso visual y funcional
        case "BTN_IMPULSAR":
            cPrinc.aplicarImpulso2(); // Aplica impulso a "Sonic"
            cPrinc.getcVentana().getvCarrera().getLblImpulso().setVisible(true);

            // Oculta el mensaje de impulso después de 1.5 segundos
            Timer timer = new Timer(1500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cPrinc.getcVentana().getvCarrera().getLblImpulso().setVisible(false);
                }
            });
            timer.setRepeats(false);
            timer.start();
            break;

        // 👉 VISTA CARRERA: Salir y mostrar vista final con resumen
        case "BTN_SALIR":
            vCarrera.dispose();
            cPrinc.mostrarFinal();
            break;

        // 👉 VISTA GANADOR: Cerrar ventana del ganador
        case "VGANADOR_CONTINUAR":
            vGanador.dispose();
            break;

        // 👉 VISTA FINAL: Cerrar ventana final
        case "BTN_SALIR_FINAL":
            vFinal.dispose();
            break;
    }
}


    /**
 * Carga e inicializa la vista gráfica de la carrera.
 * 
 * Este método:
 * - Crea una nueva instancia de `CarreraView`.
 * - Asigna las imágenes correspondientes a cada competidor.
 * - Configura los botones de la vista con sus comandos y listeners.
 * - Oculta inicialmente los mensajes visuales de accidente e impulso.
 * - Muestra la ventana en pantalla.
 */
public void cargarVistaCarrera() {
    // Crea la vista de carrera
    vCarrera = new CarreraView(this);

    // Asigna imágenes a los competidores
    setearImagenesVCarrera();

    // Configura el botón de accidente
    vCarrera.getBtnAccidente().setActionCommand("BTN_ACCIDENTE");
    vCarrera.getBtnAccidente().addActionListener(this);

    // Configura el botón de impulso
    vCarrera.getBtnImpulsar().setActionCommand("BTN_IMPULSAR");
    vCarrera.getBtnImpulsar().addActionListener(this);

    // Configura el botón de iniciar carrera
    vCarrera.getBtnIniciar().setActionCommand("BTN_INICIAR");
    vCarrera.getBtnIniciar().addActionListener(this);

    // Configura el botón de salir
    vCarrera.getBtnSalir().setActionCommand("BTN_SALIR");
    vCarrera.getBtnSalir().addActionListener(this);

    // Oculta inicialmente los indicadores de accidente e impulso
    vCarrera.getLblAccidente().setVisible(false);
    vCarrera.getLblImpulso().setVisible(false);

    // Muestra la ventana
    vCarrera.setVisible(true);
}


    /**
 * Carga e inicializa la vista que muestra al ganador de la carrera.
 * 
 * Este método:
 * - Crea una nueva instancia de `GanadorView`.
 * - Asigna las imágenes correspondientes a los competidores.
 * - Configura el botón "Continuar" con su comando y listener.
 * - Muestra la ventana al usuario.
 */
public void cargarVistaGanador() {
    // Crea la vista del ganador
    vGanador = new GanadorView(this);

    // Asigna imágenes a la vista del ganador
    setearImagenesVGanador();

    // Configura el botón "Continuar"
    vGanador.getBtnContinuar().setActionCommand("VGANADOR_CONTINUAR");
    vGanador.getBtnContinuar().addActionListener(this);

    // Muestra la vista del ganador
    vGanador.setVisible(true);
}


    /**
 * Carga e inicializa la vista final de la aplicación.
 * 
 * Este método:
 * - Crea una nueva instancia de `FinalView`.
 * - Configura el botón de salida con su comando y listener.
 * - Oculta los botones de resumen de ganadores individuales.
 * - Muestra la ventana final al usuario.
 */
public void cargarVistaFinal() {
    // Crea la vista final
    vFinal = new FinalView(this);

    // Configura el botón de salir
    vFinal.getBtnSalirFinal().setActionCommand("BTN_SALIR_FINAL");
    vFinal.getBtnSalirFinal().addActionListener(this);

    // Oculta los botones individuales de ganadores
    vFinal.getBtnGanoGoku().setVisible(false);
    vFinal.getBtnGanoSonic().setVisible(false);
    vFinal.getBtnGanoNyan().setVisible(false);
    vFinal.getBtnGanoPika().setVisible(false);

    // Muestra la vista final
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

    /**
 * Mueve el label correspondiente al competidor indicado, actualizando su posición
 * en la interfaz gráfica de acuerdo con su posición actual en la carrera.
 * 
 * También ajusta la posición del indicador de accidente o impulso cuando corresponde.
 * 
 * @param nombre Nombre del competidor ("NyanCat", "Sonic", "Goku", "Pikachu").
 */
public void moverLabels(String nombre) {
    switch (nombre) {

        case "NyanCat":
            // Mueve el label del competidor NyanCat
            vCarrera.getLblNyanCat().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getPosicionActual(),
                150
            );
            // Mueve el label de accidente asociado
            vCarrera.getLblAccidente().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(0).getCompetidorModel().getPosicionActual() + 120,
                150
            );
            break;

        case "Sonic":
            // Mueve el label del competidor Sonic
            vCarrera.getLblSonic().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getPosicionActual(),
                250
            );
            // Mueve el label de impulso asociado
            vCarrera.getLblImpulso().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(1).getCompetidorModel().getPosicionActual() - 100,
                250
            );
            break;

        case "Goku":
            // Mueve el label del competidor Goku
            vCarrera.getLblGoku().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(2).getCompetidorModel().getPosicionActual(),
                340
            );
            break;

        case "Pikachu":
            // Mueve el label del competidor Pikachu
            vCarrera.getLblPikachu().setLocation(
                cPrinc.getcHilos().getCompetidoresThread().get(3).getCompetidorModel().getPosicionActual(),
                400
            );
            break;
    }
}


    /**
 * Desactiva los botones de acciones durante la ejecución de la carrera.
 * 
 * Esto evita que el usuario aplique impulsos, accidentes o salga de la carrera
 * mientras los hilos están en ejecución.
 */
public void inhabilitarBotonesAcciones() {
    vCarrera.getBtnAccidente().setEnabled(false);
    vCarrera.getBtnImpulsar().setEnabled(false);
    vCarrera.getBtnSalir().setEnabled(false);
}

/**
 * Habilita los botones de acciones para permitir la interacción del usuario
 * con los competidores durante la carrera.
 * 
 * Nota: El botón de salir permanece desactivado en este método.
 */
public void habilitarBotonesAcciones() {
    vCarrera.getBtnAccidente().setEnabled(true);
    vCarrera.getBtnImpulsar().setEnabled(true);
}


    /**
 * Desactiva el botón para iniciar la carrera.
 * 
 * Se usa una vez la carrera ha comenzado, para evitar que el usuario vuelva a hacer clic.
 */
public void inhabilitarBotonIniciarCarrera() {
    vCarrera.getBtnIniciar().setEnabled(false);
}

/**
 * Activa el botón para iniciar una nueva carrera y también el botón para salir.
 * 
 * Se usa después de finalizar o reiniciar una carrera.
 */
public void habilitarBotonIniciarCarrera() {
    vCarrera.getBtnIniciar().setEnabled(true);
    vCarrera.getBtnSalir().setEnabled(true);
}


    /**
 * Establece la imagen de fondo en la vista de inicio.
 * 
 * La imagen se carga desde la primera ruta disponible en la lista de imágenes
 * proporcionada por el controlador principal (`imagenesRutas`).
 * 
 * Se asigna al JLabel correspondiente mediante un `ImageIcon`.
 */
public void setearImagenesVInicio() {
    System.out.println(cPrinc.getImagenesRutas().get(0)); // Ruta de depuración

    ImageIcon imagenFondoInicio = new ImageIcon(getClass().getResource(
        cPrinc.getImagenesRutas().get(0)
    ));

    vView.getLblImagenInicio().setIcon(imagenFondoInicio);
}


    /**
 * Asigna las imágenes correspondientes a los elementos de la vista de carrera.
 * 
 * Las imágenes se obtienen desde las rutas cargadas previamente en `imagenesRutas`
 * y se asignan a los labels de cada competidor, del fondo de carrera, y de los efectos visuales
 * como accidente e impulso.
 * 
 * Índices usados:
 * - [1]: Fondo de la pista
 * - [2]: Goku
 * - [3]: NyanCat
 * - [4]: Pikachu
 * - [5]: Sonic
 * - [10]: Imagen de accidente
 * - [11]: Imagen de impulso
 */
public void setearImagenesVCarrera() {
    ImageIcon imagen;

    // Imagen de fondo de carrera
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(1)));
    vCarrera.getLblImagen().setIcon(imagen);

    // Imagen de Goku
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(2)));
    vCarrera.getLblGoku().setIcon(imagen);

    // Imagen de NyanCat
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(3)));
    vCarrera.getLblNyanCat().setIcon(imagen);

    // Imagen de Pikachu
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(4)));
    vCarrera.getLblPikachu().setIcon(imagen);

    // Imagen de Sonic
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(5)));
    vCarrera.getLblSonic().setIcon(imagen);

    // Imagen para el efecto de accidente
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(10)));
    vCarrera.getLblAccidente().setIcon(imagen);

    // Imagen para el efecto de impulso
    imagen = new ImageIcon(getClass().getResource(cPrinc.getImagenesRutas().get(11)));
    vCarrera.getLblImpulso().setIcon(imagen);
}


    /**
 * Asigna las imágenes correspondientes a cada competidor en la vista de ganador.
 * 
 * Las imágenes se obtienen desde la lista de rutas `imagenesRutas`, previamente cargada
 * por el usuario, y se asignan a los labels de cada personaje en la vista `GanadorView`.
 * 
 * Índices utilizados:
 * - [6]: Imagen de Goku
 * - [7]: Imagen de NyanCat
 * - [8]: Imagen de Pikachu
 * - [9]: Imagen de Sonic
 */
public void setearImagenesVGanador() {
    vGanador.getLblImagenGoku().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(6))
    ));

    vGanador.getLblImagenNyancat().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(7))
    ));

    vGanador.getLblImagenPikachu().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(8))
    ));

    vGanador.getLblImagenSonic().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(9))
    ));
}


    /**
 * Asigna las imágenes de los competidores a la vista final.
 * 
 * Este método utiliza las rutas previamente cargadas en `imagenesRutas` para
 * configurar los íconos correspondientes en la interfaz `FinalView`.
 * 
 * Índices utilizados:
 * - [2]: Imagen de Goku
 * - [3]: Imagen de NyanCat
 * - [4]: Imagen de Pikachu
 * - [5]: Imagen de Sonic
 */
public void setearImagenesVFinal() {
    vFinal.getLblGokuFinal().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(2))
    ));

    vFinal.getLblFinalNyan().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(3))
    ));

    vFinal.getLblFinalPikachu().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(4))
    ));

    vFinal.getLblFinalSonic().setIcon(new ImageIcon(
        getClass().getResource(cPrinc.getImagenesRutas().get(5))
    ));
}


    /**
 * Devuelve el número de carreras jugadas hasta el momento.
 * 
 * @return Número entero que representa la cantidad de carreras realizadas.
 */
public int getContadorCarreras() {
    return contadorCarreras;
}

/**
 * Establece el número de carreras jugadas.
 * 
 * @param contadorCarreras Valor entero a asignar al contador de carreras.
 */
public void setContadorCarreras(int contadorCarreras) {
    this.contadorCarreras = contadorCarreras;
}

/**
 * Devuelve la vista actual de la carrera.
 * 
 * @return Objeto CarreraView que representa la interfaz de la carrera.
 */
public CarreraView getvCarrera() {
    return vCarrera;
}

/**
 * Establece la vista actual de la carrera.
 * 
 * @param vCarrera Objeto CarreraView a asignar como vista de la carrera.
 */
public void setvCarrera(CarreraView vCarrera) {
    this.vCarrera = vCarrera;
}


}
