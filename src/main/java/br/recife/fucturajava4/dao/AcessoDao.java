package br.recife.fucturajava4.dao;

import java.util.List;

import br.recife.fucturajava4.model.Acesso;

public interface AcessoDao {
	public void addAcesso(Acesso acesso);
	public List<Acesso> listar();
}
