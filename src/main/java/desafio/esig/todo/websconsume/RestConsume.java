package desafio.esig.todo.websconsume;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;

import desafio.esig.todo.model.Postit;
import desafio.esig.todo.model.Task;

public class RestConsume {
	private static final String url = "http://localhost:8080/TodoWebService";
	
	public List<Postit> listAllPostit() {
		ArrayList<Postit> listPostit = new ArrayList<>();
		ResteasyClient client = new ResteasyClientBuilder().build();
		Response response = client.target(url + "/postit/list").request().get();
		Postit[] list = new Gson().fromJson(response.readEntity(String.class), Postit[].class);
		
		for(int count = 0; count < list.length; count++) {
			listPostit.add(list[count]);
		}
		
		
		return listPostit;
	}
	
	public void create(Postit postit) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(url + "/postit/create");
		target.request(MediaType.APPLICATION_JSON_TYPE).post(
				Entity.entity(new Gson().toJson(postit), MediaType.APPLICATION_JSON), String.class
				);
	}
	
	public Task createTask(Task task) {
		task.getCePostit().setListTarefas(null);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(url + "/task/create");
		Task taskResponse = new Gson().fromJson(target.request(MediaType.APPLICATION_JSON_TYPE).post(
				Entity.entity(new Gson().toJson(task), MediaType.APPLICATION_JSON), String.class
				), Task.class);
		return taskResponse;
	}
	
	public void updateTask(Task task) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(url + "/task/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(new Gson().toJson(task), MediaType.APPLICATION_JSON), String.class
				);
	}

	public void updatePostit(Postit postit) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(url + "/postit/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(new Gson().toJson(postit), MediaType.APPLICATION_JSON), String.class
				);
	}
	
	public void deleteTask(int id) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(url + "/task/" + id + "/delete");
		target.request(MediaType.APPLICATION_JSON_TYPE).delete();
	}
}
