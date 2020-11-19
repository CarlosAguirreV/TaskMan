package vista;

import controlador.ControlTareas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Carlos Aguirre
 */
public class VistaTareas extends JFrame {

    private JPanel pnlGlobal, pnlNorte, pnlCentral, pnlTareas, pnlProceso, pnlHecho;
    private JPanel pnlInfTarea, pnlInfProceso, pnlInfHecho;
    private JPanel pnlControlTarea, pnlControlProceso, pnlControlHecho;
    private JLabel lblTTarea, lblTProceso, lblTHecho, lblTitulo;
    private JList<String> listaTarea, listaProceso, listaHecho;
    private DefaultListModel<String> lmTareas, lmProceso, lmHecho;
    private JScrollPane pnlScrTareas, pnlScrProceso, pnlScrHecho;
    private JTextField txtTarea, txtProceso, txtHecho;
    private JButton btnInicio, btnConfig, btnTareaOk, btnProcesoOk, btnHechoOk;
    private JButton btnDchaTarea, btnRemoveTarea;
    private JButton btnIzqProceso, btnRemoveProceso, btnDchaProceso;
    private JButton btnIzqHecho, btnRemoveHecho;

    private int indiceTarea, indiceProceso, indiceHecho;

    private final Font fuenteNegrita = new Font("Default", Font.BOLD, 15);

    private final Color colorTarea = new Color(242, 181, 57);
    private final Color colorTareaBack = new Color(254, 239, 208);
    private final Color colorProceso = new Color(145, 185, 243);
    private final Color colorProcesoBack = new Color(227, 238, 254);
    private final Color colorHecho = new Color(139, 214, 1);
    private final Color colorHechoBack = new Color(227, 254, 234);
    private final Color colorRemove = new Color(254, 229, 238);
    private final Color colorBack = new Color(254, 249, 251);

    private final ImageIcon imgInicio = new ImageIcon(getClass().getResource("/recursos/inicio.png"));
    private final ImageIcon imgConfig = new ImageIcon(getClass().getResource("/recursos/config.png"));
    private final ImageIcon imgDcha = new ImageIcon(getClass().getResource("/recursos/dcha.png"));
    private final ImageIcon imgIzq = new ImageIcon(getClass().getResource("/recursos/izq.png"));
    private final ImageIcon imgOk = new ImageIcon(getClass().getResource("/recursos/ok.png"));
    private final ImageIcon imgPapelera = new ImageIcon(getClass().getResource("/recursos/papelera.png"));
    private final ImageIcon imgTarea = new ImageIcon(getClass().getResource("/recursos/tarea.png"));
    private final ImageIcon imgProceso = new ImageIcon(getClass().getResource("/recursos/proceso.png"));
    private final ImageIcon imgHecho = new ImageIcon(getClass().getResource("/recursos/hecho.png"));

    private ControlTareas controlador;

