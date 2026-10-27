package htw.mitwalli.uebung.dto;

import htw.mitwalli.uebung.entity.Role;

public record RoleRequestDTO(String role) {
    public Role toRole()
    {
        Role role = new Role();
        role.setRole(this.role);
        return Role;
    }

}
