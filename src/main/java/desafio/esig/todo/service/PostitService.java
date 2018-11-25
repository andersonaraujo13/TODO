package desafio.esig.todo.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import desafio.esig.todo.model.Postit;
import desafio.esig.todo.websconsume.RestConsume;

@Stateful
public class PostitService {

	@Inject
	private RestConsume consume;
	
	public List<Postit> listALL() {
		return consume.listAllPostit();
	}
	
	public void create(Postit postit) {
		consume.create(postit);
	}
	
	public void update(Postit postit) {
		consume.updatePostit(postit);
	}
}
