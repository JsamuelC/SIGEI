package Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
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
	private JTextField LogPsw;

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
		
		LogPsw = new JTextField();
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
	
	private void btnLogin() {
		String userLogin = LogUser.getText();
		String PswLogin = LogPsw.getText();
		if (userLogin.isEmpty() || PswLogin.isEmpty() ) {
			JOptionPane.showMessageDialog(this, "Porfavor Llenar Todos los Campos");
		}
		
	}
}
