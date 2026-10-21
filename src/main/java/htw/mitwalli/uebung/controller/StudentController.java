package htw.mitwalli.uebung.controller;

import htw.mitwalli.uebung.dto.RoleResponseDTO;
import htw.mitwalli.uebung.dto.StudentRequestDTO;
import htw.mitwalli.uebung.dto.StudentResponseDTO;
import htw.mitwalli.uebung.service.StudentService;
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

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping 
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentRequestDTO request) {
        StudentResponseDTO created = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getUsers() {

        List<StudentResponseDTO> result = List.of(
                //new StudentResponseDTO(1L, "nameone", "one@mail.de", new RoleResponseDTO(1L, "admin")),
                //new StudentResponseDTO(2L, "nametwo", "two@mail.de", new RoleResponseDTO(2L, "user"))
        );
        return ResponseEntity.ok(result);
    }
}
