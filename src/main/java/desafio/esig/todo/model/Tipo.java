package desafio.esig.todo.model;

import java.io.Serializable;

public class Tipo implements Serializable {

	private static final long serialVersionUID = -1145548417307948514L;
	private String descricao;
	private String color;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
