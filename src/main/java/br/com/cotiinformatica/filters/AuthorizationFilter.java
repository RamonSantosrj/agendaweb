package br.com.cotiinformatica.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//Converter os tipos dos objetos 'request' e 'response'
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//mapear as rotas que n�o precisam de autentica��o
		List<String> rotasPermitidas = new ArrayList<String>();
		
		rotasPermitidas.add("/");
		rotasPermitidas.add("/register");
		rotasPermitidas.add("/password");		
		rotasPermitidas.add("/autenticar-usuario");
		rotasPermitidas.add("/cadastrar-usuario");
		rotasPermitidas.add("/recuperar-senha");
	
		//capturar a rota (URL) em que o usu�rio est� navegando
		String rotaAtual = req.getServletPath();
		
		//verificar se a rota atual n�o � uma das permitidas
		if( ! rotasPermitidas.contains(rotaAtual)) {
			
			//verificar se n�o h� um usu�rio em sess�o
			if(req.getSession().getAttribute("usuario_auth") == null) {
				
				//redirecionar para a p�gina de login do sistema
				resp.sendRedirect("/agendaweb/");
			}			
		}		
		
		//finalizar o m�todo do filter
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
