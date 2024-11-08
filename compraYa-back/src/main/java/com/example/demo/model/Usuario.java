package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	private String uuid;

	private String mail;
	private String password;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String alias;
	private String habilitado;

	public Usuario() {

	}

	public Usuario(String uuid, String mail, String password, String nombre, String apellido, Integer edad,
			String alias, String habilitado) {
		super();
		this.uuid = uuid;
		this.mail = mail;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.alias = alias;
		this.habilitado = habilitado;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return "Usuario [uuid=" + uuid + ", mail=" + mail + ", password=" + password + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", edad=" + edad + ", alias=" + alias + ", habilitado=" + habilitado + "]";
	}

}
