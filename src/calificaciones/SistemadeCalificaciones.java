package calificaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Conect.Conection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class SistemadeCalificaciones extends JFrame {

    private JTextField txtIdEstudiante, txtCalificacion;
    private JComboBox<String> comboMaterias;
    private JButton btnGuardar, btnEliminar, btnEditar;
    private JTable tablaCalificaciones;
    private DefaultTableModel model;

    private boolean modoEdicion = false;
    private int filaSeleccionada = -1;

    public SistemadeCalificaciones() {
        setTitle("Sistema de Calificaciones");
        setSize(595, 703);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(600, 200));

        JLabel lblEstudiante = new JLabel("ID Estudiante:");
        lblEstudiante.setBounds(30, 20, 100, 25);
        formPanel.add(lblEstudiante);

        txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(140, 20, 251, 25);
        formPanel.add(txtIdEstudiante);

        JLabel lblMateria = new JLabel("Materia:");
        lblMateria.setBounds(30, 60, 100, 25);
        formPanel.add(lblMateria);

        comboMaterias = new JComboBox<>();
        comboMaterias.setBounds(140, 60, 251, 25);
        formPanel.add(comboMaterias);

        JLabel lblCalificacion = new JLabel("Calificación:");
        lblCalificacion.setBounds(30, 100, 100, 25);
        formPanel.add(lblCalificacion);

        txtCalificacion = new JTextField();
        txtCalificacion.setBounds(140, 100, 251, 25);
        formPanel.add(txtCalificacion);

        btnGuardar = new JButton("Guardar Calificación");
        btnGuardar.setBounds(22, 169, 177, 21);
        formPanel.add(btnGuardar);

        btnEliminar = new JButton("Eliminar Calificación");
        btnEliminar.setBounds(209, 169, 159, 21);
        formPanel.add(btnEliminar);

        btnEditar = new JButton("Editar Calificación");
        btnEditar.setBounds(378, 169, 146, 21);
        formPanel.add(btnEditar);

        btnGuardar.addActionListener(e -> guardarCalificacion());
        btnEliminar.addActionListener(e -> eliminarCalificacion());
        btnEditar.addActionListener(e -> editarCalificacion());

        String[] columnNames = {"ID Estudiante", "Estudiante", "Materia", "Calificación"};
        model = new DefaultTableModel(columnNames, 0);
        tablaCalificaciones = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaCalificaciones);
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        cargarMaterias();
        cargarCalificaciones();
    }

    private void guardarCalificacion() {
        String idEstudiante = txtIdEstudiante.getText();
        String materia = (String) comboMaterias.getSelectedItem();
        String calificacion = txtCalificacion.getText();

        if (idEstudiante.isEmpty() || materia == null || calificacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try (Connection conn = Conection.getConnection()) {
            String idMateria = obtenerIdMateria(materia, conn);
            if (idMateria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La materia no existe.");
                return;
            }

            if (modoEdicion) {
                String sql = "UPDATE Calificaciones SET calificacion = ? WHERE id_estudiante = ? AND id_materia = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setDouble(1, Double.parseDouble(calificacion));
                    stmt.setInt(2, Integer.parseInt(idEstudiante));
                    stmt.setInt(3, Integer.parseInt(idMateria));
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Calificación actualizada exitosamente.");
                }
                modoEdicion = false;
                filaSeleccionada = -1;
            } else {
                String sql = "INSERT INTO Calificaciones (id_estudiante, id_materia, calificacion) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, Integer.parseInt(idEstudiante));
                    stmt.setInt(2, Integer.parseInt(idMateria));
                    stmt.setDouble(3, Double.parseDouble(calificacion));
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Calificación guardada exitosamente.");
                }
            }

            limpiarCampos();
            cargarCalificaciones();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }

    private String obtenerIdMateria(String materia, Connection conn) {
        String sql = "SELECT id_materia FROM Materias WHERE LOWER(nombre) = LOWER(?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, materia.toLowerCase());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("id_materia");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener ID de materia: " + e.getMessage());
        }
        return "";
    }

    private void eliminarCalificacion() {
        int selectedRow = tablaCalificaciones.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        int idEstudiante = (int) model.getValueAt(selectedRow, 0);
        String materia = (String) model.getValueAt(selectedRow, 2);

        try (Connection conn = Conection.getConnection()) {
            String idMateria = obtenerIdMateria(materia, conn);
            String sql = "DELETE FROM Calificaciones WHERE id_estudiante = ? AND id_materia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idEstudiante);
                stmt.setInt(2, Integer.parseInt(idMateria));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Calificación eliminada.");
                cargarCalificaciones();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
        }
    }

    private void editarCalificacion() {
        filaSeleccionada = tablaCalificaciones.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una calificación para editar.");
            return;
        }

        String idEstudiante = model.getValueAt(filaSeleccionada, 0).toString();
        String materia = model.getValueAt(filaSeleccionada, 2).toString();
        String calificacion = model.getValueAt(filaSeleccionada, 3).toString();

        txtIdEstudiante.setText(idEstudiante);
        comboMaterias.setSelectedItem(materia);
        txtCalificacion.setText(calificacion);

        modoEdicion = true;
    }

    private void cargarCalificaciones() {
        model.setRowCount(0);
        try (Connection conn = Conection.getConnection()) {
            String sql = "SELECT c.id_estudiante, e.nombre AS estudiante, m.nombre AS materia, c.calificacion " +
                         "FROM Calificaciones c " +
                         "JOIN Estudiantes e ON c.id_estudiante = e.id_estudiante " +
                         "JOIN Materias m ON c.id_materia = m.id_materia";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("id_estudiante"),
                        rs.getString("estudiante"),
                        rs.getString("materia"),
                        rs.getDouble("calificacion")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar calificaciones: " + e.getMessage());
        }
    }

    private void cargarMaterias() {
        comboMaterias.removeAllItems();
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre FROM Materias")) {

            while (rs.next()) {
                comboMaterias.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar materias: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtIdEstudiante.setText("");
        txtCalificacion.setText("");
        comboMaterias.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemadeCalificaciones().setVisible(true);
        });
    }
}

