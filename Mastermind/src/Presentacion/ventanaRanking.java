package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Ventana que se encarga de mostrar los ranquings disponibles 
 */
public class ventanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	HashMap<String,Integer> rankEst;
	HashMap<String,Integer> rankDif;
	HashMap<String,Integer> rankExt;

	public ventanaRanking() {
		rankEst = CtrlPresentacion.getRankEstandar();
		rankDif = CtrlPresentacion.getRankDificil();
		rankExt = CtrlPresentacion.getRankExtremo();
		setVisible(true);
		setTitle("Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(662, 331);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));


		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(233, 185, 110));
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Estándar:");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		
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
		
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(64))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usuario", "Puntuaci\u00F3n"
			}
		));
		
		table.setEnabled(false);
		table.getTableHeader().setEnabled(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(103);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (String name: rankEst.keySet()) {
			Integer p = rankEst.get(name);
			String punt = Integer.toString(p);
			model.addRow(new Object[]{name,punt});
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(233, 185, 110));
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Difícil:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("FreeSerif", Font.BOLD, 18));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(78)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(13)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		table_1 = new JTable();
		table_1.setForeground(new Color(0, 0, 0));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usuario", "Puntuaci\u00F3n"
			}
		));
		table_1.setEnabled(false);
		table_1.getTableHeader().setEnabled(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane_1.setViewportView(table_1);
		panel_1.setLayout(gl_panel_1);
		
		DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
		for (String name: rankDif.keySet()) {
			Integer p = rankDif.get(name);
			String punt = Integer.toString(p);
			model1.addRow(new Object[]{name,punt});
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(233, 185, 110));
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Extremo:");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("FreeSerif", Font.BOLD, 18));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnNewButton.setFont(new Font("FreeSerif", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(239, 41, 41));
		btnNewButton.setForeground(new Color(0, 0, 0));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(69)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(13)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(136)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table_2 = new JTable();
		table_2.setForeground(new Color(0, 0, 0));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usuario", "Puntuaci\u00F3n"
			}
		));
		table_2.setEnabled(false);
		table_2.getTableHeader().setEnabled(false);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane_2.setViewportView(table_2);
		panel_2.setLayout(gl_panel_2);
		
		DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
		for (String name: rankExt.keySet()) {
			Integer p = rankExt.get(name);
			String punt = Integer.toString(p);
			model2.addRow(new Object[]{name,punt});
		}
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniMenuPrincipal();
				setVisible(false);
			}
		});
	}
}
