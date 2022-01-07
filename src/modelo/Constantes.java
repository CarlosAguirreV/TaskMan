package modelo;

/**
 * Aqui estan todas las constantes usadas por la aplicacion.
 *
 * @author Carlos Aguirre
 */
public interface Constantes {

    public static final String VERSION = "01-12-2022";
    public static final String WEB_GITHUB = "https://github.com/CarlosAguirreV/TaskMan.git";
    public static final String ARCHIVO_CONF = "configuracion.conf";
    public static final String EXTENSION = "prj";
    public static final String RUTA = "./proyectos/";
    public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm:ss";

    public static final int LIMITE_NOMBRE = 30;
    public static final byte ID_TAREAS = 0;
    public static final byte ID_PROCESOS = 1;
    public static final byte ID_HECHOS = 2;
    public static final byte INDICE_AUTO = -1;
    public static final byte NADA_SELECCIONADO = -1;
}
