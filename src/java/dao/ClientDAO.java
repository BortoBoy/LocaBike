package dao;

import model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public ClientDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Bike", "root", "root");
    }

    public void insert(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, email, senha, cpf, tel, sexo, data_de_nasc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getTel());
            statement.setString(6, cliente.getSexo());
            statement.setString(7, cliente.getData_nasc());     
            
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha"); 
                String cpf = resultSet.getString("cpf");
                String tel = resultSet.getString("tel");
                String sexo = resultSet.getString("sexo");
                String data_nasc = resultSet.getString("data_de_nasc");
                
                Cliente cliente = new Cliente(id, nome, email, senha, cpf, tel, sexo, data_nasc);
                listaCliente.add(cliente);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCliente;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cliente.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, senha = ?, ";
        sql += " tel = ?, sexo = ?, data_de_nasc = ?, cpf = ?";
        sql += " WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getTel());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getData_nasc());
            statement.setString(7, cliente.getCpf());
            statement.setInt(8, cliente.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTE WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha"); 
                String cpf = resultSet.getString("cpf");
                String tel = resultSet.getString("tel");
                String sexo = resultSet.getString("sexo");
                String data_nasc = resultSet.getString("data_de_nasc");
                cliente = new Cliente(id, nome, email, senha, cpf, tel, sexo, data_nasc);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
