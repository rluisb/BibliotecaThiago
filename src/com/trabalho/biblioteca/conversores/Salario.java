package com.trabalho.biblioteca.conversores;

import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "conversorSalario")
public class Salario implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String salario) {
		return Double.parseDouble(salario);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object salarioFormatado) {
		Double salario = (Double) salarioFormatado;
		return NumberFormat.getCurrencyInstance().format(salario);
	}
}