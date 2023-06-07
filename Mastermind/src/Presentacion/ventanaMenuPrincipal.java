package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

/**
 * Ventana que muestra todas las opciones una vez logeado, como puede ser jugar, cargar una partida, consultar ranquing,record,reglas
 */
public class ventanaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public ventanaMenuPrincipal() {
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(628, 443);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_cental = new JPanel();
		contentPane.add(panel_cental, BorderLayout.CENTER);
		panel_cental.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnJugar.setForeground(new Color(0, 0, 0));
		btnJugar.setBackground(new Color(233, 185, 110));
		btnJugar.setFont(new Font("FreeSerif", Font.BOLD, 50));
		panel_cental.add(btnJugar);
		
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniVentanaConfigPartida();
				setVisible(false);
			}
		});
		
		JButton btnCargarPartida = new JButton("Cargar Partida");
		btnCargarPartida.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnCargarPartida.setFont(new Font("FreeSerif", Font.PLAIN, 30));
		btnCargarPartida.setForeground(new Color(0, 0, 0));
		btnCargarPartida.setBackground(new Color(233, 185, 110));
		panel_cental.add(btnCargarPartida);
		
		btnCargarPartida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CtrlPresentacion.iniVentanaCargar();
			}
		});
		
		JPanel panel_up = new JPanel();
		contentPane.add(panel_up, BorderLayout.NORTH);
		panel_up.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnHist = new JButton("Historial");
		btnHist.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnHist.setBackground(new Color(192, 127, 51));
		btnHist.setFont(new Font("FreeSerif", Font.PLAIN, 25));
		btnHist.setForeground(new Color(0, 0, 0));
		panel_up.add(btnHist);
		
		JButton btnReglas = new JButton("Reglas");
		btnReglas.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnReglas.setBackground(new Color(192, 127, 51));
		btnReglas.setFont(new Font("FreeSerif", Font.PLAIN, 25));
		btnReglas.setForeground(new Color(0, 0, 0));
		panel_up.add(btnReglas);
		
		btnReglas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniReglas();
				setVisible(false);
			}
		});
		
		JButton btnRec = new JButton("   Records   ");
		btnRec.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnRec.setBackground(new Color(192, 127, 51));
		btnRec.setFont(new Font("FreeSerif", Font.PLAIN, 25));
		btnRec.setForeground(new Color(0, 0, 0));
		contentPane.add(btnRec, BorderLayout.WEST);
		
		JButton btnRank = new JButton("   Ranking   ");
		btnRank.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnRank.setFont(new Font("FreeSerif", Font.PLAIN, 25));
		btnRank.setBackground(new Color(192, 127, 51));
		btnRank.setForeground(new Color(0, 0, 0));
		contentPane.add(btnRank, BorderLayout.EAST);
		
		JButton btnExit = new JButton("Salir");
		btnExit.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnExit.setFont(new Font("FreeSerif", Font.PLAIN, 20));
		btnExit.setForeground(new Color(0, 0, 0));
		btnExit.setBackground(new Color(239, 41, 41));
		contentPane.add(btnExit, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					CtrlPresentacion.guardarEnPersistencia();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniVentanaRecord();
				setVisible(false);
			}
		});
		
		
		btnHist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniVentanaHistorial();
				setVisible(false);
			}
		});
		
		
		btnRank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniVentanaRank();
				setVisible(false);
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] o = new Object[]{"Sí", "No"};
                int seleccion = JOptionPane.showOptionDialog(null, "Se cerrará la sesión.\n¿Desea continuar?\n", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, o, "Sí");
                if (seleccion == 0) {
                	CtrlPresentacion.cierraSesion();
                	CtrlPresentacion.iniGestionPerfil();
                	setVisible(false);
           
                }
			}
		});
		setVisible(true);
	}
}
