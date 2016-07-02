package com.trabalho.biblioteca.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorCombobox")
public class ConversorCombobox implements Converter {

	private static final String KEY = ConversorCombobox.class.getCanonicalName();

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {
		if (objeto != null) {
			if (!getObjetos(context).containsKey(objeto)) {
				String uuid = UUID.randomUUID().toString();
				getObjetos(context).put(objeto, uuid);
				return uuid;
			} else {
				return getObjetos(context).get(objeto);
			}
		}

		return null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
		for (Entry<Object, String> entry : getObjetos(context).entrySet()) {
			if (entry.getValue().equals(uuid)) {
				return entry.getKey();
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<Object, String> getObjetos(FacesContext context) {
		Map<String, Object> viewMap = context.getViewRoot().getViewMap();
		Map<Object, String> entities = (Map<Object, String>) viewMap.get(KEY);

		if (entities == null) {
			entities = new HashMap<>();
			viewMap.put(KEY, entities);
		}

		return entities;
	}
}
