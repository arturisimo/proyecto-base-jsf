package  org.apz.xxx.back.domain;

import java.io.Serializable;
import java.util.List;
 
/**	
 *	Tabla [XXX_SEG_PERMISOS]
 */

public class PermisoBean implements Serializable{

	//-------------------------------
	//		ATRIBUTOS
	//-------------------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long 				id;
	private String 				nombre;
	private String 				descripcion;
	private Long				permisoPadre;
	private List<PermisoBean>	permisosHijos;
	
	//-------------------------------
	//		CONSTRUCTORES
	//-------------------------------
	public PermisoBean() {
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getPermisoPadre() {
		return permisoPadre;
	}
	public void setPermisoPadre(Long permisoPadre) {
		this.permisoPadre = permisoPadre;
	}
	
	public List<PermisoBean> getPermisosHijos() {
		return permisosHijos;
	}
	
	public void setPermisosHijos(List<PermisoBean> permisosHijos) {
		this.permisosHijos = permisosHijos;
	}

}