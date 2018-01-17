package org.apz.xxx.web.facade;

import java.util.List;

import org.apz.xxx.back.domain.UsuarioBean;

public interface WebFacade {
	
	/**
	 * @param uidLDAP
	 * @param rol
	 * @return
	 * @throws Exception
	 */
	UsuarioBean getUsuario(String login) throws Exception;


	List<UsuarioBean> getUsuarios() throws Exception;


	void eliminarUsuario(Long id) throws Exception;
	
}
