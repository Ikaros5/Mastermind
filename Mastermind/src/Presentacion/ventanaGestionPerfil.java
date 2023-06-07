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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

/**
 * Ventana que se encarga de gestionar todo lo relacionado con la gestion de un usuario, ya sea iniciar sesion,registrarse o eliminar usuario
 */
public class ventanaGestionPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private static String userLogged;
	
	
	public void cierra() {
        setVisible(false);
    }
	
	private boolean validarUsuarioYContraseña(String username, String password) {
	    String error = "";
	    if (username.isEmpty() && password.isEmpty()) {
	        error = "Por favor, rellene los campos usuario y contraseña";
	    } else {
	        if (username.isEmpty()) {
	            error = "Por favor, rellene el campo usuario\n";
	        }
	        if (password.isEmpty()) {
	            error = "Por favor, rellene el campo contraseña\n";
	        }
	    }
	    if (!error.isEmpty()) {
	        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}
	
	public ventanaGestionPerfil() {
		setTitle("Gestion Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(464, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));
		contentPane.setBackground(new Color(233, 185, 110));

		setContentPane(contentPane);
		
		JButton btnEliminarPerfil = new JButton("   Eliminar Perfil   ");
		btnEliminarPerfil.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnEliminarPerfil.setBackground(new Color(192, 127, 51));
		btnEliminarPerfil.setFont(new Font("FreeSerif", Font.BOLD, 15));
		btnEliminarPerfil.setForeground(new Color(0, 0, 0));
		
		JButton btnRegistrarPerfil = new JButton("   Registrar Perfil   ");
		btnRegistrarPerfil.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnRegistrarPerfil.setFont(new Font("FreeSerif", Font.BOLD, 15));
		btnRegistrarPerfil.setBackground(new Color(192, 127, 51));
		btnRegistrarPerfil.setForeground(new Color(0, 0, 0));
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(new Color(192, 127, 51));
		btnEntrar.setFont(new Font("FreeSerif", Font.BOLD, 25));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(0, 0, 0));
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(new Color(0, 0, 0));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblMasterMind = new JLabel("MASTER MIND");
		lblMasterMind.setForeground(new Color(0, 0, 0));
		lblMasterMind.setFont(new Font("FreeSerif", Font.BOLD, 55));
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblContrasea)
								.addComponent(lblUsuario))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
							.addGap(62))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnRegistrarPerfil)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEliminarPerfil)
									.addGap(10))
								.addComponent(lblMasterMind, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(0))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(147)
					.addComponent(btnEntrar, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(143))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblMasterMind, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEliminarPerfil)
						.addComponent(btnRegistrarPerfil))
					.addGap(18))
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
		
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField_1.getText();
                String password = new String(passwordField.getPassword());
                
                boolean val = validarUsuarioYContraseña(username, password);
                if (val) {
                	CtrlPresentacion.iniciaSesion(username, password);
                	userLogged = username;
                }
			}
		});
		
		btnRegistrarPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField_1.getText();
                String password = new String(passwordField.getPassword());
                
                boolean val = validarUsuarioYContraseña(username, password);
                if (val) {
                	CtrlPresentacion.registraPerfil(username, password);
                	userLogged = username;
                }
			}
		});
		
		btnEliminarPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField_1.getText();
                String password = new String(passwordField.getPassword());
                
                boolean val = validarUsuarioYContraseña(username, password);
                if (val) {
                	CtrlPresentacion.eliminarPerfil(username, password);
                	userLogged = null;
                }
			}
		});
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
