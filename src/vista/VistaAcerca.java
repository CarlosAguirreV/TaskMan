package vista;

import controlador.ControladorPrincipal;
import static modelo.Constantes.*;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import static modelo.Diccionario.*;

/**
 * Ventana que contiene informacion del proyecto.
 *
 * @author Carlos Aguirre
 */
public final class VistaAcerca extends Vista {

    private JPanel pnlGlobal, pnlSur;
    private JLabel lblLogo, lblAcerca;
    private JButton btnCerrar, btnDonar, btnGitHub;
    private final ControladorPrincipal controlador;

    public VistaAcerca(ControladorPrincipal controlador) {
        this.controlador = controlador;

        // Pasos para la creacion de la ventana.
        this.crearElementos();
        this.crearDistribucion();
        this.colocarElementos();
        this.definirEstilos();
        this.eventos();

        // Propiedades de la ventana.
        super.setResizable(false);
        super.definirTamanioVentana(360, 360);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    protected void crearElementos() {
        pnlGlobal = new JPanel();
        pnlSur = new JPanel();

        lblLogo = new JLabel(imgLogo);
        lblAcerca = new JLabel(ACERCA);

        btnCerrar = new JButton(CERRAR);
        btnDonar = new JButton(INVITAR_CAFE);
        btnGitHub = new JButton(GIT_HUB);
    }

    @Override
    protected void crearDistribucion() {
        pnlGlobal.setLayout(new BorderLayout());
        pnlSur.setLayout(new GridLayout(1, 3, 5, 0));
    }

    @Override
    protected void colocarElementos() {
        super.getContentPane().add(pnlGlobal);

        pnlGlobal.add(lblLogo, BorderLayout.NORTH);
        pnlGlobal.add(lblAcerca, BorderLayout.CENTER);
        pnlGlobal.add(pnlSur, BorderLayout.SOUTH);

        pnlSur.add(btnCerrar);
        pnlSur.add(btnDonar);
        pnlSur.add(btnGitHub);
    }

    @Override
    protected void definirEstilos() {
        btnCerrar.setIcon(imgCerrar);
        btnDonar.setIcon(imgDonar);
        btnGitHub.setIcon(imgGitHub);

        pnlGlobal.setBackground(COLOR_BACK);
        btnCerrar.setBackground(COLOR_CERRAR);
        btnCerrar.setForeground(COLOR_BACK);
        btnDonar.setBackground(COLOR_DONAR);
        btnDonar.setForeground(COLOR_BACK);
        btnGitHub.setBackground(COLOR_GITHUB);
        btnGitHub.setForeground(COLOR_BACK);

        btnCerrar.setFont(FUENTE_NEGRITA);
        btnDonar.setFont(FUENTE_NEGRITA);
        btnGitHub.setFont(FUENTE_NEGRITA);

        lblLogo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblAcerca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlSur.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnDonar.setFocusPainted(false);
        btnDonar.setBorderPainted(false);
        btnGitHub.setFocusPainted(false);
        btnGitHub.setBorderPainted(false);

        pnlSur.setOpaque(false);
    }

    @Override
    protected void eventos() {
        btnCerrar.addActionListener((ActionEvent e) -> {
            controlador.mostrarVistaAcerca(false);
        });

        btnDonar.addActionListener((ActionEvent e) -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(WEB_DONATIVO));
                } catch (Exception ex) {
                    mostrarLinkWeb(DONATIVO, WEB_DONATIVO);
                }
            }
        });

        btnGitHub.addActionListener((ActionEvent e) -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(WEB_GITHUB));
                } catch (Exception ex) {
                    mostrarLinkWeb(GIT_HUB, WEB_GITHUB);
                }
            }
        });

        // Al cerrar esta ventana (JFrame).
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.mostrarVistaAcerca(false);
            }
        });
    }

}
