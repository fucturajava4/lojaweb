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
			return "Já existe usuário cadastrado com este e-mail.";
	}

	private boolean podeCadastrar(Usuario usuario){
		Query query = manager.createQuery("select u from Usuario as u where u.email = :email and u.id != :id");
		query.setParameter("email", usuario.getEmail());
		query.setParameter("id", usuario.getId());
		Usuario cadastrado = null;
		
		try{
			if(query.getSingleResult() != null)
				cadastrado = (Usuario)query.getSingleResult();
		}
		catch(Exception e){
			cadastrado = null;
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
			return "Já existe usuário cadastrado com este e-mail.";
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

	@Override
	public Usuario logar(String email, String senha) {
		Query query = manager.createQuery("select usuario from Usuario as usuario where usuario.email = :email and usuario.senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		Usuario logado = null;
		
		try{
			logado = (Usuario)query.getSingleResult();
		}
		catch(Exception e){
			logado = null;
		}
		
		return logado;
	}

}