package com.gestao.backend.repository;

import com.gestao.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByMatricula(String matricula);

    //Listar todos os cursos do usuario
    //Optional<User> findByMatriculaAndCursos(String matricula, String cursos);

}
