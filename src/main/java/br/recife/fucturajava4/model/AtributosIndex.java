package br.recife.fucturajava4.model;

public class AtributosIndex {
	private String titulo;
	private String mensagem;
	private String autor;
	private String data;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "AtributosIndex [titulo=" + titulo + ", mensagem=" + mensagem + ", autor=" + autor + ", data=" + data
				+ "]";
	}
}
