package dao;

import model.Locadora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocadoraDAO {
    
    public LocadoraDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Bike", "root", "root");
    }
    
    public void insert(Locadora locadora) {
        String sql = "INSERT INTO Locadora (email, senha, cnpj, nome, cidade) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getSenha());
            statement.setString(3, locadora.getCnpj());
            statement.setString(4, locadora.getNome());
            statement.setString(5, locadora.getCidade());
   
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Locadora> getAll() {
        List<Locadora> listaLocadora = new ArrayList<>();
        String sql = "SELECT * FROM Locadora";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj  = resultSet.getString("cnpj"); 
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                
                Locadora locadora = new Locadora(id, email, senha, cnpj, nome, cidade);
                listaLocadora.add(locadora);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocadora;
    }
    
    public void delete(Locadora locadora) {
        String sql = "DELETE FROM Locadora where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, locadora.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Locadora locadora) {
        String sql = "UPDATE Locadora SET email = ?, senha = ?, cnpj = ?, ";
        sql += " nome = ?, cidade = ?";
        sql += " WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getSenha());
            statement.setString(3, locadora.getCnpj());
            statement.setString(4, locadora.getNome());
            statement.setString(5, locadora.getCidade());
            statement.setInt(6, locadora.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public Locadora get(int id) {
        Locadora locadora = null;
        String sql = "SELECT * FROM locadora WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj"); 
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                locadora = new Locadora(id, email, senha, cnpj, nome, cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }
}
