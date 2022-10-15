package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Controlador {

	protected static final Component JFrame = null;
	private Vista vista;
	private Model model;
	//Variable per a guardar la ruta que triem amb el file chooser
	private String ruta;

	public Controlador(Vista vista, Model mode) {
		this.vista = vista;
		this.model = mode;
		initEventHandler();
	}

	String rutaFicheroString = "";

	private void initEventHandler() {

		// BOTON PER A TRIAR UN FITXER
		ActionListener elegirFichero = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					// Finestra per a triar el fitxer
					JFileChooser j = new JFileChooser();
					j.showOpenDialog(j);

					String nombre = j.getSelectedFile().getName();
					rutaFicheroString = nombre;
					FileReader fReader = new FileReader(nombre);
					File fichero = new File(nombre);
					ruta = fichero.getPath();
					// Mostrem les dades del fitxer
					vista.getDatos().append(Model.mostrarDatos(nombre));
					// Mostrem el contingut del fitxer
					vista.getContenidoFichero().setText(Model.mostrarContenidoPrincipal(ruta));

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		vista.getElegirFichero().addActionListener(elegirFichero);

		// Aquest ActionListener ens permetrà canviar-li el nom a un fitxer
		// Hem d'usar els dos JTEXTFIELD per al nom antic i el nom nou
		ActionListener botonReenombrar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nomActualString = vista.getNombreActual().getText();

				String nomReemplazarString = vista.getNombreReenombrar().getText();

				int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Seguro que deseas renombrar?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					model.cambiarNombre(nomActualString, nomReemplazarString);
					JOptionPane.showMessageDialog(JFrame, "FICHERO RENOMBRADO");

				}
				vista.getNombreActual().setText("");
				vista.getNombreReenombrar().setText("");

			}
		};

		vista.getBtnReenombrar().addActionListener(botonReenombrar);
		// ACTION LISTENER DEL BOTÓ PER A CREAR UN FITXER
		// Aquest botó ens permet crear un fitxer passant-li la ruta per paràmetre
		ActionListener botonCrearFicheroActionListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String ruta = vista.getRutaFichero().getText();

				model.crearFichero(ruta);
				JOptionPane.showMessageDialog(JFrame, "FICHERO CREADO");
				vista.getRutaFichero().setText("");

			}
		};

		vista.getBtnCrearFichero().addActionListener(botonCrearFicheroActionListener);

		// ACTION LISTENER DEL BOTÓ PER A ELIMINAR UN FITXER
		// Aquest botó ens permet eliminar un fitxer passant-li un nom o ruta per
		// paràmetre
		ActionListener botonEliminarFichero = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String ruta = vista.getRutaFichero().getText();
				int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {

					model.eliminarFichero(ruta);
				}
				JOptionPane.showMessageDialog(JFrame, "FICHERO ELIMINADO");
				vista.getRutaFichero().setText("");

			}
		};
		vista.getBtnBorrarFichero().addActionListener(botonEliminarFichero);

		// ACTION LISTENER DEL BOTÓ PER A COPIAR UN FITXER
		// Aquest botó ens permet copiar un fitxer passant-li un nom o ruta per
		// paràmetre
		ActionListener botonCopia = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String rutaOriginalString = vista.getRutaCopiaOriginal().getText();

				model.copiarArchivo(rutaOriginalString);

				JOptionPane.showMessageDialog(JFrame, "FICHERO COPIADO");
				vista.getRutaCopiaOriginal().setText("");

			}
		};
		vista.getBtnCopiarFichero().addActionListener(botonCopia);

		// BOTON PER A PODER REEMPLAÇAR UN FITXER
		ActionListener botonReemplazar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String palabraParaBuscar = vista.getPalabraParaReemplazar().getText();
				String palabraParaReemplazar = vista.getPalabraReemplazada().getText();
				String nombre = rutaFicheroString;

				model.reemplazar(palabraParaBuscar, palabraParaReemplazar, nombre);
				// vista.getContenidoFichero().append(Model.mostrarContenidoPrincipal(ruta));
				JOptionPane.showMessageDialog(JFrame, "REEMPLAZADO");
				vista.getContenidoFichero().setText(Model.mostrarContenidoPrincipal(ruta));
				vista.getPalabraParaReemplazar().setText("");
				vista.getPalabraReemplazada().setText("");
			}
		};
		vista.getBtnReemplazar().addActionListener(botonReemplazar);

		// BOTO PER A GUARDAR UN JTEXTAREA EN UN FITXER
		ActionListener botonGuardar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String cont = vista.getContenidoFichero().getText();

				model.guardarContenido(cont, ruta);
				JOptionPane.showMessageDialog(JFrame, "GUARDADO");
				// vista.getContenidoFichero().append(Model.mostrarContenidoPrincipal(ruta));
			}
		};
		vista.getBtnGuardar().addActionListener(botonGuardar);

		
		//BOTO PER A BUSCAR PARAULES AL CONTINGUT DEL FITXER QUE ESTA EN EL JTEXTAREA
		ActionListener botonBuscar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String palabraString = vista.getPalabraBuscar().getText();

				String cont = model.mostrarContenidoPrincipal(ruta);

				int num = model.buscarTexto(palabraString, ruta);
				JOptionPane.showMessageDialog(JFrame, num);

				//PUNT PER A REMARCAR LES PARAULES
				if (palabraString.length() >= 1) {
					DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(
							Color.GREEN);
					Highlighter h = vista.getContenidoFichero().getHighlighter();
					h.removeAllHighlights();
					String text = vista.getContenidoFichero().getText();
					String caracteres = palabraString;
					Pattern p = Pattern.compile("(?i)" + caracteres);
					Matcher m = p.matcher(text);
					while (m.find()) {
						try {
							h.addHighlight(m.start(), m.end(), highlightPainter);
						} catch (BadLocationException ex) {
							ex.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(vista.getContenidoFichero(),
							"la palabra a buscar no puede ser vacia");
				}
				//

				vista.getPalabraBuscar().setText("");

			}
		};

		vista.getBtnBuscarPalabra().addActionListener(botonBuscar);

		// BOTO PER A MOSTRAR EL CONTINGUT D`UN DIRECTORI
		ActionListener botonDirectorio = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String nombre = vista.getTxtNombreDirectorio().getText();
				System.out.println(nombre);
				File f1 = new File(nombre);
				// ARRAY AMB EL CONTINGUT
				String[] filelist = f1.list();
				String cont = "";

				for (int i = 0; i < filelist.length; i++) {

					cont += filelist[i] + "\n";
				}
				System.out.println(cont);
				vista.getTxtContenidoDirectorio().setText(cont);

			}
		};

		vista.getBtnBuscarDirectorio().addActionListener(botonDirectorio);

	}

}
