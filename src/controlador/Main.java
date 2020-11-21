package controlador;

import static modelo.Constantes.*;

/**
 * Clase principal que ejecuta la aplicacion. Esta aplicacion permite gestionar
 * listas de tareas de forma simple. Trabaja con archivos ".prj" almacenados en
 * la carpeta proyectos los cuales puedes mover con total libertad.
 *
 * @author Carlos Aguirre
 */
public class Main {

    public static void main(String[] args) {
        new ControladorPrincipal(VERSION);
    }

}
