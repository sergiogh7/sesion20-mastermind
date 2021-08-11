package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MasterMind extends JFrame {

	// atributos,

	private static final long serialVersionUID = 1L;
	protected Color[] arrayTodosColores = { Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE,
			Color.PINK, Color.RED, Color.YELLOW };
	protected String nivel;
	protected int numIntentos = 0;
	protected int numColores = 0;
	protected JLabel[] bolaColores;
	protected JLabel[] bolaSolucion;
	protected JLabel[] bola;
	protected JLabel[] bolaresul;
	protected Color[] arrayColoresDisponibles;
	protected JButton btnComprobar;
	protected int contadorDerecho = (numColores - 1);
	protected int contadorIzquierdo = 0;
	protected int contadorBolasNegras = 0;
	protected int contadorBolasBlancas = 0;
	protected boolean isGanado = false;

//	Valores por defecto.
	private final String NIVEL_DEFECTO = "Principiante";
	private final int INTENTOS_DEFECTO = 10;
	private final int COLORES_DEFECTO = 4;

//	Constructor.
//	Por defecto es el nivel de Principiante.
	public MasterMind() {
		super();
		this.nivel = this.NIVEL_DEFECTO;
		this.numIntentos = this.INTENTOS_DEFECTO;
		this.numColores = this.COLORES_DEFECTO;
	}

//	Constructor con el parámetro nivel.
	public MasterMind(String nivel, JPanel panelColoresDisponibles) {
		super();
		this.nivel = nivel;
		this.setNumIntentos(this.nivel);
		this.setNumColores(this.nivel);

//		Inicia el panel de colores disponibles y crea el array de solución.
		this.crearColores(panelColoresDisponibles);
		this.crearSolucion();
	}

	// METODOS.
	/////////// PANEL DE COLORES DISPONIBLES Y DE SOLUCIÓN
	///////////////////////////////////////////////////////

//	/Para crear los array de Labels con los colores disponibles,
//	controlamos que no se repitan colores.
	public void crearColores(JPanel panelColoresDisponibles) {

//		Inicia el array de colores que vamos a asignar.
		this.arrayColoresAuxiliar();

		this.bolaColores = new JLabel[this.numColores];

//		Elegimos colores aleatoriamente.
		for (int i = 0; i < this.bolaColores.length; i++) {

			this.bolaColores[i] = this.crearLabel();
			this.bolaColores[i].setBackground(this.arrayColoresDisponibles[i]);
			this.bolaColores[i].setBorder(new LineBorder(Color.DARK_GRAY));

			panelColoresDisponibles.add(this.bolaColores[i]);
		}
	}

//	Metodo que crea combinación secreta.
	public void crearSolucion() {

		this.bolaSolucion = new JLabel[this.numColores];

//		Elegimos colores aleatoriamente.
		for (int i = 0; i < this.bolaSolucion.length; i++) {

			this.bolaSolucion[i] = this.crearLabel();
			this.bolaSolucion[i]
					.setBackground(this.arrayColoresDisponibles[this.randomNum(this.arrayColoresDisponibles.length)]);
			this.bolaSolucion[i].setBorder(new LineBorder(Color.DARK_GRAY));
		}
	}

//	Array de colores que no se repiten para crear etiquetas de colores disponibles,
//	para rellenar el panel de los colores disponibles.
	private void arrayColoresAuxiliar() {
		Color test = this.arrayTodosColores[this.randomNum(this.arrayTodosColores.length)];

		this.arrayColoresDisponibles = new Color[this.numColores];

		for (int i = 0; i < this.arrayColoresDisponibles.length; i++) {

			do {
				test = this.arrayTodosColores[this.randomNum(this.arrayTodosColores.length)];
			} while (searchList(this.arrayColoresDisponibles, test));

			this.arrayColoresDisponibles[i] = test;
		}
	}

//	Metodo que busca el color repetido.
	private boolean searchList(Color[] strings, Color searchString) {
		return Arrays.asList(strings).contains(searchString);
	}

//	El metodo principal que se ejecuta la interface.
	public void jugar(JPanel panel, JPanel panelCombinacionSecreta, JPanel panelComprobacion) {

		if (hasIntentos() && !isGanado) {
			this.ejecutarIntento(panel, panelCombinacionSecreta, panelComprobacion);
		}
	}

//	El metodo principal que ejecuta un intento,
	public void ejecutarIntento(JPanel panel, JPanel panelCombinacionSecreta, JPanel panelComprobacion) {

		this.crear_linea_bola(panel, panelCombinacionSecreta, panelComprobacion);

		this.elegirColor();

	}

//	Metodo que comprueba la cantidad de bolas negras,
//	y si son iguales a número de colores según el nivel elgido
//	el usuario ha ganado la partida, de lo contrario el programa continua.
	protected void setIsGanado() {

		if (this.contadorBolasNegras == this.numColores) {
			this.isGanado = true;
		} else {

			this.isGanado = false;
		}
	}

//	Metodo que comprueba si hay intentos,
	protected boolean hasIntentos() {

		boolean hasIntentos = true;

		if (this.getNumIntentos() == 1) {
			hasIntentos = false;
		}

		return hasIntentos;
	}

	////////////////////// PANEL USUARIO //////////////////
//	Metodo para crear una linea de intentos y botón para comprobar.
	protected void crear_linea_bola(JPanel panel, JPanel panelCombinacionSecreta, JPanel panelComprobacion) {

		JPanel panelLinea = new JPanel();

		panel.add(panelLinea);

		this.bola = new JLabel[this.numColores];

		for (int i = 0; i < this.bola.length; i++) {

			this.bola[i] = this.crearLabel();
			this.bola[i].setBackground(new Color(244, 244, 244));
			this.bola[i].setBorder(new LineBorder(Color.DARK_GRAY));
			panelLinea.add(this.bola[i]);
		}

		this.btnComprobar = new JButton("Compr");
		panelLinea.add(this.btnComprobar);

		this.addListenerBtnComprobar(panel, panelCombinacionSecreta, panelComprobacion);

	}

//	Para elegir colores dentro de un intento.
	protected void elegirColor() {

		for (int i = 0; i < bola.length; i++) {

			final int innerI = i;

			bola[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

//					Si usuario hace click con el boton derecho de raton,
//					va desde final al inicio de array.
					if (e.isMetaDown()) {

						if (contadorDerecho < 0) {
							contadorDerecho = (numColores - 1);
						}

						for (int g = arrayColoresDisponibles.length - 1; g >= 0; g--) {
							bola[innerI].setBackground(arrayColoresDisponibles[contadorDerecho]);
						}

						contadorDerecho--;
					}
//					si usuario hace click el boton izquierdo de raton,
//					va en orden normal del array.
					else if (!e.isAltDown() && !e.isShiftDown()) {

						if (contadorIzquierdo > (numColores - 1)) {
							contadorIzquierdo = 0;
						}

						for (int j = 0; j < arrayColoresDisponibles.length; j++) {
							bola[innerI].setBackground(arrayColoresDisponibles[contadorIzquierdo]);
						}
						contadorIzquierdo++;
					}
				}
			});
		}
	}

