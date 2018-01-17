package org.apz.xxx.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apz.xxx.back.domain.UsuarioBean;

public interface UsuarioService {
	
	/**
	 * Sacar listado usuarios
	 * @param bUsuario
	 * @return
	 */
	List<UsuarioBean> 		getListadoUsuarios(UsuarioBean bUsuario);
	/**
	 * Sacar usuario por id
	 * @param id
	 * @return
	 */
	UsuarioBean 				getUsuarioById(Long id);
	/**
	 * Sacar detalle usuario
	 * @param id
	 * @return
	 */
	HashMap<String, Object> 	getDetalleUsuario(Long id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	HashMap<String, Object> 	getEdicionUsuario(Long id);
	/**
	 * 
	 * @return
	 */
	HashMap<String, Object> 	getAltaUsuario();
	/**
	 * Insertar usuario
	 * @param parametros
	 * @return
	 */
	Map<String, Object> 		guardarUsuario(Map<String, Object> parametros);
	
	void eliminarUsuario(Long id);
	
	Map<String, Object> 		modificarUsuario(Map<String, Object> parametros);
	
	
	UsuarioBean getUsuarioByLogin(String login);


	
}
