package desafio.esig.todo.model;

import java.io.Serializable;
import java.util.List;

public class Postit implements Serializable{
	
	private static final long serialVersionUID = 1473105742621897584L;
	
	private int id;
	private String stApelido;
	private String stDescricao;
	private String stBackground;
	private boolean boFinalizado;
	private List<Task> listTarefas;
	
	public String getStApelido() {
		return stApelido;
	}
	public void setStApelido(String stApelido) {
		this.stApelido = stApelido;
	}
	public String getStDescricao() {
		return stDescricao;
	}
	public void setStDescricao(String stDescricao) {
		this.stDescricao = stDescricao;
	}
	public String getStBackground() {
		return stBackground;
	}
	public void setStBackground(String stBackground) {
		this.stBackground = stBackground;
	}
	public boolean isBoFinalizado() {
		return boFinalizado;
	}
	public void setBoFinalizado(boolean boFinalizado) {
		this.boFinalizado = boFinalizado;
	}
	public List<Task> getListTarefas() {
		return listTarefas;
	}
	public void setListTarefas(List<Task> listTarefas) {
		this.listTarefas = listTarefas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
