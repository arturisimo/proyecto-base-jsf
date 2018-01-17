package org.apz.xxx.back.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apz.xxx.back.dao.RolDao;
import org.apz.xxx.back.dao.UsuariosDao;
import org.apz.xxx.back.domain.PermisoBean;
import org.apz.xxx.back.domain.RolBean;
import org.apz.xxx.back.domain.UsuarioBean;
import org.apz.xxx.back.service.PermisoService;
import org.apz.xxx.back.service.UsuarioService;

@Service
public class UsuariosServiceImpl implements UsuarioService {	  
	
	protected static Logger logger = Logger.getLogger(UsuariosServiceImpl.class);
	
	@Autowired
	UsuariosDao usuarioDao;
	
	@Autowired
	RolDao rolDao;
	
	@Autowired
	PermisoService serv_perm;
	

	@Override
	public List<UsuarioBean> getListadoUsuarios(UsuarioBean bUsuario) {
		return usuarioDao.getAllUsuarios(bUsuario);
	}
	
	@Override
	public UsuarioBean getUsuarioByLogin(String login) {
		return usuarioDao.getUsuarioByLogin(login);
	}

	@Override
	public UsuarioBean getUsuarioById(Long id) {
		return (UsuarioBean) usuarioDao.getUsuarioById(id);
	}


	@Override
	public HashMap<String, Object> getDetalleUsuario(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		UsuarioBean bUsu;
		
		bUsu = usuarioDao.getUsuarioById(id);
		
		List<PermisoBean> arbolPermisos = serv_perm.getPermisosParaDetalle(bUsu.getPermisos());
		  
	    Map<Long,Long> nodosRaices = new HashMap<Long,Long>();
	    Iterator<PermisoBean> it = arbolPermisos.iterator();
	    while(it.hasNext()){
		    PermisoBean b = it.next();
		    nodosRaices.put(b.getId(), b.getId());
	    }  

	    parametros.put("bUsu",bUsu);
	   
	    parametros.put("nodosRaices", nodosRaices);
	    parametros.put("arbolPermisos", arbolPermisos);
		
		return parametros;
	}


	@Override
	public HashMap<String, Object> getEdicionUsuario(Long id) {
		
		UsuarioBean bUsu = usuarioDao.getUsuarioById(id);
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		RolBean bRol = new RolBean();
		List<RolBean> listaRoles = rolDao.getAllRoles(bRol);
		
		parametros.put("bUsu", bUsu);
		parametros.put("listaRoles", listaRoles);

		return parametros;
	}


	@Override
	public HashMap<String, Object> getAltaUsuario() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		RolBean bRol = new RolBean();
		parametros.put("listaRoles", rolDao.getAllRoles(bRol));
		

