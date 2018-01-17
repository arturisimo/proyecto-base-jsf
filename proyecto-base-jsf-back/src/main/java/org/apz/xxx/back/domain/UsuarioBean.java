package  org.apz.xxx.back.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

 
/**	
 *	Tabla [XXX_SEG_USUARIOS]
 */
 
public class UsuarioBean implements Serializable{
	
	protected static Logger logger = Logger.getLogger(UsuarioBean.class);

	//-------------------------------
	//		ATRIBUTOS
	//-------------------------------

	private static final long serialVersionUID = 1L;
	
	private Long 				id;
	
	private String				nombre;
	private String 				login;
	private String 				password;
	private String				email;
	
	private List<RolBean>		roles;
	private List<PermisoBean>	permisos;
	
	private boolean				baja;
	
	//-------------------------------
	//		CONSTRUCTORES
	//-------------------------------
	
	public UsuarioBean() {
		super();
	}

	//-------------------------------
	//		GETTERS AND SETTERS
	//-------------------------------
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RolBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RolBean> roles) {
		this.roles = roles;
	}
	
	public List<PermisoBean> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoBean> permisos) {
		this.permisos = permisos;
	}

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
	}
	
	
}