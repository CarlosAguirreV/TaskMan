package controlador;

import static modelo.Constantes.*;
import java.util.ArrayList;
import modelo.Configuracion;
import modelo.Diccionario;
import static modelo.Diccionario.*;
import modelo.GuardarCargar;
import modelo.Proyecto;
import vista.VistaAcerca;
import vista.VistaPrincipal;

/**
 * Clase que controla los proyectos. Vista asociada: VistaPrincipal.
 *
 * @author Carlos Aguirre
 */
public final class ControladorPrincipal {

    // ########################## CAMPOS ##########################
    private final GuardarCargar cargarGuardar;
    private final VistaPrincipal vistaPrincipal;
    private final VistaAcerca vistaAcerca;
    private ArrayList<Proyecto> coleccionProyectos;
    private Proyecto proyectoActual;
    private Configuracion configuracion;

    // ########################## CONSTRUCTOR ##########################
    public ControladorPrincipal(String version) {
        cargarGuardar = new GuardarCargar();
        vistaPrincipal = new VistaPrincipal(this);
        vistaAcerca = new VistaAcerca(this);

        cargarConfiguracion();
        mostrarVentanaPrincipal();
    }

    // ########################## METODOS ##########################
    public void mostrarInfoProyectoSeleccionado(int indiceLista) {
        if (indiceLista != -1) {
            proyectoActual = coleccionProyectos.get(indiceLista);

            vistaPrincipal.setInfoProyecto(
                    proyectoActual.getNombreProyecto(),
                    proyectoActual.getNombreArchivo() + "." + EXTENSION,
                    proyectoActual.getFechaCreacion(),
                    proyectoActual.getFechaModificacion(),
                    proyectoActual.getEstado(),
                    proyectoActual.getNumTareas(),
                    proyectoActual.getNumProcesos(),
                    proyectoActual.getNumHechos());
        }
    }

    public void crearProyectoNuevo(String nombre) {
        if (existeProyecto(nombre)) {
            vistaPrincipal.mostrarMensaje(YA_EXISTE_PROYECTO, true);
        } else {
            proyectoActual = new Proyecto(nombre);

            if (cargarGuardar.guardarProyecto(proyectoActual)) {
                abrirProyectoSeleccionado();
            } else {
                vistaPrincipal.mostrarMensaje(Diccionario.NO_SE_PUDO_GUARDAR_PRJ, true);
            }
        }
    }

    private void seleccionarUltimoProyectoAbierto() {
        vistaPrincipal.setElementoSeleccionado(getIndiceProyecto(configuracion.getNombreUltimoProyectoAbierto()));
    }

    public void abrirProyectoSeleccionado() {
        if (proyectoActual != null) {
            vistaPrincipal.dispose();

            configuracion.setNombreUltimoProyectoAbierto(proyectoActual.getNombreProyecto());
            guardarConfiguracion();

            new ControlTareas(proyectoActual, this);
        }
    }

    public void eliminarProyectoSeleccionado() {
        if (proyectoActual != null) {
            if (vistaPrincipal.preguntaSeguridad(SEGURO_BORRAR)) {
                if (cargarGuardar.eliminarProyecto(proyectoActual)) {
                    cargarListadoProyectos();
                } else {
                    vistaPrincipal.mostrarMensaje(NO_SE_PUDO_ELIMINAR_PRJ, true);
                }
            }
        }
    }

    private void cargarConfiguracion() {
        this.configuracion = cargarGuardar.cargarConfiguracion();

        if (this.configuracion == null) {
            this.configuracion = new Configuracion();
            guardarConfiguracion();
        }
    }

    private void guardarConfiguracion() {
        if (this.configuracion != null) {
            if (!cargarGuardar.guardarConfiguracion(this.configuracion)) {
                vistaPrincipal.mostrarMensaje(NO_SE_PUDO_GUARDAR_CONF, true);
            }
        }
    }

    private int getIndiceProyecto(String nombre) {
        if (nombre != null) {
            int i = 0;
            for (Proyecto prj : coleccionProyectos) {
                if (prj.getNombreProyecto().equals(nombre)) {
                    break;
                }
                i++;
            }
            return i;
        } else {
            return 0;
        }
    }

    public void mostrarVentanaPrincipal() {
        cargarListadoProyectos();
        seleccionarUltimoProyectoAbierto();
        vistaPrincipal.setVisible(true);
    }

    private void cargarListadoProyectos() {
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

    public void mostrarVistaAcerca(boolean mostrar) {
        vistaAcerca.setVisible(mostrar);
        vistaPrincipal.setVisible(!mostrar);
    }
}
