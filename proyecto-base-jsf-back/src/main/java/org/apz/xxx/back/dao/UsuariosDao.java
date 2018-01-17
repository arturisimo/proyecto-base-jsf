package org.apz.xxx.back.dao;

import java.util.HashMap;
import java.util.List;

import org.apz.xxx.back.domain.UsuarioBean;

public interface UsuariosDao {

	UsuarioBean getUsuarioById(Long id);
	List<UsuarioBean> getAllUsuarios(UsuarioBean usuario);
	UsuarioBean getUsuarioByLogin(String name);
	long insertUsuario(UsuarioBean bUsu);
	void insertUsuarioRol(HashMap<String, Object> mapa);
	long updateUsuario(UsuarioBean usuario);
	int getNumUsuariosWithLogin(UsuarioBean usuario);
	void deleteUsuarioRoles(Long id);
	void deleteUsuario(Long id);
	
}
