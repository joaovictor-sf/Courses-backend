package com.gestao.backend.service;

import com.gestao.backend.domain.dto.ListaDTO;

import java.util.List;

public interface UserService {

    List<ListaDTO> findAllUserCourses(String matricula);
}
