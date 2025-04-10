package Login;


import Conect.Conection;
import MenuPrincipalGUI.Menu;
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

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField LogUser;
	private JPasswordField LogPsw;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
		setBounds(100, 100, 375, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 349, 486);
		panel.setBackground(new Color(135, 206, 235));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\redimensionada foto login(2) (1).png"));
		lblNewLabel.setBounds(113, 43, 104, 135);
		panel.add(lblNewLabel);
		
		LogUser = new JTextField();
		LogUser.setFont(new Font("Arial", Font.PLAIN, 11));
		LogUser.setForeground(Color.BLACK);
		LogUser.setToolTipText("");
		LogUser.setBounds(89, 226, 157, 30);
		panel.add(LogUser);
		LogUser.setColumns(10);
		
		LogPsw = (JPasswordField) new JPasswordField();
		LogPsw.setFont(new Font("Arial", Font.PLAIN, 11));
		LogPsw.setColumns(10);
		LogPsw.setBounds(89, 304, 157, 30);
		panel.add(LogPsw);
		
		JButton Logbtn = new JButton("Login\r\n");
		Logbtn.setBackground(new Color(30, 144, 255));
		Logbtn.setFont(new Font("Arial", Font.BOLD, 13));
		Logbtn.setBounds(89, 374, 138, 38);
		panel.add(Logbtn);
		
		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(89, 200, 104, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(89, 279, 104, 14);
		panel.add(lblNewLabel_2);
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
			this.dispose();
			Menu Menu = new Menu();
			Menu.setVisible(true);
			
			
			
		} else {
			mostrarMensaje("Usuario o Contraseña Incorrectos");
			
		}
	 } catch( SQLException ex) {
		 ex.printStackTrace();
		 mostrarMensaje(" Error de Conexión: " + ex.getMessage());
	 }
		
		
	}
		
	}

	

