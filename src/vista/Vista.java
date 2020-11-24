package vista;

import static modelo.Constantes.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static modelo.Diccionario.*;

/**
 * Clase abstracta que contiene los elementos comunes entre las vistas.
 *
 * @author Carlos Aguirre
 */
public abstract class Vista extends JFrame {

    // ########################## CAMPOS ##########################
    protected static final Font FUENTE = new Font("Default", Font.PLAIN, 15);
    protected static final Font FUENTE_NEGRITA = new Font("Default", Font.BOLD, 15);
    protected static final Font FUENTE_GRANDE = new Font("Default", Font.BOLD, 30);

    protected static final Color COLOR_TAREA = new Color(242, 181, 57);
    protected static final Color COLOR_TAREA_BACK = new Color(254, 239, 208);
    protected static final Color COLOR_PROCESO = new Color(145, 185, 243);
    protected static final Color COLOR_PROCESO_BACK = new Color(227, 238, 254);
    protected static final Color COLOR_HECHO = new Color(139, 214, 1);
    protected static final Color COLOR_HECHO_BACK = new Color(227, 254, 234);
    protected static final Color COLOR_REMOVE = new Color(254, 229, 238);
    protected static final Color COLOR_BACK = new Color(254, 249, 251);
    protected static final Color COLOR_DONAR = new Color(180, 70, 0);
    protected static final Color COLOR_GITHUB = new Color(38, 41, 46);
    protected static final Color COLOR_CERRAR = new Color(54, 2, 21);

    protected final ImageIcon imgPapelera = new ImageIcon(getClass().getResource("/recursos/papelera.png"));
    protected final ImageIcon imgTarea = new ImageIcon(getClass().getResource("/recursos/tarea.png"));
    protected final ImageIcon imgProceso = new ImageIcon(getClass().getResource("/recursos/proceso.png"));
    protected final ImageIcon imgHecho = new ImageIcon(getClass().getResource("/recursos/hecho.png"));
    protected final ImageIcon imgAbrir = new ImageIcon(getClass().getResource("/recursos/abrir.png"));
    protected final ImageIcon imgNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
    protected final ImageIcon imgInfo = new ImageIcon(getClass().getResource("/recursos/info.png"));
    protected final ImageIcon imgOk = new ImageIcon(getClass().getResource("/recursos/ok.png"));
    protected final ImageIcon imgEditar = new ImageIcon(getClass().getResource("/recursos/editar.png"));
    protected final ImageIcon imgInicio = new ImageIcon(getClass().getResource("/recursos/inicio.png"));
    protected final ImageIcon imgDcha = new ImageIcon(getClass().getResource("/recursos/dcha.png"));
    protected final ImageIcon imgIzq = new ImageIcon(getClass().getResource("/recursos/izq.png"));
    protected final ImageIcon imgArriba = new ImageIcon(getClass().getResource("/recursos/arriba.png"));
    protected final ImageIcon imgAbajo = new ImageIcon(getClass().getResource("/recursos/abajo.png"));
    protected final ImageIcon imgLogo = new ImageIcon(getClass().getResource("/recursos/logo.png"));
    protected final ImageIcon imgCerrar = new ImageIcon(getClass().getResource("/recursos/cerrar.png"));
    protected final ImageIcon imgDonar = new ImageIcon(getClass().getResource("/recursos/donar.png"));
    protected final ImageIcon imgGitHub = new ImageIcon(getClass().getResource("/recursos/github.png"));

    protected final ArrayList<Image> icono;

    // ########################## CONSTRUCTOR PREVIO ##########################
    {
        icono = new ArrayList();
        icono.add(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/icono1.png")));
        icono.add(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/icono2.png")));
        icono.add(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/icono3.png")));
        icono.add(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/icono4.png")));
        this.setIconImages(icono);
        this.setTitle(NOMBRE_APLICACION);
    }

    // ########################## METODOS ABSTRACTOS ##########################
    // Crea todos los elementos de la ventana.
    protected abstract void crearElementos();

    // Define las distribuciones que ha de tener cada panel.
    protected abstract void crearDistribucion();

    // Coloca cada elemento en su respectivo panel.
    protected abstract void colocarElementos();

    // Define los estilos de todos los elementos.
    protected abstract void definirEstilos();

    // Controla los eventos de la ventana.
    protected abstract void eventos();

    // ########################## METODOS DEFINIDOS ##########################
    protected void definirTamanioVentana(double pxAlto, double pxAncho) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        double altoFinal = pantalla.height * pxAlto / 720;
        double anchoFinal = pantalla.width * pxAncho / 1280;

        setSize(new Dimension((int) anchoFinal, (int) altoFinal));
    }

    public void mostrarMensaje(String mensaje, boolean esError) {
        JOptionPane.showMessageDialog(this, mensaje, ACEPTAR,
                esError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }

    public String pedirNuevoNombrePrj(String titulo, String textoPorDefecto) {
        Object objetoRecibido = JOptionPane.showInputDialog(this, INTRODUCE_NOMBRE_PRJ, titulo, JOptionPane.INFORMATION_MESSAGE, null, null, textoPorDefecto);
        String cadenaRecibida = null;

        if (objetoRecibido != null) {
            cadenaRecibida = objetoRecibido.toString();

            // Quitar espacios.
            cadenaRecibida = cadenaRecibida.trim();

            if (cadenaRecibida.isEmpty()) {
                cadenaRecibida = null;
            } else {
                // Limitar nombre.
                if (cadenaRecibida.length() > LIMITE_NOMBRE) {
                    cadenaRecibida = cadenaRecibida.substring(0, LIMITE_NOMBRE);
                }
            }

        }

        return cadenaRecibida;
    }

    public String pedirNuevoNombreElemento(String textoAnterior) {
        Object objetoRecibido = JOptionPane.showInputDialog(this, INTRODUCE_NOMBRE_ELEMENTO, MODIFICAR, JOptionPane.INFORMATION_MESSAGE, null, null, textoAnterior);
        String cadenaRecibida = null;

        if (objetoRecibido != null) {
            cadenaRecibida = objetoRecibido.toString();

            // Quitar espacios.
            cadenaRecibida = cadenaRecibida.trim();

            if (cadenaRecibida.isEmpty()) {
                cadenaRecibida = null;
            }
        }

        return cadenaRecibida;
    }

    public void mostrarLinkWeb(String url) {
        JOptionPane.showInputDialog(this, FALLO_NAVEGADOR, DONATIVO, JOptionPane.INFORMATION_MESSAGE, null, null, url);
    }

    public boolean preguntaSeguridad(String mensaje) {
        int decision = JOptionPane.showConfirmDialog(this, mensaje, CONFIRMAR, JOptionPane.YES_NO_OPTION);
        return decision == JOptionPane.YES_OPTION;
    }

}
