package htw.mitwalli.uebung.repository;

import htw.mitwalli.uebung.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByRoleId(Long roleId);

    boolean existsByFirstname(String firstname);

    boolean existsByLastname(String lastname);

    boolean existsByEmail(String email);

    boolean existsByLastnameAndIdNot(String lastname, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Student> findByEmail(String email);
}