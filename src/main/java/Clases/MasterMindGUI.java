package Clases;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class MasterMindGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected NivelGUI dialog = new NivelGUI();
	protected String nivel;
	protected JPanel panelColoresDisponibles;
	protected JPanel panelCombinacionSecreta;
	protected JPanel panelComprobacion;
	protected MasterMind juego;
	protected JPanel panelUsuario;

	/**
	 * Create the frame.
	 */
	public MasterMindGUI() {
		setTitle("Master Mind");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		
//		 Cierra el programa al clickar SALIR
		mntmSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				setVisible(false);
			}
		});
		mntmSalir.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSalir.setBackground(SystemColor.activeCaption);
		mnArchivo.add(mntmSalir);

//		 Item de menu nueva partida.
		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva partida");

		mnArchivo.add(mntmNuevaPartida);

		JMenuItem mntmNivel = new JMenuItem("Nivel");
		mntmNivel.addMouseListener(new MouseAdapter() {

//			Llama a la interfasce de niveles
			@Override
			public void mousePressed(MouseEvent e) {

				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		});
		mnArchivo.add(mntmNivel);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

//		Muestra info de como jugar.
		JMenuItem mntmComo = new JMenuItem("Como jugar");
		mntmComo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(mntmComo, "Es un juego muy liado, estaría mejor si buscas reglas por internet");
			}
		});
		mntmComo.setBackground(SystemColor.activeCaption);
		mnAyuda.add(mntmComo);

//		Muestra los creadores del juego.
		JMenuItem mntmAcerca = new JMenuItem("Acerca De");
		mntmAcerca.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				JOptionPane.showMessageDialog(mntmAcerca, "Created by Katia, Raul, Sergio.\nAll rights reserved.");
			}
		});

		mnAyuda.add(mntmAcerca);

//		Paneles
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));

		panelUsuario = new JPanel();
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(new GridLayout(1, 0, 0, 0));

		panelComprobacion = new JPanel();
		contentPane.add(panelComprobacion);
		panelComprobacion.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelSolucion = new JPanel();
		contentPane.add(panelSolucion);
		panelSolucion.setLayout(new GridLayout(2, 0, 0, 5));

		JPanel panelParaColoresDisponibles = new JPanel();
		panelParaColoresDisponibles.setBorder(new LineBorder(new Color(0, 102, 255), 1, true));
		panelSolucion.add(panelParaColoresDisponibles);
		panelParaColoresDisponibles.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblColores = new JLabel("Colores Disponibles");
				
		lblColores.setVerticalAlignment(SwingConstants.BOTTOM);
		lblColores.setHorizontalAlignment(SwingConstants.CENTER);
		panelParaColoresDisponibles.add(lblColores);
		
		panelColoresDisponibles = new JPanel();
		panelParaColoresDisponibles.add(panelColoresDisponibles);
		
		
		JPanel panelParaCombinacionSecreta = new JPanel();
		panelParaCombinacionSecreta.setBorder(new LineBorder(new Color(0, 102, 255), 1, true));
		panelSolucion.add(panelParaCombinacionSecreta);
		panelParaCombinacionSecreta.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblSecreta = new JLabel("Combinación secreta");
		lblSecreta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSecreta.setHorizontalAlignment(SwingConstants.CENTER);
		panelParaCombinacionSecreta.add(lblSecreta);
		
		panelCombinacionSecreta = new JPanel();
		panelParaCombinacionSecreta.add(panelCombinacionSecreta);	
		
//		Llama a un metodo que se ejecuta cada vez que elegimos juego nuevo.
		MouseAdapter nuevoJuego = new MouseAdapter() {
			
//			Listener de pulsación de ratón
			@Override
			public void mousePressed(MouseEvent e) {
				
//				Elimina componentes de la partida anterior.
				MasterMind.borrarComponentes(panelColoresDisponibles);
				MasterMind.borrarComponentes(panelCombinacionSecreta);
				MasterMind.borrarComponentes(panelUsuario);
				MasterMind.borrarComponentes(panelComprobacion);
				
//				/Guarda el nivel que ha elegido usuario.
				nivel = dialog.getNivel();

//				Comprueba si es null, y si usuario no ha elegido ningún nivel
//				muestra panel de niveles de nuevo para que usuario lo elija.
				
				if (nivel == null) {
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					nivel = dialog.getNivel();

				} else {
					
//					COMIENZA EL JUEGO				
					juego = new MasterMind(nivel, panelColoresDisponibles);
					panelUsuario.setLayout(new GridLayout(juego.numIntentos, 0, 0, 0));
					panelComprobacion.setLayout(new GridLayout(juego.numIntentos, 0, 0, 0));

//					Muestra el array de colores disponibles.
					setVisible(true);
					
					juego.jugar(panelUsuario, panelCombinacionSecreta, panelComprobacion);
					
//					Muestra ka combinacion secreta.
					setVisible(true);				
				}		
			}
		};
		
		mntmNuevaPartida.addMouseListener(nuevoJuego);				
	}
}