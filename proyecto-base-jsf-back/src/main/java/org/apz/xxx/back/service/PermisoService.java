package org.apz.xxx.back.service;

import java.util.List;

import org.apz.xxx.back.domain.PermisoBean;

public interface PermisoService {
	
	/**
	 * Sacar listado de permisos
	 * @param bPermiso
	 * @return
	 */
	List<PermisoBean> getListadoPermisos(PermisoBean permiso);
	
	/**
	 * Obtner arbol de permisos
	 * @param list_permisos
	 * @return
	 */
	List<PermisoBean> getArbolPermisos(List<PermisoBean> permisos);
	
	/**
	 * Sacar permisos para el detalle de un rol, grupo o usuario
	 * @param list_permisos
	 * @return
	 */
	List<PermisoBean> getPermisosParaDetalle(List<PermisoBean> permisos);

}
