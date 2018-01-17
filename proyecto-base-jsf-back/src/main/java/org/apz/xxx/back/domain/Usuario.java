package org.apz.xxx.back.domain;

import java.io.Serializable;


public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String matricula;
    private String password;

    public int getId() {
         return id;
    }

    public void setId(int id) {
         this.id = id;
    }

    public String getMatricula() {
         return matricula;
    }

    public void setMatricula(String matricula) {
         this.matricula = matricula;
    }

    public String getPassword() {
         return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
         return "UserDetailDomain {id=" + id + "} {username=" + matricula
                + "} {password=" + password + "}";
    }
}
