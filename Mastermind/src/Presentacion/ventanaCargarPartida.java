package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

/**
 * Clase encargada de mostrar todas las partidas por acabar del usuario,y puede iniciar cualquiera de las que se muestran
 */
public class ventanaCargarPartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private ArrayList<String> partidasNotFinished;

	public ventanaCargarPartida() {
		setVisible(true);
		partidasNotFinished = CtrlPresentacion.getNotFinished();
		setTitle("Cargar partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 426);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));
		setContentPane(contentPane);
		
		JLabel lblPartidasSinFinalizar = new JLabel("Partidas sin finalizar:");
		lblPartidasSinFinalizar.setForeground(new Color(0, 0, 0));
		lblPartidasSinFinalizar.setFont(new Font("FreeSerif", Font.BOLD, 25));
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnCerrar.setBackground(new Color(239, 41, 41));
		btnCerrar.setForeground(new Color(0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblQuePartidaQuieres = new JLabel("¿Qué partida desea continuar? Escriba el id de la partida:");
		lblQuePartidaQuieres.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblQuePartidaQuieres.setForeground(new Color(0, 0, 0));
		
		JButton btnEmpezar = new JButton("Empezar");
		btnEmpezar.setBorder(new LineBorder(new Color(0, 100, 0), 2));		
		btnEmpezar.setForeground(new Color(0, 0, 0));
		btnEmpezar.setBackground(new Color(78, 154, 6));
		btnEmpezar.setFont(new Font("FreeSerif", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(143, Short.MAX_VALUE)
					.addComponent(lblPartidasSinFinalizar)
					.addGap(122))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblQuePartidaQuieres)
					.addContainerGap(54, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(157, Short.MAX_VALUE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEmpezar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPartidasSinFinalizar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblQuePartidaQuieres)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEmpezar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CtrlPresentacion.iniMenuPrincipal();
			}
		});
		
		btnEmpezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField.getText();
					int idp = Integer.parseInt(id);
					boolean found = false;
					for (int i = 0; i < partidasNotFinished.size(); i += 5) {
						if (idp == Integer.parseInt(partidasNotFinished.get(i))) {
							found = true;
						}
					}
					if (!found ) {
						JOptionPane.showMessageDialog(null, "No tiene una partida con ese id", "Error", JOptionPane.ERROR_MESSAGE);
						setVisible(false);
						CtrlPresentacion.iniVentanaCargar();
					}
					else {
						int dif = CtrlPresentacion.getDifPartida(idp);
						switch(dif) {
							case 0:
								CtrlPresentacion.iniPECargada(idp);
								setVisible(false);
								break;
							case 1:
								CtrlPresentacion.iniPDCargada(idp);
								setVisible(false);
								break;
							case 2: 
								CtrlPresentacion.iniPEXCargada(idp);
								setVisible(false);
								break;
							default:
								break;
						}
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No existe partida con ese id,\n fijese en los ids de las partidas que puede cargar", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Dificultad", "Rol", "Ronda", "Puntuaci\u00F3n"
			}
		));
		table.getTableHeader().setEnabled(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		table.getColumnModel().getColumn(4).setPreferredWidth(94);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < partidasNotFinished.size(); i += 5) {
			String id = partidasNotFinished.get(i);
			String dif = partidasNotFinished.get(i+1);
			String rol = partidasNotFinished.get(i+2);
			String ronda = partidasNotFinished.get(i+3);
			String punt = partidasNotFinished.get(i+4);
			model.addRow(new Object[]{id,dif,rol,ronda,punt});
		}
	}
}
