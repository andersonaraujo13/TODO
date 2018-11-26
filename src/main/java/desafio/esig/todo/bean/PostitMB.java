package desafio.esig.todo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.SerializationUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import desafio.esig.todo.model.Postit;
import desafio.esig.todo.model.Task;
import desafio.esig.todo.model.Tipo;
import desafio.esig.todo.service.PostitService;
import desafio.esig.todo.service.TaskService;

@Named
@ViewScoped 
public class PostitMB implements Serializable {

	private static final long serialVersionUID = 6238221374713392582L;
	@EJB
	private PostitService postitService;
	@EJB
	private TaskService taskService;
	
	private List<Postit> listPostit;
	private Postit postitView;
	
	private String stDescricaoTask;
	private List<Tipo> listTipos;
	
	private Tipo tipo;
	private String tituloPostit;
	private String descricaoPostit;
	private List<String> listTask;
	
	@PostConstruct
	private void init() {
		listPostit = new ArrayList<>();
		listTipos = new ArrayList<>();
		
		Tipo normal = new Tipo();
		normal.setColor("bg-azul");
		normal.setDescricao("Normal");
		
		Tipo urgente = new Tipo();
		urgente.setColor("bg-vermelho");
		urgente.setDescricao("Urgente");
		
		Tipo intermediario = new Tipo();
		intermediario.setColor("bg-amarelo");
		intermediario.setDescricao("Intermediário");
		
		listTipos.add(normal);
		listTipos.add(urgente);
		listTipos.add(intermediario);
		
		listPostit = postitService.listALL();
		
		
	}	
	private void updateListPostit() {
		listPostit = postitService.listALL();
	}
	public List<Postit> getListPostit() {
		return listPostit;
	}
	public Postit getPostitView() {
		return postitView;
	}
	public void setPostitView(Postit postitView) {
		this.postitView = postitView;
	}	
	public String getStDescricaoTask() {
		return stDescricaoTask;
	}
	public void setStDescricaoTask(String stDescricaoTask) {
		this.stDescricaoTask = stDescricaoTask;
	}
	public List<Tipo> getListTipos() {
		return listTipos;
	} 
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getTituloPostit() {
		return tituloPostit;
	}
	public void setTituloPostit(String tituloPostit) {
		this.tituloPostit = tituloPostit;
	}
	public String getDescricaoPostit() {
		return descricaoPostit;
	}
	public void setDescricaoPostit(String descricaoPostit) {
		this.descricaoPostit = descricaoPostit;
	}
	public void setListTipos(List<Tipo> listTipos) {
		this.listTipos = listTipos;
	}
	public List<String> getListTask() {
		return listTask;
	}
	public void setListTask(List<String> listTask) {
		this.listTask = listTask;
	}
	public void finalizarTask(Task task){
		task.setBoFinalizado(true);
		
		boolean finalizado = postitView.getListTarefas().stream().noneMatch(tarefa -> !tarefa.isBoFinalizado());
		
		if(finalizado) {
			postitView.setBoFinalizado(true);
			postitService.update(postitView);
		}
		
		taskService.atualizar(task);
		updateListPostit();
	}	
	public void ativarTask(Task task){
		task.setBoFinalizado(false);
		
		boolean finalizado = postitView.getListTarefas().stream().noneMatch(tarefa -> !tarefa.isBoFinalizado());
		
		if(!finalizado) {
			postitView.setBoFinalizado(false);
			postitService.update(postitView);
		}
		
		taskService.atualizar(task);
		updateListPostit();
	}	
	public void removerTask(Task task) {
		postitView.getListTarefas().remove(task);		
		taskService.remover(task.getId());
	}
	public void addTask() {
		if(stDescricaoTask == null || stDescricaoTask.equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Coloque uma descrição na tarefa.");
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		} else {
			Task task = new Task();
			task.setStDescricao(stDescricaoTask);
			task.setCePostit(postitView);
			task.setBoFinalizado(false);
			
			task.setCePostit(SerializationUtils.clone(postitView));
			task = taskService.create(task);
			
			postitView.getListTarefas().add(task);
			stDescricaoTask = new String();
		}
		
	}
	
	public void addPostit() {
		Postit postit = new Postit();
		postit.setStBackground(tipo.getColor());
		postit.setStApelido(tituloPostit);
		postit.setStDescricao(descricaoPostit);
		postit.setBoFinalizado(false);
		
		List<Task> listTarefas = new ArrayList<>();
		
		for(String tarefa : listTask) {
			Task task = new Task();
			task.setBoFinalizado(false);
			task.setStDescricao(tarefa);
			listTarefas.add(task);
		}
		
		postit.setListTarefas(listTarefas);
		listPostit.add(postit);
		postitService.create(postit);
		
		tipo = new Tipo();
		tituloPostit = new String();
		descricaoPostit = new String();
		listTask = new ArrayList<>();
		updateListPostit();
		
		PrimeFaces.current().executeScript("PF('criarTarefa').hide();");
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Lista de tarefas cadastradas com sucesso.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
	}
	
}
