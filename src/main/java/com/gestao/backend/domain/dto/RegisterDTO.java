package com.gestao.backend.domain.dto;

import com.gestao.backend.domain.UserRole;

public record RegisterDTO(String matricula, String password, String name, UserRole role) {
}
