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
		panel.setBounds(10, 11, 542, 257);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Estudiantesbtn = new JButton("Estudiantes");
		Estudiantesbtn.setBounds(122, 81, 142, 47);
		panel.add(Estudiantesbtn);
		
		JButton Calificacionbtn = new JButton("Calificaciones");
		Calificacionbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Calificacionbtn.setBounds(318, 81, 140, 47);
		panel.add(Calificacionbtn);
		
		
		JButton Inscripcionbtn = new JButton("Inscripcion");
		Inscripcionbtn.setBounds(122, 151, 142, 47);
		panel.add(Inscripcionbtn);
		Inscripcionbtn.addActionListener(new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
			inscripcion();
			
		}});
		
		JButton registroUsuariobtn = new JButton("Registrar Usuario");
		registroUsuariobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroUsuario registroUsuario = new registroUsuario();
				registroUsuario.setVisible(true);
			}
		});
		registroUsuariobtn.setBounds(316, 151, 142, 47);
		panel.add(registroUsuariobtn);

		
		
	}
	
	private void inscripcion() {
		formularioInscripcion formularioInscripcion = new formularioInscripcion();
		formularioInscripcion.setVisible(true);
		
		
	}
}
