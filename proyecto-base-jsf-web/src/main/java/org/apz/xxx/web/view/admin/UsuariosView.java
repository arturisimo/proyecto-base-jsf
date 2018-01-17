package org.apz.xxx.web.view.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.apz.xxx.back.domain.UsuarioBean;
import org.apz.xxx.web.facade.WebFacade;
import org.apz.xxx.web.view.util.AppConstantes;
import org.apz.xxx.web.view.util.UtilViews;

/**
 * 
 *
 */
@Component("usuariosView")
@Scope
public class UsuariosView implements Serializable {

	/**  */
	private static final long serialVersionUID = -8935177500738247305L;

	/** El Logger para las trazas **/
	private static final Logger LOGGER = Logger.getLogger(UsuariosView.class);
	
	/** fachada para la consulta a BBDD	 */
	@Autowired
	private WebFacade webFacade;
	
	/** lista de usuario */
	private List<UsuarioBean> usuarios;
	
	private UsuarioBean usuario;
	
	
	/** listado de usuarios */
	public void init() {

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		try {
			usuarios = webFacade.getUsuarios();
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			FacesMessage exception = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage("formUsuarios", exception);
		}

	}
	
	/**
	 * Actualizacion de usuario
	 */
	public void edit() {
		
		try {
			LOGGER.info("modificacion de " + usuario.getNombre());
			
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
			UtilViews.addMensajeInformacion("proceso_editar_msj", usuario.getNombre());	
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilViews.addMensajeAlerta(AppConstantes.TITULO_ERROR);
		}
	}
	
	public void alta() {
		this.usuario =  new UsuarioBean();
	}

	/**
	 * carga los valores para el formulario de edicion usuarios
	 * @param matricula
	 */
	public void mostrarValores(UsuarioBean usuario) {
		try {
			this.usuario = usuario;
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilViews.addMensajeAlerta(AppConstantes.TITULO_ERROR);
		} 
		
	}
	
	/**
	 * carga los valores para el formulario de edicion usuarios
	 * @param matricula
	 */
	public void eliminar(UsuarioBean usuario) {
		try {
			webFacade.eliminarUsuario(usuario.getId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilViews.addMensajeAlerta(AppConstantes.TITULO_ERROR);
		} 
		
	}

	/**
	 * Lista de usuarios
	 * @return lista de procesos
	 */
	public List<UsuarioBean> getUsuarios() {
		return usuarios;
	}
	
	public UsuarioBean getUsuario() {
		return usuario;
	}

	
}