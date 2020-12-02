package controlador;

import static modelo.Diccionario.*;
import modelo.GuardarCargar;
import modelo.Proyecto;
import static modelo.Constantes.*;
import vista.VistaTareas;

/**
 * Clase que controla todas las acciones de la aplicacion. Vista asociada:
 * VistaTareas.
 *
 * @author Carlos Aguirre
 */
public final class ControlTareas {

    // ########################## CAMPOS ##########################
    private Proyecto proyectoActual;
    private GuardarCargar cargarGuardar;
    private VistaTareas vistaTareas;
    private ControladorPrincipal controladorPrincipal;

    // ########################## CONSTRUCTOR ##########################
    public ControlTareas(Proyecto proyectoSeleccionado, ControladorPrincipal controladorPrincipal) {
        this.proyectoActual = proyectoSeleccionado;
        this.controladorPrincipal = controladorPrincipal;
        cargarGuardar = new GuardarCargar(); // Esto va a variar en funcion del proyecto seleccionado.
        vistaTareas = new VistaTareas(this, proyectoActual.getNombreProyecto());
        refrescarTodo();
    }

    // ########################## GUARDAR Y CARGAR ##########################
    public void guardarTodo() {
        if (!cargarGuardar.guardarProyecto(proyectoActual)) {
            vistaTareas.mostrarMensaje(NO_SE_PUDO_GUARDAR, true);
        }
    }

    // ########################## VOLVER AL INICIO ##########################
    public void volverInicio() {
        guardarTodo();
        vistaTareas.dispose();
        controladorPrincipal.mostrarVentanaPrincipal();
    }

    // ########################## ANIADIDOS ##########################
    public void addElemento(byte idElemento, String elementoNuevo) {
        switch (idElemento) {
            case ID_HECHOS:
                proyectoActual.addHecho(elementoNuevo);
                refrescarHechos(0);
                break;
            case ID_PROCESOS:
                proyectoActual.addProceso(elementoNuevo);
                refrescarProcesos(0);
                break;
            default:
                proyectoActual.addTarea(elementoNuevo);
                refrescarTareas(0);
                break;
        }
    }

    // ########################## MOVIMIENTOS ##########################
    public void tareaAProceso(int indice) {
        proyectoActual.addProceso(proyectoActual.removeTarea(indice));
        refrescarTareas(INDICE_AUTO);
        refrescarProcesos(INDICE_AUTO);
    }

    public void procesoATarea(int indice) {
        proyectoActual.addTarea(proyectoActual.removeProceso(indice));
        refrescarTareas(INDICE_AUTO);
        refrescarProcesos(INDICE_AUTO);
    }

    public void procesoAHecho(int indice) {
        proyectoActual.addHecho(proyectoActual.removeProceso(indice));
        refrescarHechos(INDICE_AUTO);
        refrescarProcesos(INDICE_AUTO);
    }

    public void hechoAProceso(int indice) {
        proyectoActual.addProceso(proyectoActual.removeHecho(indice));
        refrescarHechos(INDICE_AUTO);
        refrescarProcesos(INDICE_AUTO);
    }

    public void subirTarea(int indice) {
        if (indice != -1) {
            refrescarTareas(proyectoActual.subirTarea(indice));
        }
    }

    public void bajarTarea(int indice) {
        if (indice != -1) {
            refrescarTareas(proyectoActual.bajarTarea(indice));
        }
    }

    public void subirProceso(int indice) {
        if (indice != -1) {
            refrescarProcesos(proyectoActual.subirProceso(indice));
        }
    }

    public void bajarProceso(int indice) {
        if (indice != -1) {
            refrescarProcesos(proyectoActual.bajarProceso(indice));
        }
    }

    public void subirHecho(int indice) {
        if (indice != -1) {
            refrescarHechos(proyectoActual.subirHecho(indice));
        }
    }

    public void bajarHecho(int indice) {
        if (indice != -1) {
            refrescarHechos(proyectoActual.bajarHecho(indice));
        }
    }

    // ########################## MODIFICAR ##########################
    public void modificarTarea(int indiceInvertido, int indiceListado) {
        if (indiceListado != NADA_SELECCIONADO) {
            String nombreNuevo = vistaTareas.pedirNuevoNombreElemento(proyectoActual.getNombreTarea(indiceInvertido));

            if (nombreNuevo != null) {
                proyectoActual.setNombreTarea(nombreNuevo, indiceInvertido);
                refrescarTareas(indiceListado);
            }
        }
    }

    public void modificarProceso(int indiceInvertido, int indiceListado) {
        if (indiceListado != NADA_SELECCIONADO) {
            String nombreNuevo = vistaTareas.pedirNuevoNombreElemento(proyectoActual.getNombreProceso(indiceInvertido));

            if (nombreNuevo != null) {
                proyectoActual.setNombreProceso(nombreNuevo, indiceInvertido);
                refrescarProcesos(indiceListado);
            }
        }
    }

    public void modificarHecho(int indiceInvertido, int indiceListado) {
        if (indiceListado != NADA_SELECCIONADO) {
            String nombreNuevo = vistaTareas.pedirNuevoNombreElemento(proyectoActual.getNombreHecho(indiceInvertido));

            if (nombreNuevo != null) {
                proyectoActual.setNombreHecho(nombreNuevo, indiceInvertido);
                refrescarHechos(indiceListado);
            }
        }
    }

    // ########################## ELIMINAR ##########################
    public void eliminarTarea(int indice) {
        proyectoActual.removeTarea(indice);
        refrescarTareas(INDICE_AUTO);
    }

    public void eliminarProceso(int indice) {
        proyectoActual.removeProceso(indice);
        refrescarProcesos(INDICE_AUTO);
    }

    public void eliminarHecho(int indice) {
        proyectoActual.removeHecho(indice);
        refrescarHechos(INDICE_AUTO);
    }

    // ########################## CAMBIAR NOMBRE PROYECTO ##########################
    public void setNombreProyecto(String nuevoNombre) {
        proyectoActual.setNombreProyecto(nuevoNombre);
        controladorPrincipal.nombreProyectoCambiado(proyectoActual.getNombreProyecto());
    }

    // ########################## METODOS AUXILIARES ##########################
    private void refrescarTareas(int posicionIndice) {
        vistaTareas.refrescarLista(ID_TAREAS, proyectoActual.getTareas(), posicionIndice);
    }

    private void refrescarProcesos(int posicionIndice) {
        vistaTareas.refrescarLista(ID_PROCESOS, proyectoActual.getProcesos(), posicionIndice);
    }

    private void refrescarHechos(int posicionIndice) {
        vistaTareas.refrescarLista(ID_HECHOS, proyectoActual.getHechos(), posicionIndice);
    }

    private void refrescarTodo() {
        refrescarTareas(INDICE_AUTO);
        refrescarProcesos(INDICE_AUTO);
        refrescarHechos(INDICE_AUTO);
    }
}
