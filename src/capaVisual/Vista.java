package capaVisual;

import java.awt.*;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import capaNegocio.CapaLogica;

public class Vista extends DefaultTableModel{
	
	private CapaLogica capaLogica;
	private ArrayList<JTextArea> cuadrosDePal = new ArrayList<>();
	private JTextArea intentosRestantes;
	private JFrame ventanaJuego;
	private JComboBox idioma;
	private JComboBox dificultad;
	private JComboBox jugador;
	private JButton closeAppButton;
	private JButton playAgainButton;
	
	public Vista() {
		this.capaLogica = new CapaLogica();
		this.intentosRestantes = new JTextArea();
		this.ventanaJuego = new JFrame();
		pantallaInicial();
	}
	
	private JFrame inicializarVentana() {
		JFrame ventana = new JFrame("");
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(""));
		ventana.setTitle("Ahorcado");
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanioPantalla.height;
		int anchoPantalla = tamanioPantalla.width;
		ventana.setSize(480, 270);
		ventana.setLocation(480, 270);
		ventana.getContentPane().setLayout(null);
		return ventana;
		
	}
	
	private JComboBox crearComboBox(Object[] array, int x, int y, int width, int heigth, int index) {
		JComboBox box = new JComboBox(array);
		box.setBounds(x, y, width, heigth);
		box.setSelectedIndex(index);
		return box;
	}
	
	private JTextArea crearTextArea(boolean editable, String text, int x, int y, int width, int heigth) {
		JTextArea texto = new JTextArea();
		texto.setEditable(editable);
		texto.setBackground(Color.CYAN);
		texto.setText(text);
		texto.setBounds(x, y, width, heigth);
		return texto;
	}
	
	private JButton crearBoton(String nombre, String text, int x, int y, int width, int heigth) {
		JButton boton = new JButton(nombre);
		boton.setText(text);
		boton.setBounds(x, y, width, heigth);
		return boton;
	}

	private Component agregarComponente(JFrame frame, Component componente){
		return frame.getContentPane().add(componente);
	}
	
	private void pantallaInicial() {
		JFrame configuraciones = inicializarVentana();
		configuraciones.setSize(539, 360);

	
		configuraciones.setResizable(false);
		configuraciones.setVisible(true);
		
		JTextArea welcome = crearTextArea(false, "BIENVENIDO AL JUEGO DEL AHORCADO", 
				0, 0, 348, 22);
		welcome.setFont(new Font("Modern No. 20", Font.BOLD | Font.ITALIC, 16));
		welcome.setForeground(Color.RED);
		welcome.setBackground(Color.black);
		agregarComponente(configuraciones, welcome);
		
		JTextArea nombreTextArea = crearTextArea(false, "Introduzca su nombre \r\nde usuario:", 248, 35, 165, 40);
		nombreTextArea.setBackground(Color.BLACK);
		nombreTextArea.setForeground(Color.WHITE);
		agregarComponente(configuraciones, nombreTextArea);
		
		JTextArea nombreJugador = crearTextArea(true, "", 341, 86, 147, 22);
		agregarComponente(configuraciones, nombreJugador);
		
		this.idioma = crearComboBox(capaLogica.getIdiomasDisponibles().toArray(), 341, 130, 147, 22, 0);	
		this.dificultad = crearComboBox(capaLogica.getDificultadesDisponibles().toArray(), 341, 174, 147, 22, 0);
		this.jugador = crearComboBox(capaLogica.getTiposJugador().toArray(), 341, 214, 147, 22, 0);
		
		JTextArea idiomaText = crearTextArea(false, "Idioma:", 270, 129, 61, 22);
		idiomaText.setBackground(Color.BLACK);
		idiomaText.setForeground(Color.WHITE);
		
		JTextArea dificultadText = crearTextArea(false, "Dificultad:", 258, 173, 93, 22);
		dificultadText.setBackground(Color.BLACK);
		dificultadText.setForeground(Color.WHITE);
		
		JTextArea jugadorText = crearTextArea(false, "Jugador:", 262, 213, 69, 22);
		jugadorText.setBackground(Color.BLACK);
		jugadorText.setForeground(Color.WHITE);
		
		JTextArea nombreInvalido = crearTextArea(false, "", 248, 97, 269, 22);
		nombreInvalido.setBackground(Color.BLACK);
		
		JButton botonNext = crearBoton("next", "Next", 424, 287, 89, 23);
		botonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					capaLogica.iniciarJuego(nombreJugador.getText(), (String) idioma.getSelectedItem(), (String) dificultad.getSelectedItem(), 
							(String) jugador.getSelectedItem());
					configuraciones.setVisible(false);
					jugar();		
			}

		});
		agregarComponente(configuraciones, idioma);
		agregarComponente(configuraciones, dificultad);
		agregarComponente(configuraciones, jugador);
		agregarComponente(configuraciones, botonNext);
		agregarComponente(configuraciones, idiomaText);
		agregarComponente(configuraciones, dificultadText);
		agregarComponente(configuraciones, jugadorText);
		agregarComponente(configuraciones, nombreInvalido);
		

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Vista.class.getResource("/capaVisual/fondoInicial.jpg")));
		fondo.setBounds(0, 0, 523, 321);
		agregarComponente(configuraciones, fondo);
	}

	public void jugar() {
		
		this.ventanaJuego = inicializarVentana();
		this.ventanaJuego.setSize(640, 360);
		this.ventanaJuego.setLocation(480, 270);
		
		JTextArea usuario = crearTextArea(false, "Usuario: "+ capaLogica.getNombreJugador(), 216, 11, 195, 22); // LISTO
		usuario.setBackground(Color.BLACK);
		usuario.setForeground(Color.WHITE);
		agregarComponente(this.ventanaJuego, usuario);
		
		this.intentosRestantes = crearTextArea(false, "Intentos\r\nrestantes:\r\n"+capaLogica.getIntentosRestantes(), 516, 221, 108, 77); // LISTO
		intentosRestantes.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 16));
		intentosRestantes.setForeground(Color.RED);
		intentosRestantes.setBackground(Color.BLACK);
		agregarComponente(this.ventanaJuego, this.intentosRestantes);
		
		for(int i = 0, x = 157; i< capaLogica.getPalabraEnJuego().length(); i++) { // LISTO
			crearCuadrosDePal(cuadrosDePal, x);
			x += 35;
		}
		cargarLetraEnCuadro(capaLogica.getPalabraEnJuego());
		
		JTextArea letraIngresada = crearTextArea(true, "", 302, 117, 15, 22); // LISTO
		letraIngresada.setBackground(Color.LIGHT_GRAY);
		agregarComponente(this.ventanaJuego, letraIngresada);
		
		
		
		this.ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventanaJuego.setResizable(false);
		this.ventanaJuego.setVisible(true);
		
		if(capaLogica.getTipoJugador().equals("Persona")) {
			JTextArea ingreseUnaLetra = crearTextArea(false, "Escriba una letra", 157, 117, 142, 22); // LISTO
			ingreseUnaLetra.setFont(new Font("Monospaced", Font.PLAIN, 14));
			ingreseUnaLetra.setBackground(new Color(0,0,0,0));
			ingreseUnaLetra.setForeground(Color.WHITE);
			agregarComponente(this.ventanaJuego,ingreseUnaLetra);
			
			JTextArea arriesgarPalabra = crearTextArea(false, "Arriesgar palabra", 157, 192, 142, 22); // LISTO
			arriesgarPalabra.setFont(new Font("Monospaced", Font.PLAIN, 14));
			arriesgarPalabra.setBackground(new Color(0,0,0,0));
			arriesgarPalabra.setForeground(Color.WHITE);
			agregarComponente(this.ventanaJuego,arriesgarPalabra);
			
			JTextArea textPalabra = crearTextArea(true, "", 302, 193, 150, 22);
			agregarComponente(this.ventanaJuego,textPalabra);
			
			JButton checkPalButton = crearBoton("Check", "Comprobar", 332, 225, 100, 23);
			checkPalButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					capaLogica.arriesgarIntento(textPalabra.getText());
					if(capaLogica.juegoGanado()) {
						gano();
					}
					else {
						perdio();
					}
					
					
				}
			});
			agregarComponente(this.ventanaJuego,checkPalButton);
			
			JTextArea letraInvalida = crearTextArea(false, "Letra no valida, ingrese una letra valida", 157, 84, 333, 22);
			letraInvalida.setVisible(false);
			letraInvalida.setForeground(Color.RED);
			letraInvalida.setBackground(new Color(0,0,0,0));
			agregarComponente(this.ventanaJuego, letraInvalida);
			
			
			JButton comprobar = crearBoton("Check", "Check", 320, 116, 77, 23); // LISTO
			comprobar.setForeground(Color.WHITE);
			comprobar.setBackground(Color.BLACK);
			comprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(capaLogica.caracterValido(letraIngresada.getText())) {
						letraInvalida.setVisible(false);
						if(!capaLogica.arriesgarIntento(letraIngresada.getText().substring(0, 1))) {
							if(capaLogica.juegoGanado()) 
								gano();
							else if(capaLogica.juegoPerdido())
								perdio();
						}
						modificarIntento();
						cargarLetraEnCuadro(capaLogica.getPalabraEnJuego());
					}
					else {
						letraInvalida.setVisible(true);
					}
						
					
			}});
			agregarComponente(this.ventanaJuego, comprobar);
		}
		
		else {
			JTextArea ingreseUnaLetra = crearTextArea(false, "Letra elegida", 157, 117, 142, 22); 
			ingreseUnaLetra.setFont(new Font("Monospaced", Font.PLAIN, 14));
			ingreseUnaLetra.setBackground(Color.BLACK);
			ingreseUnaLetra.setForeground(Color.WHITE);
			agregarComponente(this.ventanaJuego,ingreseUnaLetra);
			
			letraIngresada.setEditable(false);
			
			
			JButton nextMovementNPCButton = crearBoton("Check", "Siguiente", 320, 116, 100, 23); 
			nextMovementNPCButton.setForeground(Color.WHITE);
			nextMovementNPCButton.setBackground(Color.BLACK);
			nextMovementNPCButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					capaLogica.arriesgarIntento("siguiente");
					if(capaLogica.juegoGanado()) 
						gano();
					else if(capaLogica.juegoPerdido())
						perdio();
					modificarIntento();
					cargarLetraEnCuadro(capaLogica.getPalabraEnJuego());
					letraIngresada.setText(capaLogica.getUltimoIntento());
				}
			});
			agregarComponente(this.ventanaJuego, nextMovementNPCButton);
			
			
		}
		
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		fondo.setIcon(new ImageIcon(Vista.class.getResource("/capaVisual/fondoJuego.jpg")));
		fondo.setBounds(0, 0, 624, 321);
		agregarComponente(this.ventanaJuego, fondo);
	}

	private void crearCuadrosDePal(ArrayList<JTextArea> cuadrosDePal, int x) {
		JTextArea cuadro = crearTextArea(false, "", x, 150, 20, 22);
		agregarComponente(this.ventanaJuego, cuadro);
		cuadrosDePal.add(cuadro);
	}

	public void cargarLetraEnCuadro(String pal) {
		for(int i = 0; i < pal.length(); i++) {
			char palChar = pal.charAt(i);
		    String charToString = String.valueOf(palChar);
		    if(!charToString.equals("") && !charToString.equals(this.cuadrosDePal.get(i).getText())) {
		    	this.cuadrosDePal.get(i).setEditable(true);
		    	this.cuadrosDePal.get(i).setText(charToString);
		    	this.cuadrosDePal.get(i).setEditable(false);
		    	this.ventanaJuego.getContentPane().add(this.cuadrosDePal.get(i));
		    }
		}
	}

	public void gano() {
		this.ventanaJuego.setVisible(false);
		
		JFrame gano = inicializarVentana();
		
		JTextArea ganoText = crearTextArea(false, "CONGRATS!"+capaLogica.getNombreJugador()+"\r\nADIVINO CORRECTAMENTE\r\nLA PALABRA:\r\n\r\n"
		+capaLogica.getPalabraEnJuego(), 212, 22, 233, 107);
		ganoText.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 14));
		ganoText.setForeground(Color.CYAN);
		ganoText.setBackground(Color.BLACK);
		agregarComponente(gano, ganoText);
		
		gano.setVisible(true);
		
		this.closeAppButton = crearBoton("closeAppButton", "Salir", 52, 167, 89, 23);
		closeAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		agregarComponente(gano, closeAppButton);
		
		this.playAgainButton = crearBoton("playAgainButton", "Volver a jugar", 323, 159, 122, 23);
		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gano.setVisible(false);
				//capaLogica.volverAJugar();
				pantallaInicial();
			}
		});
		agregarComponente(gano, playAgainButton);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Vista.class.getResource("/capaVisual/fondoInicial.jpg")));
		fondo.setBounds(0, 0, 464, 231);
		agregarComponente(gano, fondo);
		
	}

	public void perdio() {
		this.ventanaJuego.setVisible(false);
		
		JFrame perdio = inicializarVentana();
		
		JTextArea perdioText = crearTextArea(false, "QUE LASTIMA! "+capaLogica.getNombreJugador()+"\r\nLA PALABRA SECRETA ERA:\r\n\r\n"+
		capaLogica.getPalabraSecreta(), 212, 22, 233, 107);
		perdioText.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 14));
		perdioText.setForeground(Color.CYAN);
		perdioText.setBackground(Color.BLACK);
		
		agregarComponente(perdio, perdioText);
		
		perdio.setVisible(true);	
		
		this.closeAppButton = crearBoton("closeAppButton", "Salir", 212, 159, 89, 23);
		closeAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		agregarComponente(perdio, closeAppButton);
		
		this.playAgainButton = crearBoton("playAgainButton", "Volver a jugar", 323, 159, 122, 23);
		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perdio.setVisible(false);
				//capaLogica.volverAJugar();
				pantallaInicial();
			}
		});
		agregarComponente(perdio, playAgainButton);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Vista.class.getResource("/capaVisual/fondoInicial.jpg")));
		fondo.setBounds(0, 0, 464, 231);
		agregarComponente(perdio, fondo);
	}

	public void modificarIntento() {
		this.intentosRestantes.setText("Intentos\r\nrestantes:\r\n"+capaLogica.getIntentosRestantes());
		agregarComponente(this.ventanaJuego,this.intentosRestantes);	
	}

}