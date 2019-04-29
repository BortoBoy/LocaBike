package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    public AuthDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Bike", "root", "root");
    }
    
    public void insert(String user, String passwd) {
        String sql = "INSERT INTO administradores (user, password) VALUES (?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, passwd);    
            
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(String user) {
        String sql = "DELETE FROM Cliente where username = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, user);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean exists(String user, String passwd){
        boolean answer = false;
        String sql = "select * from administradores where username = ? and password = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, user);
            statement.setString(2, passwd);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answer = true;
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
    
    public boolean exists_by_name(String user){
        boolean answer = false;
        String sql = "select * from administradores where username = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, user);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answer = true;
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
}
