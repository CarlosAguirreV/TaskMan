package modelo;

import static modelo.Constantes.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Esta clase se encarga del proceso de almacenar y recuperar datos.
 *
 * @author Carlos Aguirre
 */
public class GuardarCargar {

    private File ruta;
    private File archivo;

    public GuardarCargar() {
        this.ruta = new File(RUTA);

        // Si la carpeta no existe la crea.
        if (!ruta.exists()) {
            try {
                Files.createDirectories(Paths.get(RUTA));
            } catch (Exception ex) {
                System.out.println("No se ha podido crear la carpeta.");
            }
        }
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

        // Pedirle al proyecto que marque la fecha en que se modifico.
        proyecto.marcarFechaModificacion();

        // Guardar el objeto en un archivo.
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

    public boolean guardarConfiguracion(Configuracion datos) {
        boolean todoCorrecto = true;
        archivo = new File(ruta, ARCHIVO_CONF);

        // Guardar el objeto en un archivo.
        // Al ponerlo asi se cierran automaticamente los escritores.
        try (FileOutputStream archivoSalida = new FileOutputStream(archivo);
                ObjectOutputStream escritorObj = new ObjectOutputStream(archivoSalida);) {
            escritorObj.writeObject(datos);
            archivoSalida.close();
            escritorObj.close();
        } catch (Exception ex) {
            todoCorrecto = false;
        }

        return todoCorrecto;
    }

    public Configuracion cargarConfiguracion() {
        Configuracion datos;
        archivo = new File(ruta, ARCHIVO_CONF);

        try (FileInputStream archivoEntrada = new FileInputStream(archivo);
                ObjectInputStream lectorObj = new ObjectInputStream(archivoEntrada);) {
            datos = (Configuracion) lectorObj.readObject();
            archivoEntrada.close();
            lectorObj.close();
        } catch (Exception ex) {
            datos = null;
        }

        return datos;
    }

}
