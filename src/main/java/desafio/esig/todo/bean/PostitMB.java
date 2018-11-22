package desafio.esig.todo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import desafio.esig.todo.model.Postit;
import desafio.esig.todo.model.Task;

@Named
@ViewScoped 
public class PostitMB implements Serializable {

	private static final long serialVersionUID = 6238221374713392582L;
	private List<Postit> listPostit;
	private Postit postitView;
	
	private String stDescricaoTask;
	
	@PostConstruct
	private void init() {
		listPostit = new ArrayList<>();
		
		Postit postit = new Postit();		
		postit.setStApelido("Atividades Diarias");
		postit.setStDescricao("Lista de atividades diarias para manter a vida saudável e organizada.");
		postit.setStBackground("bg-azul");
		
		Postit postit2 = new Postit();		
		postit2.setStApelido("Desafio Esig");
		postit2.setStDescricao("Desenvolver um sistema do tipo TODO como parte da primeira parte do processo de seleção para vaga de desenvolvedor java.");
		postit2.setStBackground("bg-vermelho");
		
		
		Postit postit3 = new Postit();		
		postit3.setStApelido("Exercicios da Academia");
		postit3.setStDescricao("Lista de exercicio para fazer nos dias de segunda da academia. Está incluso as refeições de pré-treino e pós treino.");
		postit3.setStBackground("bg-amarelo");
		postit3.setBoFinalizado(true);
		
		Postit postit4 = new Postit();		
		postit4.setStApelido("Compras da Casa");
		postit4.setStDescricao("Lista de compras dos alimentos e dos materiais de limpeza desse mês. Comprar no Mercado do Zezim, e passar no mercado seu joão para saber das promoções.");
		postit4.setStBackground("bg-azul");
		
		Postit postit5 = new Postit();		
		postit5.setStApelido("Viagem para o Interior");
		postit5.setStDescricao("Viagem para o interior com a família. Viagem longa e demorada.");
		postit5.setStBackground("bg-amarelo");
		
		Postit postit6 = new Postit();		
		postit6.setStApelido("Festa de Aniversário");
		postit6.setStDescricao("Festa de aniversário da namorada. Fazer direito para ela não ficar irado com você.");
		postit6.setStBackground("bg-azul");
		
		Postit postit7 = new Postit();		
		postit7.setStApelido("Churrasco Fim de Semana");
		postit7.setStDescricao("Lembrete para fazer a compra para o churrasco de comemoração no fim de semana.");
		postit7.setStBackground("bg-amarelo");
		
		Task task = new Task();
		task.setId(1);
		task.setStDescricao("Descrição1");
		
		Task task2 = new Task();
		task2.setId(2);
		task2.setStDescricao("Descrição2");
		
		Task task3 = new Task();
		task3.setId(3);
		task3.setStDescricao("Descrição3");
		
		Task task4 = new Task();
		task4.setId(4);
		task4.setStDescricao("Descrição4");
		
		Task task5 = new Task();
		task5.setId(5);
		task5.setStDescricao("Descrição5");
		
		
		ArrayList<Task> listTask = new ArrayList<>();
		listTask.add(task);
		listTask.add(task2);
		listTask.add(task3);
		listTask.add(task4);
		listTask.add(task5);
		
		postit7.setListTarefas(listTask);
		
		listPostit.add(postit);
		listPostit.add(postit2);
		listPostit.add(postit3);
		listPostit.add(postit4);
		listPostit.add(postit5);
		listPostit.add(postit6);
		listPostit.add(postit7);
		
		
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
	public void finalizarTask(Task task){
		task.setBoFinalizado(true);
	}	
	public void ativarTask(Task task){
		task.setBoFinalizado(false);
	}	
	public void removerTask(Task task) {
		postitView.getListTarefas().remove(task);
	}
	public void addTask() {
		Task task = new Task();
		task.setStDescricao(stDescricaoTask);
		task.setCePostit(postitView);
		task.setBoFinalizado(false);
		
		postitView.getListTarefas().add(task);
		stDescricaoTask = new String();
	}
	
}
