
package EstudianteCrud;

import java.awt.EventQueue;
import Conect.Conection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EstudianteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				EstudianteGUI frame = new EstudianteGUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public EstudianteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 677, 399);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Estudiantes");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(232, 11, 142, 32);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 657, 206);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"id_estudiante", "Nombre", "Sexo", "Matricula", "Correo", "Materias", "Telefono"
			}
		) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});

		JButton actualizarbtn = new JButton("Actualizar");
		actualizarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		actualizarbtn.setBounds(40, 309, 156, 43);
		panel.add(actualizarbtn);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarEstudiante();
			}
		});
		btnEliminar.setBounds(248, 309, 156, 43);
		panel.add(btnEliminar);

		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCambios();
			}
		});
		btnGuardarCambios.setBounds(456, 309, 156, 43);
		panel.add(btnGuardarCambios);

		
		actualizarTabla();
	}

	public void actualizarTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); 

		String query = "SELECT * FROM Estudiantes";

		try (Connection conn = Conection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id_estudiante");
				String nombre = rs.getString("Nombre");
				String sexo = rs.getString("Sexo");
				String matricula = rs.getString("Matricula");
				String correo = rs.getString("Correo");
				String materias = rs.getString("Materias");
				String telefono = rs.getString("Telefono");

				model.addRow(new Object[]{id, nombre, sexo, matricula, correo, materias, telefono});
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void eliminarEstudiante() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			System.out.println("No se ha seleccionado ninguna fila.");
			return;
		}

		int id = (int) table.getValueAt(fila, 0); // columna 0: id_estudiante

		String deleteQuery = "DELETE FROM Estudiantes WHERE id_estudiante = ?";

		try (Connection conn = Conection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Estudiante eliminado.");
			actualizarTabla();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void guardarCambios() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String updateQuery = "UPDATE Estudiantes SET Nombre=?, Sexo=?, Matricula=?, Correo=?, Materias=?, Telefono=? WHERE id_estudiante=?";

		try (Connection conn = Conection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

			for (int i = 0; i < model.getRowCount(); i++) {
				int id = (int) model.getValueAt(i, 0);
				String nombre = (String) model.getValueAt(i, 1);
				String sexo = (String) model.getValueAt(i, 2);
				String matricula = (String) model.getValueAt(i, 3);
				String correo = (String) model.getValueAt(i, 4);
				String materias = (String) model.getValueAt(i, 5);
				String telefono = (String) model.getValueAt(i, 6);

				stmt.setString(1, nombre);
				stmt.setString(2, sexo);
				stmt.setString(3, matricula);
				stmt.setString(4, correo);
				stmt.setString(5, materias);
				stmt.setString(6, telefono);
				stmt.setInt(7, id);

				stmt.addBatch(); 
			}

			stmt.executeBatch(); 
			System.out.println("Cambios guardados correctamente.");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

