package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static modelo.Constantes.*;
import static modelo.Diccionario.*;

/**
 * Esta clase contiene los datos que se van a guardar.
 *
 * @author Carlos Aguirre
 */
public class Proyecto implements Serializable {

    // ########################## CAMPOS ##########################
    private String nombreProyecto;
    private String nombreArchivo;
    private String fechaUltimaModificacion;
    private final String fechaCreacion;
    private final ArrayList<String> coleccionTareas;
    private final ArrayList<String> coleccionProcesos;
    private final ArrayList<String> coleccionHechos;

    // ########################## CONSTRUCTOR ##########################
    public Proyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.nombreArchivo = generarNombreArchivo(nombreProyecto);
        this.fechaCreacion = getFechaActual();
        this.fechaUltimaModificacion = "";
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

    public int subirTarea(int indice) {
        return subirElemento(indice, coleccionTareas);
    }

    public int bajarTarea(int indice) {
        return bajarElemento(indice, coleccionTareas);
    }

    public void setNombreTarea(String nuevoNombre, int indice) {
        coleccionTareas.set(indice, nuevoNombre);
    }

    public String getNombreTarea(int indice) {
        return coleccionTareas.get(indice);
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

    public int subirProceso(int indice) {
        return subirElemento(indice, coleccionProcesos);
    }

    public int bajarProceso(int indice) {
        return bajarElemento(indice, coleccionProcesos);
    }

    public void setNombreProceso(String nuevoNombre, int indice) {
        coleccionProcesos.set(indice, nuevoNombre);
    }

    public String getNombreProceso(int indice) {
        return coleccionProcesos.get(indice);
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

    public int subirHecho(int indice) {
        return subirElemento(indice, coleccionHechos);
    }

    public int bajarHecho(int indice) {
        return bajarElemento(indice, coleccionHechos);
    }

    public void setNombreHecho(String nuevoNombre, int indice) {
        coleccionHechos.set(indice, nuevoNombre);
    }

    public String getNombreHecho(int indice) {
        return coleccionHechos.get(indice);
    }

    // ########################## OTROS METODOS ##########################
    public String getEstado() {
        String estado;
        if (coleccionTareas.isEmpty() && coleccionProcesos.isEmpty() && !coleccionHechos.isEmpty()) {
            estado = ESTADO_PRJ[2];
        } else if (coleccionTareas.isEmpty() && coleccionProcesos.isEmpty() && coleccionHechos.isEmpty()) {
            estado = ESTADO_PRJ[0];
        } else {
            estado = ESTADO_PRJ[1];
        }

        return estado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getFechaModificacion() {
        return fechaUltimaModificacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public static String generarNombreArchivo(String nombreProyecto) {
        String[] palabras = nombreProyecto.toLowerCase().trim().split(" ");
        String nombreArchivo = "";

        for (String palabra : palabras) {
            if (palabra.isEmpty()) {
                continue;
            }
            palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1, palabra.length());
            nombreArchivo += palabra;
        }

        return nombreArchivo;
    }

    public void marcarFechaModificacion() {
        this.fechaUltimaModificacion = getFechaActual();
    }

    private static String getFechaActual() {
        return new SimpleDateFormat(FORMATO_FECHA_HORA).format(Calendar.getInstance().getTime());
    }

    private int subirElemento(int indice, ArrayList<String> coleccion) {
        int indiceMax = coleccion.size() - 1;
        int indiceReal = indiceMax - indice;

        if (indiceReal < indiceMax) {
            String tareaAnterior = coleccion.get(indiceReal + 1);
            coleccion.set(indiceReal + 1, coleccion.get(indiceReal));
            coleccion.set(indiceReal, tareaAnterior);

            return indice - 1;
        }

        return INDICE_AUTO;
    }

    private int bajarElemento(int indice, ArrayList<String> coleccion) {
        int indiceMax = coleccion.size() - 1;
        int indiceReal = indiceMax - indice;

        if (indiceReal > 0) {
            String tareaAnterior = coleccion.get(indiceReal - 1);
            coleccion.set(indiceReal - 1, coleccion.get(indiceReal));
            coleccion.set(indiceReal, tareaAnterior);

            return indice + 1;
        }

        return INDICE_AUTO;
    }

    @Override
    public String toString() {
        return "Título: " + getNombreProyecto() + "\n"
                + "Nombre archivo: " + getNombreArchivo() + "\n"
                + "Fecha de creación: " + getFechaCreacion() + "\n"
                + "Fecha de modificacion: " + getFechaModificacion() + "\n"
                + "Estado: " + getEstado() + "\n"
                + "Tareas " + getNumTareas() + " / Procesos " + getNumProcesos() + " / Hechos " + getNumHechos();
    }

}
