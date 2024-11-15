/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym_software.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jhon0
 */
public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/gym";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static Connection driverManager;

    public Conexion() throws SQLException {
        this.driverManager = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public Map<String, Object> ejecutarConsulta(String query) throws SQLException {
        Map<String, Object> resultado = new HashMap<>();
        
        try (PreparedStatement statement = driverManager.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    resultado.put(columnName, value);
                }
            }
        }
        return resultado;
    }
    
    public List<Map<String, Object>> ejecutarConsultaVariosResultados(String query) throws SQLException {
        List<Map<String, Object>> resultados = new ArrayList<>();
        
        try (PreparedStatement statement = driverManager.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    fila.put(columnName, value);
                }
                resultados.add(fila);
            }
        }
        return resultados;
    }
    
    public void ejecutarUpdate(String sql) throws SQLException {
        try (PreparedStatement statement = driverManager.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    // Cierra la conexión cuando ya no se necesite
    public void cerrarConexion() throws SQLException {
        if (driverManager != null && !driverManager.isClosed()) {
            driverManager.close();
        }
    }
}
