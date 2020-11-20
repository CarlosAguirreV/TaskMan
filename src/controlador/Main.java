package controlador;

import vista.VistaAcerca;

/**
 * Clase principal que ejecuta la aplicacion. Esta aplicacion permite gestionar
 * listas de tareas de forma simple. Trabaja con archivos ".prj" almacenados en
 * la carpeta proyectos los cuales puedes mover con total libertad.
 *
 * @author Carlos Aguirre
 */
public class Main {

    public final static String VERSION = "20-11-2020";
    public final static String WEB_DONATIVO = "https://ko-fi.com/carlosaguirrev";

    public static void main(String[] args) {
        new ControladorPrincipal(VERSION);
    }

}
