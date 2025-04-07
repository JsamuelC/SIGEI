package registroUsuario;

import java.awt.EventQueue;
import Conect.Conection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;  
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;

public class registroUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombretxt;
	private JTextField correotxt;
	private JTextField contratxt;
	private JRadioButton adminrdbtn;
	private JRadioButton normalrdbt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				registroUsuario frame = new registroUsuario();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registroUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(10, 11, 316, 496);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel registrolbl = new JLabel("Registro Usuario");
		registrolbl.setFont(new Font("Arial Black", Font.PLAIN, 15));
		registrolbl.setBounds(78, 11, 146, 14);
		panel.add(registrolbl);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombre.setBounds(101, 169, 82, 14);
		panel.add(lblNombre);

		nombretxt = new JTextField();
		nombretxt.setBounds(92, 194, 122, 20);
		panel.add(nombretxt);
		nombretxt.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCorreo.setBounds(101, 236, 82, 14);
		panel.add(lblCorreo);

		correotxt = new JTextField();
		correotxt.setColumns(10);
		correotxt.setBounds(92, 261, 122, 20);
		panel.add(correotxt);

		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setFont(new Font("Arial", Font.PLAIN, 15));
		lblContra.setBounds(101, 307, 82, 14);
		panel.add(lblContra);

		contratxt = new JTextField();
		contratxt.setColumns(10);
		contratxt.setBounds(92, 332, 122, 20);
		panel.add(contratxt);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\redimensionada foto login(2) (1).png"));
		lblNewLabel.setBounds(95, 35, 108, 109);
		panel.add(lblNewLabel);

		JButton btnRegistar = new JButton("Registrar");
		btnRegistar.setBackground(new Color(30, 144, 255));
		btnRegistar.setFont(new Font("Arial", Font.BOLD, 12));
		btnRegistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegistrarse();
			}
		});
		btnRegistar.setBounds(114, 462, 89, 23);
		panel.add(btnRegistar);

		JLabel rolrdbt = new JLabel("Rol");
		rolrdbt.setFont(new Font("Arial", Font.PLAIN, 13));
		rolrdbt.setBounds(137, 377, 46, 14);
		panel.add(rolrdbt);


		adminrdbtn = new JRadioButton("Administrador");
		adminrdbtn.setBackground(new Color(135, 206, 235));
		adminrdbtn.setFont(new Font("Arial", Font.BOLD, 12));
		adminrdbtn.setBounds(46, 410, 109, 23);
		panel.add(adminrdbtn);

		normalrdbt = new JRadioButton("Normal");
		normalrdbt.setBackground(new Color(135, 206, 235));
		normalrdbt.setFont(new Font("Arial", Font.BOLD, 12));
		normalrdbt.setBounds(182, 410, 109, 23);
		panel.add(normalrdbt);

		ButtonGroup grupoUsuario = new ButtonGroup();
		grupoUsuario.add(adminrdbtn);
		grupoUsuario.add(normalrdbt);
	}

	private void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void btnRegistrarse() {
		String nombre = nombretxt.getText();
		String correo = correotxt.getText();
		String contra = contratxt.getText();
		String rol = adminrdbtn.isSelected() ? "Administrador" : "Normal";

		try (Connection conn = Conection.getConnection()) {
			String query = "INSERT INTO Usuarios(nombre, correo, contraseña, rol) VALUES(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, nombre);
			stmt.setString(2, correo);
			stmt.setString(3, contra);
			stmt.setString(4, rol);

			int rows = stmt.executeUpdate();
			if (rows > 0) {
				mostrarMensaje("Cuenta creada exitosamente");
			} else {
				mostrarMensaje("No se pudo registrar");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			mostrarMensaje("Error de conexión: " + ex.getMessage());
		}
	}
}
