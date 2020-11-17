package controlador;

import modelo.GuardarCargar;
import modelo.Proyecto;
import vista.VistaTareas;

/**
 * Clase que controla todas las acciones de la aplicacion.
 *
 * @author Carlos Aguirre
 */
public class ControlTareas {

    private Proyecto proyectoActual;
    private GuardarCargar cargarGuardar;
    private VistaTareas vistaProyecto;
    private ControladorPrincipal controladorPrincipal;

    public ControlTareas(Proyecto proyectoSeleccionado, ControladorPrincipal controladorPrincipal) {
        this.proyectoActual = proyectoSeleccionado;
        this.controladorPrincipal = controladorPrincipal;
        cargarGuardar = new GuardarCargar(); // Esto va a variar en funcion del proyecto seleccionado.
        vistaProyecto = new VistaTareas(this, proyectoActual.getNombreProyecto());
        refrescarTodo();
    }

    // ########################## GUARDAR Y CARGAR ##########################
    public void guardarTodo() {
        if (!cargarGuardar.guardarProyecto(proyectoActual)) {
            vistaProyecto.mostrarMensaje("No se ha podido guardar el proceso.", true);
        }
    }

    // ########################## VOLVER AL INICIO ##########################
    public void volverInicio() {
        guardarTodo();
        vistaProyecto.dispose();
        controladorPrincipal.mostrarVentanaPrincipal();
    }

    // ########################## ANIADIDOS ##########################
    public void addTarea(String tarea) {
        proyectoActual.addTarea(tarea);
        vistaProyecto.refrescarListaTareas(proyectoActual.getTareas());
    }

    public void addProceso(String proceso) {
        proyectoActual.addProceso(proceso);
        vistaProyecto.refrescarListaProcesos(proyectoActual.getProcesos());
    }

    public void addHecho(String hecho) {
        proyectoActual.addHecho(hecho);
        vistaProyecto.refrescarListaHechos(proyectoActual.getHechos());
    }

    // ########################## MOVIMIENTOS ##########################
    public void tareaAProceso(int indice) {
        proyectoActual.addProceso(proyectoActual.removeTarea(indice));
        refrescarTareas();
        refrescarProcesos();
    }

    public void procesoATarea(int indice) {
        proyectoActual.addTarea(proyectoActual.removeProceso(indice));
        refrescarTareas();
        refrescarProcesos();
    }

    public void procesoAHecho(int indice) {
        proyectoActual.addHecho(proyectoActual.removeProceso(indice));
        refrescarHechos();
        refrescarProcesos();
    }

    public void hechoAProceso(int indice) {
        proyectoActual.addProceso(proyectoActual.removeHecho(indice));
        refrescarHechos();
        refrescarProcesos();
    }

    // ########################## ELIMINAR ##########################
    public void eliminarTarea(int indice) {
        proyectoActual.removeTarea(indice);
        refrescarTareas();
    }

    public void eliminarProceso(int indice) {
        proyectoActual.removeProceso(indice);
        refrescarProcesos();
    }

    public void eliminarHecho(int indice) {
        proyectoActual.removeHecho(indice);
        refrescarHechos();
    }

    // ########################## METODOS AUXILIARES ##########################
    private void refrescarTareas() {
        vistaProyecto.refrescarListaTareas(proyectoActual.getTareas());
    }

    private void refrescarProcesos() {
        vistaProyecto.refrescarListaProcesos(proyectoActual.getProcesos());
    }

    private void refrescarHechos() {
        vistaProyecto.refrescarListaHechos(proyectoActual.getHechos());
    }

    private void refrescarTodo() {
        refrescarTareas();
        refrescarProcesos();
        refrescarHechos();
    }
}
