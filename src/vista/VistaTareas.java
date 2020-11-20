package vista;

import controlador.ControlTareas;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Vista que permite gestionar las tareas. Esta clase no puede ser heredada
 * (final).
 *
 * @author Carlos Aguirre
 */
public final class VistaTareas extends Vista {

    // ########################## CAMPOS ##########################
    private JPanel pnlGlobal, pnlNorte, pnlCentral, pnlTareas, pnlProceso,
            pnlHecho;
    private JPanel pnlInfTarea, pnlInfProceso, pnlInfHecho,
            pnlControlTarea, pnlControlProceso, pnlControlHecho,
            pnlTNTarea, pnlTNProceso, pnlTNHecho;
    private JScrollPane pnlScrTareas, pnlScrProceso, pnlScrHecho;
    private JLabel lblTTarea, lblTProceso, lblTHecho, lblTitulo;
    private JList<String> listaTarea, listaProceso, listaHecho;
    private DefaultListModel<String> lmTareas, lmProceso, lmHecho;
    private JTextField txtTarea, txtProceso, txtHecho;
    private JButton btnInicio, btnEditar, btnTareaOk, btnProcesoOk, btnHechoOk,
            btnDchaTarea, btnRemoveTarea, btnIzqProceso, btnRemoveProceso,
            btnDchaProceso, btnIzqHecho, btnRemoveHecho,
            btnTareaArriba, btnTareaAbajo,
            btnProcesoArriba, btnProcesoAbajo,
            btnHechoArriba, btnHechoAbajo;
    private final ControlTareas controlador;

    // ########################## CONSTRUCTOR ##########################
    public VistaTareas(ControlTareas controlador, String nombreProyecto) {
        // Definir el controlador.
        this.controlador = controlador;

        // Pasos para la creacion de la ventana.
        this.crearElementos();
        this.crearDistribucion();
        this.colocarElementos();
        this.definirEstilos();
        this.eventos();

        // Propiedades de la ventana.
        super.setResizable(true);
        super.setTitle("Task Man");
        super.definirTamanioVentana(450, 700);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.lblTitulo.setText(nombreProyecto);
        super.setVisible(true);
    }

    // ########################## METODOS SOBRESCRITOS ##########################
    @Override
    protected void crearElementos() {
        pnlGlobal = new JPanel();
        pnlNorte = new JPanel();
        pnlCentral = new JPanel();
        pnlTareas = new JPanel();
        pnlProceso = new JPanel();
        pnlHecho = new JPanel();

        pnlInfTarea = new JPanel();
        pnlInfProceso = new JPanel();
        pnlInfHecho = new JPanel();

        lblTitulo = new JLabel("#", SwingConstants.CENTER);
        lblTTarea = new JLabel("#", SwingConstants.CENTER);
        lblTProceso = new JLabel("#", SwingConstants.CENTER);
        lblTHecho = new JLabel("#", SwingConstants.CENTER);

        lmTareas = new DefaultListModel();
        lmProceso = new DefaultListModel();
        lmHecho = new DefaultListModel();

        listaTarea = new JList(lmTareas);
        listaProceso = new JList(lmProceso);
        listaHecho = new JList(lmHecho);

        pnlScrTareas = new JScrollPane(listaTarea);
        pnlScrProceso = new JScrollPane(listaProceso);
        pnlScrHecho = new JScrollPane(listaHecho);

        txtTarea = new JTextField();
        txtProceso = new JTextField();
        txtHecho = new JTextField();

        btnInicio = new JButton(imgInicio);
        btnEditar = new JButton(imgEditar);
        btnTareaOk = new JButton(imgOk);
        btnProcesoOk = new JButton(imgOk);
        btnHechoOk = new JButton(imgOk);

        pnlControlTarea = new JPanel();
        pnlControlProceso = new JPanel();
        pnlControlHecho = new JPanel();

        btnDchaTarea = new JButton(imgDcha);
        btnRemoveTarea = new JButton(imgPapelera);

        btnIzqProceso = new JButton(imgIzq);
        btnRemoveProceso = new JButton(imgPapelera);
        btnDchaProceso = new JButton(imgDcha);

        btnIzqHecho = new JButton(imgIzq);
        btnRemoveHecho = new JButton(imgPapelera);

        pnlTNTarea = new JPanel();
        pnlTNProceso = new JPanel();
        pnlTNHecho = new JPanel();
        btnTareaArriba = new JButton(imgArriba);
        btnTareaAbajo = new JButton(imgAbajo);
        btnProcesoArriba = new JButton(imgArriba);
        btnProcesoAbajo = new JButton(imgAbajo);
        btnHechoArriba = new JButton(imgArriba);
        btnHechoAbajo = new JButton(imgAbajo);
    }

