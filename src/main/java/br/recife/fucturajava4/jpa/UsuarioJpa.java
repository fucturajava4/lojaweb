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
	public String cadastrar(Usuario usuario) {
		if(podeCadastrar(usuario)){
			manager.persist(usuario);
			return "Usuário cadastrado com exito.";
		}
		else
			return "Já existe usuário cadastrado com este nome ou e-mail.";
	}

	private boolean podeCadastrar(Usuario usuario){
		Query query = manager.createQuery("select u from Usuario as u where u.nome = :nome or u.email = :email");
		query.setParameter("nome", usuario.getNome());
		query.setParameter("email", usuario.getEmail());
		Usuario cadastrado = null;
		
		try{
			cadastrado = (Usuario) query.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(cadastrado == null)
			return true;
		else
			return false;
	}
	
	@Override
	public String atualizar(Usuario usuario) {
		if(podeCadastrar(usuario)){
			manager.merge(usuario);
			return "Usuário atualizado com exito.";
		}
		else
			return "Já existe usuário cadastrado com este nome ou e-mail.";
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
