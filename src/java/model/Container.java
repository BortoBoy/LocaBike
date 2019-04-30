package model;

public class Container {
    private String nome_locadora;
    private String data;

    public Container(String nome_locadora, String data) {
        this.nome_locadora = nome_locadora;
        this.data = data;
    }

    public String getNome_locadora() {
        return nome_locadora;
    }

    public void setNome_locadora(String nome_locadora) {
        this.nome_locadora = nome_locadora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
