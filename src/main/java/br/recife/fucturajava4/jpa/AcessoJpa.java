package br.recife.fucturajava4.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.recife.fucturajava4.dao.AcessoDao;
import br.recife.fucturajava4.model.Acesso;

@Repository
public class AcessoJpa implements AcessoDao{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void addAcesso(Acesso acesso) {
		manager.persist(acesso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acesso> listar() {
		Query q = manager.createQuery("select a from Acesso as a");
		return q.getResultList();
	}

}
