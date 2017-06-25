package br.recife.fucturajava4.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de recebimento(requests) e entrega(responses)
 * @author douglas.f.filho
 *
 */
//Est� mapeado em /src/main/webapp/WEB-INF/spring-context.xml
@Controller //Anotado como controlador de requisi��es.
public class SiteController {
	
	/**
	 * M�todo que usado como primeira url do site ou quando � chamado "/home"
	 * @return
	 */
	@RequestMapping(value={"/","home"})
	public String home(Model model){
		String titulo = "Loja WEB";
		String mensagem = "Seja bem vindo � nossa loja!";
		String autor = "Turma Java4 - Fuctura";
		
		Calendar hoje = Calendar.getInstance(new Locale("pt","BR"));
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data = fmt.format(hoje.getTime());
		
		model.addAttribute("titulo", titulo);
		model.addAttribute("mensagem", mensagem);
		model.addAttribute("autor", autor);
		model.addAttribute("data", data);
		
		//Retorna a p�gina src/main/webapp/WEB-INF/views/index.jsp
		return "index";
	}
	
}