package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Color;

/**
 * Ventana para configurar una partida, pudiendo escoger el rol y la dificultad
 */
public class ventanaConfigPartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int dif;
	private String rol = "CodeBreaker";

	public ventanaConfigPartida() {
		setVisible(true);
		dif = -1;
		setTitle("Configuración");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(396, 416);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));
		contentPane.setBackground(new Color(233, 185, 110));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(233, 185, 110));
		contentPane.add(panel);
		
		JLabel lblSeleccionaDificultad = new JLabel("Selecciona dificultad:");
		lblSeleccionaDificultad.setForeground(new Color(0, 0, 0));
		lblSeleccionaDificultad.setFont(new Font("FreeSerif", Font.BOLD, 22));
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(75, Short.MAX_VALUE)
					.addComponent(lblSeleccionaDificultad)
					.addGap(88))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(lblSeleccionaDificultad)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		
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
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		ButtonGroup bg = new ButtonGroup();
		ButtonGroup bg2 = new ButtonGroup();
		
		JToggleButton btnEstandar = new JToggleButton("Estándar");
		btnEstandar.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnEstandar.setBackground(new Color(192, 127, 51));
		btnEstandar.setForeground(new Color(0, 0, 0));
		btnEstandar.setFont(new Font("FreeSerif", Font.BOLD, 18));
		panel_3.add(btnEstandar);
		
		JToggleButton btnDificil = new JToggleButton("Difícil");
		btnDificil.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnDificil.setBackground(new Color(192, 127, 51));
		btnDificil.setForeground(new Color(0, 0, 0));
		btnDificil.setFont(new Font("FreeSerif", Font.BOLD, 18));
		panel_3.add(btnDificil);
		
		JToggleButton btnExtremo = new JToggleButton("Extremo");
		btnExtremo.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnExtremo.setBackground(new Color(192, 127, 51));
		btnExtremo.setForeground(new Color(0, 0, 0));
		btnExtremo.setFont(new Font("FreeSerif", Font.BOLD, 18));
		panel_3.add(btnExtremo);
		panel.setLayout(gl_panel);
		
		bg.add(btnExtremo);
		bg.add(btnDificil);
		bg.add(btnEstandar);
		
		btnEstandar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnEstandar.isSelected()) {
					btnDificil.setSelected(false);
					btnExtremo.setSelected(false);
					dif = 0;
				}
			}
		});
		
		btnDificil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnDificil.isSelected()) {
					btnEstandar.setSelected(false);
					btnExtremo.setSelected(false);
					dif = 1;
				}
			}
		});
		
		btnExtremo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnExtremo.isSelected()) {
					btnDificil.setSelected(false);
					btnEstandar.setSelected(false);
					dif = 2;
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(233, 185, 110));
		contentPane.add(panel_1);
		
		JLabel lblSeleccionaRol = new JLabel("Selecciona rol:");
		lblSeleccionaRol.setFont(new Font("FreeSerif", Font.BOLD, 22));
		lblSeleccionaRol.setForeground(new Color(0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnCerrar.setBackground(new Color(239, 41, 41));
		btnCerrar.setForeground(new Color(0, 0, 0));
		btnCerrar.setFont(new Font("FreeSerif", Font.BOLD, 16));
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBorder(new LineBorder(new Color(0, 100, 0), 2));		
		btnSiguiente.setBackground(new Color(78, 154, 6));
		btnSiguiente.setForeground(new Color(0, 0, 0));
		btnSiguiente.setFont(new Font("FreeSerif", Font.BOLD, 16));
		
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(dif) {
					case 0:
						CtrlPresentacion.iniPE(rol,dif);
						setVisible(false);
						break;
					case 1:
						CtrlPresentacion.iniPD(rol,dif);
						setVisible(false);
						break;
					case 2:
						CtrlPresentacion.iniPEX(rol,dif);
						setVisible(false);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Selecciona una dificultad", "Error", JOptionPane.ERROR_MESSAGE);
						break;
				}
			}
		});
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(116)
							.addComponent(lblSeleccionaRol)))
					.addGap(23))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(15)
					.addComponent(lblSeleccionaRol)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JToggleButton btnCodeBreaker = new JToggleButton("CodeBreaker");
		btnCodeBreaker.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnCodeBreaker.setFont(new Font("FreeSerif", Font.BOLD, 22));
		btnCodeBreaker.setForeground(new Color(0, 0, 0));
		btnCodeBreaker.setBackground(new Color(192, 127, 51));
		panel_2.add(btnCodeBreaker);
		
		JToggleButton btnCodemaker = new JToggleButton("CodeMaker");
		btnCodemaker.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnCodemaker.setFont(new Font("FreeSerif", Font.BOLD, 22));
		btnCodemaker.setForeground(new Color(0, 0, 0));
		btnCodemaker.setBackground(new Color(192, 127, 51));
		panel_2.add(btnCodemaker);
		panel_1.setLayout(gl_panel_1);
		
		bg2.add(btnCodemaker);
		bg2.add(btnCodeBreaker);
		
		btnCodemaker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnCodemaker.isSelected()) {
					btnCodeBreaker.setSelected(false);
					rol = "CodeMaker";
				}
			}
		});
		
		btnCodeBreaker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnCodeBreaker.isSelected()) {
					btnCodemaker.setSelected(false);
					rol = "CodeBreaker";
				}
			}
		});
		
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniMenuPrincipal();
				setVisible(false);
			}
		});
	}
}
