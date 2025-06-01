/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Carrera;
import Modelo.Competidor;
import Modelo.ConexionProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Clase principal del controlador que coordina la ejecución de la carrera.
 * 
 * Administra la interacción entre la vista, la lógica de la carrera,
 * los competidores, la configuración y los hilos de ejecución.
 * 
 * @author sangr
 */
public class ControlPrincipal {

    /**
     * Controlador de la lógica principal de la carrera.
     */
    private ControlCarrera cCarrera;

    /**
     * Controlador encargado de manejar los hilos de los competidores.
     */
    private ControlHilos cHilos;

    /**
     * Controlador de la interfaz gráfica de usuario.
     */
    private ControlVentana cVentana;

    /**
     * Manejador de la configuración externa definida en un archivo de propiedades.
     */
    private ConexionProperties conxPro;

    /**
     * Lista de rutas de imágenes utilizadas en la aplicación (por ejemplo, para los competidores).
     */
    private ArrayList<String> imagenesRutas;

    /**
     * Constructor principal que inicializa los controladores de ventana, carrera y hilos.
     * También crea la lista de rutas de imágenes vacía.
     */
    public ControlPrincipal() {
        imagenesRutas = new ArrayList<>();

        // Inicializa la interfaz gráfica
        cVentana = new ControlVentana(this);

        // Inicializa la lógica de la carrera
        cCarrera = new ControlCarrera(this);

        // Inicializa los hilos de los competidores
        cHilos = new ControlHilos(this);
    }


    /**
 * Inicia una nueva carrera desde cero.
 * 
 * Este método realiza las siguientes acciones:
 * - Establece el tiempo inicial de la carrera.
 * - Habilita los botones de acciones durante la carrera.
 * - Lanza los hilos de los competidores.
 * - Inhabilita los botones una vez iniciada la carrera.
 * - Muestra al ganador en la interfaz gráfica.
 * - Reinicia las posiciones y tiempos de los competidores para la siguiente carrera.
 * - Reinicia los valores de duración y estado finalizado de la carrera.
 * - Vuelve a habilitar el botón para iniciar una nueva carrera.
 */
public void iniciarCarrera() {

    // Registra el tiempo en que inicia la carrera
    getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());

    // Habilita botones para aplicar acciones como accidentes o impulsos
    cVentana.habilitarBotonesAcciones();

    // Inicia los hilos de cada competidor
    cHilos.iniciarHilos();

    // Una vez iniciados, se inhabilitan los botones para evitar interferencias
    inhabilitarBotonesAcciones();

    // Carga la vista de ganadores y muestra al primer lugar
    cVentana.cargarVistaGanador();
    cVentana.mostrarGanador(determinarGanadores().get(0).getCompetidorModel().getNombre());

    // Reinicia datos de los competidores para una nueva carrera
    for (int i = 0; i < cHilos.getCompetidoresThread().size(); i++) {
        cHilos.getCompetidoresThread().get(i).getCompetidorModel().setPosicionActual(0);
        cHilos.getCompetidoresThread().get(i).getCompetidorModel().setTiempoLlegada(0);
    }

    // Reinicia datos globales de la carrera
    cCarrera.getCarrera().setDuracionCarrera(0);
    getcCarrera().getCarrera().setTiempoInicial(System.currentTimeMillis());
    cCarrera.getCarrera().setEsFinalizada(false);

    // Habilita nuevamente el botón para permitir iniciar otra carrera
    cVentana.habilitarBotonIniciarCarrera();
}


    /**
 * Marca al primer competidor (NyanCat) como accidentado directamente desde la lista de hilos.
 * Este cambio afecta su ejecución dentro del hilo correspondiente.
 */
public void setearAccidenteTrue() {
    cHilos.getCompetidoresThread().get(0).setIsAccidentado(true);
}

/**
 * Aplica un accidente al competidor "NyanCat" si la carrera aún no ha finalizado.
 * Se utiliza el método aplicarAccidente del controlador de hilos.
 */
public void aplicarAccidente1() {
    if (!cCarrera.getCarrera().isEsFinalizada()) {
        cHilos.aplicarAccidente("NyanCat");
    }
}

/**
 * Marca al segundo competidor (Sonic) como impulsado directamente desde la lista de hilos.
 * El efecto del impulso será visible en la próxima ejecución del hilo del competidor.
 */
public void setearImpulsoTrue() {
    cHilos.getCompetidoresThread().get(1).setIsImpulsado(true);
}

