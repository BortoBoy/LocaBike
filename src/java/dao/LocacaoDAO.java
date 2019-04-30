package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Locacao;

public class LocacaoDAO {
    public LocacaoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Bike", "root", "root");
    }
    
    public void insert(Locacao locacao) {
        String sql = "INSERT INTO locacao(data_locacao, cliente_id, locadora_id) VALUES (?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, locacao.getData());
            statement.setInt(2, locacao.getCliente_id());
            statement.setInt(3, locacao.getLocadora_id());
   
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Locacao> getAll() {
        List<Locacao> listaLocacao = new ArrayList<>();
        String sql = "SELECT * FROM Locacao";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String data = resultSet.getString("data_locacao");
                int cliente = resultSet.getInt("cliente_id");
                int locadora = resultSet.getInt("lcoadora_id");
                int id = resultSet.getInt("id");
                
                Locacao locacao = new Locacao(id, data, cliente, locadora);
                listaLocacao.add(locacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacao;
    }
    
    public List<Locacao> getByClientID(int cliente_id) {
        List<Locacao> listaLocacao = new ArrayList<>();
        String sql = "SELECT * FROM Locacao where cliente_id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cliente_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String data = resultSet.getString("data_locacao");
                int cliente = resultSet.getInt("cliente_id");
                int locadora = resultSet.getInt("locadora_id");
                int id = resultSet.getInt("id");
                
                Locacao locacao = new Locacao(id, data, cliente, locadora);
                listaLocacao.add(locacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacao;
    }
    
}
