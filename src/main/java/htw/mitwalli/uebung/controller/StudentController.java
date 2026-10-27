package htw.mitwalli.uebung.controller;

import htw.mitwalli.uebung.dto.RoleResponseDTO;
import htw.mitwalli.uebung.dto.StudentRequestDTO;
import htw.mitwalli.uebung.dto.StudentResponseDTO;
import htw.mitwalli.uebung.entity.Student;
import htw.mitwalli.uebung.service.StudentService;
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

@RestController 
@RequestMapping ("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    public StudentController(StudentRepository studentRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }



    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getUsers() {

        List<StudentResponseDTO> result = List.of(
                new StudentResponseDTO(1L, "nameone", "one@mail.de", new RoleResponseDTO(1l, "admin")),
                new StudentResponseDTO(2L, "nametwo", "two@mail.de", new RoleResponseDTO(2L, "user")),
        );
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<StudentResponseDTO> registerStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        
        Optional<Role> studentRole = roleRepository.findByRole("student");
        if (userRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(studentRepository.existsByEmailOrAccoutn(studentRequestDTO.email(), studentRequestDTO.account())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Student create = new Student();
        create.setEmail(studentRequestDTO.email());
        create.setPassword(studentRequestDTO.password());
        create.setAccount(studentRequestDTO.account());
        create.setRole(studentRole.get());
        Student saved = this.studentRepository.save(create);
        return new ResponseEntity(saved, HttpStatus.OK);
    }
}