    @Override
    protected void crearDistribucion() {
        pnlGlobal.setLayout(new BorderLayout());
        pnlNorte.setLayout(new BorderLayout());
        pnlCentral.setLayout(new GridLayout(1, 3, 7, 0));
        pnlTareas.setLayout(new BorderLayout());
        pnlProceso.setLayout(new BorderLayout());
        pnlHecho.setLayout(new BorderLayout());

        pnlInfTarea.setLayout(new BorderLayout());
        pnlInfProceso.setLayout(new BorderLayout());
        pnlInfHecho.setLayout(new BorderLayout());

        pnlControlTarea.setLayout(new GridLayout(1, 3));
        pnlControlProceso.setLayout(new GridLayout(1, 3));
        pnlControlHecho.setLayout(new GridLayout(1, 3));

        pnlTNTarea.setLayout(new BorderLayout());
        pnlTNProceso.setLayout(new BorderLayout());
        pnlTNHecho.setLayout(new BorderLayout());
    }

    @Override
    protected void colocarElementos() {
        super.getContentPane().add(pnlGlobal);

        pnlGlobal.add(pnlNorte, BorderLayout.NORTH);
        pnlGlobal.add(pnlCentral, BorderLayout.CENTER);

        pnlNorte.add(lblTitulo, BorderLayout.CENTER);
        pnlNorte.add(btnInicio, BorderLayout.WEST);
        pnlNorte.add(btnEditar, BorderLayout.EAST);

        pnlCentral.add(pnlTareas);
        pnlCentral.add(pnlProceso);
        pnlCentral.add(pnlHecho);

        pnlTareas.add(pnlTNTarea, BorderLayout.NORTH);
        pnlTareas.add(pnlScrTareas, BorderLayout.CENTER);
        pnlTareas.add(pnlInfTarea, BorderLayout.SOUTH);

        pnlTNTarea.add(lblTTarea, BorderLayout.CENTER);
        pnlTNTarea.add(btnTareaArriba, BorderLayout.WEST);
        pnlTNTarea.add(btnTareaAbajo, BorderLayout.EAST);

        pnlProceso.add(pnlTNProceso, BorderLayout.NORTH);
        pnlProceso.add(pnlScrProceso, BorderLayout.CENTER);
        pnlProceso.add(pnlInfProceso, BorderLayout.SOUTH);

        pnlTNProceso.add(lblTProceso, BorderLayout.CENTER);
        pnlTNProceso.add(btnProcesoArriba, BorderLayout.WEST);
        pnlTNProceso.add(btnProcesoAbajo, BorderLayout.EAST);

        pnlHecho.add(pnlTNHecho, BorderLayout.NORTH);
        pnlHecho.add(pnlScrHecho, BorderLayout.CENTER);
        pnlHecho.add(pnlInfHecho, BorderLayout.SOUTH);

        pnlTNHecho.add(lblTHecho, BorderLayout.CENTER);
        pnlTNHecho.add(btnHechoArriba, BorderLayout.WEST);
        pnlTNHecho.add(btnHechoAbajo, BorderLayout.EAST);

        pnlInfTarea.add(txtTarea, BorderLayout.CENTER);
        pnlInfTarea.add(btnTareaOk, BorderLayout.EAST);
        pnlInfTarea.add(pnlControlTarea, BorderLayout.SOUTH);

        pnlInfProceso.add(txtProceso, BorderLayout.CENTER);
        pnlInfProceso.add(btnProcesoOk, BorderLayout.EAST);
        pnlInfProceso.add(pnlControlProceso, BorderLayout.SOUTH);

        pnlInfHecho.add(txtHecho, BorderLayout.CENTER);
        pnlInfHecho.add(btnHechoOk, BorderLayout.EAST);
        pnlInfHecho.add(pnlControlHecho, BorderLayout.SOUTH);

        pnlControlTarea.add(new JLabel());
        pnlControlTarea.add(btnRemoveTarea);
        pnlControlTarea.add(btnDchaTarea);

        pnlControlProceso.add(btnIzqProceso);
        pnlControlProceso.add(btnRemoveProceso);
        pnlControlProceso.add(btnDchaProceso);

        pnlControlHecho.add(btnIzqHecho);
        pnlControlHecho.add(btnRemoveHecho);
        pnlControlHecho.add(new JLabel());
    }

