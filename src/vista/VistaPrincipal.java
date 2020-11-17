package vista;

import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Vista principal, la cual muestra los proyectos y su estado.
 *
 * @author Carlos Aguirre
 */
public class VistaPrincipal extends JFrame {

    private static final int LIMITE_NOMBRE = 30;

    private ControladorPrincipal controlador;

    private JPanel pnlGlobal, pnlNorte, pnlNorte1, pnlNorte2, pnlCentral, pnlSur, pnlCentroIzq, pnlCentroCentral;

    private JComboBox<String> cmbProyectos;

    private JButton btnNuevo, btnAbrir, btnEliminar, btnAcercaDe;

    private JLabel lblSelecciona, lblInfoProyecto, lblTituloInfo, lblNombreArchivoInfo, lblFechaCreacionInfo, lblEstadoInfo, lblTitulo, lblNombreArchivo, lblFechaCreacion, lblEstado;
    private JLabel lblTareasInfo, lblProcesosInfo, lblHechosInfo;
    private JLabel lblTareas, lblProcesos, lblHechos;

    private final ImageIcon imgPapelera = new ImageIcon(getClass().getResource("/recursos/papelera.png"));
    private final ImageIcon imgTarea = new ImageIcon(getClass().getResource("/recursos/tarea.png"));
    private final ImageIcon imgProceso = new ImageIcon(getClass().getResource("/recursos/proceso.png"));
    private final ImageIcon imgHecho = new ImageIcon(getClass().getResource("/recursos/hecho.png"));
    private final ImageIcon imgAbrir = new ImageIcon(getClass().getResource("/recursos/abrir.png"));
    private final ImageIcon imgNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
    private final ImageIcon imgInfo = new ImageIcon(getClass().getResource("/recursos/info.png"));
    private final ImageIcon imgOk = new ImageIcon(getClass().getResource("/recursos/ok.png"));

    private final Color colorProceso = new Color(145, 185, 243);
    private final Color colorProcesoBack = new Color(227, 238, 254);
    private final Color colorHecho = new Color(139, 214, 1);
    private final Color colorHechoBack = new Color(227, 254, 234);
    private final Color colorRemove = new Color(254, 229, 238);
    private final Color colorBack = new Color(254, 249, 251);

