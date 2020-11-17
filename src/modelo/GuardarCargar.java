package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Carlos Aguirre
 */
public class GuardarCargar {

    private File ruta;
    private File archivo;
    public static final String EXTENSION = "prj";
    private static final String RUTA = "./proyectos/";

    public GuardarCargar() {
        this.ruta = new File(RUTA);
    }

    // Si no existe el proyecto crea uno nuevo.
    public Proyecto cargarProyecto(String nombreArchivo) {
        Proyecto proyectoLeido;
        archivo = new File(ruta, nombreArchivo);

        try (FileInputStream archivoEntrada = new FileInputStream(archivo);
                ObjectInputStream lectorObj = new ObjectInputStream(archivoEntrada);) {
            proyectoLeido = (Proyecto) lectorObj.readObject();
            archivoEntrada.close();
            lectorObj.close();
        } catch (Exception ex) {
            proyectoLeido = null;
        }

        return proyectoLeido;
    }

    // Guarda el proyecto pasado por parametro, si no ha podido hacerlo retorna false.
    public boolean guardarProyecto(Proyecto proyecto) {
        boolean todoCorrecto = true;
        archivo = new File(ruta, proyecto.getNombreArchivo() + "." + EXTENSION);

        // Al ponerlo asi se cierran automaticamente los escritores.
        try (FileOutputStream archivoSalida = new FileOutputStream(archivo);
                ObjectOutputStream escritorObj = new ObjectOutputStream(archivoSalida);) {
            escritorObj.writeObject(proyecto);
            archivoSalida.close();
            escritorObj.close();
        } catch (Exception ex) {
            todoCorrecto = false;
        }

        return todoCorrecto;
    }

    public boolean eliminarProyecto(Proyecto proyectoActual) {
        archivo = new File(RUTA, proyectoActual.getNombreArchivo() + "." + EXTENSION);
        return archivo.delete();
    }

    // Lee todos los archivos de la carpeta, abre solo los que tengan extension ".prj" y devuelve un ArrayList con todos ellos.
    public ArrayList<Proyecto> getProyectos() {
        ArrayList<Proyecto> proyectos = new ArrayList();

        for (File archivoLeido : ruta.listFiles()) {
            // Obtener el nombre de archivo.
            String nombreArchivo = archivoLeido.getName();

            // Cribar solo quedarme con aquellos cuya extension sea ".prj".
            String[] nombreSeparado = nombreArchivo.split("\\.");
            if (nombreSeparado.length > 1) {
                if (nombreSeparado[1].equals(EXTENSION)) {

                    // Ahora hay que intentar abrirlo, si se puede se almacena, de lo contrario se descarta.
                    Proyecto proyectoCargado = cargarProyecto(nombreArchivo);
                    if (proyectoCargado != null) {

                        // Si el nombre del archivo no corresponde con el nombre del archivo almacenado,
                        //entonces se cambia y guarda automaticamente el nuevo nombre del proyecto.
                        if (!proyectoCargado.getNombreArchivo().equals(nombreSeparado[0])) {
                            proyectoCargado.setNombreProyecto(nombreSeparado[0]);
                            proyectoCargado.setNombreArchivo(nombreSeparado[0]);
                            guardarProyecto(proyectoCargado);
                        }

                        // Almacenar el proyecto en el ArrayList.
                        proyectos.add(proyectoCargado);
                    }

                }
            }
        }
        return proyectos;
    }

}