//	Metodo que escucha cuando usuario pulsa el boton comprobar.
	protected void addListenerBtnComprobar(JPanel panel, JPanel panelCombinacionSecreta, JPanel panelComprobacion) {

//		En cada intento nuevo reinciia los contadores de bolas negras y bolas blancas.
		contadorBolasNegras = 0;
		contadorBolasBlancas = 0;

		ArrayList<Color> arrayAuxiliar = new ArrayList<>();

		this.btnComprobar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				for (int i = 0; i < bola.length; i++) {

//					Comprueba si el usuario ha acertado con la posición y el color de bola,
//					entonces contamos negras.
					if (bola[i].getBackground() == bolaSolucion[i].getBackground()) {
						contadorBolasNegras++;
					} else {

//						Contamos blancas
//						Garda en un arraylist bolas donde este el color pero no la posicion,
//						y descarta bolas repetiodas
						for (int j = 0; j < bolaSolucion.length; j++) {

							if (bola[i].getBackground() == bolaSolucion[j].getBackground()) {

								if (!arrayAuxiliar.contains(bola[i].getBackground())) {
									arrayAuxiliar.add(bola[i].getBackground());
								}
							}
						}
					}
				}
				
//				Guarda la cantidad de bolas blancas.
				contadorBolasBlancas = arrayAuxiliar.size();

//				Creamos línea nueva de comprobación.
				crear_linea_bolaresul(panelComprobacion);

//				Continua el juego cuando el contador de bolas negras 
//				es menor a la dificultad elegida por el usuario y todavia tiene intentos.
				if (contadorBolasNegras != numColores && hasIntentos()) {
					
//					Resta un intento.
					numIntentos--;

					ejecutarIntento(panel, panelCombinacionSecreta, panelComprobacion);
				
//				En caso de haber ganado la partida.
				} else {
					
					setIsGanado();

//					Muestra la solucion.
					for (int j = 0; j < bolaSolucion.length; j++) {

						panelCombinacionSecreta.add(bolaSolucion[j]);
					}
					
//					Muestra un mensaje.
					if (isGanado) {
						JOptionPane.showMessageDialog(panel, "Ha ganado");
					}

//					Si usuario ha perdidio muestra un mensaje de que usuario ha perdido,
//					y bloquea el boton de comprobacion.
					else {
						
//						Bloquea el boton de comprobacion.
						btnComprobar.setEnabled(false);

//						Muestra un mensaje.
						JOptionPane.showMessageDialog(panel, "Ha perdido");
					}
				}
			}
		});
	}

	////////// PANEL COMPROBACIÓN /////////////////////////
