package clases;

import java.awt.Color;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.EventQueue;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JButton btnAbrirFicheros;
	private JTextArea txtDatos;
	private JTextArea txtContenidoFichero;
	private JScrollPane scrollPane;
	private JTextField nombreReemplazar;
	private JTextField txtReenombrado;
	private JButton btnRenombrar;
	private JTextField txtRutaFichero;
	private JButton btnCrearFichero;
	private JButton btnBorrarFichero;
	private JButton btnCopiarFichero;
	private JTextField txtRutaFicheroOriginal;
	private JTextField txtPalabraReemplazar;
	private JTextField txtPalabraReemplazada;
	private JButton btnReemplazar;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscarPalabra;
	private JButton btnBuscarPalabra;
	private JButton btnGuardar;
	private JLabel LABELRUTAFICHERO;
	private JTextField txtNombreDirectorio;
	private JButton btnBuscarDirectorio;
	private JTextArea txtContenidoDirectorio;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_1;

	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 662);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAbrirFicheros = new JButton("BUSCADOR DE FICHEROS");
		btnAbrirFicheros.setForeground(new Color(255, 0, 0));
		btnAbrirFicheros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAbrirFicheros.setBackground(new Color(0, 128, 192));
		btnAbrirFicheros.setBounds(24, 38, 199, 39);
		contentPane.add(btnAbrirFicheros);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(438, 284, 528, 132);
		contentPane.add(scrollPane);
		
				txtDatos = new JTextArea();
				scrollPane.setViewportView(txtDatos);

		nombreReemplazar = new JTextField();
		nombreReemplazar.setBounds(41, 536, 175, 20);
		contentPane.add(nombreReemplazar);
		nombreReemplazar.setColumns(10);

		txtReenombrado = new JTextField();
		txtReenombrado.setColumns(10);
		txtReenombrado.setBounds(41, 580, 175, 20);
		contentPane.add(txtReenombrado);

		JLabel lblNewLabel = new JLabel("NOMBRE DEL FICHERO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(41, 522, 175, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombreParaReemplazar = new JLabel("NOMBRE PARA REEMPLAZAR");
		lblNombreParaReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreParaReemplazar.setBounds(41, 563, 182, 14);
		contentPane.add(lblNombreParaReemplazar);

		btnRenombrar = new JButton("RENOMBRAR");
		btnRenombrar.setBounds(226, 546, 147, 39);
		contentPane.add(btnRenombrar);

		txtRutaFichero = new JTextField();
		txtRutaFichero.setBounds(41, 439, 178, 20);
		contentPane.add(txtRutaFichero);
		txtRutaFichero.setColumns(10);

		btnCrearFichero = new JButton("CREAR FICHERO");
		btnCrearFichero.setBounds(233, 414, 140, 20);
		contentPane.add(btnCrearFichero);

		JLabel lblNombreNuevoFichero = new JLabel("Nombre del fichero");
		lblNombreNuevoFichero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreNuevoFichero.setBounds(44, 417, 150, 14);
		contentPane.add(lblNombreNuevoFichero);

		btnBorrarFichero = new JButton("BORRAR FICHERO");
		btnBorrarFichero.setBounds(233, 457, 140, 20);
		contentPane.add(btnBorrarFichero);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 392, 373, 106);
		contentPane.add(panel_1);

		btnCopiarFichero = new JButton("COPIAR FICHERO");
		btnCopiarFichero.setBounds(226, 318, 147, 32);
		contentPane.add(btnCopiarFichero);

		JPanel panel = new JPanel();
		panel.setBounds(24, 509, 373, 106);
		contentPane.add(panel);

		txtRutaFicheroOriginal = new JTextField();
		txtRutaFicheroOriginal.setBounds(41, 324, 168, 20);
		contentPane.add(txtRutaFicheroOriginal);
		txtRutaFicheroOriginal.setColumns(10);

		txtPalabraReemplazar = new JTextField();
		txtPalabraReemplazar.setBounds(448, 451, 147, 20);
		contentPane.add(txtPalabraReemplazar);
		txtPalabraReemplazar.setColumns(10);

		txtPalabraReemplazada = new JTextField();
		txtPalabraReemplazada.setBounds(448, 491, 147, 20);
		contentPane.add(txtPalabraReemplazada);
		txtPalabraReemplazada.setColumns(10);

		btnReemplazar = new JButton("REEMPLAZAR");
		btnReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReemplazar.setBounds(605, 457, 97, 41);
		contentPane.add(btnReemplazar);

		JLabel lblNewLabel_1 = new JLabel("PALABRA PARA REEMPLAZAR");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(448, 427, 168, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("PALABRA REEMPLAZADA");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setBounds(448, 474, 147, 14);
		contentPane.add(lblNewLabel_2);

		txtBuscarPalabra = new JTextField();
		txtBuscarPalabra.setBounds(712, 457, 147, 20);
		contentPane.add(txtBuscarPalabra);
		txtBuscarPalabra.setColumns(10);

		btnBuscarPalabra = new JButton("BUSCAR");
		btnBuscarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarPalabra.setBounds(869, 457, 97, 41);
		contentPane.add(btnBuscarPalabra);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBackground(new Color(0, 128, 192));
		btnGuardar.setForeground(new Color(255, 0, 0));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnGuardar.setBounds(767, 571, 199, 39);
		contentPane.add(btnGuardar);

		LABELRUTAFICHERO = new JLabel("");
		LABELRUTAFICHERO.setFont(LABELRUTAFICHERO.getFont().deriveFont(0f));
		LABELRUTAFICHERO.setEnabled(false);
		LABELRUTAFICHERO.setBounds(153, 398, 46, 14);
		contentPane.add(LABELRUTAFICHERO);

		JLabel lblBuscarPalabra = new JLabel("PALABRA PARA BUSCAR");
		lblBuscarPalabra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblBuscarPalabra.setBounds(712, 442, 147, 14);
		contentPane.add(lblBuscarPalabra);

		JLabel lblNombreNuevoFichero_1 = new JLabel("Nombre del fichero");
		lblNombreNuevoFichero_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreNuevoFichero_1.setBounds(41, 300, 150, 14);
		contentPane.add(lblNombreNuevoFichero_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(24, 275, 373, 106);
		contentPane.add(panel_1_1);

		txtNombreDirectorio = new JTextField();
		txtNombreDirectorio.setBounds(24, 115, 121, 20);
		contentPane.add(txtNombreDirectorio);
		txtNombreDirectorio.setColumns(10);

		btnBuscarDirectorio = new JButton("Buscar Directorio");
		btnBuscarDirectorio.setBounds(150, 114, 133, 23);
		contentPane.add(btnBuscarDirectorio);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 148, 240, 109);
		contentPane.add(scrollPane_2);

		txtContenidoDirectorio = new JTextArea();
		scrollPane_2.setViewportView(txtContenidoDirectorio);
		
		JLabel lblnomDirectorio = new JLabel("Ruta Directorio");
		lblnomDirectorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnomDirectorio.setBounds(24, 99, 121, 14);
		contentPane.add(lblnomDirectorio);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(438, 38, 528, 235);
		contentPane.add(scrollPane_1);
		
		 txtContenidoFichero = new JTextArea();
		scrollPane_1.setViewportView(txtContenidoFichero);
		setVisible(true);
	}

	public JButton getElegirFichero() {
		return btnAbrirFicheros;
	}

	public JTextArea getDatos() {
		return txtDatos;
	}

	public JTextArea getContenidoFichero() {
		return txtContenidoFichero;
	}

	public JTextField getNombreActual() {
		return nombreReemplazar;
	}

	public JTextField getNombreReenombrar() {
		return txtReenombrado;

	}

	public JButton getBtnReenombrar() {
		return btnRenombrar;
	}

	public JButton getBtnCrearFichero() {
		return btnCrearFichero;
	}

	public JTextField getRutaFichero() {
		return txtRutaFichero;
	}

	public JButton getBtnBorrarFichero() {
		return btnBorrarFichero;
	}

	public JButton getBtnCopiarFichero() {
		return btnCopiarFichero;
	}

	public JTextField getRutaCopiaOriginal() {
		return txtRutaFicheroOriginal;

	}

	public JButton getBtnReemplazar() {
		return btnReemplazar;
	}

	public JTextField getPalabraParaReemplazar() {
		return txtPalabraReemplazar;
	}

	public JTextField getPalabraReemplazada() {
		return txtPalabraReemplazada;
	}

	public JButton getBtnBuscarPalabra() {
		return btnBuscarPalabra;
	}

	public JTextField getPalabraBuscar() {
		return txtBuscarPalabra;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JTextField getTxtNombreDirectorio() {

		return txtNombreDirectorio;
	}

	public JButton getBtnBuscarDirectorio() {
		return btnBuscarDirectorio;
	}

	public JTextArea getTxtContenidoDirectorio() {
		return txtContenidoDirectorio;
	}
	

	
}

