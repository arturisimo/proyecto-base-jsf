package org.apz.xxx.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apz.xxx.web.view.SesionView;
import org.apz.xxx.web.view.util.AppConstantes;


/**
 * Filtro para el control de la sesion
 *
 */
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		// Verifica si se esta intentando acceder a una pagina comodin
		// donde no se requiere de una sesion válida/abierta
		boolean paginaComodin = false;
		for (AppConstantes.FILTRO_SESION_COMODINES filtroSesionComodin : AppConstantes.FILTRO_SESION_COMODINES.values()) {
			if (paginaComodin(filtroSesionComodin.toString(), req)) {
				paginaComodin = true;
				break;
			}
		}
		
		
		// Si el usuario esta intentando acceder a una página no comodín
		boolean peticionValida = true;
		if (!paginaComodin) {
			final StringBuffer urlRedireccion = new StringBuffer(req.getContextPath());
	
			// Comprueba que la sesión este abierta y sea válida
			// En caso contrario se le redirecciona a la página de sesión
			// caducada
			if (req.getRequestedSessionId() != null && !req.isRequestedSessionIdValid()) {
				urlRedireccion.append(AppConstantes.FILTRO_SESION_COMODINES.PAGINA_SESION_CADUCADA.toString());
				res.sendRedirect(urlRedireccion.toString());
				peticionValida = false;
			}
	
			// Si la sesión esta abierta verifica que el usuario este
			// logueado en el portal
			// En caso contrario se le redirecciona a la página raiz
			else {
				if (!usuarioConectado(req)) {
					urlRedireccion.append(AppConstantes.RAIZ);
					res.sendRedirect(urlRedireccion.toString());
					peticionValida = false;
				}
			}
		}

		// Si se esta intentando acceder a una pagina comodin o si la sesión
		// es correcta permite el acceso
		if (peticionValida) {
			// Si se trata de la pantalla de login y ya esta el usuario logueado
			// se le debe redireccionar a la de presentacion, en lugar de
			// permitirle acceder a la de login nuevamente
			if (getPaginaActual(req).equals(AppConstantes.RAIZ) && usuarioConectado(req)) {
				String urlPresentacion = req.getContextPath() + "/presentacion";
				res.sendRedirect(urlPresentacion.toString());
				peticionValida = false;
			}
			// Para cualquier otra pagina se permite el acceso
			else {
				filterChain.doFilter(request, response);
			}
		}
	}
	
	/**
	 * Verifica si la ruta a la que se está intentando acceder es una ruta que
	 * no requiere de sesión y por lo tanto se puede acceder a ella
	 *
	 * @param path
	 *            URL a la que se quiere acceder
	 * @param request
	 *            Request
	 * @return
	 */
	private static boolean paginaComodin(String path, HttpServletRequest request) {
		final String requestPath = getPaginaActual(request);
		return requestPath != null && (requestPath.equals(AppConstantes.RAIZ) || (requestPath.startsWith(path)));
	}
	
	/**
	 * Obtiene la pagina que se esta solicitando
	 *
	 * @param request
	 *            Request
	 * @return
	 */
	private static String getPaginaActual(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	/**
	 * Verifica si el usuario está actualmente conectado
	 *
	 * @param request
	 *            Request
	 * @return
	 */
	private static boolean usuarioConectado(HttpServletRequest request) {
		SesionView sesionView = (SesionView) request.getSession().getAttribute(AppConstantes.SESION_VIEW);
		if (sesionView == null || sesionView.getUsuario() == null) {
			return false;
		}

		return true;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// No se usa
	}

	@Override
	public void destroy() {
		// No se usa
	}
}