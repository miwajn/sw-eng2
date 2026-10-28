package htw.mitwalli.uebung.dto;

import htw.mitwalli.uebung.entity.Role;

public record RoleResponseDTO(String role) {

    public static RoleResponseDTO from(Role entity) {
        return new RoleResponseDTO(entity.getRole());
    }
}
