package com.trabalho.biblioteca.fabricas;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class FabricaDeEntityManager {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("BibliotecaUnit");

	@Produces
	@RequestScoped
	public EntityManager criarEntityManager() {
		return factory.createEntityManager();
	}

	public void fecharEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
