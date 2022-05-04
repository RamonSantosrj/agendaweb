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

		//mapear as rotas que não precisam de autenticação
		List<String> rotasPermitidas = new ArrayList<String>();
		
		rotasPermitidas.add("/");
		rotasPermitidas.add("/register");
		rotasPermitidas.add("/password");		
		rotasPermitidas.add("/autenticar-usuario");
		rotasPermitidas.add("/cadastrar-usuario");
		rotasPermitidas.add("/recuperar-senha");
	
		//capturar a rota (URL) em que o usuário está navegando
		String rotaAtual = req.getServletPath();
		
		//verificar se a rota atual não é uma das permitidas
		if( ! rotasPermitidas.contains(rotaAtual)) {
			
			//verificar se não há um usuário em sessão
			if(req.getSession().getAttribute("usuario_auth") == null) {
				
				//redirecionar para a página de login do sistema
				resp.sendRedirect("/agendaweb/");
			}			
		}		
		
		//finalizar o método do filter
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
