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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Ventana que se encarga de gestionar una partida de dificultad dificil
 */
public class ventanaPartidaDificil extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JPanel panel_combinacion;
	private JPanel panel_colores;
    private JPanel panel_casillas;
    private JButton[] botones_colores;
    private JButton[] botones_casillas;
    private JButton[] botones_combinacion;
    private Color[] colores = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA};
    private int casilla_seleccionada = -1;
    private Color color_seleccionado = null;
    private String rol = new String();
    private int idP;
    private int rowCount = 0;
    private List<List<Integer>> intentos_jugador = new ArrayList<> ();
	private List<List<Integer>> intentos_maquina = new ArrayList<>();
	private int puntuacion;
	private int rondas;
	private HashMap<Color,Integer> mapColors = CtrlPresentacion.Colors2Ints(colores);
	private HashMap<Integer,Color> Intmap = CtrlPresentacion.Ints2Colors(colores);
	private int counter;
	private boolean enter;
	
	public ventanaPartidaDificil(int id) {
		this.idP = id;
		this.rol = CtrlPresentacion.getRol(idP);
		this.puntuacion = CtrlPresentacion.getPuntuacionPartida(idP);
		this.rondas = CtrlPresentacion.getRondaPartida(idP);
		if (rondas == 0) {
			counter = 0;
			this.enter = false;
		}
		else if (rondas == 1) {
			counter = 2;
			this.enter = true;
			if (rol.equals("CodeBreaker")) CtrlPresentacion.newCode(idP);
		}
		else if (rondas == 2) {
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Partida que ya esta acabada \n Irá al menú principal", "Error", JOptionPane.ERROR_MESSAGE);
			CtrlPresentacion.iniMenuPrincipal();
			
		}
		if (rondas != 2) setVisible(true);
		setTitle("Partida Difícil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(875, 648);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 185, 110));
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 10));

		setContentPane(contentPane);
		
		JLabel lblRonda = new JLabel("Ronda :");
		lblRonda.setFont(new Font("FreeSerif", Font.BOLD, 20));
		lblRonda.setForeground(new Color(0, 0, 0));
		lblRonda.setBounds(72, 38, 85, 29);
		
		JPanel panel_rol = new JPanel();
		panel_rol.setBackground(new Color(233, 185, 110));
		panel_rol.setBounds(578, 38, 243, 29);
		
		JLabel lblRol_1 = new JLabel("Rol:");
		lblRol_1.setFont(new Font("FreeSerif", Font.BOLD, 20));
		lblRol_1.setForeground(new Color(0, 0, 0));
		
		JLabel label_3_1 = new JLabel((String) null);
		label_3_1.setFont(new Font("FreeSerif", Font.BOLD, 20));
		label_3_1.setForeground(new Color(0, 0, 0));
		label_3_1.setText(rol);
		
		
		JLabel lblPuntuacin = new JLabel("Puntuación:");
		lblPuntuacin.setFont(new Font("FreeSerif", Font.BOLD, 20));
		lblPuntuacin.setForeground(new Color(0, 0, 0));
		lblPuntuacin.setBounds(293, 38, 119, 29);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(509, 91, 329, 195);
		
		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("FreeSerif", Font.BOLD, 20));
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setBounds(414, 38, 91, 29);
		
		JLabel label = new JLabel("-");
		label.setFont(new Font("FreeSerif", Font.BOLD, 20));
		label.setForeground(new Color(0, 0, 0));
		label.setBounds(150, 34, 58, 36);
		label.setText(Integer.toString(rondas));
		label_2.setText(Integer.toString(puntuacion));
		
		panel_colores = new JPanel();
		panel_colores.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel_colores.setBounds(34, 522, 562, 72);
		panel_colores.setLayout(new GridLayout(1, 0, 0, 0));
		
		botones_colores = new JButton[colores.length];
		for (int i = 0; i < colores.length; i++) {
            botones_colores[i] = new JButton("");
            botones_colores[i].setBackground(colores[i]);
            botones_colores[i].addActionListener(this);
            panel_colores.add(botones_colores[i]);
        }
		
		panel_casillas = new JPanel();
		panel_casillas.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel_casillas.setBounds(39, 411, 557, 72);
		panel_casillas.setLayout(new GridLayout(1, 0, 0, 0));
		
		botones_casillas = new JButton[4];
        for (int i = 0; i < botones_casillas.length; i++) {
            botones_casillas[i] = new JButton(Integer.toString(i+1));
            botones_casillas[i].setBackground(Color.BLACK);
            botones_casillas[i].setForeground(Color.WHITE);
            if (rol.equals("CodeMaker")) botones_casillas[i].setEnabled(false);
            botones_casillas[i].addActionListener(this);
            panel_casillas.add(botones_casillas[i]);
        }
        
        panel_combinacion = new JPanel();
        panel_combinacion.setBorder(new LineBorder(new Color(102, 51, 0), 2));
        panel_combinacion.setBounds(411, 304, 427, 63);
        panel_combinacion.setLayout(new GridLayout(1, 0, 0, 0));
		
        botones_combinacion = new JButton[4];
        for (int i = 0; i < 4; i++) {
        	botones_combinacion[i] = new JButton(Integer.toString(i+1));
        	botones_combinacion[i].setBackground(Color.BLACK);
        	botones_combinacion[i].setForeground(Color.WHITE);
        	if (rol.equals("CodeBreaker")) botones_combinacion[i].setEnabled(false);
        	botones_combinacion[i].addActionListener(this);
        	panel_combinacion.add(botones_combinacion[i]);
        }
        
		
		JLabel lblCasillas = new JLabel("Casillas:");
		lblCasillas.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblCasillas.setForeground(new Color(0, 0, 0));
		lblCasillas.setBounds(27, 384, 60, 15);
		
		JLabel lblColores = new JLabel("Colores:");
		lblColores.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblColores.setForeground(new Color(0, 0, 0));
		lblColores.setBounds(28, 503, 59, 15);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("FreeSerif", Font.BOLD, 14));
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setBounds(131, 298, 207, 63);
		
		JToggleButton tglbtnAyuda = new JToggleButton("Ayuda");
		tglbtnAyuda.setBackground(new Color(192, 127, 51));
		tglbtnAyuda.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		tglbtnAyuda.setFont(new Font("FreeSerif", Font.BOLD, 16));
		tglbtnAyuda.setForeground(new Color(0, 0, 0));
		tglbtnAyuda.setBounds(28, 314, 85, 36);
		
		JScrollPane Intentos = new JScrollPane();
		Intentos.setBounds(28, 91, 453, 195);
		
		JLabel lblCombinacinSecreta = new JLabel("Combinación secreta:");
		lblCombinacinSecreta.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblCombinacinSecreta.setForeground(new Color(0, 0, 0));
		lblCombinacinSecreta.setBounds(248, 298, 153, 15);
		
		tglbtnAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p = CtrlPresentacion.getPista(idP);
				ArrayList<Integer> c = CtrlPresentacion.getCode(idP);
				int x = c.get(p);
				String s = null;
				switch(x) {
					case 0:
						s = "rojo";
						break;
					case 1:
						s = "amarillo";
						break;
					case 2:
						s = "verde";
						break;
					case 3:
						s = "azul";
						break;
					case 4:
						s = "naranja";
						break;
					case 5:
						s = "magenta";
						break;
					default:
						break;
							
				}
				label_1.setText("<html>En la casilla " + (p + 1) + ",<br>está el color " + s + "</html>");
				tglbtnAyuda.setEnabled(false);
			}
		});
		
		if (rol.equals("CodeMaker")) tglbtnAyuda.setEnabled(false);
		
		Color[][] allSelectedColors = new Color[10][botones_casillas.length];
		JButton btnComprueba = new JButton("<html><center>Escoger<br>combinación</html>");
		btnComprueba.setBackground(new Color(192, 127, 51));
		btnComprueba.setFont(new Font("FreeSerif", Font.BOLD, 30));
		btnComprueba.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		btnComprueba.setForeground(new Color(0, 0, 0));
		btnComprueba.setBounds(622, 390, 216, 204);
		btnComprueba.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (CtrlPresentacion.getRol(idP).equals("CodeBreaker")) {
		    		if (rowCount < 10) {
		    			Color[] selectedColors = new Color[botones_casillas.length];
		    			boolean hasBlackColor = false;
		    			for (int i = 0; i < botones_casillas.length; i++) {
		    				selectedColors[i] = botones_casillas[i].getBackground();
		    				if (selectedColors[i] == Color.BLACK) {
		    					hasBlackColor = true;
		    					break;
		    				}
		    			}
		            
		    			if (hasBlackColor) {
		    				JOptionPane.showMessageDialog(null, "Por favor, introduzca una combinación válida.");
		    				return;
		    			}

		    			DefaultTableModel model = (DefaultTableModel) table.getModel();
		    			model.addRow(new Object[]{"", "", "", " "});

		    			int lastRow = model.getRowCount() - 1;
		    			for (int j = 0; j < botones_casillas.length; ++j) {
		    				allSelectedColors[lastRow][j] = selectedColors[j];
		    			}

		    			table.getColumnModel().getColumn(0).setCellRenderer(new ColorCellRenderer(allSelectedColors));
		    			table.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer(allSelectedColors));
		    			table.getColumnModel().getColumn(2).setCellRenderer(new ColorCellRenderer(allSelectedColors));
		    			table.getColumnModel().getColumn(3).setCellRenderer(new ColorCellRenderer(allSelectedColors));
		    			List<Integer> intento = new ArrayList<> ();
		    			rowCount++;
		    			for (int i = 0; i < botones_casillas.length; ++i) {
		    				Color c = botones_casillas[i].getBackground();
		    				intento.add(mapColors.get(c));	
		    			}
		    			intentos_jugador.add(intento);

		    			
		    			List<Integer> f = CtrlPresentacion.feedBackIntento(intento,idP);
		    			DefaultTableModel modeloTabla = (DefaultTableModel) table_1.getModel();
		    			String[] fila = {Integer.toString(f.get(0)),Integer.toString(f.get(1)),Integer.toString(f.get(2))};
		    			modeloTabla.addRow(fila);
		    			limpia_casillas();
		    			
		    			
		    			if (f.get(0) == botones_casillas.length) {
		    				ArrayList<Integer> c = CtrlPresentacion.getCode(idP);
					    	for (int i = 0; i < botones_combinacion.length; ++i) {
					    		botones_combinacion[i].setBackground(Intmap.get(c.get(i)));
					    	}
		    				JOptionPane.showMessageDialog(null, "¡Has acertado el codigo! Ahora eres CodeMaker", "Combinación correcta", JOptionPane.INFORMATION_MESSAGE);
		    				CtrlPresentacion.cambioRol(idP);
					    	rol = CtrlPresentacion.getRol(idP);
					    	++counter;
					    	label_3_1.setText(rol);
					    	
					    	for (int i = 0; i < rowCount; ++i) {
					    		model.removeRow(0);
					    		modeloTabla.removeRow(0);
					    	}
					    	limpia_casillas();
					    	limpia_combinacion();
					    	label_1.setText(""); 
					    	for (int i = 0; i < botones_combinacion.length; i++) {
					    		botones_combinacion[i].setEnabled(true);
					    		botones_casillas[i].setEnabled(false);
					    	}
					    	tglbtnAyuda.setEnabled(false);
		    			}
		    		}
		    		else {
		    			ArrayList<Integer> c = CtrlPresentacion.getCode(idP);
				    	for (int i = 0; i < botones_combinacion.length; ++i) {
				    		botones_combinacion[i].setBackground(Intmap.get(c.get(i)));
				    	}
		    			JOptionPane.showMessageDialog(null, "Se han terminado los intentos. Ahora eres CodeMaker", "Cambio de rol", JOptionPane.INFORMATION_MESSAGE);
				    	CtrlPresentacion.cambioRol(idP);
				    	rol = CtrlPresentacion.getRol(idP);
				    	label_3_1.setText(rol);
				    	++counter;
				    	
				    	for (int i = 0; i < rowCount; ++i) {
				    		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
				    		model1.removeRow(0);
				    		
				    		DefaultTableModel model2 = (DefaultTableModel) table_1.getModel();
				    		model2.removeRow(0);
				    	}
				    	limpia_casillas();
				    	limpia_combinacion();
				    	label_1.setText(""); 
				    	for (int i = 0; i < botones_combinacion.length; i++) {
				    		botones_combinacion[i].setEnabled(true);
				    		botones_casillas[i].setEnabled(false);
				    	}
				    	tglbtnAyuda.setEnabled(false);
		    		}
		    		if (counter == 2 && !enter) {
		    			++rondas;
		    			puntuacion += CtrlPresentacion.actualizarPuntuacion(idP,rowCount,intentos_maquina.size());
		    			label_2.setText(Integer.toString(puntuacion));
		    			label.setText(Integer.toString(rondas));
		    			Object[] o = new Object[]{"Sí", "No"};
		                int seleccion = JOptionPane.showOptionDialog(null, "Quieres guardar la partida y continuarla más tarde?\n", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, o, "Sí");
		                if (seleccion == 0) {
		                	CtrlPresentacion.guardarPartida(idP, puntuacion);
		                	CtrlPresentacion.actualizarNotFinished(idP);
		                	JOptionPane.showMessageDialog(null, "Guardada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                	setVisible(false);
			                CtrlPresentacion.iniMenuPrincipal();
		                }

		    		}
		    		else if (counter == 4) {
		    			JOptionPane.showMessageDialog(null, "Ha finalizado la partida.\n Gracias por jugar.", "Fin partida", JOptionPane.INFORMATION_MESSAGE);
		    			CtrlPresentacion.actualizarPuntuacion(idP, rowCount, intentos_maquina.size());
		    			CtrlPresentacion.actualizarRankingDificil(id);
		    			CtrlPresentacion.setAcabada(idP);
		    			CtrlPresentacion.actualizarRecordHistorial(idP);
		    			setVisible(false);
		    			CtrlPresentacion.iniMenuPrincipal();
		    		}
		    	}
		    	else if (CtrlPresentacion.getRol(idP).equals("CodeMaker")) {
		    		rowCount = 0;
	    			Color[] selectedColors = new Color[botones_combinacion.length];
	    			boolean hasBlackColor = false;
	    			for (int i = 0; i < botones_combinacion.length; i++) {
	    				selectedColors[i] = botones_combinacion[i].getBackground();
	    				if (selectedColors[i] == Color.BLACK) {
	    					hasBlackColor = true;
	    					break;
	    				}
	    			}
	            
	    			if (hasBlackColor) {
	    				JOptionPane.showMessageDialog(null, "Por favor, introduzca una combinación válida.");
	    				return;
	    			}
	    			List<Integer> code = new ArrayList<> ();
	    			for (int i = 0; i < botones_combinacion.length; ++i) {
	    				Color c = botones_combinacion[i].getBackground();
	    				code.add(mapColors.get(c));		    				
	    			}
	    			intentos_maquina = CtrlPresentacion.solve(idP, code);
	    			Color[][] result = new Color[10][botones_casillas.length];
	    			int r = 0;
	    			for (List<Integer> l : intentos_maquina) {
		    			DefaultTableModel model = (DefaultTableModel) table.getModel();
		    			model.addRow(new Object[]{"", "", "", " "});
		    			Color[] col = new Color[botones_casillas.length];
	    				for (int i = 0; i < l.size(); ++i) {
	    					col[i] = (Intmap.get(l.get(i)));
	    				}
	    				result[r] = col;
	    				++r;
	    			}
	    			table.getColumnModel().getColumn(0).setCellRenderer(new ColorCellRenderer(result));
	    			table.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer(result));
	    			table.getColumnModel().getColumn(2).setCellRenderer(new ColorCellRenderer(result));
	    			table.getColumnModel().getColumn(3).setCellRenderer(new ColorCellRenderer(result));
		    		JOptionPane.showMessageDialog(null, "Ahora eres CodeBreaker", "Cambio de rol", JOptionPane.INFORMATION_MESSAGE);

		    		CtrlPresentacion.cambioRol(idP);
		    		rol = CtrlPresentacion.getRol(idP);
		    		++counter;
		    		CtrlPresentacion.newCode(idP);
		    		label_3_1.setText(rol);
			    	
		    		for (int i = 0; i < intentos_maquina.size(); ++i) {
		    			DefaultTableModel model = (DefaultTableModel) table.getModel();
		    			model.removeRow(0);
		    		}
		    		limpia_combinacion();
		    		for (int i = 0; i < botones_combinacion.length; i++) {
		    			botones_combinacion[i].setEnabled(false);
		    			botones_casillas[i].setEnabled(true);
		    		}
		    		tglbtnAyuda.setEnabled(true);
		    		if (counter == 2 && !enter) {
		    			++rondas;
		    			enter = true;
		    			puntuacion += CtrlPresentacion.actualizarPuntuacion(idP,intentos_jugador.size(),intentos_maquina.size());
		    			label_2.setText(Integer.toString(puntuacion));
		    			label.setText(Integer.toString(rondas));
		    			intentos_jugador.clear();
		    			
		    			Object[] o = new Object[]{"Sí", "No"};
		                int seleccion = JOptionPane.showOptionDialog(null, "Quieres guardar la partida y continuarla más tarde?\n", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, o, "Sí");
		                if (seleccion == 0) {
		                	CtrlPresentacion.guardarPartida(idP, puntuacion);
		                	CtrlPresentacion.actualizarNotFinished(idP);
		                	JOptionPane.showMessageDialog(null, "Guardada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                	setVisible(false);
			                CtrlPresentacion.iniMenuPrincipal();
		                }
		    		}
		    		else if (counter == 4) {
		    			JOptionPane.showMessageDialog(null, "Ha finalizado la partida.\n Gracias por jugar.", "Fin partida", JOptionPane.INFORMATION_MESSAGE);
		    			CtrlPresentacion.actualizarPuntuacion(idP, intentos_jugador.size(), intentos_maquina.size());
		    			CtrlPresentacion.actualizarRankingDificil(id);
		    			CtrlPresentacion.setAcabada(idP);
		    			CtrlPresentacion.actualizarRecordHistorial(idP);
		    			setVisible(false);
		    			CtrlPresentacion.iniMenuPrincipal();
		    		}
		    	}
		    }
		});
		
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		
		
		
		GroupLayout gl_panel_rol = new GroupLayout(panel_rol);
		gl_panel_rol.setHorizontalGroup(
			gl_panel_rol.createParallelGroup(Alignment.LEADING)
				.addGap(0, 243, Short.MAX_VALUE)
				.addGroup(gl_panel_rol.createSequentialGroup()
					.addComponent(lblRol_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_3_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(85))
		);
		gl_panel_rol.setVerticalGroup(
			gl_panel_rol.createParallelGroup(Alignment.LEADING)
				.addGap(0, 29, Short.MAX_VALUE)
				.addGroup(gl_panel_rol.createSequentialGroup()
					.addGroup(gl_panel_rol.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRol_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_rol.setLayout(gl_panel_rol);
		
		table_1 = new JTable();
		table_1.setForeground(new Color(0, 0, 0));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA rojas", "N\u00BA blancas", "N\u00BA vac\u00EDas"
			}
		));
		table_1.setEnabled(false);
		table_1.getTableHeader().setEnabled(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(109);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(123);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(88);
		scrollPane.setViewportView(table_1);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Casilla 1", "Casilla 2", "Casilla 3", "Casilla 4"
			}
		));
		table.setEnabled(false);
		table.getTableHeader().setEnabled(false);
		contentPane.setLayout(null);
		Intentos.setViewportView(table);
		contentPane.add(Intentos);
		contentPane.add(lblRonda);
		contentPane.add(label);
		contentPane.add(lblPuntuacin);
		contentPane.add(label_2);
		contentPane.add(tglbtnAyuda);
		contentPane.add(label_1);
		contentPane.add(lblCombinacinSecreta);
		contentPane.add(panel_combinacion);
		contentPane.add(scrollPane);
		contentPane.add(panel_rol);
		contentPane.add(lblColores);
		contentPane.add(panel_colores);
		contentPane.add(lblCasillas);
		contentPane.add(panel_casillas);
		contentPane.add(btnComprueba);
	}
	
	public void limpia_casillas() {
		for (int i = 0; i < botones_casillas.length; i++) {
            botones_casillas[i].setBackground(Color.BLACK);
            botones_casillas[i].setForeground(Color.WHITE);
        }
	}
	
	public void limpia_combinacion() {
		for (int i = 0; i < botones_combinacion.length; i++) {
            botones_combinacion[i].setBackground(Color.BLACK);
            botones_combinacion[i].setForeground(Color.WHITE);
        }
	}
		
	public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botones_colores.length; i++) {
            if (e.getSource() == botones_colores[i]) {
                color_seleccionado = colores[i];
                casilla_seleccionada = -1;
                return;
            }
        }
        if (CtrlPresentacion.getRol(idP).equals("CodeBreaker")) {
            for (int i = 0; i < botones_casillas.length; i++) {
                if (e.getSource() == botones_casillas[i]) {
                    casilla_seleccionada = i;
                    if (color_seleccionado != null) {
                        botones_casillas[i].setBackground(color_seleccionado);
                        botones_casillas[i].setForeground(Color.BLACK);
                        color_seleccionado = null;
                        casilla_seleccionada = -1;
                    }
                    return;
                }
            }        	
        }
        else {
            for (int i = 0; i < botones_combinacion.length; i++) {
                if (e.getSource() == botones_combinacion[i]) {
                    casilla_seleccionada = i;
                    if (color_seleccionado != null) {
                        botones_combinacion[i].setBackground(color_seleccionado);
                        botones_combinacion[i].setForeground(Color.BLACK);
                        color_seleccionado = null;
                        casilla_seleccionada = -1;
                    }
                    return;
                }
            }
        }
    }
}