    @Override
    protected void definirEstilos() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/logo.png")));

        lblTTarea.setIcon(imgTarea);
        lblTProceso.setIcon(imgProceso);
        lblTHecho.setIcon(imgHecho);

        pnlGlobal.setBackground(COLOR_BACK);
        pnlTareas.setBackground(COLOR_TAREA);
        pnlProceso.setBackground(COLOR_PROCESO);
        pnlHecho.setBackground(COLOR_HECHO);
        listaTarea.setBackground(COLOR_TAREA_BACK);
        listaProceso.setBackground(COLOR_PROCESO_BACK);
        listaHecho.setBackground(COLOR_HECHO_BACK);
        btnInicio.setBackground(COLOR_BACK);
        btnEditar.setBackground(COLOR_BACK);
        btnRemoveTarea.setBackground(COLOR_REMOVE);
        btnRemoveProceso.setBackground(COLOR_REMOVE);
        btnRemoveHecho.setBackground(COLOR_REMOVE);
        btnDchaTarea.setBackground(COLOR_PROCESO_BACK);
        btnIzqProceso.setBackground(COLOR_TAREA_BACK);
        btnDchaProceso.setBackground(COLOR_HECHO_BACK);
        btnIzqHecho.setBackground(COLOR_PROCESO_BACK);
        listaTarea.setSelectionBackground(COLOR_TAREA);
        listaProceso.setSelectionBackground(COLOR_PROCESO);
        listaHecho.setSelectionBackground(COLOR_HECHO);

        lblTitulo.setFont(FUENTE_NEGRITA);
        listaTarea.setFont(FUENTE_NEGRITA);
        listaProceso.setFont(FUENTE_NEGRITA);
        listaHecho.setFont(FUENTE_NEGRITA);
        txtTarea.setFont(FUENTE_NEGRITA);
        txtProceso.setFont(FUENTE_NEGRITA);
        txtHecho.setFont(FUENTE_NEGRITA);
        lblTTarea.setFont(FUENTE_NEGRITA);
        lblTProceso.setFont(FUENTE_NEGRITA);
        lblTHecho.setFont(FUENTE_NEGRITA);

        txtTarea.setMargin(new Insets(0, 5, 0, 0));
        txtProceso.setMargin(new Insets(0, 5, 0, 0));
        txtHecho.setMargin(new Insets(0, 5, 0, 0));

