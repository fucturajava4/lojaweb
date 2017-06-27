package br.recife.fucturajava4.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.recife.fucturajava4.dao.UsuarioDao;
import br.recife.fucturajava4.model.Usuario;

@Repository
public class UsuarioJpa implements UsuarioDao{
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void cadastrar(Usuario usuario) {
		manager.persist(usuario);
	}

	@Override
	public void atualizar(Usuario usuario) {
		manager.merge(usuario);
	}

	@Override
	public void remover(long id) {
		manager.remove(pegarUsuarioPorId(id));
	}

	private Usuario pegarUsuarioPorId(long id){
		Query query = manager.createQuery("select usuario from Usuario as usuario where usuario.id = :paramId");
		query.setParameter("paramId", id);
		return (Usuario)query.getSingleResult();
	}
	
	@Override
	public List<Usuario> listar() {
		Query query = manager.createQuery("select usuario from Usuario as usuario");
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		return lista;
	}

}
