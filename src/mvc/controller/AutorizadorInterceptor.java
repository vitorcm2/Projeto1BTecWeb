package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.endsWith("cadastrado") || uri.endsWith("fazLogin") || uri.endsWith("editar")
				|| uri.endsWith("deletar") || uri.endsWith("adicionar")) {
			return true;
		}
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}
		response.sendRedirect("login");
		return false;
	}
}