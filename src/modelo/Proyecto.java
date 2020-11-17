package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Esta clase contiene los datos que se van a guardar.
 *
 * @author Carlos Aguirre
 */
public class Proyecto implements Serializable {

    // ########################## CAMPOS ##########################
    private String nombreProyecto;
    private String nombreArchivo;
    private final String fechaCreacion;
    private final ArrayList<String> coleccionTareas;
    private final ArrayList<String> coleccionProcesos;
    private final ArrayList<String> coleccionHechos;

    // ########################## CONSTRUCTOR ##########################
    public Proyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.nombreArchivo = generarNombreArchivo(nombreProyecto);
        this.fechaCreacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        coleccionTareas = new ArrayList();
        coleccionProcesos = new ArrayList();
        coleccionHechos = new ArrayList();
    }

    // ########################## NOMBRE PROYECTO ##########################
    public void setNombreProyecto(String nombre) {
        this.nombreProyecto = nombre;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreArchivo(String nuevoNombre) {
        nombreArchivo = nuevoNombre;
    }

    // ########################## TAREAS ##########################
    public void addTarea(String tarea) {
        coleccionTareas.add(tarea);
    }

    public String[] getTareas() {
        return coleccionTareas.toArray(new String[0]);
    }

    public String removeTarea(int indice) {
        return coleccionTareas.remove(indice);
    }

    public int getNumTareas() {
        return coleccionTareas.size();
    }

    // ########################## PROCESOS ##########################
    public void addProceso(String proceso) {
        coleccionProcesos.add(proceso);
    }

    public String[] getProcesos() {
        return coleccionProcesos.toArray(new String[0]);
    }

    public String removeProceso(int indice) {
        return coleccionProcesos.remove(indice);
    }

    public int getNumProcesos() {
        return coleccionProcesos.size();
    }

    // ########################## HECHOS ##########################
    public void addHecho(String hecho) {
        coleccionHechos.add(hecho);
    }

    public String[] getHechos() {
        return coleccionHechos.toArray(new String[0]);
    }

    public String removeHecho(int indice) {
        return coleccionHechos.remove(indice);
    }

    public int getNumHechos() {
        return coleccionHechos.size();
    }

    // ########################## OTROS METODOS ##########################
    public String getEstado() {
        String estado;
        if (coleccionTareas.isEmpty() && coleccionProcesos.isEmpty() && !coleccionHechos.isEmpty()) {
            estado = "Terminado";
        } else if (coleccionTareas.isEmpty() && coleccionProcesos.isEmpty() && coleccionHechos.isEmpty()) {
            estado = "Sin empezar";
        } else if (!coleccionTareas.isEmpty() || !coleccionProcesos.isEmpty()) {
            estado = "En proceso";
        } else {
            estado = "Empezado";
        }

        return estado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public static String generarNombreArchivo(String nombreProyecto) {
        String[] palabras = nombreProyecto.toLowerCase().trim().split(" ");
        String nombreArchivo = "";

        for (String palabra : palabras) {
            palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1, palabra.length());
            nombreArchivo += palabra;
        }

        return nombreArchivo;
    }

    @Override
    public String toString() {
        return "Título: " + getNombreProyecto() + "\n"
                + "Nombre archivo: " + getNombreArchivo() + "\n"
                + "Fecha de creación: " + getFechaCreacion() + "\n"
                + "Estado: " + getEstado() + "\n"
                + "Tareas " + getNumTareas() + " / Procesos " + getNumProcesos() + " / Hechos " + getNumHechos();
    }

}
