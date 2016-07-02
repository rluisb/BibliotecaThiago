package com.trabalho.biblioteca.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensageiro {

	public static void nootificaErro(String mensagemDeErro, String detalhes){
		notificar(FacesMessage.SEVERITY_ERROR, mensagemDeErro, detalhes);
	}
	
	public static void notificaAviso(String mensagemDeAviso, String detalhes){
		notificar(FacesMessage.SEVERITY_WARN, mensagemDeAviso, detalhes);
	}
	
	public static void notificaInformacao(String mensagemInformativa, String detalhes){
		notificar(FacesMessage.SEVERITY_INFO,	mensagemInformativa, detalhes);
	}
	
	private static void notificar(FacesMessage.Severity severidade, String mensagem, String detalhes){
		FacesMessage msg = new FacesMessage(severidade,	mensagem, detalhes);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
