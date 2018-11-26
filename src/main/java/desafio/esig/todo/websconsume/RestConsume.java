package desafio.esig.todo.websconsume;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import desafio.esig.todo.model.Postit;
import desafio.esig.todo.model.Task;

public class RestConsume {
	private static final String url = "http://localhost:8080/TodoWebService";
	
	public List<Postit> listAllPostit() {
		List<Postit> listPostit = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		Response response = client.target(url + "/postit/list").request().get();
		JsonArray array = response.readEntity(JsonArray.class);
		
		for(int contador = 0; contador < array.size(); contador++) {
			JsonValue value = array.get(contador);
			Postit postit = new Gson().fromJson(value.toString(), Postit.class);
			listPostit.add(postit);	
		}
		
		return listPostit;
	}
	
	public void create(Postit postit) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/postit/create");
		target.request(MediaType.APPLICATION_JSON_TYPE).post(
				Entity.entity(postit, MediaType.APPLICATION_JSON), Postit.class
				);
	}
	
	public Task createTask(Task task) {
		task.getCePostit().setListTarefas(null);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/task/create");
		Task taskResponse = target.request(MediaType.APPLICATION_JSON_TYPE).post(
				Entity.entity(task, MediaType.APPLICATION_JSON), Task.class
				);
		return taskResponse;
	}
	
	public void updateTask(Task task) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/task/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(task, MediaType.APPLICATION_JSON), Task.class
				);
	}

	public void updatePostit(Postit postit) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/postit/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(postit, MediaType.APPLICATION_JSON), Postit.class
				);
	}
	
	public void deleteTask(int id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/task/" + id + "/delete");
		target.request(MediaType.APPLICATION_JSON_TYPE).delete();
	}
}
