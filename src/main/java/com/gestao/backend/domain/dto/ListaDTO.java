package com.gestao.backend.domain.dto;

import com.gestao.backend.domain.UserRole;

public record ListaDTO(Long id, String name, String description, String imageUrl, String userMatricula, Boolean active) {
}
