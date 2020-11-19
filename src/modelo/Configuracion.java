package modelo;

import java.io.Serializable;

/**
 * Modelo que permite almacenar el nombre del ultimo proyecto abierto.
 *
 * @author Carlos Aguirre
 */
public class Configuracion implements Serializable {

    private String nombreUltimoProyectoAbierto;

    public Configuracion() {
    }

    public String getNombreUltimoProyectoAbierto() {
        return nombreUltimoProyectoAbierto;
    }

    public void setNombreUltimoProyectoAbierto(String nombreUltimoProyectoAbierto) {
        this.nombreUltimoProyectoAbierto = nombreUltimoProyectoAbierto;
    }

    @Override
    public String toString() {
        return "DatosPrograma:\n" + "Nombre del ultimo proyecto abierto: " + nombreUltimoProyectoAbierto;
    }
}
