package controlador;

import java.util.ArrayList;
import modelo.GuardarCargar;
import modelo.Proyecto;
import vista.VistaPrincipal;

/**
 *
 * @author Carlos Aguirre
 */
public class ControladorPrincipal {

    private GuardarCargar cargarGuardar;
    private ArrayList<Proyecto> coleccionProyectos;
    private VistaPrincipal vistaPrincipal;
    private Proyecto proyectoActual;

    public ControladorPrincipal(String version) {
        cargarGuardar = new GuardarCargar();
        vistaPrincipal = new VistaPrincipal(this);
        mostrarVentanaPrincipal();
    }

    public void proyectoSeleccionado(int indice) {
        if (indice != -1) {
            proyectoActual = coleccionProyectos.get(indice);

            vistaPrincipal.setInfoProyecto(
                    proyectoActual.getNombreProyecto(),
                    proyectoActual.getNombreArchivo() + "." + GuardarCargar.EXTENSION,
                    proyectoActual.getFechaCreacion(),
                    proyectoActual.getEstado(),
                    proyectoActual.getNumTareas(),
                    proyectoActual.getNumProcesos(),
                    proyectoActual.getNumHechos());
        }
    }

    public void crearProyectoNuevo(String nombre) {

        if (existeProyecto(nombre)) {
            vistaPrincipal.mostrarMensaje("Ya existe un proyecto con ese nombre, usa otro.", true);
        } else {
            proyectoActual = new Proyecto(nombre);

            if (cargarGuardar.guardarProyecto(proyectoActual)) {
                abrirProyectoSeleccionado();
            } else {
                vistaPrincipal.mostrarMensaje("No se ha podido guardar el nuevo proyecto.", true);
            }
        }
    }

    public void abrirProyectoSeleccionado() {
        if (proyectoActual != null) {
            vistaPrincipal.dispose();
            new ControlTareas(proyectoActual, this);
        }
    }

    public void eliminarProyectoSeleccionado() {
        if (proyectoActual != null) {
            if (vistaPrincipal.preguntaSeguridad("¿Estas seguro de que deseas borrarlo?")) {
                if (cargarGuardar.eliminarProyecto(proyectoActual)) {
                    refrescarListadoProyectos();
                } else {
                    vistaPrincipal.mostrarMensaje("No se ha podido eliminar el proyecto.", true);
                }
            }
        }
    }

    public void mostrarVentanaPrincipal() {
        refrescarListadoProyectos();
        vistaPrincipal.setVisible(true);
    }

    private void refrescarListadoProyectos() {
        coleccionProyectos = cargarGuardar.getProyectos();
        vistaPrincipal.setListadoProyectos(getListadoProyectos());
    }

    private String[] getListadoProyectos() {
        String[] listadoProyectos = new String[coleccionProyectos.size()];

        int i = 0;
        for (Proyecto prj : coleccionProyectos) {
            listadoProyectos[i++] = prj.getNombreProyecto();
        }

        return listadoProyectos;
    }

    private boolean existeProyecto(String nombreProyecto) {
        String nombreArchivo = Proyecto.generarNombreArchivo(nombreProyecto);
        boolean existe = false;

        for (Proyecto prj : coleccionProyectos) {
            if (prj.getNombreArchivo().equals(nombreArchivo)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

}
