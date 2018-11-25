package desafio.esig.todo.service;

import javax.ejb.Stateful;
import javax.inject.Inject;

import desafio.esig.todo.model.Task;
import desafio.esig.todo.websconsume.RestConsume;

@Stateful
public class TaskService {
	@Inject
	private RestConsume consume;
	
	public void atualizar(Task task) {
		consume.updateTask(task);
	}
}
