package htw.mitwalli.uebung.repository;

import htw.mitwalli.uebung.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRole(String role);
    Optional<Role> findByRole(String role);
}