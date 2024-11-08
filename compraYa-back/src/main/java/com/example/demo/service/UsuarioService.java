package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmailSenderService emailSenderService;

	public List<Usuario> usuarios() {
		return usuarioRepository.findAll();
	}

//	public String login(String mail,String password) {
//		
//	}

	public String register(String mail, String password, String nombre, String apellido, Integer edad) {
		Usuario usuarioOptional = buscarUsuarioMail(mail);
		if (usuarioOptional != null) {
			return "El mail ya se encuentra registrado";
		}
		if (password.length() == 0) {
			return "El password no puede estar vacio";
		}
		if (nombre.length() == 0) {
			return "El nombre no puede estar vacio";
		}
		if (apellido.length() == 0) {
			return "El apellido no puede estar vacio";
		}
		if (edad < 18) {
			return "La edad no puede ser menor a 18 anos";
		}
		String uuid = UUID.randomUUID().toString();
		String alias = nombre + apellido + edad + ".compraYa";
		String passwordHasheado = hashearPassword(password);

//		Usuario a gauardar en BD
		Usuario usuario = new Usuario(uuid, mail, passwordHasheado, nombre, apellido, edad, alias, "habilitado");
		usuarioRepository.save(usuario);

//		Para enviar el mail
		String destinatario = usuario.getMail();
		String mensaje = "Estimado " + nombre + " " + apellido + " su registro en la App CompraYa ha sido exitoso\n";
		String motivo = "Registro en CompraYa";
		emailSenderService.sendEmail(destinatario, motivo, mensaje);
		return "Usuario creado con exito";
	}

	public String login(String mail, String password) {
		if (mail.length() == 0) {
			return "El mail no puede estar vacio";
		}
		if (password.length() == 0) {
			return "El password no puede estar vacio";
		}
		Usuario usuario = buscarUsuarioMail(mail);
		if (usuario == null) {
			return "Usuario no registrado";
		}
		String hasheada = usuario.getPassword();
		if (validarPassword(password, hasheada)) {
			return "Login exitoso";
		}
		return "La contraseña ingresada es incorrecta";

	}

	public String hashearPassword(String password) {
		String hasheada = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		return hasheada;
	}

	public Boolean validarPassword(String ingresada, String hasheada) {
		BCrypt.Result result = BCrypt.verifyer().verify(ingresada.toCharArray(), hasheada);
		return result.verified;
	}

	public Usuario buscarUsuarioUuid(String uuid) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(uuid);
		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			return usuario;
		}
		return null;

	}

	public Usuario buscarUsuarioMail(String mail) {
		List<Usuario> usuarios = usuarioRepository.findByMail(mail);
		if (usuarios.isEmpty()) {
			return null;
		}
		Usuario usuario = usuarios.get(0);
		return usuario;
	}

}
