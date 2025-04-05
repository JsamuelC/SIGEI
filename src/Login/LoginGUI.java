package Login;


import Conect.Conection;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField LogUser;
	private JPasswordField LogPsw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(10, 11, 349, 412);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\josue\\OneDrive\\Imágenes\\Capturas de pantalla\\icon login.png"));
		lblNewLabel.setBounds(97, 26, 149, 151);
		panel.add(lblNewLabel);
		
		LogUser = new JTextField();
		LogUser.setBounds(89, 221, 157, 30);
		panel.add(LogUser);
		LogUser.setColumns(10);
		
		LogPsw = (JPasswordField) new JPasswordField();
		LogPsw.setColumns(10);
		LogPsw.setBounds(89, 262, 157, 30);
		panel.add(LogPsw);
		
		JButton Logbtn = new JButton("Login\r\n");
		Logbtn.setBounds(100, 331, 138, 38);
		panel.add(Logbtn);
		Logbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e ) {
			btnLogin();
		}
		
	});}
	
	private void mostrarMensaje(String  Mensaje) {
		JOptionPane.showMessageDialog(this,Mensaje);
	}
	
	private void btnLogin() {
	 String usuario = this.LogUser.getText();
	 String contra = new String(this.LogPsw.getPassword());
	 
	 try (Connection conn = Conection.getConnection()){
		String query = "SELECT * FROM  Usuarios WHERE nombre = ? AND contraseña = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, usuario);
		stmt.setString(2, contra);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			mostrarMensaje("Welcome " + usuario);
			
			
			
		} else {
			mostrarMensaje("Usuario o Contraseña Incorrectos");
			
		}
	 } catch( SQLException ex) {
		 ex.printStackTrace();
		 mostrarMensaje(" Error de Conexión: " + ex.getMessage());
	 }
		
		
	}
		
	}

	

