package com.gestao.backend.domain.dto;

import com.gestao.backend.domain.UserRole;

public record LoginResponseDTO(String token, UserRole role) {
}
