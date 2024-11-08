package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/compraYa")
public class Controller {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/usuarios/register")
	public ResponseEntity<String> register(@RequestParam String mail, @RequestParam String password,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam Integer edad) {
		String resultado = usuarioService.register(mail, password, nombre, apellido, edad);
		if (resultado.contains("Usuario creado con exito")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}
	
	@PostMapping("/usuarios/login")
	public ResponseEntity<String> login(@RequestParam String mail, @RequestParam String password) {
		String resultado = usuarioService.login(mail, password);
		if (resultado.contains("Login exitoso")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

}
