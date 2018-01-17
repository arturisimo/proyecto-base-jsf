package org.apz.xxx.back.dao;

import java.util.HashMap;
import java.util.List;

import org.apz.xxx.back.domain.RolBean;

public interface RolDao {

	 List<RolBean> getAllRoles(RolBean rol);
	 long insertRol(RolBean rol);
	 void insertRolPermiso(HashMap<String, Object> mapa);
	 RolBean getRolById(Long id);
	 long updateRol(RolBean rol);
	 void deleteRolPermisos(Long id);
	 void deleteRol(Long id);
	 List<RolBean> getRolesWithPermiso(Long idPermiso);
	
}


