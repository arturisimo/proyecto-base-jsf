package org.apz.xxx.web.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.apz.xxx.back.domain.UsuarioBean;
import org.apz.xxx.web.facade.WebFacade;
import org.apz.xxx.web.view.util.AppConstantes;
import org.apz.xxx.web.view.util.UtilViews;

/**
 * Bean para el control de la autenticacion
 *
 */
@Component("loginView")
@Scope("request")
public class LoginView implements Serializable {

	private static final long serialVersionUID = -2734627240599796453L;

	@Autowired
	private SesionView sesionView;

	@Autowired
	private WebFacade webFacade;
	
	FacesMessage feedback;

	private final Log logger = LogFactory.getLog(LoginView.class);

	private String codigoUsuario;
	private String contrasenia;

	/**
	 * Verifica que los credenciales son correctos y autentica al usuario
	 *
	 * @return
	 */
	public String conectar() {
		
		try {
			
			// Se comprueba si el usuario está registrado en el sistema
			UsuarioBean usuario = this.webFacade.getUsuario(codigoUsuario);
			
			if (usuario == null) {
				throw new Exception("Usuario no registrado en BBDD");
			}
			
			logger.info(codigoUsuario + "tiene permisos para acceder");
			
			//Parametro del environment.properties para activar desactivar el login contra ldap
			//en produccion este parametro "login.ldap.validar" siempre debe ir a true
			//en local lo ponemos a false para que no valide contra ldap.
			PropertiesConfiguration environment = new PropertiesConfiguration("environment.properties");
			
			Boolean validarLDAP = environment.getBoolean("login.ldap.validar");
					
			if (validarLDAP) {
				
			} else {
				logger.info("no se valida el usuario contra ldap");
			}
	
			// Guarda en sesion los datos del usuario
			sesionView.conectar(usuario);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, UtilViews.getMensaje(AppConstantes.TITULO_ERROR), e.getMessage()));
			return null;
		}

		this.logger.debug("Se ha conectado el usuario: " + this.codigoUsuario);
		return "pretty:presentacion";
	}
	
	
	/**
	 * Obtiene el codigo del usuario utilizado en el formulario de entrada
	 *
	 * @return
	 */
	public String getCodigoUsuario() {
		return this.codigoUsuario;
	}

	/**
	 * Establece el codigo del usuario utilizado en el formulario de entrada
	 *
	 * @return
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * Obtiene la contraseña del usuario utilizado en el formulario de entrada
	 *
	 * @return
	 */
	public String getContrasenia() {
		return this.contrasenia;
	}

	/**
	 * Establece la contraseña del usuario utilizado en el formulario de entrada
	 *
	 * @return
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}
