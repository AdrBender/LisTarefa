package com.adriano.listarefa;

import java.io.Serializable;

public class Tarefa {
    private int id;
    private String tarefa;
    private String data;
    private String hora;
	
	public Tarefa(){}
	
	public Tarefa(String tarefa, String data, String hora) {
	 this.tarefa = tarefa;
	 this.data = data;
	 this.hora = hora;
	 }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }
    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