		return parametros;
	}


	@Override
	public Map<String, Object> guardarUsuario(Map<String, Object> parametros) {
		return null;
		/*
		
		String nombre = (String) parametros.get("nombre");
		String login = (String) parametros.get("login");
		String email = (String) parametros.get("email");
		String password = (String) parametros.get("password");

		UsuarioBean bUsu = new UsuarioBean();
		
		bUsu.setLogin(login);
		bUsu.setNombre(nombre);
		bUsu.setEmail(email);
		
		bUsu.setPassword(Utilidades.convertToMD5(password));
		
		long id_result = -1;
		
		int numUsuariosLogin = usuDao.getNumUsuariosWithLogin(bUsu);
		
		if (numUsuariosLogin > 0)
		{
			parametros.put("error",1);
			parametros.put("mensajeError","Ya existe un usuario con el mismo login.");
		} 
		
		else 
		{
			id_result = usuDao.insertUsuario(bUsu);
			
			//Se crea el historico de la creacion/modificacion
			Long idUser = (Long) parametros.get("id_usuario");
			String nombreUser = (String) parametros.get("nombre_usuario");
			  
			parametros.put("bUsu", bUsu);
			
			if (id_result != -1)
			{
				HashMap<String, Object> mapa = new HashMap<String, Object>();
				
				for (int i = 0; i < bUsu.getList_id_roles().length; i++) {
	    			mapa.put("idusuario", bUsu.getId());
	            	mapa.put("idrol", bUsu.getList_id_roles()[i]);
	    			usuDao.insertUsuarioRol(mapa);
				}
				
				id_result = bUsu.getId();
				
			} 
			else 
			{
				parametros.put("error",2);
				parametros.put("mensajeError","El usuario no se ha podido modificar.");
			}
		} 
		
		if (id_result == -1) {
			
			//Crear lista de grupos seleccionados para la busqueda
			GrupoBean bGrupo = new GrupoBean();
			List<GrupoBean> listaGrupos = gruDao.getAllGrupos(bGrupo);
			Boolean encontradoGru;
			for (int i = 0; i < listaGrupos.size(); i++) {
				encontradoGru = false;
				int cont = 0;
				if (bUsu.getList_id_grupos() != null) {
					while (cont<bUsu.getList_id_grupos().length && !encontradoGru){
						if(listaGrupos.get(i).getId().equals(Long.parseLong(bUsu.getList_id_grupos()[cont]))){
							listaGrupos.get(i).setSeleccionado(true);
						}
						cont++;
					}
				}
			}
			//Crear lista de roles seleccionados para la busqueda
			RolBean bRol = new RolBean();
			List<RolBean> listaRoles = rolDao.getAllRoles(bRol);
			Boolean encontradoRol;
			for (int i = 0; i < listaRoles.size(); i++) {
				encontradoRol = false;
				int cont = 0;
				if (bUsu.getList_id_roles() != null) {
					while (cont<bUsu.getList_id_roles().length && !encontradoRol){
						if(listaRoles.get(i).getId().equals(Long.parseLong(bUsu.getList_id_roles()[cont]))){
							listaRoles.get(i).setSeleccionado(true);
						}
						cont++;
					}
				}
			}
			
			parametros.put("bUsu",bUsu);
			parametros.put("listaGrupos", listaGrupos);
			parametros.put("listaRoles", listaRoles);
		}
		
		parametros.put("id_result", id_result);
		
		return parametros;
		*/
	}


	@Override
	public void eliminarUsuario(Long id) {
		//Eliminacion de las relaciones con los roles y grupos
		usuarioDao.deleteUsuarioRoles(id);
		
		
		//Eliminacion del usuario
		usuarioDao.deleteUsuario(id);

		
	}
	
	@Override
	public Map<String, Object> modificarUsuario(Map<String, Object> parametros) {
		return null;
	}
	
	/*
	@Override
	public Map<String, Object> modificarUsuario(Map<String, Object> parametros) {
		String idUsuario = (String) parametros.get("id");
		String nombre = (String) parametros.get("nombre");
		String login = (String) parametros.get("login");
		String email = (String) parametros.get("email");		 
		
		UsuarioBean bUsu = usuDao.getUsuarioById(Long.parseLong(idUsuario));
		String loginPrevio = bUsu.getLogin();
		
		bUsu.setNombre(nombre);
		bUsu.setLogin(login);
		bUsu.setEmail(email);
		
		int numUsuariosLogin = usuDao.getNumUsuariosWithLogin(bUsu);
		long id_result = -1;
		
		if ((!loginPrevio.equals(login) && numUsuariosLogin > 0) || (loginPrevio.equals(login) && numUsuariosLogin > 1)){
			
			parametros.put("error",1);
			parametros.put("mensajeError","Ya existe un usuario con el mismo login.");
			
		} else {
				
			String change = (String) parametros.get("change");
			
			Boolean cambioCorrecto = true;
			
			if (change!=null && change.equals("S")){
				String password = (String) parametros.get("password");
				String newpassword = (String) parametros.get("newpassword");
				String repassword = (String) parametros.get("repassword");
				
				if (password!=null && Utilidades.convertToMD5(password).equals(bUsu.getPassword())){
					if (newpassword!=null && !newpassword.equals("") && newpassword.equals(repassword)){
						bUsu.setPassword(Utilidades.convertToMD5(newpassword));
					}else{
						cambioCorrecto = false;
						parametros.put("mensajeError","Las nuevas contraseñas no coinciden.");
						parametros.put("error",3);
					}
				}else{
					cambioCorrecto = false;
					parametros.put("mensajeError","La contraseña no es correcta.");
					parametros.put("error",4);
				}
			}
				
			if (cambioCorrecto){
			
				
				id_result = usuDao.updateUsuario(bUsu);
				
				if (id_result != -1){
				
//					Eliminacion de las relaciones antiguas con los grupos y roles
		    		usuDao.deleteUsuarioRoles(bUsu.getId());
					
		    		HashMap<String, Object> mapa = new HashMap<String, Object>();
		
					for (int i = 0; i < bUsu.getList_id_grupos().length; i++) {
		    			mapa.put("idusuario", bUsu.getId());
		            	mapa.put("idgrupo", bUsu.getList_id_grupos()[i]);
		    			usuDao.insertUsuarioGrupo(mapa);
					}
					
					mapa.clear();
					
					for (int i = 0; i < bUsu.getList_id_roles().length; i++) {
		    			mapa.put("idusuario", bUsu.getId());
		            	mapa.put("idrol", bUsu.getList_id_roles()[i]);
		    			usuDao.insertUsuarioRol(mapa);
					}
					
					id_result = bUsu.getId();
					
				} else {
					parametros.put("error",2);
					parametros.put("mensajeError","El usuario no se ha podido modificar.");
				}

				//Se crea el historico de la creacion/modificacion
				Long idUser = (Long) parametros.get("id_usuario");
				String nombreUser = (String) parametros.get("nombre_usuario");
				  
				HistoricoUsuariosBean bHusuario = new HistoricoUsuariosBean();
				bHusuario.setId_usuario(bUsu.getId());
				bHusuario.setNombre_usuario(bUsu.getLogin());
				bHusuario.setId_usuario_accion(idUser);
			    bHusuario.setNombre_usuario_accion(nombreUser);
			    
				bHusuario.setId_accion(new Long(ConstantesGenerales.GLOBAL_ACCION_MODIFICAR));
							  
				histUsuDao.insertHistoricoUsuarios(bHusuario);
						
			} 
		}
		
		if (id_result == -1) {
			
			//Crear lista de grupos seleccionados para la busqueda
			GrupoBean bGrupo = new GrupoBean();
			List<GrupoBean> listaGrupos = gruDao.getAllGrupos(bGrupo);
			Boolean encontradoGru;
			for (int i = 0; i < listaGrupos.size(); i++) {
				encontradoGru = false;
				int cont = 0;
				if (bUsu.getList_id_grupos() != null) {
					while (cont<bUsu.getList_id_grupos().length && !encontradoGru){
						if(listaGrupos.get(i).getId().equals(Long.parseLong(bUsu.getList_id_grupos()[cont]))){
							listaGrupos.get(i).setSeleccionado(true);
						}
						cont++;
					}
				}
			}
			//Crear lista de roles seleccionados para la busqueda
			RolBean bRol = new RolBean();
			List<RolBean> listaRoles = rolDao.getAllRoles(bRol);
			Boolean encontradoRol;
			for (int i = 0; i < listaRoles.size(); i++) {
				encontradoRol = false;
				int cont = 0;
				if (bUsu.getList_id_roles() != null) {
					while (cont<bUsu.getList_id_roles().length && !encontradoRol){
						if(listaRoles.get(i).getId().equals(Long.parseLong(bUsu.getList_id_roles()[cont]))){
							listaRoles.get(i).setSeleccionado(true);
						}
						cont++;
					}
				}
			}
			parametros.put("bUsu",bUsu);
			parametros.put("listaGrupos", listaGrupos);
			parametros.put("listaRoles", listaRoles);
		}
		
		parametros.put("id_result",id_result);
		
		return parametros;
	}

	*/
	
	
}
