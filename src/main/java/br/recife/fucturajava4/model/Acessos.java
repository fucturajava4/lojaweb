package br.recife.fucturajava4.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "acessos")
public class Acessos {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	@OneToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Acessos [id=" + id + ", data=" + data + ", usuario=" + usuario + "]";
	}
}
