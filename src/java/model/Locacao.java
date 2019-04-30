/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
public class Locacao {
    
    private int id;
    private String data;
    private int cliente_id;
    private int locadora_id;

    public Locacao(String data, int cliente_id, int locadora_id) {
        this.data = data;
        this.cliente_id = cliente_id;
        this.locadora_id = locadora_id;
    }

    public Locacao(int id, String data, int cliente_id, int locadora_id) {
        this.id = id;
        this.data = data;
        this.cliente_id = cliente_id;
        this.locadora_id = locadora_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getLocadora_id() {
        return locadora_id;
    }

    public void setLocadora_id(int locadora_id) {
        this.locadora_id = locadora_id;
    }
    
    
}
