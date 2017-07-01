package br.recife.fucturajava4.dao;

import java.util.List;

import br.recife.fucturajava4.model.Login;
import br.recife.fucturajava4.model.Usuario;

public interface UsuarioDao {
	public String cadastrar(Usuario usuario);
	public String atualizar(Usuario usuario);
	public String remover(long id);
	public List<Usuario> listar();
	
	public Login logar(String nomeOuEmail, String senha);
	
	public void primeiroAcesso();
}
