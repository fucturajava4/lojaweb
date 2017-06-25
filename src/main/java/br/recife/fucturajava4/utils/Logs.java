package br.recife.fucturajava4.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gera logs no console mais facilmente.
 * S�o encontrados nos arquivos de log dentro da pasta de logs do tomcat, por exemplo.
 * @author douglas.f.filho
 *
 */
public class Logs{
	
	private static final String INFO = "info";
	private static final String WARNING = "warning";
	private static final String CONFIG = "config";
	
	
	/**
	 * Loga uma informa��o do sistema.
	 * @param mensagem
	 */
	public static void info(String mensagem){
		Logger logger = Logger.getLogger(INFO);
		logger.log(Level.INFO, mensagem);
	}
	
	/**
	 * Loga um alerta do sistema.
	 * @param mensagem
	 */
	public static void warn(String mensagem){
		Logger logger = Logger.getLogger(WARNING);
		logger.log(Level.WARNING, mensagem);
	}
	
	/**
	 * Loga uma falha do sistema.
	 * @param mensagem
	 */
	public static void debug(String mensagem){
		Logger logger = Logger.getLogger(CONFIG);
		logger.log(Level.CONFIG, mensagem);
	}
}
