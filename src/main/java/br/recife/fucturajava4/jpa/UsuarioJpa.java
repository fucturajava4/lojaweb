package br.recife.fucturajava4.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.recife.fucturajava4.dao.UsuarioDao;
import br.recife.fucturajava4.model.Login;
import br.recife.fucturajava4.model.Usuario;
import br.recife.fucturajava4.utils.Logs;

@Repository
public class UsuarioJpa implements UsuarioDao{
	
	@PersistenceContext
	EntityManager manager;
	
	/**
	 * Pesquisar usu�rio por ID
	 * @param id
	 * @return
	 */
	private Usuario pegarUsuarioPorId(long id){
		try{
			Query query = manager.createQuery("select u from Usuario as u where u.id = :id");
			query.setParameter("id", id);
			Usuario encontrado = (Usuario)query.getSingleResult();
			return encontrado;
		}
		catch(Exception e){
			Logs.warn("[UsuarioJpa]::pegarUsuarioPorId::Erro ao tentar pegar usuario por ID. Exception:");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Pesquisar usu�rio por nome
	 * @param nome
	 * @return
	 */
	private Usuario pegarUsuarioPorNome(String nome){
		try{
			Query query = manager.createQuery("select u from Usuario as u where u.nome = :nome");
			query.setParameter("nome", nome);
			Usuario encontrado = (Usuario)query.getSingleResult();
			return encontrado;
		}
		catch(Exception e){
			Logs.warn("[UsuarioJpa]::pegarUsuarioPorNome::Erro ao tentar pegar usuario por NOME. Exception:");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Pesquisar usu�rio por e-mail
	 * @param email
	 * @return
	 */
	private Usuario pegarUsuarioPorEmail(String email){
		try{
			Query query = manager.createQuery("select u from Usuario as u where u.email = :email");
			query.setParameter("email", email);
			Usuario encontrado = (Usuario)query.getSingleResult();
			return encontrado;
		}
		catch(Exception e){
			Logs.warn("[UsuarioJpa]::pegarUsuarioPorEmail::Erro ao tentar pegar usuario por E-MAIL. Exception:");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * M�todo para cadastrar um novo usu�rio
	 */
	@Override
	public String cadastrar(Usuario usuario) {
		Usuario conflito = pegarUsuarioPorNome(usuario.getNome());
		if(conflito == null){
			conflito = pegarUsuarioPorEmail(usuario.getEmail());
			if(conflito == null){
				manager.persist(usuario);
				Logs.info("[UsuarioJpa]::cadastrar::Usuario cadastrado com exito.");
				return "Usu�rio cadastrado com exito.";
			}
			else{
				Logs.warn("[UsuarioJpa]::cadastrar::Ja existe usuario cadastrado com o e-mail "+usuario.getEmail()+" com ID:"+conflito.getId());
				return "J� existe usu�rio cadastrado com este e-mail.";
			}
		}
		else{
			Logs.warn("[UsuarioJpa]::cadastrar::Ja existe usuario cadastrado com o nome "+usuario.getNome()+" com ID:"+conflito.getId());
			return "J� existe usu�rio cadastrado com este nome.";
		}
	}
	
	/**
	 * M�todo para atualizar um usu�rio.
	 */
	@Override
	public String atualizar(Usuario usuario) {
		Usuario conflito = pegarUsuarioPorNome(usuario.getNome());
		if(conflito == null || usuario.getId() == conflito.getId()){
			conflito = pegarUsuarioPorEmail(usuario.getEmail());
			if(conflito == null || usuario.getId() == conflito.getId()){
				manager.persist(usuario);
				Logs.info("[UsuarioJpa]::atualizar::Usuario atualizado com exito.");
				return "Usu�rio atualizado com exito.";
			}
			else{
				Logs.warn("[UsuarioJpa]::atualizar::Ja existe usuario atualizado com o e-mail "+usuario.getEmail()+" com ID:"+conflito.getId());
				return "J� existe usu�rio atualizado com este e-mail.";
			}
		}
		else{
			Logs.warn("[UsuarioJpa]::atualizar::Ja existe usuario atualizado com o nome "+usuario.getNome()+" com ID:"+conflito.getId());
			return "J� existe usu�rio atualizado com este nome.";
		}
	}

	/**
	 * M�todo para remover um usu�rio.
	 */
	@Override
	public String remover(long id) {
		try{
			manager.remove(pegarUsuarioPorId(id));
			Logs.info("[UsuarioJpa]::remover::Usuario removido com exito.");
			return "Usu�rio removido com �xito.";
		}
		catch(Exception e){
			Logs.info("[UsuarioJpa]::remover::Falha tentando remover usu�rio:");
			e.printStackTrace();
			return "Erro ao tentar remover usu�rio.";
		}
		
	}
	
	@Override
	public List<Usuario> listar() {
		Query query = manager.createQuery("select u from Usuario as u");
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		return lista;
	}

	/**
	 * Metodo de login de usuario
	 */
	@Override
	public Login logar(String nomeOuEmail, String senha) {
		Login login = new Login();
		Usuario cadastrado = null;
		
		if(nomeOuEmail.contains("@")){
			cadastrado = pegarUsuarioPorEmail(nomeOuEmail);
			if(cadastrado != null){
				if(cadastrado.getSenha().equals(senha)){
					login.setLogado(cadastrado);
					login.setMensagem("Seja bem vindo, "+cadastrado.getNome());
					Logs.info("[UsuarioJpa]::logar::Seja bem vindo, "+cadastrado.getNome());
				}
				else{
					login.setLogado(null);
					login.setMensagem("Senha incorreta.");
					Logs.warn("[UsuarioJpa]::logar::Senha de usuario incorreta para o usuario "+nomeOuEmail);
				}
			}
			else{
				login.setLogado(null);
				login.setMensagem("N�o existe usu�rio com esse e-mail.");
				Logs.warn("[UsuarioJpa]::logar::Nao existe usuario cadastrado com o e-mail "+nomeOuEmail);
			}
		}
		else{
			cadastrado = pegarUsuarioPorNome(nomeOuEmail);
			if(cadastrado != null){
				if(cadastrado.getSenha().equals(senha)){
					login.setLogado(cadastrado);
					login.setMensagem("Seja bem vindo, "+cadastrado.getNome());
					Logs.info("[UsuarioJpa]::logar::Seja bem vindo, "+cadastrado.getNome());
				}
				else{
					login.setLogado(null);
					login.setMensagem("Senha incorreta.");
					Logs.warn("[UsuarioJpa]::logar::Senha de usuario incorreta para o usuario "+nomeOuEmail);
				}
			}
			else{
				login.setLogado(null);
				login.setMensagem("N�o existe usu�rio com esse nome.");
				Logs.warn("[UsuarioJpa]::logar::Nao existe usuario cadastrado com o nome "+nomeOuEmail);
			}
		}
		
		return login;
	}

	/**
	 * M�todo usado para criar o primeiro usu�rio do banco de dados.
	 */
	@Override
	public void primeiroAcesso() {
		try{
			Usuario primeiroUsuario = new Usuario();
			primeiroUsuario.setNome("Administrador");
			primeiroUsuario.setEmail("professordouglas.filho@gmail.com");
			primeiroUsuario.setSenha("adm");
			
			cadastrar(primeiroUsuario);
		}
		catch(Exception e){
			Logs.warn("[UsuarioJpa]::primeiroAcesso::Erro ao tentar gravar primeiro usuario no banco de dados.");
			e.printStackTrace();
		}
	}

}