    public VistaTareas(ControlTareas controlador, String nombreProyecto) {
        this.controlador = controlador;

        // Pasos para la creacion de la ventana.
        this.crearElementos();
        this.crearDistribucion();
        this.colocarElementos();
        this.definirEstilos();
        this.eventos();

        // Propiedades de la ventana.
        this.setResizable(true);
        this.setTitle("Gestión de tareas");
        this.definirTamanioVentana(450, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lblTitulo.setText(nombreProyecto);
        this.setVisible(true);
    }

    // Crea todos los elementos de la ventana.
    private void crearElementos() {
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
        lblTTarea = new JLabel("Lista de tareas", SwingConstants.CENTER);
        lblTProceso = new JLabel("En proceso", SwingConstants.CENTER);
        lblTHecho = new JLabel("Hechos", SwingConstants.CENTER);

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
        btnConfig = new JButton(imgConfig);
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
    }

    // Define las distribuciones que ha de tener cada panel.
    private void crearDistribucion() {
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
    }

    // Coloca cada elemento en su respectivo panel.
    private void colocarElementos() {
        this.getContentPane().add(pnlGlobal);
        pnlGlobal.add(pnlNorte, BorderLayout.NORTH);
        pnlGlobal.add(pnlCentral, BorderLayout.CENTER);
        pnlNorte.add(lblTitulo, BorderLayout.CENTER);
        pnlNorte.add(btnInicio, BorderLayout.WEST);
        pnlNorte.add(btnConfig, BorderLayout.EAST);
        pnlCentral.add(pnlTareas);
        pnlCentral.add(pnlProceso);
        pnlCentral.add(pnlHecho);
        pnlTareas.add(lblTTarea, BorderLayout.NORTH);
        pnlTareas.add(pnlScrTareas, BorderLayout.CENTER);
        pnlTareas.add(pnlInfTarea, BorderLayout.SOUTH);
        pnlProceso.add(lblTProceso, BorderLayout.NORTH);
        pnlProceso.add(pnlScrProceso, BorderLayout.CENTER);
        pnlProceso.add(pnlInfProceso, BorderLayout.SOUTH);
        pnlHecho.add(lblTHecho, BorderLayout.NORTH);
        pnlHecho.add(pnlScrHecho, BorderLayout.CENTER);
        pnlHecho.add(pnlInfHecho, BorderLayout.SOUTH);

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

    // Define los estilos de todos los elementos.
    private void definirEstilos() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/recursos/logo.png")));

        pnlGlobal.setBackground(colorBack);
        pnlTareas.setBackground(colorTarea);
        pnlProceso.setBackground(colorProceso);
        pnlHecho.setBackground(colorHecho);
        listaTarea.setBackground(colorTareaBack);
        listaProceso.setBackground(colorProcesoBack);
        listaHecho.setBackground(colorHechoBack);
        btnInicio.setBackground(colorBack);
        btnConfig.setBackground(colorBack);
        btnRemoveTarea.setBackground(colorRemove);
        btnRemoveProceso.setBackground(colorRemove);
        btnRemoveHecho.setBackground(colorRemove);
        btnDchaTarea.setBackground(colorProcesoBack);
        btnIzqProceso.setBackground(colorTareaBack);
        btnDchaProceso.setBackground(colorHechoBack);
        btnIzqHecho.setBackground(colorProcesoBack);

        btnInicio.setFocusPainted(false);
        btnConfig.setFocusPainted(false);
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

        listaTarea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProceso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaHecho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaTarea.setVisibleRowCount(-1);
        listaProceso.setVisibleRowCount(-1);
        listaHecho.setVisibleRowCount(-1);

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

        lblTitulo.setFont(fuenteNegrita);
        listaTarea.setFont(fuenteNegrita);
        listaProceso.setFont(fuenteNegrita);
        listaHecho.setFont(fuenteNegrita);

        listaTarea.setSelectionBackground(colorTarea);
        listaProceso.setSelectionBackground(colorProceso);
        listaHecho.setSelectionBackground(colorHecho);

        txtTarea.setFont(fuenteNegrita);
        txtProceso.setFont(fuenteNegrita);
        txtHecho.setFont(fuenteNegrita);
        lblTTarea.setFont(fuenteNegrita);
        lblTProceso.setFont(fuenteNegrita);
        lblTHecho.setFont(fuenteNegrita);

        lblTTarea.setIcon(imgTarea);
        lblTProceso.setIcon(imgProceso);
        lblTHecho.setIcon(imgHecho);
    }

    // Controla los eventos de la ventana.
    private void eventos() {

        btnInicio.addActionListener((ActionEvent e) -> {
            controlador.volverInicio();
        });

        btnTareaOk.addActionListener((ActionEvent e) -> {
            addTarea();
        });

        txtTarea.addActionListener((ActionEvent e) -> {
            addTarea();
        });

        btnProcesoOk.addActionListener((ActionEvent e) -> {
            addProgreso();
        });

        txtProceso.addActionListener((ActionEvent e) -> {
            addProgreso();
        });

        btnHechoOk.addActionListener((ActionEvent e) -> {
            addHecho();
        });

        txtHecho.addActionListener((ActionEvent e) -> {
            addHecho();
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

        // Al cerrar esta ventana (JDialog)
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.guardarTodo();
            }
        });
    }

    private void definirTamanioVentana(double pxAlto, double pxAncho) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        double altoFinal = pantalla.height * pxAlto / 720;
        double anchoFinal = pantalla.width * pxAncho / 1280;

        setSize(new Dimension((int) anchoFinal, (int) altoFinal));
    }

    public void refrescarListaTareas(String[] tareas) {
        indiceTarea = listaTarea.getSelectedIndex();
        lmTareas.clear();

        for (int i = tareas.length - 1; i > -1; i--) {
            lmTareas.addElement(tareas[i]);
        }

        if (lmTareas.size() - 1 < indiceTarea) {
            indiceTarea--;
        }
        if (!lmTareas.isEmpty() && indiceTarea < 0) {
            indiceTarea = 0;
        }
        listaTarea.setSelectedIndex(indiceTarea);
    }

    public void refrescarListaProcesos(String[] procesos) {
        indiceProceso = listaProceso.getSelectedIndex();
        lmProceso.clear();

        for (int i = procesos.length - 1; i > -1; i--) {
            lmProceso.addElement(procesos[i]);
        }

        if (lmProceso.size() - 1 < indiceProceso) {
            indiceProceso--;
        }
        if (!lmProceso.isEmpty() && indiceProceso < 0) {
            indiceProceso = 0;
        }
        listaProceso.setSelectedIndex(indiceProceso);
    }

    public void refrescarListaHechos(String[] hechos) {
        indiceHecho = listaHecho.getSelectedIndex();
        lmHecho.clear();

        for (int i = hechos.length - 1; i > -1; i--) {
            lmHecho.addElement(hechos[i]);
        }

        if (lmHecho.size() - 1 < indiceHecho) {
            indiceHecho--;
        }
        if (!lmHecho.isEmpty() && indiceHecho < 0) {
            indiceHecho = 0;
        }
        listaHecho.setSelectedIndex(indiceHecho);

    }

    private void addTarea() {
        String cadena = txtTarea.getText().trim();
        if (!cadena.isEmpty()) {
            controlador.addTarea(cadena);
        }
        txtTarea.setText("");
    }

    private void addProgreso() {
        String cadena = txtProceso.getText().trim();
        if (!cadena.isEmpty()) {
            controlador.addProceso(cadena);
        }
        txtProceso.setText("");
    }

    private void addHecho() {
        String cadena = txtHecho.getText().trim();
        if (!cadena.isEmpty()) {
            controlador.addHecho(cadena);
        }
        txtHecho.setText("");
    }

    public void mostrarMensaje(String mensaje, boolean esError) {
        JOptionPane.showMessageDialog(this, mensaje, "Aceptar",
                esError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }

}