//	Metodo para mostrar resultado de un intento.
	protected void crear_linea_bolaresul(JPanel panel) {

		JPanel panelLinea = new JPanel();

		panel.add(panelLinea);

		this.bolaresul = new JLabel[this.contadorBolasNegras + this.contadorBolasBlancas];

		for (int i = 0; i < this.bolaresul.length; i++) {

			this.bolaresul[i] = this.crearLabel();
		}

		for (int j = 0; j < this.bolaresul.length; j++) {

			for (int l = 0; l < this.contadorBolasNegras; l++) {

				this.bolaresul[l].setBackground(Color.black);
			}

			for (int g = this.contadorBolasNegras; g < this.bolaresul.length; g++) {

				this.bolaresul[g].setBackground(Color.white);
			}
			this.bolaresul[j].setHorizontalAlignment(SwingConstants.LEADING);
			panelLinea.add(this.bolaresul[j]);
		}

		this.btnComprobar.setVisible(false);
	}

	//// Metodos auxiliares,
//	MEtodo que crea un label simple.
	protected JLabel crearLabel() {

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setPreferredSize(new Dimension(15, 15));

		return lblNewLabel;

	}

//	MEtodo para elegir el numero de color aleatoriamente.
	private int randomNum(int numMax) {
		Random r = new Random();
		return r.nextInt(numMax - 1);
	}

	public static void borrarComponentes(JPanel panel) {
		panel.removeAll();
		panel.repaint();
	}

//	Setter para asignar intentos según el nivel elegido.
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

//	Comprueba la cantidad de intentos.
	public int getNumIntentos() {
		return numIntentos;
	}

//	Setter para asignar la cantidad de colores que tiene el juego segun el nivel elegido.
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

//	Metodo toString.
	@Override
	public String toString() {
		return "MasterMind [nivel=" + nivel + ", intentos=" + numIntentos + ", colores=" + numColores + "]";
	}

}