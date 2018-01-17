package org.apz.xxx.web.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apz.xxx.back.dao.UserDetailsDAO;
import org.apz.xxx.back.domain.UsuarioBean;
import org.apz.xxx.back.service.RolService;
import org.apz.xxx.back.service.UsuarioService;


@Service
public class WebFacadeImpl implements WebFacade {
	
	/** El Logger para las trazas **/
	private static final Logger LOGGER = Logger.getLogger(WebFacadeImpl.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	
	@Override
	public UsuarioBean getUsuario(String login) throws Exception {
		return usuarioService.getUsuarioByLogin(login);
	}
	
	@Override
	public List<UsuarioBean> getUsuarios() throws Exception {
		return usuarioService.getListadoUsuarios(new UsuarioBean());
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception {
		usuarioService.eliminarUsuario(id);
	} 
	
	
	/*
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	
	@Override
	public Usuario getUsuario(String matricula) throws Exception {
		return userDetailsDAO.getUserDetail(matricula);
	}
	
	@Override
	public List<Usuario> getUsuarios() throws Exception {
		return userDetailsDAO.getUsuarios();
	}
	*/
	

}