/**
 * Aplica un impulso al competidor "Sonic" si la carrera aún no ha finalizado.
 * Se utiliza el método impulsar del controlador de hilos.
 */
public void aplicarImpulso2() {
    if (!cCarrera.getCarrera().isEsFinalizada()) {
        cHilos.impulsar("Sonic");
    }
}

    /**
 * Finaliza la carrera verificando si algún competidor ha llegado a la meta.
 * 
 * Este método está sincronizado para evitar conflictos entre hilos que puedan
 * intentar finalizar la carrera al mismo tiempo.
 * 
 * Llama al método terminarCarrera() del controlador de carrera, pasando la lista
 * de hilos de competidores como argumento.
 */
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
    
    /**
 * Determina los ganadores de la carrera actual.
 * 
 * Este método delega la lógica de verificación al método determinarGanadores()
 * del controlador de carrera, pasando la lista de competidores actuales.
 * 
 * @return Lista de objetos CompetidorThread que ganaron la carrera.
 */
    public ArrayList<CompetidorThread> determinarGanadores() {
        return cCarrera.determinarGanadores(cHilos.getCompetidoresThread());
        //System.out.println("El ganador es: "+ cCarrera.determinarGanadores(cHilos.getCompetidoresThread()).get(0).getCompetidorModel().getNombre());

    }

    //Método para convertir el tiempo dado en milisegundos a segundos (mas entendible para el usuario)
    public String formatearTiempo(long tiempoMs) {
        double tiempoSeg = tiempoMs / 1000.0;
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(tiempoSeg) + " s";
    }

    /**
 * Inhabilita los botones de acciones (como aplicar impulso o accidente)
 * en la interfaz gráfica durante la ejecución de la carrera.
 */
public void inhabilitarBotonesAcciones() {
    cVentana.inhabilitarBotonesAcciones();
}

/**
 * Habilita los botones de acciones en la interfaz gráfica,
 * permitiendo interactuar con los competidores durante la carrera.
 */
public void habilitarBotonesAcciones() {
    cVentana.habilitarBotonesAcciones();
}

//////////////////////////7
///
    /**
 * Devuelve la lista de rutas de imágenes utilizadas en la aplicación.
 * 
 * @return Lista de strings que representan rutas de imágenes.
 */
public ArrayList<String> getImagenesRutas() {
    return imagenesRutas;
}

/**
 * Establece una nueva lista de rutas de imágenes.
 * 
 * @param imagenesRutas Lista de rutas de imágenes a asignar.
 */
public void setImagenesRutas(ArrayList<String> imagenesRutas) {
    this.imagenesRutas = imagenesRutas;
}

    
    

    /**
 * Inicializa el arreglo de rutas de imágenes a partir de un archivo de propiedades.
 * 
 * El archivo debe contener claves en el formato `1img`, `2img`, ..., `12img`,
 * donde cada valor representa la ruta de una imagen.
 * 
 * Si el archivo es válido, se cargan las propiedades y se agregan a la lista `imagenesRutas`.
 * Si el archivo es nulo o hay un error de lectura, no se realiza ninguna acción.
 * 
 * @param archivo Archivo de propiedades desde el cual se leerán las rutas de imágenes.
 */
public void inicializarArrayImagenes(File archivo) {
    if (archivo != null) {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(fis);

            // Carga las rutas de imágenes desde las propiedades (1img a 12img)
            for (int i = 1; i <= 12; i++) {
                imagenesRutas.add(props.getProperty(i + "img"));
            }

        } catch (IOException e) {
            // Manejo silencioso del error (se podría mejorar mostrando un mensaje o registrando el error)
        }
    } else {
        // Archivo es nulo, no se realiza ninguna acción
    }
}

///////////////////////
    /**
 * Devuelve el controlador de la carrera.
 * 
 * @return Objeto ControlCarrera asociado a la carrera actual.
 */
public ControlCarrera getcCarrera() {
    return cCarrera;
}

/**
 * Devuelve el controlador de los hilos de los competidores.
 * 
 * @return Objeto ControlHilos que administra los hilos de ejecución.
 */
public ControlHilos getcHilos() {
    return cHilos;
}

/**
 * Devuelve el controlador de la interfaz gráfica.
 * 
 * @return Objeto ControlVentana que maneja la vista del programa.
 */
public ControlVentana getcVentana() {
    return cVentana;
}

/**
 * Establece el controlador de la interfaz gráfica.
 * 
 * @param cVentana Objeto ControlVentana a asignar como controlador de la vista.
 */
public void setcVentana(ControlVentana cVentana) {
    this.cVentana = cVentana;
}


}
