package vista;

import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Vista principal, la cual muestra los proyectos y su estado. Esta clase no
 * puede ser heredada (final).
 *
 * @author Carlos Aguirre
 */
public final class VistaPrincipal extends Vista {

    // ########################## CAMPOS ##########################
    private JPanel pnlGlobal, pnlNorte, pnlNorte1, pnlNorte2, pnlCentral,
            pnlSur, pnlCentroIzq, pnlCentroCentral;
    private JComboBox<String> cmbProyectos;
    private JButton btnNuevo, btnAbrir, btnEliminar, btnAcercaDe;
    private JLabel lblSelecciona, lblTituloInfo, lblNombreArchivoInfo,
            lblFechaCreacionInfo, lblFechaModificacionInfo, lblEstadoInfo,
            lblTitulo, lblNombreArchivo, lblFechaCreacion, lblFechaModificacion,
            lblEstado, lblTareasInfo, lblProcesosInfo, lblHechosInfo, lblTareas,
            lblProcesos, lblHechos;
    private final ControladorPrincipal controlador;

    // ########################## CONSTRUCTOR ##########################
    public VistaPrincipal(ControladorPrincipal controlador) {
        // Definir el controlador.
        this.controlador = controlador;

        // Pasos para la creacion de la ventana.
        this.crearElementos();
        this.crearDistribucion();
        this.colocarElementos();
        this.definirEstilos();
        this.eventos();

        // Propiedades de la ventana.
        super.setResizable(false);
        super.setTitle("Control de proyectos");
        super.definirTamanioVentana(220, 420);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // ########################## METODOS SOBRESCRITOS ##########################
    @Override
    protected void crearElementos() {
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
        lblTituloInfo = new JLabel("Título");
        lblNombreArchivoInfo = new JLabel("Nombre de archivo");
        lblFechaCreacionInfo = new JLabel("Fecha de creación");
        lblFechaModificacionInfo = new JLabel("Última modificación");
        lblEstadoInfo = new JLabel("Estado");
        lblTitulo = new JLabel("#");
        lblNombreArchivo = new JLabel("#");
        lblFechaCreacion = new JLabel("#");
        lblFechaModificacion = new JLabel("#");
        lblEstado = new JLabel("#");
        lblTareasInfo = new JLabel("Tareas", SwingConstants.CENTER);
        lblProcesosInfo = new JLabel("En proceso", SwingConstants.CENTER);
        lblHechosInfo = new JLabel("Hechos", SwingConstants.CENTER);
        lblTareas = new JLabel("--", SwingConstants.CENTER);
        lblProcesos = new JLabel("--", SwingConstants.CENTER);
        lblHechos = new JLabel("--", SwingConstants.CENTER);
    }

    @Override
    protected void crearDistribucion() {
        pnlGlobal.setLayout(new BorderLayout());
        pnlNorte.setLayout(new GridLayout(2, 1));
        pnlNorte1.setLayout(new BorderLayout());
        pnlNorte2.setLayout(new GridLayout(1, 3));
        pnlCentral.setLayout(new BorderLayout());
        pnlSur.setLayout(new GridLayout(2, 3, 25, 0));
        pnlCentroIzq.setLayout(new GridLayout(5, 1));
        pnlCentroCentral.setLayout(new GridLayout(5, 1));
    }

    @Override
    protected void colocarElementos() {
        super.getContentPane().add(pnlGlobal);

        pnlGlobal.add(pnlNorte, BorderLayout.NORTH);
        pnlGlobal.add(pnlCentral, BorderLayout.CENTER);
        pnlGlobal.add(pnlSur, BorderLayout.SOUTH);

        pnlNorte.add(pnlNorte1);
        pnlNorte.add(pnlNorte2);
        pnlNorte1.add(lblSelecciona, BorderLayout.WEST);
        pnlNorte1.add(cmbProyectos, BorderLayout.CENTER);
        pnlNorte1.add(btnAcercaDe, BorderLayout.EAST);
        pnlNorte2.add(btnNuevo);
        pnlNorte2.add(btnAbrir);
        pnlNorte2.add(btnEliminar);

        pnlCentral.add(pnlCentroIzq, BorderLayout.WEST);
        pnlCentral.add(pnlCentroCentral, BorderLayout.CENTER);
        pnlCentroIzq.add(lblTituloInfo);
        pnlCentroIzq.add(lblNombreArchivoInfo);
        pnlCentroIzq.add(lblFechaCreacionInfo);
        pnlCentroIzq.add(lblFechaModificacionInfo);
        pnlCentroIzq.add(lblEstadoInfo);
        pnlCentroCentral.add(lblTitulo);
        pnlCentroCentral.add(lblNombreArchivo);
        pnlCentroCentral.add(lblFechaCreacion);
        pnlCentroCentral.add(lblFechaModificacion);
        pnlCentroCentral.add(lblEstado);

        pnlSur.add(lblTareasInfo);
        pnlSur.add(lblProcesosInfo);
        pnlSur.add(lblHechosInfo);
        pnlSur.add(lblTareas);
        pnlSur.add(lblProcesos);
        pnlSur.add(lblHechos);
    }

    @Override
    protected void definirEstilos() {
        super.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/logo.png")));

        btnNuevo.setIcon(imgNuevo);
        btnAbrir.setIcon(imgAbrir);
        btnEliminar.setIcon(imgPapelera);
        lblTareasInfo.setIcon(imgTarea);
        lblProcesosInfo.setIcon(imgProceso);
        lblHechosInfo.setIcon(imgHecho);
        btnAcercaDe.setIcon(imgInfo);

        pnlGlobal.setBackground(COLOR_BACK);
        cmbProyectos.setBackground(COLOR_PROCESO_BACK);
        btnAcercaDe.setBackground(COLOR_PROCESO_BACK);
        btnNuevo.setBackground(COLOR_BACK);
        btnAbrir.setBackground(COLOR_HECHO_BACK);
        btnEliminar.setBackground(COLOR_REMOVE);

        lblSelecciona.setFont(FUENTE);
        cmbProyectos.setFont(FUENTE_NEGRITA);
        btnNuevo.setFont(FUENTE_NEGRITA);
        btnAbrir.setFont(FUENTE_NEGRITA);
        btnEliminar.setFont(FUENTE_NEGRITA);
        lblTituloInfo.setFont(FUENTE_NEGRITA);
        lblNombreArchivoInfo.setFont(FUENTE_NEGRITA);
        lblFechaCreacionInfo.setFont(FUENTE_NEGRITA);
        lblFechaModificacionInfo.setFont(FUENTE_NEGRITA);
        lblEstadoInfo.setFont(FUENTE_NEGRITA);
        lblTareasInfo.setFont(FUENTE_NEGRITA);
        lblProcesosInfo.setFont(FUENTE_NEGRITA);
        lblHechosInfo.setFont(FUENTE_NEGRITA);
        lblTitulo.setFont(FUENTE);
        lblNombreArchivo.setFont(FUENTE);
        lblFechaCreacion.setFont(FUENTE);
        lblFechaModificacion.setFont(FUENTE);
        lblEstado.setFont(FUENTE);
        lblTareas.setFont(FUENTE_GRANDE);
        lblProcesos.setFont(FUENTE_GRANDE);
        lblHechos.setFont(FUENTE_GRANDE);

        pnlGlobal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblSelecciona.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10));
        pnlNorte1.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlSur.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCentroIzq.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        lblTareas.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, COLOR_TAREA));
        lblProcesos.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, COLOR_PROCESO));
        lblHechos.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, COLOR_HECHO));

        btnNuevo.setFocusPainted(false);
        btnAbrir.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        btnAcercaDe.setFocusPainted(false);

        pnlCentral.setOpaque(false);
        pnlSur.setOpaque(false);
        pnlNorte.setOpaque(false);
        pnlNorte1.setOpaque(false);
        pnlNorte2.setOpaque(false);
        pnlCentroIzq.setOpaque(false);
        pnlCentroCentral.setOpaque(false);
    }

    @Override
    protected void eventos() {
        cmbProyectos.addActionListener((ActionEvent e) -> {
            controlador.mostrarInfoProyectoSeleccionado(cmbProyectos.getSelectedIndex());
        });

        btnAcercaDe.addActionListener((ActionEvent e) -> {
            controlador.mostrarVistaAcerca(true);
        });

        btnNuevo.addActionListener((ActionEvent e) -> {
            accionNuevoProyecto();
        });

        btnAbrir.addActionListener((ActionEvent e) -> {
            controlador.abrirProyectoSeleccionado();
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            controlador.eliminarProyectoSeleccionado();
        });
    }

    // ########################## METODOS ##########################
    private void accionNuevoProyecto() {
        String respuesta = pedirTexto("Nuevo proyecto", "");
        if (respuesta != null) {
            controlador.crearProyectoNuevo(respuesta);
        }
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
            setInfoProyecto("#", "#", "#", "#", "#", -1, -1, -1);
        }
    }

    public void setElementoSeleccionado(int indice) {
        if (indice < cmbProyectos.getItemCount()) {
            cmbProyectos.setSelectedIndex(indice);
        }
    }

    public void setInfoProyecto(String nombreProyecto, String nombreArchivo,
            String fechaCreacion, String fechaModificacion, String estado,
            int tareas, int procesos, int hechos) {

        lblTitulo.setText(nombreProyecto);
        lblNombreArchivo.setText(nombreArchivo);
        lblFechaCreacion.setText(fechaCreacion);
        lblFechaModificacion.setText(fechaModificacion);
        lblEstado.setText(estado);
        lblTareas.setText(tareas == -1 ? "--" : Integer.toString(tareas));
        lblProcesos.setText(procesos == -1 ? "--" : Integer.toString(procesos));
        lblHechos.setText(hechos == -1 ? "--" : Integer.toString(hechos));
    }

}
