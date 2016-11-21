package gcom.gui.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gcom.util.GerenciadorSSO;

public class FiltroSSO extends HttpServlet implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		GerenciadorSSO sso = new GerenciadorSSO((HttpServletRequest) request);
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("urlPortal", sso.getUrlPortal());
		
		if (sso.isLogado()) {
			chain.doFilter(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
