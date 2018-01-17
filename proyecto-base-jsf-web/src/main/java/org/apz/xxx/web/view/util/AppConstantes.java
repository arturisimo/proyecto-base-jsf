package org.apz.xxx.web.view.util;

import javax.faces.application.ResourceHandler;

public class AppConstantes {
	
	
	/** sesion */
	public static final String SESION_VIEW = "sesionView";
	
	/**
	 * Nombre del ResourceBundle utilizado para el acceso a los mensajes en
	 * UtilBean.
	 */
	public static final String RESOURCEBOUNDLE_NAME = "MensajeApp";
	
	
	/**
	 * Pagina raiz
	 */
	public static final String RAIZ = "/";
	
	/** filtro paginas error	 */
	public static final String FILTRO_ERRORES = "/pages/error/";
	
	/** filtro paginas 	 */
	public static final String FILTRO_XHTML = "/pages/";
	
	/**
	 * Rutas utilizadas en el filtro del control de la sesión para identificar
	 * paginas clave a las que siempre se debe permitir el acceso
	 * independientemente de la sesión.
	 *
	 * Los valores de las paginas deben coincidir con la etiqueta <b>pattern</b>
	 * de la pagina asociada en el fichero pretty-config.xml
	 */
	public static enum FILTRO_SESION_COMODINES {
		PAGINA_SESION_CADUCADA("/sesion-caducada"),
		RECURSO_JSF(ResourceHandler.RESOURCE_IDENTIFIER),
		JSESSION_ID("/;jsessionid"),
		RECURSO_JSF_FONTS("/fonts");

		private final String path;

		private FILTRO_SESION_COMODINES(String path) {
			this.path = path;
		}

		@Override
		public String toString() {
			return this.path;
		}
	}
	
	
	/**
	 * Pantallas de mensajes
	 */
	public static final String TITULO_WARNING = "mensajes.titulo.atencion";
	public static final String TITULO_ERROR = "mensajes.titulo.error";
	public static final String TITULO_INFORMACION = "mensajes.titulo.informacion";
	
	public static final String ALERTA_DNI_INCORRECTO = "mensajes.error.dni";
	public static final String ALERTA_DNI_DC = "mensajes.error.dc";
	
	
	/**
	 * Pantalla de login
	 */
	public static final String ALERTA_CONTRASENIA_INCORRECTA = "loginContraseniaIncorrecta";
	public static final String ALERTA_USUARIO_NO_REGISTRADO = "loginUsuarioNoRegistrado";
	public static final String ALERTA_USUARIO_ROL = "rolUsuario";
	
}
