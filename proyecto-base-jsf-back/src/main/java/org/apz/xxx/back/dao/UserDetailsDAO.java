package org.apz.xxx.back.dao;

import java.util.List;

import org.apz.xxx.back.domain.Usuario;

public interface UserDetailsDAO {

    List<String> getRoles(final int idUsuario);

    Usuario getUserDetail(final String matricula);

	List<Usuario> getUsuarios();

}

