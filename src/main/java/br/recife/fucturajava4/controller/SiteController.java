package br.recife.fucturajava4.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.recife.fucturajava4.dao.UsuarioDao;
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
	
}