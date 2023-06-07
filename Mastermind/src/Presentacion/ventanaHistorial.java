package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Ventana que se encarga de mostrar el historial de partidas del usuario
 */
public class ventanaHistorial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<String> historial;

	public ventanaHistorial() {
		setVisible(true);
		historial = CtrlPresentacion.getHist(CtrlPresentacion.getLogged());
		setTitle("Historial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(530, 297);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblHistorialPartidas = new JLabel("HISTORIAL PARTIDAS");
		lblHistorialPartidas.setForeground(new Color(0, 0, 0));
		lblHistorialPartidas.setFont(new Font("FreeSerif", Font.BOLD, 25));
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnNewButton.setBackground(new Color(239, 41, 41));
		btnNewButton.setFont(new Font("FreeSerif", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(0, 0, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
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
		
		
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(109)
					.addComponent(lblHistorialPartidas)
					.addContainerGap(116, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(432, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHistorialPartidas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniMenuPrincipal();
				setVisible(false);
			}
		});
		
	    table = new JTable(new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "ID", "Dificultad", "Puntuaci\u00F3n", "Estado"
	            }
	        ));
	    table.setForeground(new Color(0, 0, 0));
		table.getTableHeader().setEnabled(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.setEnabled(false);
		
		DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
		for (int i = 0; i < historial.size(); i += 4) {
			String[] nueva_fila = {historial.get(i),historial.get(i+1),historial.get(i+2),historial.get(i+3)};
			modeloTabla.addRow(nueva_fila);
		}
	}
}
