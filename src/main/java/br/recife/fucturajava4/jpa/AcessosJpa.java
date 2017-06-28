package br.recife.fucturajava4.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.recife.fucturajava4.dao.AcessosDao;
import br.recife.fucturajava4.model.Acessos;

@Repository
public class AcessosJpa implements AcessosDao{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void setAcesso(Acessos acessos) {
		manager.persist(acessos);
	}

}
