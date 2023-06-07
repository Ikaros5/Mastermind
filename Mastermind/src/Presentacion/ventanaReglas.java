package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.awt.Color;

/**
 * Ventana que se encarga de mostrar las reglas del juego
 */
public class ventanaReglas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ventanaReglas() {
		setVisible(true);
		setTitle("Reglas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(885, 543);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));


		setContentPane(contentPane);
		
		JLabel lblReglasDeJuego = new JLabel("REGLAS DE JUEGO");
		lblReglasDeJuego.setForeground(new Color(0, 0, 0));
		lblReglasDeJuego.setFont(new Font("FreeSerif", Font.BOLD, 24));
		JButton btnClose = new JButton("Cerrar");
		btnClose.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnClose.setBackground(new Color(239, 41, 41));
		btnClose.setForeground(new Color(0, 0, 0));
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniMenuPrincipal();
				setVisible(false);
			}
		});
		
		JLabel lblNewLabel = new JLabel("<html>\n"
				+ "    <p>1- En el rol de CodeMaker, lo único que debes hacer es escoger una combinación de colores.</p>\n"
				+ "    <br>\n"
				+ "    <p>2- En el caso de que desarrolles el rol de CodeBreaker, la máquina generará un código secreto el cual deberás ir adivinando.</p>\n"
				+ "    <br>\n"
				+ "    <p>Como con el otro rol, tendrás que escoger una combinación, y una vez introducida, se te informará de lo siguiente:</p>\n"
				+ "    <ul>\n"
				+ "        <li>Cuantas rojas has conseguido, es decir, cuantas colores has puesto en su sitio.</li>\n"
				+ "        <li>Cuantas blancas has conseguido, es decir, pusiste bien el color pero no la posición en que corresponde.</li>\n"
				+ "        <li>Cuantos huecos quedaron vacíos.</li>\n"
				+ "    </ul>\n"
				+ "    <br>\n"
				+ "    <p>3- Reglas generales:</p>\n"
				+ "    <ul>\n"
				+ "        <li>Numero máximo de intentos para adivinar el código secreto -> 10 intentos</li>\n"
				+ "        <li>Numero de rondas -> 2 (en ambas rondas pasas por los dos tipos de roles)</li>\n"
				+ "        <li>Sistema de puntuación:</li>\n"
				+ "        <ul>\n"
				+ "            <li>En cada ronda se comparan los intentos que tardaron tanto usuario como máquina en resolver,</li>\n"
				+ "            <li>En caso de que el usuario haga menos intentos se lleva 50 puntos, en caso de empate 25 y en caso de derrota ante la máquina se lleva 0 puntos.</li>\n"
				+ "        </ul>\n"
				+ "    </ul>\n"
				+ "</html>\n"
				+ "");
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(317)
					.addComponent(lblReglasDeJuego)
					.addContainerGap(326, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(743, Short.MAX_VALUE)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 822, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addComponent(lblReglasDeJuego)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
