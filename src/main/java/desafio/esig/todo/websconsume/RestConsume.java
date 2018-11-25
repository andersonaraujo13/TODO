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
	
	public List<Postit> listAllPostit() {
		List<Postit> listPostit = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/TodoWebService/postit/list").request().get();
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
		WebTarget target = client.target("http://localhost:8080/TodoWebService/postit/create");
		target.request(MediaType.APPLICATION_JSON_TYPE).post(
				Entity.entity(postit, MediaType.APPLICATION_JSON), Postit.class
				);
	}
	
	public void updateTask(Task task) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TodoWebService/task/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(task, MediaType.APPLICATION_JSON), Task.class
				);
	}

	public void updatePostit(Postit postit) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TodoWebService/postit/update");
		target.request(MediaType.APPLICATION_JSON_TYPE).put(
				Entity.entity(postit, MediaType.APPLICATION_JSON), Postit.class
				);
	}
}
