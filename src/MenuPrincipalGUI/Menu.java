package MenuPrincipalGUI;

import java.awt.EventQueue;
import formularioInscripcion.formularioInscripcion;
import registroUsuario.registroUsuario;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(10, 11, 542, 257);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Estudiantesbtn = new JButton("Estudiantes");
		Estudiantesbtn.setBackground(new Color(30, 144, 255));
		Estudiantesbtn.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\Estudiantes red(1) (1).png"));
		Estudiantesbtn.setFont(new Font("Arial", Font.PLAIN, 11));
		Estudiantesbtn.setBounds(122, 81, 142, 47);
		panel.add(Estudiantesbtn);
		
		JButton Calificacionbtn = new JButton("Calificaciones");
		Calificacionbtn.setBackground(new Color(30, 144, 255));
		Calificacionbtn.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\Calificionesred (1) (1).png"));
		Calificacionbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Calificacionbtn.setBounds(318, 81, 140, 47);
		panel.add(Calificacionbtn);
		
		
		JButton Inscripcionbtn = new JButton("Inscripcion");
		Inscripcionbtn.setBackground(new Color(30, 144, 255));
		Inscripcionbtn.setFont(new Font("Arial", Font.PLAIN, 11));
		Inscripcionbtn.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\inscripcionred(2) (1).png"));
		Inscripcionbtn.setBounds(122, 151, 142, 47);
		panel.add(Inscripcionbtn);
		Inscripcionbtn.addActionListener(new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
			inscripcion();
			
		}});
		
		JButton registroUsuariobtn = new JButton("Registrar Usuario");
		registroUsuariobtn.setBackground(new Color(30, 144, 255));
		registroUsuariobtn.setIcon(new ImageIcon("C:\\Users\\josue\\Desktop\\Proyecto final PROG\\imagen\\registrored (1).png"));
		registroUsuariobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroUsuario registroUsuario = new registroUsuario();
				registroUsuario.setVisible(true);
			}
		});
		registroUsuariobtn.setBounds(316, 151, 142, 47);
		panel.add(registroUsuariobtn);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gestion Estudiantil");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(139, 11, 285, 29);
		panel.add(lblNewLabel);

		
		
	}
	
	private void inscripcion() {
		formularioInscripcion formularioInscripcion = new formularioInscripcion();
		formularioInscripcion.setVisible(true);
		
		
	}
}
