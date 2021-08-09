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
		setBounds(100, 100, 650, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		
		//para cerrar el programa,
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

		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva partida");

		mnArchivo.add(mntmNuevaPartida);

		JMenuItem mntmNivel = new JMenuItem("Nivel");
		mntmNivel.addMouseListener(new MouseAdapter() {

			// llamamos el interface de niveles,
			@Override
			public void mousePressed(MouseEvent e) {

				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		});
		mnArchivo.add(mntmNivel);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmComo = new JMenuItem("Como jugar");
		mntmComo.setBackground(SystemColor.activeCaption);
		mnAyuda.add(mntmComo);

		JMenuItem mntmAcerca = new JMenuItem("Acerca De");
		mntmAcerca.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				JOptionPane.showMessageDialog(mntmAcerca, "Create by group Raul, Sergio, Katia. All right reserved)))");
			}
		});

		mnAyuda.add(mntmAcerca);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));

		panelUsuario = new JPanel();
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(new GridLayout(1, 0, 0, 0));

		panelComprobacion = new JPanel();
		contentPane.add(panelComprobacion);

		JPanel panelSolucion = new JPanel();
		contentPane.add(panelSolucion);
		panelSolucion.setLayout(new GridLayout(2, 0, 0, 0));

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
		
		
		
		
		
		MouseAdapter nuevoJuego = new MouseAdapter() {
			
			// cuando usuario quiere empezar un juego nuevo,
			@Override
			public void mousePressed(MouseEvent e) {
				
				//para borrar componentes de la partida anterior,
				MasterMind.borrarComponentes(panelColoresDisponibles);
				MasterMind.borrarComponentes(panelCombinacionSecreta);
				MasterMind.borrarComponentes(panelUsuario);
				MasterMind.borrarComponentes(panelComprobacion);
				
				// guardamos el nivel que ha elegido usuario,
				nivel = dialog.getNivel();
				System.out.println(nivel);

				// comprobamos si es null en el caso cuando usuario ,
				// no ha elegido ningún nivel, mostramos panel de
				//niveles de nuevo para que usuario lo eliga,
				
				if (nivel == null) {
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					nivel = dialog.getNivel();

					System.out.println("Nivel 2:" + nivel);
				} else {
					// empieza el juego,
					
					juego = new MasterMind(nivel, panelColoresDisponibles);
					panelUsuario.setLayout(new GridLayout(juego.numIntentos, 0, 0, 0));
					System.out.println(juego.toString());

					//para que se vea array de colores disponibles,
					setVisible(true);
					
					//añadimos componentes de colores disponibles,
				/*	for (int i = 0; i < juego.bolaColores.length; i++) {
			
						panelColoresDisponibles.add(juego.bolaColores[i]);
						
						setVisible(true);
					}	*/
					
					juego.jugar(panelUsuario, panelCombinacionSecreta, panelComprobacion);
					
					//para que se vea la combinación secreta,
					setVisible(true);
					
					
					
					
					
					/*for (int j = 0; j < juego.bolaSolucion.length; j++) {

						panelCombinacionSecreta.add(juego.bolaSolucion[j]);
						
						setVisible(true);
					}*/
					
					
				
				}		
			}
		};
		
		mntmNuevaPartida.addMouseListener(nuevoJuego);
		
		
	}

}
