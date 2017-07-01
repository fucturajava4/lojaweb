package br.recife.fucturajava4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.recife.fucturajava4.model.Usuario;
import br.recife.fucturajava4.utils.Logs;

/**
 * Interceptador de requisi��es mapeado no arquivo src/main/java/webapp/WEB-INF/spring-context.xml
 * � o primeiro recurso a ser acessado no momento de uma requisi��o, serve para filtrar qual a��o tomar durante a request
 * e o que deve ser entregue ao usu�rio ap�s essa requisi��o.
 * 
 * Estende a classe HandlerInterceptorAdapter a qual possui o m�todo preHandle que � respons�vel por manipular a entrega
 * da requisi��o.
 * 
 * O fluxo natural � que o usu�rio fa�a uma requisi��o (HttpServletRequest request) e 
 * obtenha uma resposta (HttpServletResponse response)
 * 
 * O interceptador retorna "true" se deve prosseguir e entregar a solicita��o ao Controller ou retorna "false" caso
 * seja ncess�rio um bloqueio.
 * 
 * @author douglas.f.filho
 *
 */
public class MainInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		//Obtendo a url da solicita��o
		String url = request.getRequestURI();
		
		//Logando a url no console do servidor e no arquivo de logs
		Logs.info("[MainInterceptor]::preHandle::URL("+url+")");
				
		if(url.endsWith("logar") || url.endsWith("login") || url.contains("resources/"))
			return true;
		
		HttpSession session = request.getSession();
		Usuario logado = (Usuario)session.getAttribute("logado");
		
		if(logado == null){
			response.sendRedirect("login");
			return false;
		}
		
		//Continua para o controlador (br.recife.fucturajava4.controller.SiteController)
		return true;
	}
}
