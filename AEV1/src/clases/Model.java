package clases;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter.HighlightPainter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Model {

	private static String lecturaString = "ficherin";
	private static String EscrituraString = "prueba.txt";

	/***
	 * Aquest metode ens monstra les dades d`un fitxer com el tamany, dades, etc...
	 * 
	 * @param es el nom o ruta del fitxer que anem a visualitzar
	 * @return retorna el contigut d`una variable amb les dades del fitxer
	 */
	public static String mostrarDatos(String nombre) {

		File fichero = new File(nombre);

		String contenido = "";

		BasicFileAttributes attrs;
		try {
			attrs = Files.readAttributes(fichero.toPath(), BasicFileAttributes.class);
			FileTime time = attrs.creationTime();
			// Creem el formato de fecha i hora
			String pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			String formatted = simpleDateFormat.format(new Date(time.toMillis()));

			contenido += "\n" + "Nombre del fichero: " + fichero.getName();
			contenido += "\n" + "Ruta absoluta del fichero: " + fichero.getAbsolutePath();
			contenido += "\n" + "Fecha de creacion: " + formatted;
			contenido += "\n" + "Tamaño en bits: " + fichero.length();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contenido;

	}

	/**
	 * Aquest metode ens serveix per a poder llegir el contengunt d'un fitxer que
	 * pasem per parametre
	 * 
	 * @param és la ruta que li passem per a poder accedir al fitxer
	 * @return retorna el contingut sencer del fitxer
	 */
	public static String mostrarContenidoPrincipal(String ruta) {
		String texto = "";
		try (FileReader fileReader = new FileReader(ruta)) {
			int caracterLeido = fileReader.read();
			while (caracterLeido != -1) {
				char caracter = (char) caracterLeido;
				caracterLeido = fileReader.read();
				texto += caracter;
			}
		} catch (IOException ex) {
			System.err.println("Error al leer el archivo");
			ex.printStackTrace();
		}
		return texto;
	}

	/**
	 * 
	 * Aquest mètode ens permet accedir a un fitxer del nostre pc mitjançant una
	 * ruta i canviar-li el nom
	 * 
	 * @param És el nom del fitxer que estem buscant per a reemplaçar
	 * @param És el nom que passarem per a reemplaçar al nom que ja estava
	 * @return Retorna true si s'ha pogut canviar de nom el fitxer i false si no
	 *         s'ha pogut
	 */
	public static boolean cambiarNombre(String nombreActual, String nombreReemplazar) {
		File f1 = new File(nombreActual);
		File f2 = new File(nombreReemplazar);

		if (f1.renameTo(f2)) {
			System.out.println("okay");
			return true;

		} else {
			System.out.println("nop");
			return false;

		}
	}

	/**
	 * 
	 * Ací podem crear un fitxer nou en la ruta que vulguem
	 * 
	 * @param És la ruta on voldrem crear el fitxer
	 */
	public static void crearFichero(String ruta) {
		File fichero = new File(ruta);

		try {
			if (!fichero.createNewFile()) {
				System.out.println("No se ha podido crear");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Ací podem eliminar un fitxer nou en la ruta que vulguem
	 * 
	 * @param És la ruta on voldrem eliminar el fitxer
	 */
	public static void eliminarFichero(String ruta) {
		File fichero = new File(ruta);

		if (!fichero.delete()) {
			System.out.println("No se ha podido crear");
		}

	}

	/**
	 * Aquest mètode ens copiarà un arxiu en la mateixa ruta afegint-li un _Còpia al
	 * final
	 * 
	 * @param Es la ruta o el nom del ficher original que anem a copiar
	 */
	public static void copiarArchivo(String archivoOriginal) {
		File originalFile = new File(archivoOriginal);
		String nomCopia = originalFile.getName() + "_Copia.txt";
		File copiaFile = new File(nomCopia);

		try {
			FileReader fr = new FileReader(originalFile);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(copiaFile);
			BufferedWriter bw = new BufferedWriter(fw);

			String linea = br.readLine();

			while (linea != null) {
				bw.write(linea);
				System.out.println(linea);
				bw.newLine();
				linea = br.readLine();
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Açi podem trovar una paraula en el fitxer, fent un contador i si el trova
	 * aumentarlo
	 * 
	 * @param la   paraula que volem buscar
	 * @param ruta del fitxer
	 * @return tornem el contador de les vegades que ha aparegut la paraula
	 */
	public static int buscarTexto(String palabra, String ruta) {
		String pal = palabra;
		int contadorPalabras = 0;
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();

			String[] lineas;
			do {
				lineas = linea.split(" ");
				for (int i = 0; i < lineas.length; i++) {
					if (lineas[i].indexOf(palabra) != -1) {
						contadorPalabras++;
					}
				}
				linea = br.readLine();
			} while (linea != null);

			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contadorPalabras;

	}

	/**
	 * Metode per a poder sustituir una paraula per un altra en el fitxer de text
	 * 
	 * @param paraula que volem sutituir
	 * @param paraula que va a sustituir a la anterior
	 * @param nom o ruta del fitxer que anem a utilitzar
	 */
	public static void reemplazar(String palabraReemplazar, String Reemplazada, String nomFichero) {
		String palabraParaCambiar = palabraReemplazar;
		String palabraReemplazada = Reemplazada;
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(nomFichero));
			// Creem cadenes buides de contingut i línia
			String linea = "", content = "";
			// Separa tot amb espais i per línies
			while ((linea = reader.readLine()) != null) {
				content += linea + "\r\n";
				System.out.println(content);
			}

			reader.close();
			// Li assignem les paraules
			String replacement = palabraReemplazada;
			String needle = palabraParaCambiar;
			// Li diem a això les paraules que reemplaçarem
			String newContent = content.replaceAll(needle, replacement);
			// Fem un filewriter per a escriure i tal
			FileWriter writer = new FileWriter(nomFichero);
			// Escrivim en el fitxer d'escriptura amb les paraules reemplaçades
			writer.write(newContent);

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Aquest metode guarda el contigut d`un jtextarea en un fitxer de text
	 * 
	 * @param es el contingut del jtextarea que es pot modificar
	 * @param la ruta on es va a guardar el contingut en un txt
	 */
	public void guardarContenido(String contenido, String ruta) {
		File f1 = new File(ruta);
		try {
			FileWriter fw = new FileWriter(f1);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = contenido;

			bw.write(linea);

			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
