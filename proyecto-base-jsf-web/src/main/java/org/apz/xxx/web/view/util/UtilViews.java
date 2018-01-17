package org.apz.xxx.web.view.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utilidades comunes para todos los Beans
 */
public class UtilViews {

	/**
	 * Obtiene un literal del fichero de propiedades
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 * @return Texto asociado a la clave
	 */
	public static String getMensaje(String clave) {
		final ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), AppConstantes.RESOURCEBOUNDLE_NAME);
		final String msgResult = bundle.getString(clave);
		return msgResult;
	}

	/**
	 * Obtiene un literal del fichero de propiedades
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 * @param paramas
	 *            Listado de parametros a insertar en el literal
	 * @return Texto asociado a la clave
	 */
	public static String getMensaje(String clave, Object... params) {
		final String msgResult = MessageFormat.format(getMensaje(clave), params);
		return msgResult;
	}

	/**
	 * Añade un mensaje de error a la pila de JSF para mostrarlo por pantalla
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 */
	public static void addMensajeError(String clave) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, UtilViews.getMensaje(AppConstantes.TITULO_ERROR), UtilViews.getMensaje(clave)));
	}

	public static void addMessageErrorConID(String clientId, String msgSumary, String msgDetail) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgSumary, msgDetail));
	}

	/**
	 * Añade un mensaje de error a la pila de JSF para mostrarlo por pantalla
	 * Modificado de SEVERITY_ERROR a SEVERITY_WARN, por problemas de
	 * visibilidad del icono
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 * @param paramas
	 *            Listado de parametros a insertar en el literal
	 */
	public static void addMensajeError(String clave, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, UtilViews.getMensaje(AppConstantes.TITULO_ERROR), UtilViews.getMensaje(clave, params)));
	}

	/**
	 * Añade un mensaje de alerta a la pila de JSF para mostrarlo por pantalla
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 */
	public static void addMensajeAlerta(String clave) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, UtilViews.getMensaje(AppConstantes.TITULO_WARNING), UtilViews.getMensaje(clave)));
	}

	/**
	 * Añade un mensaje de alerta a la pila de JSF para mostrarlo por pantalla
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 * @param paramas
	 *            Listado de parametros a insertar en el literal
	 */
	public static void addMensajeAlerta(String clave, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, UtilViews.getMensaje(AppConstantes.TITULO_WARNING), UtilViews.getMensaje(clave, params)));
	}

	/**
	 * Añade un mensaje de informacion a la pila de JSF para mostrarlo por
	 * pantalla
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 */
	public static void addMensajeInformacion(String clave) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, UtilViews.getMensaje(AppConstantes.TITULO_INFORMACION), UtilViews.getMensaje(clave)));
	}

	/**
	 * Añade un mensaje de informacion a la pila de JSF para mostrarlo por
	 * pantalla
	 *
	 * @param clave
	 *            Clave que identifica al literal en el fichero de propiedades
	 * @param paramas
	 *            Listado de parametros a insertar en el literal
	 */
	public static void addMensajeInformacion(String clave, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, UtilViews.getMensaje(AppConstantes.TITULO_INFORMACION), UtilViews.getMensaje(clave, params)));
	}	
	
	
}
