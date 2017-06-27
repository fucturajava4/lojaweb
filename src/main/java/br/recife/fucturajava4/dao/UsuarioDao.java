package br.recife.fucturajava4.dao;

import java.util.List;

import br.recife.fucturajava4.model.Usuario;

public interface UsuarioDao {
	public void cadastrar(Usuario usuario);
	public void atualizar(Usuario usuario);
	public void remover(long id);
	public List<Usuario> listar();
}
