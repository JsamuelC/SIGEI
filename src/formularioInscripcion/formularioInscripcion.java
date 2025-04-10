package formularioInscripcion;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conect.Conection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class formularioInscripcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textMatricula;
	private JTextField textCorreo;
	private JTextField textTelefono;
	private JTextField textCarrera;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formularioInscripcion frame = new formularioInscripcion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formularioInscripcion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 588);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Formulario de  Inscripción");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(89, 21, 241, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(174, 62, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(147, 87, 96, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatricula.setBounds(174, 118, 106, 14);
		contentPane.add(lblMatricula);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(147, 143, 96, 20);
		contentPane.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGenero.setBounds(174, 186, 62, 14);
		contentPane.add(lblGenero);
		
	    rdbtnMasculino = new JRadioButton("Masculino");
	    rdbtnMasculino.setBackground(new Color(135, 206, 235));
		rdbtnMasculino.setBounds(106, 218, 111, 23);
		contentPane.add(rdbtnMasculino);
		
		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBackground(new Color(135, 206, 235));
		rdbtnFemenino.setBounds(219, 218, 111, 23);
		contentPane.add(rdbtnFemenino);
		
		ButtonGroup grupoGenero = new ButtonGroup();
		grupoGenero.add(rdbtnMasculino);
		grupoGenero.add(rdbtnFemenino);
		
		
		JLabel lblCorreo = new JLabel("Correo Electronico");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCorreo.setBounds(144, 264, 123, 14);
		contentPane.add(lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(147, 302, 96, 20);
		contentPane.add(textCorreo);
		textCorreo.setColumns(10);
		
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(157, 333, 93, 14);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(147, 358, 96, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		JLabel lblCarrera = new JLabel("Materia\r\n");
		lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCarrera.setBounds(161, 401, 82, 14);
		contentPane.add(lblCarrera);
		
		textCarrera = new JTextField();
		textCarrera.setBounds(147, 426, 96, 20);
		contentPane.add(textCarrera);
		textCarrera.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEnviar.setBackground(new Color(30, 144, 255));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnEnviar();
				
			}
		});
		btnEnviar.setBounds(147, 467, 89, 33);
		contentPane.add(btnEnviar);
	}
		
				private void btnEnviar() {
					String nombre= textNombre.getText();
					String Matricula=textMatricula.getText();
					String Correo= textCorreo.getText();
					String Telefono= textTelefono.getText();
					String Materias= textCarrera.getText();
					String genero = rdbtnMasculino.isSelected() ? "Masculino" : "Femenino";
					
					try (Connection conn = Conection.getConnection()){
						String query = "INSERT INTO Estudiantes (nombre, sexo, matricula, correo, Materias, telefono)  VALUES (?, ?, ?, ?, ?, ?)";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, nombre);
						stmt.setString(2, genero);
						stmt.setString(3, Matricula);
						stmt.setString(4, Correo);
						stmt.setString(5, Materias);
						stmt.setString(6, Telefono);
						
						int rows = stmt.executeUpdate();
						if (rows > 0) {
							mostrarMensaje("Registro exitoso.");
						} else {
							mostrarMensaje("No se pudo registrar.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						mostrarMensaje("Error de Conexión: " + ex.getMessage());
					}
				}
				
					
					private void mostrarMensaje(String mensaje) {
						JOptionPane.showMessageDialog(this, mensaje);
					}
				}
