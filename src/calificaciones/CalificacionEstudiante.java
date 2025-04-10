package calificaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Conect.Conection;

import java.awt.*;
import java.sql.*;

public class CalificacionEstudiante extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaCalificaciones;
    private DefaultTableModel model;
    private int idEstudiante;  

    
    public CalificacionEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 600, 400);  
        setTitle("Calificación del estudiante");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 235));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        
        JLabel lblTitle = new JLabel("Calificación del estudiante", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        contentPane.add(lblTitle, BorderLayout.NORTH);

       
        String[] columnNames = {"Materia", "Calificación"};
        model = new DefaultTableModel(columnNames, 0);
        tablaCalificaciones = new JTable(model);
        tablaCalificaciones.setEnabled(false);  
        JScrollPane scrollPane = new JScrollPane(tablaCalificaciones);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        
        cargarCalificaciones();
    }

   
    private void cargarCalificaciones() {
        try (Connection conn = Conection.getConnection()) {
            String sql = "SELECT m.nombre, c.calificacion " +
                         "FROM Calificaciones c " +
                         "JOIN Materias m ON c.id_materia = m.id_materia " +
                         "WHERE c.id_estudiante = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, idEstudiante);  
                try (ResultSet rs = pst.executeQuery()) {
                    model.setRowCount(0);  
                    while (rs.next()) {
                        String materia = rs.getString("nombre");
                        double calificacion = rs.getDouble("calificacion");
                        model.addRow(new Object[]{materia, calificacion});  
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las calificaciones: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                
                CalificacionEstudiante frame = new CalificacionEstudiante(1); 
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}