    public VistaPrincipal(ControladorPrincipal controlador) {
        this.controlador = controlador;

        // Pasos para la creacion de la ventana.
        this.crearElementos();
        this.crearDistribucion();
        this.colocarElementos();
        this.definirEstilos();
        this.eventos();

        // Propiedades de la ventana.
        this.setResizable(false);
        this.setTitle("Control de proyectos");
        this.definirTamanioVentana(200, 370);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void crearElementos() {
        pnlGlobal = new JPanel();
        pnlNorte = new JPanel();
        pnlNorte1 = new JPanel();
        pnlNorte2 = new JPanel();
        pnlCentral = new JPanel();
        pnlSur = new JPanel();
        pnlCentroIzq = new JPanel();
        pnlCentroCentral = new JPanel();

        cmbProyectos = new JComboBox();

        btnNuevo = new JButton("Crear proyecto");
        btnAbrir = new JButton("Abrir seleccionado");
        btnEliminar = new JButton("Eliminar");
        btnAcercaDe = new JButton();

        lblSelecciona = new JLabel("Selecciona un proyecto");
        lblInfoProyecto = new JLabel("Vista previa", SwingConstants.CENTER);
        lblTituloInfo = new JLabel("Título");
        lblNombreArchivoInfo = new JLabel("Nombre de archivo");
        lblFechaCreacionInfo = new JLabel("Fecha de creación");
        lblEstadoInfo = new JLabel("Estado");
        lblTitulo = new JLabel("#");
        lblNombreArchivo = new JLabel("#");
        lblFechaCreacion = new JLabel("#");
        lblEstado = new JLabel("#");
        lblTareasInfo = new JLabel("Tareas", SwingConstants.CENTER);
        lblProcesosInfo = new JLabel("Procesos", SwingConstants.CENTER);
        lblHechosInfo = new JLabel("Hechos", SwingConstants.CENTER);
        lblTareas = new JLabel("--", SwingConstants.CENTER);
        lblProcesos = new JLabel("--", SwingConstants.CENTER);
        lblHechos = new JLabel("--", SwingConstants.CENTER);
    }

    private void crearDistribucion() {
        pnlGlobal.setLayout(new BorderLayout());
        pnlNorte.setLayout(new GridLayout(2, 1));
        pnlNorte1.setLayout(new BorderLayout());
        pnlNorte2.setLayout(new GridLayout(1, 3));
        pnlCentral.setLayout(new BorderLayout());
        pnlSur.setLayout(new GridLayout(2, 3));
        pnlCentroIzq.setLayout(new GridLayout(4, 1));
        pnlCentroCentral.setLayout(new GridLayout(4, 1));
    }

    private void colocarElementos() {
        this.getContentPane().add(pnlGlobal);
        pnlGlobal.add(pnlNorte, BorderLayout.NORTH);
        pnlNorte.add(pnlNorte1);
        pnlNorte.add(pnlNorte2);

        pnlNorte1.add(lblSelecciona, BorderLayout.WEST);
        pnlNorte1.add(cmbProyectos, BorderLayout.CENTER);
        pnlNorte1.add(btnAcercaDe, BorderLayout.EAST);
        pnlNorte2.add(btnNuevo);
        pnlNorte2.add(btnAbrir);
        pnlNorte2.add(btnEliminar);

        pnlGlobal.add(pnlCentral, BorderLayout.CENTER);
        pnlCentral.add(lblInfoProyecto, BorderLayout.NORTH);

        pnlCentral.add(pnlCentroIzq, BorderLayout.WEST);
        pnlCentral.add(pnlCentroCentral, BorderLayout.CENTER);

        pnlCentroIzq.add(lblTituloInfo);
        pnlCentroIzq.add(lblNombreArchivoInfo);
        pnlCentroIzq.add(lblFechaCreacionInfo);
        pnlCentroIzq.add(lblEstadoInfo);

        pnlCentroCentral.add(lblTitulo);
        pnlCentroCentral.add(lblNombreArchivo);
        pnlCentroCentral.add(lblFechaCreacion);
        pnlCentroCentral.add(lblEstado);

        pnlGlobal.add(pnlSur, BorderLayout.SOUTH);
        pnlSur.add(lblTareasInfo);
        pnlSur.add(lblProcesosInfo);
        pnlSur.add(lblHechosInfo);
        pnlSur.add(lblTareas);
        pnlSur.add(lblProcesos);
        pnlSur.add(lblHechos);
    }

    private void definirEstilos() {
        btnNuevo.setIcon(imgNuevo);
        btnAbrir.setIcon(imgAbrir);
        btnEliminar.setIcon(imgPapelera);
        lblInfoProyecto.setIcon(imgOk);
        lblTareasInfo.setIcon(imgTarea);
        lblProcesosInfo.setIcon(imgProceso);
        lblHechosInfo.setIcon(imgHecho);
        btnAcercaDe.setIcon(imgInfo);

        btnNuevo.setFocusPainted(false);
        btnAbrir.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        btnAcercaDe.setFocusPainted(false);

        pnlGlobal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblSelecciona.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        pnlNorte1.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlSur.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCentroIzq.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        pnlCentral.setBackground(colorProcesoBack);
        pnlSur.setBackground(colorProcesoBack);
        pnlGlobal.setBackground(colorBack);
        btnNuevo.setBackground(colorBack);
        btnAbrir.setBackground(colorHechoBack);
        btnEliminar.setBackground(colorRemove);

        pnlNorte.setOpaque(false);
        pnlNorte1.setOpaque(false);
        pnlNorte2.setOpaque(false);
        pnlCentroIzq.setOpaque(false);
        pnlCentroCentral.setOpaque(false);
    }

    private void eventos() {
        cmbProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.proyectoSeleccionado(cmbProyectos.getSelectedIndex());
            }
        });

        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoProyecto();
            }
        });

        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirProyectoSeleccionado();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.eliminarProyectoSeleccionado();
            }
        });
    }

    public void setListadoProyectos(String[] listado) {
        boolean hayProyectos = listado.length != 0;

        cmbProyectos.removeAllItems();

        cmbProyectos.setEnabled(hayProyectos);
        btnAbrir.setEnabled(hayProyectos);
        btnEliminar.setEnabled(hayProyectos);

        if (hayProyectos) {
            for (String proyecto : listado) {
                cmbProyectos.addItem(proyecto);
            }
        } else {
            setInfoProyecto("#", "#", "#", "#", -1, -1, -1);
        }
    }

    public void setInfoProyecto(
            String nombreProyecto,
            String nombreArchivo,
            String fechaCreacion,
            String estado,
            int tareas, int procesos, int hechos) {

        lblTitulo.setText(nombreProyecto);
        lblNombreArchivo.setText(nombreArchivo);
        lblFechaCreacion.setText(fechaCreacion);
        lblEstado.setText(estado);
        lblTareas.setText(tareas == -1 ? "--" : Integer.toString(tareas));
        lblProcesos.setText(procesos == -1 ? "--" : Integer.toString(procesos));
        lblHechos.setText(hechos == -1 ? "--" : Integer.toString(hechos));

    }

    private void definirTamanioVentana(double pxAlto, double pxAncho) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        double altoFinal = pantalla.height * pxAlto / 720;
        double anchoFinal = pantalla.width * pxAncho / 1280;

        setSize(new Dimension((int) anchoFinal, (int) altoFinal));
    }

    public void nuevoProyecto() {
        String respuesta = JOptionPane.showInputDialog(this, "Introduce el nombre del proyecto.\n(No debe exceder los " + LIMITE_NOMBRE + " caracteres)", "Nuevo proyecto", JOptionPane.INFORMATION_MESSAGE);

        if (respuesta != null) {
            respuesta = respuesta.trim();
            if (respuesta.length() > LIMITE_NOMBRE) {
                respuesta = respuesta.substring(0, LIMITE_NOMBRE);
            }
            controlador.crearProyectoNuevo(respuesta);
        }
    }

    public void mostrarMensaje(String mensaje, boolean esError) {
        JOptionPane.showMessageDialog(this, mensaje, "Aceptar",
                esError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean preguntaSeguridad(String mensaje) {
        int decision = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return decision == JOptionPane.YES_OPTION;
    }

}
