package org.apz.xxx.web.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.apz.xxx.back.domain.UsuarioBean;
import org.apz.xxx.web.view.util.AppConstantes;


/**
 * Bean para el manejo de datos en la sesion
 *
 */
@Component(AppConstantes.SESION_VIEW)
@Scope("session")
public class SesionView implements Serializable {

	private static final long serialVersionUID = 2377069254997602937L;

	private final Log logger = LogFactory.getLog(SesionView.class);

	/**
	 * Usuario conectado al portal
	 */
	private UsuarioBean usuario;

	/**
	 * Se utiliza para verificar si se debe renderizar el menu horizontal o no.
	 * Comprueba que el usuario este dado de alta en la sesión.
	 *
	 * @return
	 */
	@SuppressWarnings("static-method")
	public boolean isActivo() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		if (session.getId() == null) {
			return false;
		}
		return true;
	}

	
	/**
	 * Almacena los datos del usuario en la sesión
	 *
	 * @param usuario_
	 *            Usuario que se esta conectando al portal
	 */
	public void conectar(UsuarioBean usuario) {
		// Almacena en sesion el usuario
		this.usuario = usuario;

	}

	/**
	 * Realiza la desconexion de la sesion
	 */
	@SuppressWarnings("static-method")
	public String desconectar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "pretty:raiz";
	}

	/**
	 *
	 * @param faces
	 *            Context de JSF
	 * @param ruta
	 *            Ruta a la que se quiere redireccionar
	 */
	public void navegarHttps(FacesContext faces, String ruta) {
		final ExternalContext context = faces.getExternalContext();
		try {
			context.dispatch(ruta);
		} catch (IOException e) {
			this.logger.error("IOException" + e.getMessage());
		}
	}

	
	/**
	 * Obtiene el usuario conectado en la web
	 *
	 * @return
	 */
	public UsuarioBean getUsuario() {
		return this.usuario;
	}

	/**
	 * Establece el usuario conectado en la web
	 *
	 * @return
	 */
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	
}
