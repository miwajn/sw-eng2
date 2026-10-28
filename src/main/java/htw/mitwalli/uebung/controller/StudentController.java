package htw.mitwalli.uebung.controller;

import htw.mitwalli.uebung.dto.RoleResponseDTO;
import htw.mitwalli.uebung.dto.StudentRequestDTO;
import htw.mitwalli.uebung.dto.StudentResponseDTO;
import htw.mitwalli.uebung.entity.Student;
import htw.mitwalli.uebung.entity.Role;
import htw.mitwalli.uebung.repository.StudentRepository;
import htw.mitwalli.uebung.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping ("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    public StudentController(StudentRepository studentRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getUser() {
        List<StudentResponseDTO> result = List.of(
                new StudentResponseDTO("Mira", "Miro", 2, "student1@test.de", new RoleResponseDTO("student")),
                new StudentResponseDTO("Leen", "Linux", 2, "student2@test.de", new RoleResponseDTO("student")),
                new StudentResponseDTO("Kim", "Keir", 2, "student3@test.de", new RoleResponseDTO("student"))
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> registerStudent(@RequestBody StudentRequestDTO studentRequestDTO) {

        Optional<Role> role = roleRepository.findById(studentRequestDTO.roleId());
        if (role.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (studentRepository.existsByEmail(studentRequestDTO.email())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Student create = new Student();
        create.setFirstname(studentRequestDTO.firstname());
        create.setLastname(studentRequestDTO.lastname());
        create.setPassword(studentRequestDTO.password());
        create.setEmail(studentRequestDTO.email());
        create.setCurrentSemester(studentRequestDTO.currentSemester());
        create.setRole(role.get());
        Student saved = studentRepository.save(create);

        StudentResponseDTO response = new StudentResponseDTO(
                saved.getFirstname(),
                saved.getLastname(),
                saved.getCurrentSemester(),
                saved.getEmail(),
                new RoleResponseDTO(saved.getRole().getRole())
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}