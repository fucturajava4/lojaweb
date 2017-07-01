package br.recife.fucturajava4.model;

/**
 * Classe usada como retorno após tentativa de login.
 * @author douglas.f.filho
 *
 */
public class Login {
	private String mensagem;
	private Usuario logado;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Usuario getLogado() {
		return logado;
	}
	public void setLogado(Usuario logado) {
		this.logado = logado;
	}
	
	@Override
	public String toString() {
		return "Login [mensagem=" + mensagem + ", logado=" + logado + "]";
	}
}
