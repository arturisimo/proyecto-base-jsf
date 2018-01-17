package org.apz.xxx.back.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apz.xxx.back.dao.PermisosDao;
import org.apz.xxx.back.dao.RolDao;
import org.apz.xxx.back.dao.UsuariosDao;
import org.apz.xxx.back.domain.PermisoBean;
import org.apz.xxx.back.domain.RolBean;
import org.apz.xxx.back.service.RolService;
import org.apz.xxx.back.service.PermisoService;
/**
 * 
 * 
 * Esta clase actua como un servicio (BO) para realizar todas las tareas desde el controller
 * para Adminstracion -> Roles
 */
@Service
public class RolesServiceImpl implements RolService {	  
	
	protected static Logger logger = Logger.getLogger(RolesServiceImpl.class);
	
	@Autowired
	RolDao rolDao;
	
	@Autowired
	UsuariosDao usuariosDao;
	
	@Autowired
	PermisosDao permDao;
	
	@Autowired
	PermisoService serv_perm;
	
	
	@Override
	public List<RolBean> getListadoRoles(RolBean bRol) {
		return rolDao.getAllRoles(bRol);
	}

	@Override
	public Map<String, Object> guardarRol(Map<String, Object> parametros) {
		return null;
		/*
		String nombre = (String) parametros.get("nombre");
		String descripcion = (String) parametros.get("descripcion");
		
		RolBean bRol = new RolBean();
		
		bRol.setNombre(nombre);
		bRol.setDescripcion(descripcion);
		bRol.setList_id_permisos((String[])parametros.get("arrayPermisos"));
		
		long id_result = rolDao.insertRol(bRol);

		if (id_result != -1){
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			for (int i = 0; i < bRol.getList_id_permisos().length; i++) {
    			mapa.put("idrol", bRol.getId());
            	mapa.put("idpermiso", bRol.getList_id_permisos()[i]);
    			rolDao.insertRolPermiso(mapa);
			}
			
			parametros.put("id_result", bRol.getId());
			
		}
		
		return parametros;
		*/
	}

	@Override
	public HashMap<String, Object> getDetalleRol(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		RolBean bRol = rolDao.getRolById(id);
		
		List<PermisoBean> arbolPermisos = serv_perm.getPermisosParaDetalle(bRol.getPermisos());
		  
	    Map<Long,Long> nodosRaices = new HashMap<Long,Long>();
	    Iterator<PermisoBean> it = arbolPermisos.iterator();
	    while(it.hasNext()){
		    PermisoBean b = it.next();
		    nodosRaices.put(b.getId(), b.getId());
	    } 

	    parametros.put("bRol", bRol);
	   
	    parametros.put("nodosRaices", nodosRaices);
	    parametros.put("arbolPermisos", arbolPermisos);
		
		return parametros;
	}

	@Override
	public HashMap<String, Object> getEdicionRol(Long id) {
		
		RolBean bRol = rolDao.getRolById(id);
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		PermisoBean bPermiso = new PermisoBean();
		List<PermisoBean> listaPermisos = permDao.getAllPermisos(bPermiso);
		
		parametros.put("bRol", bRol);
		
		List<PermisoBean> arbolPermisos = serv_perm.getArbolPermisos(listaPermisos);
		
		parametros.put("arbolPermisos", arbolPermisos);
		parametros.put("listaPermisos", listaPermisos);	
		
		return parametros;
	}

	@Override
	public Map<String, Object> getAltaRol() {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		PermisoBean bPermiso = new PermisoBean();
		List<PermisoBean> list_permisos = permDao.getAllPermisos(bPermiso);
		
		List<PermisoBean> arbol_permisos = serv_perm.getArbolPermisos(list_permisos);
		
		parametros.put("arbolPermisos", arbol_permisos);
		parametros.put("listaPermisos", list_permisos);
		
		
		return parametros;
	}

	@Override
	public Map<String, Object> modificarRol(Map<String, Object> parametros) {
		return null;
		/*
		String idRol = (String) parametros.get("id");
		String nombre = (String) parametros.get("nombre");
		String descripcion = (String) parametros.get("descripcion");
		
		RolBean bRol = rolDao.getRolById(Long.parseLong(idRol));
		
		long id_result = -1;
		
		bRol.setNombre(nombre);
		bRol.setDescripcion(descripcion);
			
		id_result = rolDao.updateRol(bRol);
		
		
		if (id_result != -1){
			
			//Eliminacion de las relaciones antiguas con los permisos
    		rolDao.deleteRolPermisos(bRol.getId());
			
			Map<String, Object> mapa = new HashMap<String, Object>();
			for (int i = 0; i < bRol.getList_id_permisos().length; i++) {
    			mapa.put("idrol", bRol.getId());
            	mapa.put("idpermiso", bRol.getList_id_permisos()[i]);
    			rolDao.insertRolPermiso(mapa);
			}
			
			parametros.put("id_result", bRol.getId());
			
		}
		
		return parametros;
		*/
	}

	@Override
	public void eliminarRol(Long id) {
		
		//Eliminacion de las relaciones antiguas con los permisos
		rolDao.deleteRolPermisos(id);
		
		rolDao.deleteRol(id);
		
	}

}