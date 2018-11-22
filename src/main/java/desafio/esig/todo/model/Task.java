package desafio.esig.todo.model;

import java.io.Serializable;

public class Task implements Serializable{

	private static final long serialVersionUID = -4949473102280879863L;
	
	private int id;
	private String stDescricao;
	private boolean boFinalizado;
	private Postit cePostit;
	
	public String getStDescricao() {
		return stDescricao;
	}
	public void setStDescricao(String stDescricao) {
		this.stDescricao = stDescricao;
	}
	public boolean isBoFinalizado() {
		return boFinalizado;
	}
	public void setBoFinalizado(boolean boFinalizado) {
		this.boFinalizado = boFinalizado;
	}
	public Postit getCePostit() {
		return cePostit;
	}
	public void setCePostit(Postit cePostit) {
		this.cePostit = cePostit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		

}
