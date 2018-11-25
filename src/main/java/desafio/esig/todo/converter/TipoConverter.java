package desafio.esig.todo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import desafio.esig.todo.model.Tipo;

@FacesConverter(forClass = Tipo.class, value = "tipoConverter")
public class TipoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value != null && !value.isEmpty()) {
	            return (Tipo) component.getAttributes().get(value);
	        }
	        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 if (value instanceof Tipo) {
	            Tipo entity= (Tipo) value;
	            if (entity != null && entity instanceof Tipo && entity.getDescricao() != null) {
	            	component.getAttributes().put(entity.getDescricao(), entity);
	                return entity.getDescricao();
	            }
	        }
	        return "";
	}

}
