package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MasterMind extends JFrame {

	// atributos,

	private static final long serialVersionUID = 1L;
	protected Color[] arrayTodosColores = { Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK,
			Color.RED, Color.YELLOW };
	protected String nivel;
	protected int numIntentos = 0;
	protected int numColores = 0;
	protected JLabel[] bolaColores;
	protected JLabel[] bolaSolucion;
	protected JLabel[] bola;
	protected Color[] arrayColoresDisponibles;

	private final String NIVEL_DEFECTO = "Principiante";
	private final int INTENTOS_DEFECTO = 10;
	private final int COLORES_DEFECTO = 4;

	// constructor,
	// por defecto es el nivel de Principiante,
	public MasterMind() {
		super();
		this.nivel = this.NIVEL_DEFECTO;
		this.numIntentos = this.INTENTOS_DEFECTO;
		this.numColores = this.COLORES_DEFECTO;
	}

	// constructor con el parámetro nivel,
	public MasterMind(String nivel) {
		super();
		this.nivel = nivel;
		this.setNumIntentos(this.nivel);
		this.setNumColores(this.nivel);
		
		this.crearColores();
		this.crearSolucion();

	}

	// métodos,
	
	//array de colores que no se repiten para crear etiquetas de colores disponibles,
	private void arrayColoresAuxiliar() {
		Color test = this.arrayTodosColores[this.randomNum(this.arrayTodosColores.length)];

		this.arrayColoresDisponibles = new Color[this.numColores];

		for (int i = 0; i < this.arrayColoresDisponibles.length; i++) {

			do {
				test = this.arrayTodosColores[this.randomNum(this.arrayTodosColores.length)];
			} 
			while (searchList(this.arrayColoresDisponibles, test));
	
			this.arrayColoresDisponibles[i] = test;			
		}
	}

	//método para buscar el color repetido,
	private boolean searchList(Color[] strings, Color searchString) {
		return Arrays.asList(strings).contains(searchString);
	}

	
	// para crear array de Labels con colores disponibles,
	// controlamos que no se repiten colores,
	public void crearColores() {
		
		//inicializamos el array de colores que vamos a asignar,
		this.arrayColoresAuxiliar();

		// System.out.println(randomNumColor());
		this.bolaColores = new JLabel[this.numColores];

		// elegimos colores aleatoriamente;

		for (int i = 0; i < this.bolaColores.length; i++) {

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBackground(this.arrayColoresDisponibles[i]);
			lblNewLabel.setPreferredSize(new Dimension(15, 15));
			
			this.bolaColores[i] = lblNewLabel;

		}
	}
	
	//método para crear linea de un intento,
	public void crear_linea_bola(JPanel panel) {
		
		JPanel panelLinea = new JPanel();
		panel.add(panelLinea);
		
		this.bola = new JLabel[this.numColores];
		
		for (int i = 0; i < this.bola.length; i++) {
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBackground(Color.white);
			lblNewLabel.setPreferredSize(new Dimension(15, 15));
			
			this.bola[i] = lblNewLabel;	
			panelLinea.add(this.bola[i]);
		}
	}

	//método que crea combinación secreta,
	public void crearSolucion() {
		
		this.bolaSolucion = new JLabel[this.numColores];
		
		System.out.println("solucion len" + this.bolaSolucion.length);

		// elegimos colores aleatoriamente;

		for (int i = 0; i < this.bolaSolucion.length; i++) {
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setOpaque(true);
			
			lblNewLabel.setBackground(this.arrayColoresDisponibles[this.randomNum(this.arrayColoresDisponibles.length)]);
			lblNewLabel.setPreferredSize(new Dimension(15, 15));
			
			this.bolaSolucion[i] = lblNewLabel;

		}
	}



	// método para elegir número de color aleatoriamente,
	private int randomNum(int numMax) {
		Random r = new Random();
		return r.nextInt(numMax - 1);
	}
	

	public static void borrarComponentes(JPanel panel) {
		panel.removeAll();
		panel.repaint();
	}

	// setter para asignar intentos según el nivel elegido,
	public void setNumIntentos(String nivel) {

		switch (nivel) {
		case "Medio":
			this.numIntentos = 8;
			break;
		case "Avanzado":
			this.numIntentos = 6;
			break;
		default:
			this.numIntentos = this.INTENTOS_DEFECTO;
			break;
		}
	}

	// setter para asignar cantidad de colores que tiene el juego según el nivel
	// elegido,
	public void setNumColores(String nivel) {

		switch (nivel) {
		case "Medio":
			this.numColores = 5;
			break;
		case "Avanzado":
			this.numColores = 6;
			break;
		default:
			this.numColores = this.COLORES_DEFECTO;
			break;
		}
	}

	@Override
	public String toString() {
		return "MasterMind [nivel=" + nivel + ", intentos=" + numIntentos + ", colores=" + numColores + "]";
	}

}
