package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JTree;
import java.awt.Color;

/**
 * Ventana que se encarga de mostrar los records del usuario actualmente logeado
 */
public class ventanaRecords extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public ventanaRecords() {
		setVisible(true);
		setTitle("Records");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(233, 185, 110));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Estándar:");
		btnNewButton.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnNewButton.setFont(new Font("FreeSerif", Font.BOLD, 25));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(192, 127, 51));
		panelCentral.add(btnNewButton);
				
		JLabel lblNewLabel_2 = new JLabel("          ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		panelCentral.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Difícil:");
		btnNewButton_1.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnNewButton_1.setBackground(new Color(192, 127, 51));
		btnNewButton_1.setFont(new Font("FreeSerif", Font.BOLD, 25));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		panelCentral.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		panelCentral.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Extremo:");
		btnNewButton_2.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnNewButton_2.setBackground(new Color(192, 127, 51));
		btnNewButton_2.setFont(new Font("FreeSerif", Font.BOLD, 25));
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		panelCentral.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		panelCentral.add(lblNewLabel_4);
		
		JPanel panelTop = new JPanel();
		panelTop.setForeground(Color.WHITE);
		panelTop.setBackground(new Color(233, 185, 110));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel(" DIFICULTAD");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 30));
		panelTop.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PUNTUACIÓN");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("FreeSerif", Font.BOLD, 30));
		panelTop.add(lblNewLabel_1);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(233, 185, 110));
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Mostrar todas");
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 100, 0), 2));		
		btnNewButton_3.setFont(new Font("FreeSerif", Font.BOLD, 16));
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(78, 154, 6));
		panelBottom.add(btnNewButton_3);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(139, 0, 0), 2));
		btnCerrar.setFont(new Font("FreeSerif", Font.BOLD, 16));
		panelBottom.add(btnCerrar);
		btnCerrar.setForeground(new Color(0, 0, 0));
		btnCerrar.setBackground(new Color(239, 41, 41));
		
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrlPresentacion.iniMenuPrincipal();
				setVisible(false);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rec = CtrlPresentacion.getRecordEst();
				if (rec == -1) {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad Estandar", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String record = Integer.toString(rec);
					lblNewLabel_2.setText(record);
					lblNewLabel_3.setText("");
					lblNewLabel_4.setText("");
				}
			}
		});
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rec = CtrlPresentacion.getRecordDif();
				if (rec == -1) {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad dificil", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String record = Integer.toString(rec);
					lblNewLabel_3.setText(record);
					lblNewLabel_2.setText("");
					lblNewLabel_4.setText("");
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rec = CtrlPresentacion.getRecordExt();
				if (rec == -1) {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad extremo", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String record = Integer.toString(rec);
					lblNewLabel_4.setText(record);
					lblNewLabel_2.setText("");
					lblNewLabel_3.setText("");
				}
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rec1 = CtrlPresentacion.getRecordEst();
				int rec2 = CtrlPresentacion.getRecordDif();
				int rec3 = CtrlPresentacion.getRecordExt();
				if (rec1 != -1) {
					lblNewLabel_2.setText(Integer.toString(rec1));
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad estándar", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				if (rec2 != -1) {
					lblNewLabel_3.setText(Integer.toString(rec2));
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad dificil", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				if (rec3 != -1) {
					lblNewLabel_4.setText(Integer.toString(rec3));
				} 
				else {
					JOptionPane.showMessageDialog(null, "No hay record en dificultad extremo", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
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
	}

}
