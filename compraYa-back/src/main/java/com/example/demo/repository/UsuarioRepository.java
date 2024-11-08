package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	List<Usuario> findByMail(String mail);
	
	List<Usuario> findByAlias(String alias);
	
}
