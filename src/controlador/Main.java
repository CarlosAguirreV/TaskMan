package controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import vista.VistaPrincipal;

/**
 * Clase principal que ejecuta la aplicacion. Esta aplicacion permite gestionar
 * listas de tareas de forma simple. Trabaja con archivos ".prj" almacenados en
 * la carpeta proyectos los cuales puedes mover con total libertad.
 *
 * @author Carlos Aguirre
 */
public class Main {

    private final static String VERSION = "17-11-2020";

    public static void main(String[] args) {
//        new ControlTareas();
        new ControladorPrincipal(VERSION);
    }

}
