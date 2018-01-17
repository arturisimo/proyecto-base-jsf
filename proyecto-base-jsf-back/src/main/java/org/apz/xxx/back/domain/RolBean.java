package  org.apz.xxx.back.domain;

import java.io.Serializable;
import java.util.List;
 
/**	
 *	Tabla [XXX_SEG_ROLES]
 */

public class RolBean implements Serializable{

	//-------------------------------
	//		ATRIBUTOS
	//-------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private Long 				id;
	private String 				nombre;
	private String 				descripcion;
	
	private List<PermisoBean>	permisos;
	private boolean				baja;
	
	//-------------------------------
	//		CONSTRUCTORES
	//-------------------------------
	
	public RolBean() {
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

	public List<PermisoBean> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoBean> permisos) {
		this.permisos = permisos;
	}
	
	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}
		
}