package br.recife.fucturajava4.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.recife.fucturajava4.dao.AcessoDao;
import br.recife.fucturajava4.dao.UsuarioDao;
import br.recife.fucturajava4.model.Acesso;
import br.recife.fucturajava4.model.Login;
import br.recife.fucturajava4.model.Usuario;
import br.recife.fucturajava4.reports.Relatorios;

/**
 * Controlador de recebimento(requests) e entrega(responses)
 * @author douglas.f.filho
 *
 */
//Est� mapeado em /src/main/webapp/WEB-INF/spring-context.xml
@Controller //Anotado como controlador de requisi��es.
@Transactional //Anotado como gerente de ttransa��es
public class SiteController {
	//Atributo usado para troca de mensagens entre solicita��es e p�ginas
	private static String mensagem = "";
	
	//Objeto usado para manipular a tabela de usuarios conforme suas regras
	@Autowired
	@Qualifier("usuarioJpa")
	private UsuarioDao tabelaDeUsuarios;
	
	//Objeto usado para manipular a tabela de acessos conforme suas regras
	@Autowired
	@Qualifier("acessoJpa")
	private AcessoDao tabelaDeAcessos;
	
	/**
	 * M�todo que � usado como primeira url do site ou quando � chamado "/home"
	 * retorna a primeira p�gina a ser exibida pelo sistema.
	 * @return
	 */
	@RequestMapping(value={"/","home"})
	public String home(Model model){
		//Carregar data e hora atual como String
		Calendar hoje = Calendar.getInstance(new Locale("pt","BR"));
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data = fmt.format(hoje.getTime());
		
		//Adicionar atributos � p�gina que ser� exibida
		model.addAttribute("titulo", "Loja WEB");
		model.addAttribute("texto", "Seja bem vindo � nossa loja!");
		model.addAttribute("autor", "Turma Java4 - Fuctura");
		model.addAttribute("data", data);
		
		//Adiciona mensagem, caso exista, � p�gina que ser� exibida
		model.addAttribute("mensagem", mensagem);
		mensagem = "";
		
		//Retornar a p�gina src/main/webapp/WEB-INF/views/index.jsp
		return "index";
	}
	
	/**
	 * Mostra a p�gina de usu�rios
	 * @param model
	 * @return
	 */
	@RequestMapping("usuarios")
	public String usuarios(Model model){
		List<Usuario> usuarios = tabelaDeUsuarios.listar();
		model.addAttribute("usuarios", usuarios);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = "";
		
		return "usuarios";
	}
	
	/**
	 * M�todo usado para cadastrar um usu�rio
	 * @param usuario = Objeto do tipo Usuario que � construido a partir dos &lt;input&gt; do formul�rio.
	 * Usa-se o atributo "name" dos inputs para relacionar com os atributos da classe Usuario
	 * @param model = auxiliar de renderiza��o da p�gina. � instanciado pelo SpringFramework
	 * @return
	 */
	@RequestMapping("adicionarUsuario")
	public String adicionarUsuario(Usuario usuario, Model model){
		mensagem = tabelaDeUsuarios.cadastrar(usuario);
		return "redirect:usuarios";
	}
	
	/**
	 * M�todo usado para atualizar um usu�rio
	 * @param usuario = Objeto do tipo Usuario que � construido a partir dos &lt;input&gt; do formul�rio.
	 * Usa-se o atributo "name" dos inputs para relacionar com os atributos da classe Usuario
	 * @param model = auxiliar de renderiza��o da p�gina. � instanciado pelo SpringFramework
	 * @return
	 */
	@RequestMapping("atualizarUsuario")
	public String atualizarUsuario(Usuario usuario, Model model){
		mensagem = tabelaDeUsuarios.atualizar(usuario);
		return "redirect:usuarios";
	}
	
	/**
	 * M�todo que pretende remover um usu�rio do banco de dados
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("removerUsuario")
	public String removerUsuario(long id, Model model){
		mensagem = tabelaDeUsuarios.remover(id);
		return "redirect:usuarios";
	}
	
	/**
	 * Mostrar a p�gina de login
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model){
		tabelaDeUsuarios.primeiroAcesso();
		
		model.addAttribute("mensagem", mensagem);
		mensagem = "";
		return "login";
	}
	
	/**
	 * Tentativa de logar um usu�rio no sistema
	 * @param nomeOuEmail = Nome ou e-mail obtido de um input do formul�rio de login
	 * @param senha = Senha obtida de um input do formul�rio de login
	 * @param session = Sees�o do sistema para o usu�rio que est� tentando logar, instanciada pelo SpringFramework automaticamente
	 * @return
	 */
	@RequestMapping("logar")
	public String logar(String nomeOuEmail, String senha, HttpSession session){
		
		Login login = tabelaDeUsuarios.logar(nomeOuEmail, senha);
		mensagem = login.getMensagem();
		Usuario logado = login.getLogado();
		
		session.setAttribute("logado", logado);
		
		if(logado != null){
			Acesso acesso = new Acesso();
			acesso.setUsuario(logado);
			acesso.setData(Calendar.getInstance(new Locale("pt","BR")));
			
			tabelaDeAcessos.addAcesso(acesso);
		}
		
		return "redirect:home";
	}
	
	/**
	 * Realiza logout do sistema.
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		
		mensagem = "Voc� saiu do sistema.";
		
		return "redirect:login";
	}
	
	/**
	 * Emiss�o de relat�rios usando jasper reports e mostrando o relat�rio em PDF
	 * @param tipo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("relatorio")
	public void mostrarPdf(String tipo, HttpServletResponse response) throws IOException{
		try{
			if(tipo != null && !tipo.equals("") && tipo.equals("acessos")){
				List<Acesso> acessos = tabelaDeAcessos.listar();
				String path = getClass().getResource("/Blank_A4.jrxml").getFile();
				byte[] arquivo = Relatorios.apresentar(path, null, acessos);
				
				response.setContentType("application/pdf");
				response.getOutputStream().write(arquivo);
				response.getOutputStream().close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("redirect:home");
		}
	}
}