        pnlGlobal.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        listaTarea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        listaProceso.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        listaHecho.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlTareas.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlProceso.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlHecho.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblTTarea.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
        lblTProceso.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
        lblTHecho.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));

        btnInicio.setFocusPainted(false);
        btnEditar.setFocusPainted(false);
        btnRemoveTarea.setFocusPainted(false);
        btnRemoveProceso.setFocusPainted(false);
        btnRemoveHecho.setFocusPainted(false);
        btnDchaTarea.setFocusPainted(false);
        btnIzqProceso.setFocusPainted(false);
        btnDchaProceso.setFocusPainted(false);
        btnIzqHecho.setFocusPainted(false);
        btnRemoveTarea.setFocusPainted(false);
        btnRemoveProceso.setFocusPainted(false);
        btnRemoveHecho.setFocusPainted(false);
        btnTareaOk.setFocusPainted(false);
        btnProcesoOk.setFocusPainted(false);
        btnHechoOk.setFocusPainted(false);

        pnlCentral.setOpaque(false);
        pnlTNTarea.setOpaque(false);
        pnlTNProceso.setOpaque(false);
        pnlTNHecho.setOpaque(false);

        listaTarea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProceso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaHecho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaTarea.setVisibleRowCount(-1);
        listaProceso.setVisibleRowCount(-1);
        listaHecho.setVisibleRowCount(-1);

        auxEstiloBotonSimple(btnTareaArriba);
        auxEstiloBotonSimple(btnTareaAbajo);
        auxEstiloBotonSimple(btnProcesoArriba);
        auxEstiloBotonSimple(btnProcesoAbajo);
        auxEstiloBotonSimple(btnHechoArriba);
        auxEstiloBotonSimple(btnHechoAbajo);
    }

    @Override
    protected void eventos() {
        btnInicio.addActionListener((ActionEvent e) -> {
            controlador.volverInicio();
        });

        btnEditar.addActionListener((ActionEvent e) -> {
            String nombreNuevo = pedirTexto("Editar", lblTitulo.getText());
            if (nombreNuevo != null) {
                controlador.setNombreProyecto(nombreNuevo);
                lblTitulo.setText(nombreNuevo);
            }
        });

        txtTarea.addActionListener((ActionEvent e) -> {
            addElemento(ID_TAREAS);
        });

        btnTareaOk.addActionListener((ActionEvent e) -> {
            addElemento(ID_TAREAS);
        });

        txtProceso.addActionListener((ActionEvent e) -> {
            addElemento(ID_PROCESOS);
        });

        btnProcesoOk.addActionListener((ActionEvent e) -> {
            addElemento(ID_PROCESOS);
        });

        txtHecho.addActionListener((ActionEvent e) -> {
            addElemento(ID_HECHOS);
        });

        btnHechoOk.addActionListener((ActionEvent e) -> {
            addElemento(ID_HECHOS);
        });

        btnDchaTarea.addActionListener((ActionEvent e) -> {
            int indiceSeleccionado = listaTarea.getSelectedIndex();
            if (indiceSeleccionado > -1) {
                controlador.tareaAProceso((lmTareas.size() - 1) - indiceSeleccionado);
            }
        });

        btnIzqProceso.addActionListener((ActionEvent e) -> {
            int indiceSeleccionado = listaProceso.getSelectedIndex();
            if (indiceSeleccionado > -1) {
                controlador.procesoATarea((lmProceso.size() - 1) - indiceSeleccionado);
            }
        });

        btnDchaProceso.addActionListener((ActionEvent e) -> {
            int indiceSeleccionado = listaProceso.getSelectedIndex();
            if (indiceSeleccionado > -1) {
                controlador.procesoAHecho((lmProceso.size() - 1) - indiceSeleccionado);
            }
        });

        btnIzqHecho.addActionListener((ActionEvent e) -> {
            int indiceSeleccionado = listaHecho.getSelectedIndex();
            if (indiceSeleccionado > -1) {
                controlador.hechoAProceso((lmHecho.size() - 1) - indiceSeleccionado);
            }
        });

        btnRemoveTarea.addActionListener((ActionEvent e) -> {
            int indice = listaTarea.getSelectedIndex();
            if (indice > -1) {
                controlador.eliminarTarea((lmTareas.size() - 1) - indice);
            }
        });

        btnRemoveProceso.addActionListener((ActionEvent e) -> {
            int indice = listaProceso.getSelectedIndex();
            if (indice > -1) {
                controlador.eliminarProceso((lmProceso.size() - 1) - indice);
            }
        });

        btnRemoveHecho.addActionListener((ActionEvent e) -> {
            int indice = listaHecho.getSelectedIndex();
            if (indice > -1) {
                controlador.eliminarHecho((lmHecho.size() - 1) - indice);
            }
        });

        btnTareaArriba.addActionListener((ActionEvent e) -> {
            controlador.subirTarea(listaTarea.getSelectedIndex());
        });

        btnTareaAbajo.addActionListener((ActionEvent e) -> {
            controlador.bajarTarea(listaTarea.getSelectedIndex());
        });

        btnProcesoArriba.addActionListener((ActionEvent e) -> {
            controlador.subirProceso(listaProceso.getSelectedIndex());
        });

        btnProcesoAbajo.addActionListener((ActionEvent e) -> {
            controlador.bajarProceso(listaProceso.getSelectedIndex());
        });

        btnHechoArriba.addActionListener((ActionEvent e) -> {
            controlador.subirHecho(listaHecho.getSelectedIndex());
        });

        btnHechoAbajo.addActionListener((ActionEvent e) -> {
            controlador.bajarHecho(listaHecho.getSelectedIndex());
        });

        // Al cerrar esta ventana (JFrame).
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.guardarTodo();
            }
        });
    }

    // ########################## METODOS ##########################
    public void refrescarLista(byte idLista, String[] cadenas, int indice) {
        DefaultListModel modeloLista;
        JList listaActual;
        int indiceSeleccionado;
        JLabel lblTitulo;
        JButton btnSubir;
        JButton btnBajar;

        switch (idLista) {
            case ID_HECHOS:
                modeloLista = lmHecho;
                listaActual = listaHecho;
                lblTitulo = lblTHecho;
                lblTitulo.setText("Hechos");
                btnSubir = btnHechoArriba;
                btnBajar = btnHechoAbajo;
                break;
            case ID_PROCESOS:
                modeloLista = lmProceso;
                listaActual = listaProceso;
                lblTitulo = lblTProceso;
                lblTitulo.setText("En proceso");
                btnSubir = btnProcesoArriba;
                btnBajar = btnProcesoAbajo;
                break;
            default:
                modeloLista = lmTareas;
                listaActual = listaTarea;
                lblTitulo = lblTTarea;
                lblTitulo.setText("Lista de tareas");
                btnSubir = btnTareaArriba;
                btnBajar = btnTareaAbajo;
                break;
        }

        indiceSeleccionado = listaActual.getSelectedIndex();
        modeloLista.clear();

        for (int i = cadenas.length - 1; i > -1; i--) {
            modeloLista.addElement(cadenas[i]);
        }

        // Si el indice es -1 significa que el indice se va a desplazar de forma automatica ya que se ha aniadido un elemento.
        if (indice == -1) {
            if (modeloLista.size() - 1 < indiceSeleccionado) {
                indiceSeleccionado--;
            }
            if (!modeloLista.isEmpty() && indiceSeleccionado < 0) {
                indiceSeleccionado = 0;
            }
            listaActual.setSelectedIndex(indiceSeleccionado);
        } else {
            listaActual.setSelectedIndex(indice);
        }

        if (cadenas.length != 0) {
            lblTitulo.setText(lblTitulo.getText() + " [" + cadenas.length + "]");
        }

        if (cadenas.length > 1) {
            btnSubir.setEnabled(true);
            btnBajar.setEnabled(true);
        } else {
            btnSubir.setEnabled(false);
            btnBajar.setEnabled(false);
        }
    }

    private void addElemento(byte idElemento) {
        String elementoNuevo;

        switch (idElemento) {
            case ID_TAREAS:
                elementoNuevo = txtTarea.getText();
                txtTarea.setText("");
                break;
            case ID_PROCESOS:
                elementoNuevo = txtProceso.getText();
                txtProceso.setText("");
                break;
            default:
                elementoNuevo = txtHecho.getText();
                txtHecho.setText("");
                break;
        }

        elementoNuevo = elementoNuevo.trim();

        if (!elementoNuevo.isEmpty()) {
            controlador.addElemento(idElemento, elementoNuevo);
        }
    }

    // ########################## METODOS AUXILIARES ##########################
    private void auxEstiloBotonSimple(JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
    }
}
