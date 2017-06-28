package br.recife.fucturajava4.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.recife.fucturajava4.dao.AcessosDao;
import br.recife.fucturajava4.dao.UsuarioDao;
import br.recife.fucturajava4.model.Acessos;
import br.recife.fucturajava4.model.AtributosIndex;
import br.recife.fucturajava4.model.Usuario;

/**
 * Controlador de recebimento(requests) e entrega(responses)
 * @author douglas.f.filho
 *
 */
//Está mapeado em /src/main/webapp/WEB-INF/spring-context.xml
@Controller //Anotado como controlador de requisições.
@Transactional //Anotado como gerente de ttransações
public class SiteController {
	private static String mensagem = "";
	
	
	@Autowired
	@Qualifier("usuarioJpa")
	private UsuarioDao tabelaUsuario;
	
	@Autowired
	@Qualifier("acessosJpa")
	private AcessosDao tabelaAcessos;
	
	/**
	 * Método que usado como primeira url do site ou quando é chamado "/home"
	 * @return
	 */
	@RequestMapping(value={"/","home"})
	public String home(Model model){
		Calendar hoje = Calendar.getInstance(new Locale("pt","BR"));
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data = fmt.format(hoje.getTime());
		
		AtributosIndex attr = new AtributosIndex();
		attr.setTitulo("Loja WEB");
		attr.setMensagem("Seja bem vindo à nossa loja!");
		attr.setAutor("Turma Java4 - Fuctura");
		attr.setData(data);
		
		model.addAttribute("atributos", attr);
		
		if(mensagem != null && !mensagem.equals("")){
			model.addAttribute("mensagem", mensagem);
			mensagem = "";
		}
		
		List<Usuario> lista_de_usuarios = tabelaUsuario.listar();
		model.addAttribute("lista_de_usuarios",lista_de_usuarios);
		
		//Retorna a página src/main/webapp/WEB-INF/views/index.jsp
		return "index";
	}
	
	@RequestMapping("adicionarUsuario")
	public String adicionarUsuario(Usuario usuario, Model model){
		mensagem = tabelaUsuario.cadastrar(usuario);
		return "redirect:home";
	}
	
	@RequestMapping("atualizarUsuario")
	public String atualizarUsuario(Usuario usuario, Model model){
		mensagem = tabelaUsuario.atualizar(usuario);
		return "redirect:home";
	}
	
	@RequestMapping("removerUsuario")
	public String removerUsuario(long id, Model model){
		tabelaUsuario.remover(id);
		return "redirect:home";
	}
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("logar")
	public String login(String email, String senha, HttpSession session){
		
		Usuario usuario = tabelaUsuario.logar(email, senha);
		session.setAttribute("logado", usuario);
		
		if(usuario != null){
			Acessos acesso = new Acessos();
			acesso.setUsuario(usuario);
			acesso.setData(Calendar.getInstance(new Locale("pt","BR")));
			
			tabelaAcessos.setAcesso(acesso);
		}
		
		return "redirect:home";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
}