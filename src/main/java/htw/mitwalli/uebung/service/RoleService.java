package htw.mitwalli.uebung.service;

import htw.mitwalli.uebung.dto.RoleRequestDTO;
import htw.mitwalli.uebung.dto.RoleResponseDTO;
import htw.mitwalli.uebung.entity.Role;
import htw.mitwalli.uebung.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDTO create(RoleRequestDTO request) {
        if (roleRepository.existsByRole(request.role())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        Role saved = roleRepository.save(request.toRole());
        return RoleResponseDTO.from(saved); // an dein DTO anpassen
    }

    public List<RoleResponseDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleResponseDTO::from) // an dein DTO anpassen
                .toList();
    }
}
