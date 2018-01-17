package org.apz.xxx.back.service;

import java.util.List;
import java.util.Map;

import org.apz.xxx.back.domain.RolBean;

public interface RolService {
	
	/**
	 * Sacar listado de roles
	 * @param bRol
	 * @return
	 */
	List<RolBean> 			getListadoRoles(RolBean bRol);
	
	/**
	 * Guardar Rol
	 * @param parametros
	 * @return
	 */
	Map<String, Object> 		guardarRol(Map<String, Object> parametros);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> 	getDetalleRol(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> 	getEdicionRol(Long id);
	
	/**
	 * 
	 * @return
	 */
	Map<String, Object> 	getAltaRol();
	
	/**
	 * Modificar datos del rol
	 * @param parametros
	 * @return
	 */
	Map<String, Object> 		modificarRol(Map<String, Object> parametros);
	
	/**
	 * Eliminar rol
	 * @param id
	 */
	void 					eliminarRol(Long id);

	
	
}
